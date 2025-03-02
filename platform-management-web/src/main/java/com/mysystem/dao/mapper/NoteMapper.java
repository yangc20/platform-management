package com.mysystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysystem.dao.po.NotePO;
import com.mysystem.model.ro.NoteQueryRO;
import com.mysystem.model.vo.NoteVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NoteMapper extends BaseMapper<NotePO> {

}
