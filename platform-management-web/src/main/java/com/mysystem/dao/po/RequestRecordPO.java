package com.mysystem.dao.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("request_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestRecordPO {

    @Id
    private String id;
    private String requestId;

    private String url;

    private String method;

    private String requestParam;

    private Date requestTime;
}
