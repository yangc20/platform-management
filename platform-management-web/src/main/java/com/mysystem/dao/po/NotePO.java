package com.mysystem.dao.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_note")
public class NotePO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String title; // 标题

    @TableField(updateStrategy = FieldStrategy.IGNORED)
    // 该字段允许更新为空
    private String content; // 内容

    private Integer tagId; // 标签id


    private Integer dayno; // 日期 如20291231

    private String createUser;

    private Integer deleted; // 0-未删除 1-已删除

    private Date createTime;

    private Date updateTime;
}
