create database if not exists smart_medical char set utf8mb4;

use smart_medical;

create table if not exists medical_user
(
    id          bigint unsigned auto_increment comment '自增主键'
        primary key,
    user_id     bigint unsigned default 0                 not null comment '用户id',
    email       varchar(32)     default ''                not null comment '个人邮箱',
    password    varchar(32)     default ''                not null comment '用户密码',
    phone       varchar(32)     default ''                not null comment '手机号码',
    avatar      varchar(128)    default ''                not null comment '用户头像',
    type        tinyint         default 0                 not null comment '0-管理员 1-病人 2-患者',
    status      tinyint         default 0                 not null comment '账号状态 0-正常 1-封禁',
    create_time timestamp       default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp       default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '用户登录表';

alter table medical_user
    add unique index idx_user_id (user_id);
alter table medical_user
    add unique index idx_email_password (email, password);

create table if not exists medical_patient
(
    id             bigint unsigned auto_increment comment '自增主键'
        primary key,
    user_id        bigint unsigned default 0                 not null comment '用户id',
    name           varchar(32)     default ''                not null comment '患者姓名',
    email          varchar(32)     default ''                not null comment '邮箱',
    age            int unsigned    default 0                 not null comment '患者年龄',
    birthday       timestamp       default CURRENT_TIMESTAMP not null comment '生日',
    height         decimal(5, 2)   default 0.0               not null comment '患者身高,单位cm',
    weight         decimal(5, 2)   default 0.0               not null comment '患者体重,单位kg',
    sick_reason    varchar(2048)   default ''                not null comment '病因',
    sick_history   varchar(1024)   default ''                not null comment '病史',
    department     tinyint         default 0                 not null comment '科室id',
    health_monitor varchar(2048)   default ''                not null comment '身体检测数据',
    location       varchar(2048)   default ''                not null comment '家庭住址',
    create_time    timestamp       default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time    timestamp       default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '患者信息表';

alter table medical_patient
    add unique index idx_user_id (user_id);

create table if not exists medical_doctor
(
    id          bigint unsigned auto_increment comment '自增主键'
        primary key,
    user_id     bigint unsigned default 0                 not null comment '用户id',
    name        varchar(32)     default ''                not null comment '医生姓名',
    gender      tinyint         default 0                 not null comment '性别 0-男,1-女',
    position    varchar(32)     default ''                not null comment '医生头衔/职位',
    department  tinyint         default 0                 not null comment '科室id',
    description varchar(512)    default ''                not null comment '医生简介',
    phone       varchar(32)     default ''                not null comment '手机号码',
    create_time timestamp       default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp       default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '医生信息表';

alter table medical_doctor
    add unique index idx_user_id (user_id);

create table if not exists medical_manager
(
    id          bigint unsigned auto_increment comment '自增主键'
        primary key,
    user_id     bigint unsigned default 0                 not null comment '用户id',
    name        varchar(32)     default ''                not null comment '管理员姓名',
    create_time timestamp       default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp       default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '超级管理员信息表';

alter table medical_manager
    add unique index idx_user_id (user_id);

create table if not exists medical_reservation
(
    id          bigint unsigned auto_increment comment '自增主键'
        primary key,
    patient_id  bigint unsigned default 0                 not null comment '患者id',
    doctor_id   bigint unsigned default 0                 not null comment '医生id',
    status      tinyint         default 0                 not null comment '预约状态：0-已预约 1-生效中 2-已完成 3-已取消',
    reservation varchar(1024)   default ''                not null comment '预约介绍',
    begin_time  timestamp       default CURRENT_TIMESTAMP not null comment '起始时间',
    end_time    timestamp       default CURRENT_TIMESTAMP not null comment '结束时间',
    create_time timestamp       default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp       default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
) comment '预约信息记录表';

