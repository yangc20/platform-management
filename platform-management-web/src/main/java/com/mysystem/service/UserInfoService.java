package com.mysystem.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysystem.dao.mapper.UserInfoMapper;
import com.mysystem.dao.po.UserInfoPO;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfoPO> {

    public String test(){
        UserInfoPO userInfoPO = baseMapper.selectById(1);
        return userInfoPO.getUserName();
    }
}
