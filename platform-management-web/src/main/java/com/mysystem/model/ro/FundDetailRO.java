package com.mysystem.model.ro;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FundDetailRO {

    private Integer id;

    @ApiModelProperty("类型")
    private Integer type;

    private Integer money;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("相关人")
    private String relativeUserName;
}
