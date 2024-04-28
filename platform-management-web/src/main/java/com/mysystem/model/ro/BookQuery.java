package com.mysystem.model.ro;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookQuery {

    private String keyWord;

    private Integer pageSize = 20;

    private Integer pageNum = 1;
}
