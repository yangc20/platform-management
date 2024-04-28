package com.mysystem.controller;

import com.mysystem.model.PageTemplate;
import com.mysystem.model.ro.PersonQuery;
import com.mysystem.model.vo.PersonDetailVO;
import com.mysystem.model.vo.PersonInfoVO;
import com.mysystem.service.PersonInfoService;
import com.mysystem.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("人物信息检索")
@RestController
@RequestMapping("/api/person")
public class PersonManagerController {
    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PersonInfoService personInfoService;

    @ApiOperation(value = "展示首页")
    @GetMapping("/latest/page")
    public List<PersonInfoVO> latestPage(){
        List<PersonInfoVO> personInfoVOS = personInfoService.homePage();
        return personInfoVOS;
    }

    @ApiOperation(value = "搜索")
    @PostMapping("/search")
    public PageTemplate<PersonInfoVO> searchPage(@RequestBody PersonQuery query){
        PageTemplate<PersonInfoVO> personInfoVOS = personInfoService.queryPage(query);
        return personInfoVOS;
    }

    @ApiOperation(value = "查看详情")
    @GetMapping("/detail")
    public PersonDetailVO getDetail(@RequestParam("id") Integer id){
        PersonDetailVO personDetailVO = personInfoService.detail(id);
        return personDetailVO;
    }
}
