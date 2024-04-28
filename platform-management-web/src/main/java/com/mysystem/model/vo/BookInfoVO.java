package com.mysystem.model.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class BookInfoVO {

    private String bookId;

    private String bookName;

    private String bookPrefix; // 前缀信息

    private List<String> tagTypeList;

    private String imgUrl; // 封面
}
