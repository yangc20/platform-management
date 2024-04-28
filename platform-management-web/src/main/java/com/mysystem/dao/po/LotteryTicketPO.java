package com.mysystem.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_lottery_ticket")
public class LotteryTicketPO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name; // 默认双色球

    private String code; // 期数

    private String blue;

    private String red;

    private String content;  // 开奖信息

    private String date; // 开奖时间和星期

    private String week; // 星期

    private String poolmoney; // 奖池金额

    private Date createTime;

    private Date updateTime;

}
