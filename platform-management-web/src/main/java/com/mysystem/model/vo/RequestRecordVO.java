package com.mysystem.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RequestRecordVO {

    private String requestId;

    private String url;

    private String method;

    private String requestParam;

    private Date requestTime;
}
