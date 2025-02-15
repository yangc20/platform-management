package com.mysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysystem.dao.mapper.VersionRecordMapper;
import com.mysystem.dao.po.VersionRecordPO;
import com.mysystem.model.ro.VersionRecordRO;
import com.mysystem.model.vo.VersionRecordVO;
import com.mysystem.utils.RandomUidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VersionRecordService extends ServiceImpl<VersionRecordMapper, VersionRecordPO> {

    /**
     * 查询近interval个版本的记录
     *
     * @param interval
     * @return
     */
    public Map<String, List<VersionRecordVO>> getRecord(Integer interval) {
        List<VersionRecordVO> result = new ArrayList<>();

        LambdaQueryWrapper<VersionRecordPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(VersionRecordPO::getDayno);
        List<VersionRecordPO> list = this.list(queryWrapper);

        if (CollectionUtils.isEmpty(list)) {
            return new HashMap<>();
        }

        List<Integer> daynoList = list.stream().map(VersionRecordPO::getDayno).distinct().collect(Collectors.toList());
        List<Integer> needDaynoList = daynoList.subList(0, Math.min(daynoList.size(), interval));

        List<VersionRecordPO> collect = list.stream().filter(po -> needDaynoList.contains(po.getDayno())).collect(Collectors.toList());
        collect.forEach(po -> {
            VersionRecordVO versionRecordVO = new VersionRecordVO();
            BeanUtils.copyProperties(po, versionRecordVO);
            result.add(versionRecordVO);
        });

        Map<String, List<VersionRecordVO>> resultMap = result.stream().collect(Collectors.groupingBy(VersionRecordVO::getVersionId));
        return resultMap;
    }

    public String addRecord(VersionRecordRO ro) {
        if (Objects.isNull(ro) || CollectionUtils.isEmpty(ro.getDescriptions())) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String today = dateFormat.format(new Date());

        String versionId = RandomUidUtils.getNewString(12);

        List<VersionRecordPO> list = new ArrayList<>();
        ro.getDescriptions().forEach(data -> {
            VersionRecordPO versionRecordPO = new VersionRecordPO();
            versionRecordPO.setVersionId(versionId);
            versionRecordPO.setDayno(Integer.valueOf(today));
            versionRecordPO.setDescription(data);

            list.add(versionRecordPO);
        });

        this.saveBatch(list);
        return "success";
    }

    /**
     * 支持单条内容的更新
     *
     * @param ro
     * @return
     */
    public String updateRecord(VersionRecordRO ro) {
        VersionRecordPO po = this.getById(ro.getId());

        if (Objects.isNull(po)) {
            throw new RuntimeException("未查询到记录,id:" + ro.getId());
        }

        po.setDescription(ro.getDescription());
        po.setUpdateTime(new Date());
        this.updateById(po);
        return "success";
    }
}
