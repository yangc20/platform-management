package com.mysystem.inverter.note;

import com.mysystem.dao.po.NotePO;
import com.mysystem.model.ro.NoteRO;
import com.mysystem.model.vo.NoteVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteInverter {

    @Mappings({})
    NoteVO poToVO(NotePO po);

    @Mappings({})
    List<NoteVO> posToVOs(List<NotePO> pos);

    @Mappings({})
    NotePO roToPO(NoteRO ro);
}
