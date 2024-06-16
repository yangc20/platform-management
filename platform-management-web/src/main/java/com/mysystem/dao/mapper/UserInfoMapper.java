package com.mysystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysystem.dao.po.UserInfoPO;
import com.mysystem.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoPO> {


    @Select("select * from t_user_info where user_name = #{name}")
    List<UserInfoPO> selectByName(String name);
}
