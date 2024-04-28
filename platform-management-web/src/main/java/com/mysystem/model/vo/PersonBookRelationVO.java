package com.mysystem.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PersonBookRelationVO {

    @ApiModelProperty("人物id")
    private Integer personId;

    @ApiModelProperty("书籍主键id")
    private Integer bookIntId;

    @ApiModelProperty("书籍id")
    private String bookId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
