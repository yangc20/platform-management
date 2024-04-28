package com.mysystem.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FundInfoVO {

    private String totalInput;

    private String totalOutput;

    private String targetMoney;

    private List<String> waitingEvent;
}
