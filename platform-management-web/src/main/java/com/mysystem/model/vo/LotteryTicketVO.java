package com.mysystem.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LotteryTicketVO {

    @ApiModelProperty("开奖号码")
    public LotteryTicketNumberVO winNumberVO;

    @ApiModelProperty("自选彩票号码")
    public List<LotteryTicketNumberVO> selfNumberVOs;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("最近一次开奖信息")
    public Date lastWinTime;

    @ApiModelProperty("一等奖中奖情况")
    private String content;
}
