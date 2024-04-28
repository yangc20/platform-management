##开奖记录
drop table if exists t_lottery_ticket;
create table t_lottery_ticket
(
    `id`          int auto_increment comment "主键id",
    `name`        varchar(64)  not null comment "名称，默认双色球",
    `code`        varchar(32)  not null comment "期数",
    `red`         varchar(64)  default null comment "红球号码,逗号分隔",
    `blue`        varchar(64)  default null comment "蓝球号码,逗号分隔",
    `content`     varchar(256) default null comment "中奖地区相关信息",
    `date`        varchar(32)  default null comment "开奖日期 yyyy-MM-dd",
    `week`        varchar(64)  default null comment "开奖星期 例如一、二、三",
    `poolmoney`   varchar(32)  default null comment "奖池金额",
    `create_time` datetime     default current_timestamp comment "创建时间",
    `update_time` datetime     default null on update current_timestamp comment "更新时间",
    primary key (`id`),
    unique key (`name`,`code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 comment '彩票开奖信息表';
