package com.mysystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysystem.dao.po.UserInfoPO;
import com.mysystem.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoPO> {

}
