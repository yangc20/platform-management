package com.mysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysystem.common.Constant;
import com.mysystem.dao.mapper.BookInfoMapper;
import com.mysystem.dao.po.BookInfoPO;
import com.mysystem.inverter.book.BookInfoInverter;
import com.mysystem.model.PageTemplate;
import com.mysystem.model.ro.BookQuery;
import com.mysystem.model.vo.BookDetailVO;
import com.mysystem.model.vo.BookInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BookInfoService extends ServiceImpl<BookInfoMapper, BookInfoPO> {

    @Autowired
    private BookInfoInverter bookInfoInverter;

    public PageTemplate<BookInfoVO> queryPage(BookQuery query) {
        LambdaQueryWrapper<BookInfoPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BookInfoPO::getDeleted, Constant.NO_DELETED);
        if (Objects.nonNull(query.getKeyWord())) {
            queryWrapper.and(wrapper -> wrapper.like(BookInfoPO::getBookId, query.getKeyWord())
                    .or().like(BookInfoPO::getBookName, query.getKeyWord()));
        }
        Page<BookInfoPO> pageHelper = new Page<>(query.getPageNum(), query.getPageSize());
        Page<BookInfoPO> page = baseMapper.selectPage(pageHelper, queryWrapper);
        List<BookInfoPO> bookInfoPOS = page.getRecords();
        List<BookInfoVO> collect = bookInfoPOS.stream().map(po ->
                BookInfoVO.builder().bookId(po.getBookId()).bookName(po.getBookName()).bookPrefix(po.getBookPrefix()).build()
        ).collect(Collectors.toList());
        PageTemplate<BookInfoVO> result = new PageTemplate<>(collect, page.getSize(), page.getCurrent(), page.getTotal());
        return result;
    }

    public BookDetailVO detail(Integer id) {
        LambdaQueryWrapper<BookInfoPO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BookInfoPO::getDeleted, Constant.NO_DELETED)
                .eq(BookInfoPO::getId,id);
        BookInfoPO po = baseMapper.selectOne(queryWrapper);

        return BookDetailVO.builder()
                .id(id).otherName(po.getBookName())
                .build();
    }

    public List<BookInfoVO> queryBookInfosById(List<Integer> ids){
        if (CollectionUtils.isEmpty(ids)){
            return new ArrayList<>();
        }
        List<BookInfoPO> bookInfoPOS = baseMapper.selectBatchIds(ids);
        return bookInfoInverter.posToVOs(bookInfoPOS);
    }
}
