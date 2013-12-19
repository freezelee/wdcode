/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/12/19 17:19:00                          */
/*==============================================================*/


/*==============================================================*/
/* Table: logs_login                                            */
/*==============================================================*/
create table logs_login
(
   id                   int not null auto_increment,
   name                 varchar(50),
   user_id              int,
   ip                   char(15),
   time                 int,
   state                tinyint,
   primary key (id)
);

/*==============================================================*/
/* Index: Index_Date                                            */
/*==============================================================*/
create index Index_Date on logs_login
(
   time
);

/*==============================================================*/
/* Table: logs_operate                                          */
/*==============================================================*/
create table logs_operate
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(50),
   user_id              int comment '用户主键',
   time                 int comment '操作时间',
   content              text,
   state                tinyint,
   primary key (id)
);

alter table logs_operate comment '日志信息表';

/*==============================================================*/
/* Index: Index_Date                                            */
/*==============================================================*/
create index Index_Date on logs_operate
(
   time
);

/*==============================================================*/
/* Table: logs_page                                             */
/*==============================================================*/
create table logs_page
(
   id                   int not null auto_increment,
   name                 varchar(50),
   page                 varchar(200),
   referrer             varchar(200),
   user_id              int,
   ip                   char(15),
   time                 int,
   out_time             int,
   user_agent           varchar(200),
   language             varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Index: Index_Date                                            */
/*==============================================================*/
create index Index_Date on logs_page
(
   time
);

/*==============================================================*/
/* Table: statistics_login                                      */
/*==============================================================*/
create table statistics_login
(
   user_id              int not null comment '主键',
   name                 varchar(50),
   count                int default 0,
   time                 int,
   ip                   char(15),
   last_time            int comment '手机',
   last_ip              char(15) comment '电话',
   primary key (user_id)
);

alter table statistics_login comment '用户信息表';

/*==============================================================*/
/* Index: INDEX_Time                                            */
/*==============================================================*/
create index INDEX_Time on statistics_login
(
   time
);

/*==============================================================*/
/* Table: statistics_page                                       */
/*==============================================================*/
create table statistics_page
(
   page                 varchar(200) not null comment '主键',
   count                int default 0,
   user_id              int comment '主键',
   time                 int,
   ip                   char(15),
   primary key (page)
);

alter table statistics_page comment '用户信息表';

/*==============================================================*/
/* Index: INDEX_Time                                            */
/*==============================================================*/
create index INDEX_Time on statistics_page
(
   time
);

