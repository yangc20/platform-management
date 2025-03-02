package com.mysystem.model.ro;

import lombok.Data;

@Data
public class NoteQueryRO {

    private Integer pageSize;

    private Integer pageNum;

    private String keyword;

    private Integer startDate;

    private Integer endDate;
}
