package com.mysystem.inverter.person;


import com.mysystem.dao.po.PersonInfoPO;
import com.mysystem.model.vo.PersonDetailVO;
import com.mysystem.model.vo.PersonInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonInfoInverter {

    @Mappings({})
    PersonDetailVO poToDetailVO(PersonInfoPO po);

    @Mappings({})
    PersonInfoVO poToVO(PersonInfoPO po);

    @Mappings({})
    List<PersonInfoVO> posToVOS(List<PersonInfoPO> pos);
}
