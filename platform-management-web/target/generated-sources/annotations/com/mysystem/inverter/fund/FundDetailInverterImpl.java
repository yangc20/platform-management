package com.mysystem.inverter.fund;

import com.mysystem.dao.po.fund.FundDetailPO;
import com.mysystem.model.ro.FundDetailRO;
import com.mysystem.model.vo.FundDetailVO;
import com.mysystem.model.vo.FundDetailVO.FundDetailVOBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-21T15:27:37+0800",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_291 (Oracle Corporation)"
)
@Component
public class FundDetailInverterImpl implements FundDetailInverter {

    @Override
    public FundDetailPO roToPo(FundDetailRO ro) {
        if ( ro == null ) {
            return null;
        }

        FundDetailPO fundDetailPO = new FundDetailPO();

        fundDetailPO.setId( ro.getId() );
        fundDetailPO.setType( ro.getType() );
        fundDetailPO.setMoney( ro.getMoney() );
        fundDetailPO.setDescription( ro.getDescription() );

        return fundDetailPO;
    }

    @Override
    public FundDetailVO poToVo(FundDetailPO po) {
        if ( po == null ) {
            return null;
        }

        FundDetailVOBuilder fundDetailVO = FundDetailVO.builder();

        fundDetailVO.id( po.getId() );
        fundDetailVO.money( po.getMoney() );
        fundDetailVO.type( po.getType() );
        fundDetailVO.description( po.getDescription() );
        fundDetailVO.createTime( po.getCreateTime() );
        fundDetailVO.updateTime( po.getUpdateTime() );

        return fundDetailVO.build();
    }

    @Override
    public List<FundDetailVO> poListToVoList(List<FundDetailPO> pos) {
        if ( pos == null ) {
            return null;
        }

        List<FundDetailVO> list = new ArrayList<FundDetailVO>( pos.size() );
        for ( FundDetailPO fundDetailPO : pos ) {
            list.add( poToVo( fundDetailPO ) );
        }

        return list;
    }
}
