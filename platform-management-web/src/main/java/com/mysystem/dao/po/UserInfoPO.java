package com.mysystem.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@TableName("t_user_info")
public class UserInfoPO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String password;
}
