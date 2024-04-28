package com.mysystem.inverter.fund;


import com.mysystem.dao.po.fund.FundDetailPO;
import com.mysystem.model.ro.FundDetailRO;
import com.mysystem.model.vo.FundDetailVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FundDetailInverter {

    @Mappings({})
     FundDetailPO roToPo(FundDetailRO ro);

    @Mappings({})
    FundDetailVO poToVo(FundDetailPO po);
    @Mappings({
    })
    List<FundDetailVO> poListToVoList(List<FundDetailPO> pos);
}
