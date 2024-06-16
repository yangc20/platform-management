package com.mysystem.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@TableName("t_user_info")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoPO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String password;
}
