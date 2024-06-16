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
public class FundRelativeUserVO {

    private Integer type;

    private List<UserInfoVO> userInfoVOS;

}
