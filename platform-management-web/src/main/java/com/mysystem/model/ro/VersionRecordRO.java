package com.mysystem.model.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class VersionRecordRO {

    @ApiModelProperty("单条更新内容id")
    private Integer id;

    @ApiModelProperty("单条更新内容")
    private String description;

    @ApiModelProperty("更新内容")
    private List<String> descriptions;

}
