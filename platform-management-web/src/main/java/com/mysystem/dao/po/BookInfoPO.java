package com.mysystem.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@TableName("t_book_info")
public class BookInfoPO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String bookId;

    private String bookName;

    private String bookPrefix; // 前缀信息

    private Integer deleted;

    private Date createTime;

    private Date updateTime;

}
