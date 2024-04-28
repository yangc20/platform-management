package com.mysystem.inverter.book;

import com.mysystem.dao.po.BookInfoPO;
import com.mysystem.model.vo.BookInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookInfoInverter {

    @Mappings({})
    List<BookInfoVO> posToVOs(List<BookInfoPO> pos);

    @Mappings({})
    BookInfoVO poToVO(BookInfoPO po);
}
