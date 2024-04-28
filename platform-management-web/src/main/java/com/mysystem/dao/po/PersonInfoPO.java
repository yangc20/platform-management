package com.mysystem.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@TableName("t_person_info")
public class PersonInfoPO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String otherName;

    private Date birthday;

    private Integer faceId; // 头像id

    private Integer deleted;

    private String createUser;

    private Date createTime;

    private Date updateTime;

}
