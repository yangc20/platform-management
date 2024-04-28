package com.mysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.mysystem.common.Constant;
import com.mysystem.dao.mapper.PersonInfoMapper;
import com.mysystem.dao.po.PersonInfoPO;
import com.mysystem.inverter.person.PersonInfoInverter;
import com.mysystem.model.PageTemplate;
import com.mysystem.model.ro.PersonQuery;
import com.mysystem.model.vo.BookInfoVO;
import com.mysystem.model.vo.PersonBookRelationVO;
import com.mysystem.model.vo.PersonDetailVO;
import com.mysystem.model.vo.PersonInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonInfoService extends ServiceImpl<PersonInfoMapper, PersonInfoPO> {

    private Integer defaultNum = 8;

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private PersonInfoInverter personInfoInverter;

    @Autowired
    private PersonBookRelationService personBookRelationService;

    // 随机展示8条数据
    public List<PersonInfoVO> homePage() {
        LambdaQueryWrapper<PersonInfoPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PersonInfoPO::getDeleted, Constant.NO_DELETED);

        List<PersonInfoPO> personInfoPOS = baseMapper.selectList(queryWrapper);
        Random random = new Random();
        List<PersonInfoPO> selectPOs = new ArrayList<>();

        Set<Integer> existId = new HashSet<>();
        Integer showNum = Math.min(defaultNum, personInfoPOS.size());
        while (selectPOs.size() < showNum) {
            int i = random.nextInt(personInfoPOS.size());
            if (existId.contains(i)) {
                continue;
            } else {
                existId.add(i);
            }
            selectPOs.add(personInfoPOS.get(i));
        }
        return personInfoInverter.posToVOS(selectPOs);
    }

    public PageTemplate<PersonInfoVO> queryPage(PersonQuery query) {
        LambdaQueryWrapper<PersonInfoPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PersonInfoPO::getDeleted, Constant.NO_DELETED);
        if (Objects.nonNull(query.getKeyWord())) {
            queryWrapper.and(wrapper -> wrapper.like(PersonInfoPO::getId, query.getKeyWord())
                    .or().like(PersonInfoPO::getName, query.getKeyWord())
                    .or().like(PersonInfoPO::getOtherName, query.getKeyWord()));
        }
        Page<PersonInfoPO> pageHelper = new Page<>(query.getPageNum(), query.getPageSize());
        Page<PersonInfoPO> page = baseMapper.selectPage(pageHelper, queryWrapper);
        List<PersonInfoPO> personInfoPOS = page.getRecords();
        List<PersonInfoVO> collect = personInfoPOS.stream().map(po ->
                PersonInfoVO.builder().id(po.getId()).name(po.getName()).otherName(po.getOtherName()).build()
        ).collect(Collectors.toList());
        PageTemplate<PersonInfoVO> result = new PageTemplate<>(collect, page.getSize(), page.getCurrent(), page.getTotal());
        return result;
    }

    public PersonDetailVO detail(Integer id) {
        LambdaQueryWrapper<PersonInfoPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PersonInfoPO::getDeleted, Constant.NO_DELETED)
                .eq(PersonInfoPO::getId, id);
        PersonInfoPO po = baseMapper.selectOne(queryWrapper);
        if (Objects.isNull(po)) {
            return new PersonDetailVO();
        }
        PersonDetailVO personDetailVO = personInfoInverter.poToDetailVO(po);

        // 查询书籍信息
        List<PersonBookRelationVO> relationVOS = personBookRelationService.queryRelationsByPersonIds(Lists.newArrayList(po.getId()));
        List<Integer> bookIntIds = relationVOS.stream().map(PersonBookRelationVO::getBookIntId).collect(Collectors.toList());
        personDetailVO.setBookInfoVOList(bookInfoService.queryBookInfosById(bookIntIds));

        return personDetailVO;
    }
}
