package com.mysystem.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class LotteryTicketNumberVO {

    @ApiModelProperty("所选/开奖红球号码")
    public List<String> redNums;

    @ApiModelProperty("命中红球号码")
    public List<String> scoreRedNums;

    @ApiModelProperty("所选/开奖蓝球号码")
    private List<String> blueNums;

    @ApiModelProperty("命中蓝球号码")
    public List<String> scoreBlueNums;
}
