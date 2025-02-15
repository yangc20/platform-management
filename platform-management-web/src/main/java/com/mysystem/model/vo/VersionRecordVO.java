package com.mysystem.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class VersionRecordVO {

    @ApiModelProperty("当前内容的主键id")
    private Integer id;

    @ApiModelProperty("版本id")
    private String versionId;

    @ApiModelProperty("版本描述")
    private String description;

    @ApiModelProperty("版本时间 yyyyMMdd")
    private Integer dayno;

    private Date createTime;

    private Date updateTime;

}
