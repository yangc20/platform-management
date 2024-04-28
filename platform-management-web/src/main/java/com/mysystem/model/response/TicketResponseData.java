package com.mysystem.model.response;
import lombok.Data;

import java.util.List;

@Data
public class TicketResponseData<T> {

    private Integer state;

    private String message;

    private List<T> result;

    private Integer pageNum; // 总量

    private Integer pageNo; // 当前页面

    private Integer pageSize; // 页面大小

    private Integer Tflag;
}
