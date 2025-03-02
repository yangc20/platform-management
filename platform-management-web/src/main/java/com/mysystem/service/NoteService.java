package com.mysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysystem.common.Constant;
import com.mysystem.dao.mapper.NoteMapper;
import com.mysystem.dao.po.NotePO;
import com.mysystem.inverter.note.NoteInverter;
import com.mysystem.model.PageTemplate;
import com.mysystem.model.ro.NoteQueryRO;
import com.mysystem.model.ro.NoteRO;
import com.mysystem.model.vo.NoteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class NoteService extends ServiceImpl<NoteMapper, NotePO> {

    @Autowired
    private NoteInverter noteInverter;

    public void saveNote(NoteRO ro) {
        NotePO notePO = noteInverter.roToPO(ro);
        if (Objects.nonNull(ro.getId())) {
            notePO.setUpdateTime(new Date());
            this.updateById(notePO);
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String today = dateFormat.format(new Date());
            notePO.setDayno(Integer.valueOf(today));
            this.save(notePO);
        }

    }

    public PageTemplate<NoteVO> notePage(NoteQueryRO queryRO) {
        Page<NotePO> pageHelper = new Page<>(queryRO.getPageNum(), queryRO.getPageSize());

        LambdaQueryWrapper<NotePO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(NotePO::getDeleted, Constant.NO_DELETED);
        if (StringUtils.isNotBlank(queryRO.getKeyword())) {
            queryWrapper.like(NotePO::getTitle, queryRO.getKeyword());
        }

        if (Objects.nonNull(queryRO.getStartDate()) && Objects.nonNull(queryRO.getEndDate())) {
            queryWrapper.between(NotePO::getDayno, queryRO.getStartDate(), queryRO.getEndDate());
        }

        queryWrapper.orderByDesc(NotePO::getCreateTime);
        Page<NotePO> pageResult = baseMapper.selectPage(pageHelper, queryWrapper);

        List<NotePO> records = pageResult.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            return new PageTemplate<>();
        }

        // 只返回对应的标题
        List<NoteVO> collect = records.stream().map(po -> {
            NoteVO noteVO = new NoteVO();
            noteVO.setId(po.getId());
            noteVO.setTitle(po.getTitle());
            noteVO.setDayno(po.getDayno());
            noteVO.setCreateTime(po.getCreateTime());
            noteVO.setCreateUser(po.getCreateUser());
            return noteVO;
        }).collect(Collectors.toList());

        return PageTemplate.<NoteVO>builder()
                .pageNum(pageResult.getCurrent())
                .pageSize(pageResult.getSize())
                .data(collect)
                .total(pageResult.getTotal())
                .build();
    }

    public NoteVO noteDetail(Integer id) {
        NotePO byId = this.getById(id);
        if (Objects.isNull(byId)) {
            return new NoteVO();
        }

        NoteVO noteVO = noteInverter.poToVO(byId);
        return noteVO;
    }

    public String noteDelete(Integer id){
        NotePO po = this.getById(id);

        if (Objects.nonNull(po)){
            po.setDeleted(Constant.DELETED);
            po.setUpdateTime(new Date());
            this.updateById(po);
        }

        return "success";
    }
}
