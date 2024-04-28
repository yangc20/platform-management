package com.mysystem.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@TableName("t_person_book_relation")
public class BookTagRelationPO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String bookId;

    private Integer tagId;

    private Date createTime;

    private Date updateTime;

}
