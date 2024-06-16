create table t_user_info
(
    id        int auto_increment comment '主键'
        primary key,
    user_name varchar(128) null comment '用户名',
    password  varchar(128) null comment '密码'
);