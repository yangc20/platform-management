package com.mysystem.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_version_record")
public class VersionRecordPO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String versionId; // 版本id

    private String description; // 版本描述

    private Integer dayno; // 时间 yyyyMMdd

    private Date createTime;

    private Date updateTime;

}
