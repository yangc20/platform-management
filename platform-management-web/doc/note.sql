drop table if exists t_note;
create table t_note
(
    `id`          int auto_increment comment "主键",
    `title`       varchar(32) null comment "标题",
    `content`     text null comment "正文",
    `tag_id`      int null comment "标签id",
    `dayno`       int         not null comment "日期，如20991231",
    `create_user` varchar(64) not null comment "创建人",
    `deleted`     int      default 0 comment "0-未删除 1-已删除",
    `create_time` datetime default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    primary key (`id`)
) comment "笔记表";