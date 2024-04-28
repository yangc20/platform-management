package com.mysystem.model.response;

import lombok.Data;

@Data
public class LotteryTicketDetailData {

    private String name; // 默认双色球

    private String code; // 期数

    private String blue;

    private String red;

    private String content;  // 开奖信息

    private String date; // 开奖时间和星期

    private String poolmoney; // 奖池金额


}
