package com.mysystem.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class PersonInfoVO {

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
    @ApiModelProperty("头像url")
    private String faceImgUrl;
    @ApiModelProperty("是否删除")
    private Integer deleted;
    @ApiModelProperty("创建人")
    private String createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
