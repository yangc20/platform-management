package com.mysystem.controller;

import com.mysystem.model.PageTemplate;
import com.mysystem.model.ro.BookQuery;
import com.mysystem.model.ro.PersonQuery;
import com.mysystem.model.vo.BookDetailVO;
import com.mysystem.model.vo.BookInfoVO;
import com.mysystem.model.vo.PersonDetailVO;
import com.mysystem.model.vo.PersonInfoVO;
import com.mysystem.service.BookInfoService;
import com.mysystem.service.PersonInfoService;
import com.mysystem.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("书籍信息检索")
@RestController
@RequestMapping("/api/book")
public class BookManagerController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private BookInfoService bookInfoService;

    @ApiOperation(value = "展示首页")
    @GetMapping("/latest/page")
    public String latestPage(){
        String test = userInfoService.test();
        return test;
    }

    @ApiOperation(value = "搜索")
    @PostMapping("/search")
    public PageTemplate<BookInfoVO> searchPage(@RequestBody BookQuery query){
        PageTemplate<BookInfoVO> bookInfo = bookInfoService.queryPage(query);
        return bookInfo;
    }

    @ApiOperation(value = "查看详情")
    @GetMapping("/detail")
    public BookDetailVO getDetail(@RequestParam("id") Integer id){
        BookDetailVO bookDetailVO = bookInfoService.detail(id);
        return bookDetailVO;
    }
}
