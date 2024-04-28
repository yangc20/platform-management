package com.mysystem.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@TableName("t_book_tag")
public class BookTagPO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String tagName;

    private String describe;

    private Date createTime;

    private Date updateTime;

}
