package com.mysystem.model.ro;

import lombok.Data;

import java.util.Date;

@Data
public class NoteRO {

    private Integer id;

    private String title; // 标题

    private String content; // 内容

    private Integer tagId; // 标签id

    private String createUser;

}
