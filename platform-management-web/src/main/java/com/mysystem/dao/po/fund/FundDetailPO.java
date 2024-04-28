package com.mysystem.dao.po.fund;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_fund_detail")
public class FundDetailPO {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String uuid; // 流水id

    private Integer type; // 类型 0-支出 1-收入 2-目标

    private Integer money; // 具体金额

    private String description; // 描述

    private String createUser;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;
}
