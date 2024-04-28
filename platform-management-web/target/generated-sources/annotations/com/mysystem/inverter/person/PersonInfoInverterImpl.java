package com.mysystem.inverter.person;

import com.mysystem.dao.po.PersonInfoPO;
import com.mysystem.model.vo.PersonDetailVO;
import com.mysystem.model.vo.PersonDetailVO.PersonDetailVOBuilder;
import com.mysystem.model.vo.PersonInfoVO;
import com.mysystem.model.vo.PersonInfoVO.PersonInfoVOBuilder;
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
public class PersonInfoInverterImpl implements PersonInfoInverter {

    @Override
    public PersonDetailVO poToDetailVO(PersonInfoPO po) {
        if ( po == null ) {
            return null;
        }

        PersonDetailVOBuilder personDetailVO = PersonDetailVO.builder();

        personDetailVO.id( po.getId() );
        personDetailVO.name( po.getName() );
        personDetailVO.otherName( po.getOtherName() );
        personDetailVO.birthday( po.getBirthday() );
        personDetailVO.faceId( po.getFaceId() );
        personDetailVO.createTime( po.getCreateTime() );
        personDetailVO.updateTime( po.getUpdateTime() );

        return personDetailVO.build();
    }

    @Override
    public PersonInfoVO poToVO(PersonInfoPO po) {
        if ( po == null ) {
            return null;
        }

        PersonInfoVOBuilder personInfoVO = PersonInfoVO.builder();

        personInfoVO.id( po.getId() );
        personInfoVO.name( po.getName() );
        personInfoVO.otherName( po.getOtherName() );
        personInfoVO.birthday( po.getBirthday() );
        personInfoVO.faceId( po.getFaceId() );
        personInfoVO.deleted( po.getDeleted() );
        personInfoVO.createUser( po.getCreateUser() );
        personInfoVO.createTime( po.getCreateTime() );
        personInfoVO.updateTime( po.getUpdateTime() );

        return personInfoVO.build();
    }

    @Override
    public List<PersonInfoVO> posToVOS(List<PersonInfoPO> pos) {
        if ( pos == null ) {
            return null;
        }

        List<PersonInfoVO> list = new ArrayList<PersonInfoVO>( pos.size() );
        for ( PersonInfoPO personInfoPO : pos ) {
            list.add( poToVO( personInfoPO ) );
        }

        return list;
    }
}
