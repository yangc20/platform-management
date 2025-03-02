package com.mysystem.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class NoteVO {


    private Integer id;

    @ApiModelProperty("标题")
    private String title; //

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("标签id")
    private Integer tagId;

    @ApiModelProperty("日期 如20291231")
    private Integer dayno;

    @ApiModelProperty("创建人")
    private String createUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
