##资金流水信息
drop table if exists t_fund_detail;
create table t_fund_detail
(
    `id`          int auto_increment comment "主键",
    `uuid`        varchar(32) default null comment "流水id",
    `type`        int                                   not null comment "类型，0-支出，1-收入，2-目标",
    `money`       int                                   not null comment "具体金额",
    `description` varchar(128)                          null comment "描述",
    `create_user` varchar(32)                           null comment "操作人",
    `create_time` datetime    default CURRENT_TIMESTAMP null comment '创建时间',
    update_time   datetime    default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    primary key (`id`),
    unique key unique_uuid (`uuid`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

alter table t_fund_detail add column `deleted` int default 0 comment '是否删除，0-否 1-是';