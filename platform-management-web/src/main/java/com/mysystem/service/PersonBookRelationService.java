package com.mysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysystem.common.Constant;
import com.mysystem.dao.mapper.BookInfoMapper;
import com.mysystem.dao.mapper.PersonBookRelationMapper;
import com.mysystem.dao.po.BookInfoPO;
import com.mysystem.dao.po.PersonBookRelationPO;
import com.mysystem.model.PageTemplate;
import com.mysystem.model.ro.BookQuery;
import com.mysystem.model.vo.BookDetailVO;
import com.mysystem.model.vo.BookInfoVO;
import com.mysystem.model.vo.PersonBookRelationVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PersonBookRelationService extends ServiceImpl<PersonBookRelationMapper, PersonBookRelationPO> {


    public List<PersonBookRelationVO> queryRelationsByPersonIds(List<Integer> personIds) {
        if (CollectionUtils.isEmpty(personIds)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<PersonBookRelationPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PersonBookRelationPO::getPersonId, personIds);
        List<PersonBookRelationPO> relationPOS = baseMapper.selectList(queryWrapper);
        return relationPOS.stream().map(po ->
                PersonBookRelationVO.builder()
                        .bookId(po.getBookId()).bookIntId(po.getBookIntId()).personId(po.getPersonId())
                        .updateTime(po.getUpdateTime()).createTime(po.getCreateTime())
                        .build()
        ).collect(Collectors.toList());
    }
}
