create database if not exists test_db char set utf8mb4;

use test_db;

create table if not exists doc_platform_user
(
    id          bigint unsigned auto_increment comment '自增主键'
        primary key,
    user_id     varchar(32)  default '0'               not null comment '用户id',
    nick_name   varchar(32)  default ''                not null comment '用户昵称',
    description varchar(128) default ''                not null comment '个人介绍',
    email       varchar(32)  default ''                not null comment '个人邮箱',
    account     varchar(32)  default ''                not null comment '用户账号',
    password    varchar(32)  default ''                not null comment '用户密码',
    phone       varchar(32)  default ''                not null comment '手机号码',
    avatar      varchar(128) default ''                not null comment '用户头像',
    location    varchar(512) default ''                not null comment '用户住址',
    create_time timestamp    default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    constraint idx_email_password
        unique (email, password)
)
    comment '用户信息表';