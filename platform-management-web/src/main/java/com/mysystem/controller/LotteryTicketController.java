package com.mysystem.controller;

import com.mysystem.model.vo.LotteryTicketVO;
import com.mysystem.service.LotteryTicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("彩票管理")
@RestController
@RequestMapping("/api/lotteryticket")
public class LotteryTicketController {

    @Autowired
    private LotteryTicketService lotteryTicketService;


    @ApiOperation("彩票首页")
    @GetMapping("/home")
    public LotteryTicketVO home(){
        return lotteryTicketService.home();
    }
}
