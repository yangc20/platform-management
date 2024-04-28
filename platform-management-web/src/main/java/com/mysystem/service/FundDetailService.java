package com.mysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysystem.common.Constant;
import com.mysystem.common.FundTypeEnum;
import com.mysystem.dao.mapper.FundDetailMapper;
import com.mysystem.dao.po.fund.FundDetailPO;
import com.mysystem.inverter.fund.FundDetailInverter;
import com.mysystem.model.ro.FundDetailRO;
import com.mysystem.model.vo.FundDetailVO;
import com.mysystem.model.vo.FundInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FundDetailService extends ServiceImpl<FundDetailMapper, FundDetailPO> {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private FundDetailInverter fundDetailInverter;

    public boolean saveFundDetail(FundDetailRO ro) {
        FundDetailPO po = null;
        //写入或更新数据库
        if (Objects.isNull(ro.getId())) {
            po = fundDetailInverter.roToPo(ro);
//            po.setUuid(UUID.randomUUID().toString());
        } else {
            po = this.getById(ro.getId());
            po.setMoney(ro.getMoney());
            po.setDescription(ro.getDescription());
        }
        this.save(po);
        //写入redis
        updateRedisByType(ro.getType());
        return true;
    }

    public boolean updateTarget(FundDetailRO ro) {
        // 查询数据库中是否存在已经设定的目标值
        LambdaQueryWrapper<FundDetailPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FundDetailPO::getType, ro.getType());
        FundDetailPO po = baseMapper.selectOne(queryWrapper);
        //写入或更新数据库
        if (Objects.isNull(po)) {
            po = fundDetailInverter.roToPo(ro);
//            po.setUuid(UUID.randomUUID().toString());
        } else {
            po.setMoney(ro.getMoney());
            po.setDescription(ro.getDescription());
        }
        this.saveOrUpdate(po);
        //写入redis
        updateRedisByType(ro.getType());
        return true;
    }

    public List<FundDetailVO> queryDetail(Integer type, Integer pageNum, Integer pageSize) {
        Page<FundDetailPO> pageHelper = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<FundDetailPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FundDetailPO::getType, type);
        queryWrapper.eq(FundDetailPO::getDeleted, Constant.NO_DELETED);
        Page<FundDetailPO> page = baseMapper.selectPage(pageHelper, queryWrapper);

        return fundDetailInverter.poListToVoList(page.getRecords());
    }

    public FundInfoVO homeIndex() {
        // 首先查询redis
        String redisKey = SystemService.getEnv() + "fundMap";
        Map<String, String> fundTypeMap = redisTemplate.opsForHash().entries(redisKey);

        if (CollectionUtils.isEmpty(fundTypeMap)) {
            LambdaQueryWrapper<FundDetailPO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(FundDetailPO::getDeleted, Constant.NO_DELETED);
            List<FundDetailPO> fundDetailPOS = baseMapper.selectList(queryWrapper);
            Map<Integer, List<FundDetailPO>> typeMap = fundDetailPOS.stream().collect(Collectors.groupingBy(FundDetailPO::getType));
            typeMap.entrySet().forEach(entry -> {
                List<FundDetailPO> pos = entry.getValue();
                if (CollectionUtils.isEmpty(pos)) {
                    return;
                }
                fundTypeMap.put(entry.getKey().toString(), pos.stream().map(FundDetailPO::getMoney).reduce(0, (x1, x2) -> x1 + x2).toString());
            });
            redisTemplate.opsForHash().putAll(redisKey, fundTypeMap);
        }


        return FundInfoVO.builder()
                .totalInput(fundTypeMap.get(FundTypeEnum.TOTAL_INPUT.getType().toString()))
                .totalOutput(fundTypeMap.get(FundTypeEnum.TOTAL_OUTPUT.getType().toString()))
                .targetMoney(fundTypeMap.get(FundTypeEnum.TARGET_MONEY.getType().toString()))
                .build();
    }

    private void updateRedisByType(Integer type) {
        // 查询当前值
        LambdaQueryWrapper<FundDetailPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FundDetailPO::getType, type);
        queryWrapper.eq(FundDetailPO::getDeleted, Constant.NO_DELETED);
        List<FundDetailPO> fundDetailPOS = baseMapper.selectList(queryWrapper);
        Integer totalMoney = fundDetailPOS.stream().map(FundDetailPO::getMoney).reduce(0, (x1, x2) -> x1 + x2);

        // 写入redis
        String redisKey = SystemService.getEnv() + "fundMap";
        Map<String, String> fundMap = redisTemplate.opsForHash().entries(redisKey);
        if (CollectionUtils.isEmpty(fundMap)) {
            fundMap = new HashMap<>();
        }
        fundMap.put(String.valueOf(type), String.valueOf(totalMoney));
        redisTemplate.opsForHash().putAll(redisKey, fundMap);
    }

    public String delete(Integer id) {
        FundDetailPO po = baseMapper.selectById(id);
        if (Objects.isNull(po)) {
            throw new RuntimeException("未查询到记录,id=" + id);
        }
        po.setDeleted(Constant.DELETED);
        baseMapper.updateById(po);
        // 更新redis
        updateRedisByType(po.getType());
        return "success";
    }
}
