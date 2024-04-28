package com.mysystem.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageTemplate<T> {

    private List<T> data;
    private Long pageSize;

    private Long pageNum;

    private Long total;

}
