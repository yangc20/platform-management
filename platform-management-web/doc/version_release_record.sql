drop table if exists t_version_record;
create table t_version_record
(
    `id`          int auto_increment comment "主键",
    `version_id`  varchar(64)                        null comment "版本id",
    `description` varchar(256)                       null comment "描述",
    `dayno`       int                                not null comment "版本时间，如20991231",
    `create_time` datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    primary key (`id`)
) comment "版本更新记录";