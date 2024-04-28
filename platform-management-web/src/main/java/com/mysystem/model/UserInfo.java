package com.mysystem.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfo {

    private Integer id;

    private String userName;

    private String password;
}
