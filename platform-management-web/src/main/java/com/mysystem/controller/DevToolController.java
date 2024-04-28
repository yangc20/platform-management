package com.mysystem.controller;

import com.mysystem.service.SystemService;
import com.mysystem.task.LotteryTicketSyncTask;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("测试工具")
@RestController
@RequestMapping("/api/devtool")
public class DevToolController {

    @Autowired
    private LotteryTicketSyncTask lotteryTicketSyncTask;

    @ApiOperation("获取当前环境")
    @GetMapping("/getEnv")
    public String getEnv(){
        return SystemService.getEnv();
    }


    @ApiOperation("开奖信息同步")
    @GetMapping("/lotteryTicket/sync")
    public String lotteryTicketSync(){
        lotteryTicketSyncTask.sync();
        return "success";
    }
}
