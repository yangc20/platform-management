package com.mysystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mysystem.dao.po.BookInfoPO;
import com.mysystem.dao.po.PersonBookRelationPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonBookRelationMapper extends BaseMapper<PersonBookRelationPO> {

}
