##人物信息表
drop table if exists t_person_info;
create table t_person_info
(
    `id`          int auto_increment comment "主键",
    `name`        varchar(128) default null comment "姓名",
    `other_name`  varchar(128) default null comment "别名",
    `birthday`    datetime     default null comment "生日",
    `face_id`     int          default null comment "头像id",
    `deleted`     tinyint      default 0 comment "是否删除 0否1是",
    `create_user` varchar(128) default null comment "创建人",
    `create_time` datetime     default current_timestamp comment "创建时间",
    `update_time` datetime     default null on update current_timestamp comment "更新时间",
    primary key (`id`),
    unique key (name),
    unique key (other_name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
##书籍名称
drop table if exists t_book_info;
create table t_book_info
(
    `id`          int auto_increment comment "主键",
    `book_id`     varchar(128) default null comment "编号",
    `book_name`   varchar(128) default null comment "名称",
    `book_prefix` varchar(64)  default null comment "前缀",
    `deleted`     tinyint      default 0 comment "是否删除 0否1是",
    `create_time` datetime     default current_timestamp comment "创建时间",
    `update_time` datetime     default null on update current_timestamp comment "更新时间",
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
##人物书籍关联表
drop table if exists t_person_book_relation;
create table t_person_book_relation
(
    `id`          int auto_increment comment "主键",
    `person_id`   int default null comment "人物id",
    `book_id`     varchar(128) default null comment "书籍编号id",
    `create_time` datetime     default current_timestamp comment "创建时间",
    `update_time` datetime     default null on update current_timestamp comment "更新时间",
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
##书籍标签表
drop table if exists t_book_tag;
create table t_book_tag
(
    `id`          int auto_increment comment "主键,标签id",
    `tag_name`    varchar(128) default null comment "标签名称",
    `describe`    varchar(128) default null comment "标签描述",
    `create_time` datetime     default current_timestamp comment "创建时间",
    `update_time` datetime     default null on update current_timestamp comment "更新时间",
    primary key (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
##书籍标签关联表
drop table if exists t_book_tag_relation;
create table t_book_tag_relation
(
    `id`          int auto_increment comment "主键",
    `book_id`     varchar(128) default null comment "书籍编号id",
    `tag_id`      int not null comment "标签id",
    `create_time` datetime     default current_timestamp comment "创建时间",
    `update_time` datetime     default null on update current_timestamp comment "更新时间",
    primary key (`id`),
    unique key (book_id,tag_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;