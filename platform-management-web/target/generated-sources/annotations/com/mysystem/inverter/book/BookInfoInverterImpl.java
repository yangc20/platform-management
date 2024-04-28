package com.mysystem.inverter.book;

import com.mysystem.dao.po.BookInfoPO;
import com.mysystem.model.vo.BookInfoVO;
import com.mysystem.model.vo.BookInfoVO.BookInfoVOBuilder;
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
public class BookInfoInverterImpl implements BookInfoInverter {

    @Override
    public List<BookInfoVO> posToVOs(List<BookInfoPO> pos) {
        if ( pos == null ) {
            return null;
        }

        List<BookInfoVO> list = new ArrayList<BookInfoVO>( pos.size() );
        for ( BookInfoPO bookInfoPO : pos ) {
            list.add( poToVO( bookInfoPO ) );
        }

        return list;
    }

    @Override
    public BookInfoVO poToVO(BookInfoPO po) {
        if ( po == null ) {
            return null;
        }

        BookInfoVOBuilder bookInfoVO = BookInfoVO.builder();

        bookInfoVO.bookId( po.getBookId() );
        bookInfoVO.bookName( po.getBookName() );
        bookInfoVO.bookPrefix( po.getBookPrefix() );

        return bookInfoVO.build();
    }
}
