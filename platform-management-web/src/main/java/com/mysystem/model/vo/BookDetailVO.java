package com.mysystem.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class BookDetailVO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("别名")
    private String otherName;
    @ApiModelProperty("生日")
    private Date birthday;
    @ApiModelProperty("头像id")
    private Integer faceId;
    @ApiModelProperty("是否删除")
    private Integer deleted;
    @ApiModelProperty("创建人")
    private String createUser;
    private Date createTime;
    private Date updateTime;

    @ApiModelProperty("书籍")
    private List<BookInfoVO> bookInfoVOList;
}
