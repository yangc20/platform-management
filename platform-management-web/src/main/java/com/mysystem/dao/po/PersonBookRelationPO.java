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
public class PersonBookRelationPO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer personId;

    private Integer bookIntId; // 书籍所在表主键
    private String bookId; // 书籍id

    private Date createTime;

    private Date updateTime;

}
