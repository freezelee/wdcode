/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/11/15 14:11:26                          */
/*==============================================================*/


/*==============================================================*/
/* Table: login_logs                                            */
/*==============================================================*/
create table login_logs
(
   id                   int not null auto_increment,
   name                 varchar(50),
   user_id              int,
   ip                   char(15),
   time                 int,
   state                tinyint,
   primary key (id)
)
ENGINE = MYISAM;

/*==============================================================*/
/* Index: Index_Date                                            */
/*==============================================================*/
create index Index_Date on login_logs
(
   time
);

/*==============================================================*/
/* Table: login_statistics                                      */
/*==============================================================*/
create table login_statistics
(
   user_id              int not null comment '主键',
   name                 varchar(50),
   count                int default 0,
   time                 int,
   ip                   char(15),
   last_time            int comment '手机',
   last_ip              char(15) comment '电话',
   primary key (user_id)
)
ENGINE = MYISAM;

alter table login_statistics comment '用户信息表';

/*==============================================================*/
/* Index: INDEX_Time                                            */
/*==============================================================*/
create index INDEX_Time on login_statistics
(
   time
);

/*==============================================================*/
/* Table: operate_logs                                          */
/*==============================================================*/
create table operate_logs
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(50),
   user_id              int comment '用户主键',
   time                 int comment '操作时间',
   content              text,
   state                tinyint,
   primary key (id)
);

alter table operate_logs comment '日志信息表';

/*==============================================================*/
/* Index: Index_Date                                            */
/*==============================================================*/
create index Index_Date on operate_logs
(
   time
);

/*==============================================================*/
/* Table: page_logs                                             */
/*==============================================================*/
create table page_logs
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
)
ENGINE = MYISAM;

/*==============================================================*/
/* Index: Index_Date                                            */
/*==============================================================*/
create index Index_Date on page_logs
(
   time
);

/*==============================================================*/
/* Table: page_statistics                                       */
/*==============================================================*/
create table page_statistics
(
   page                 varchar(200) not null comment '主键',
   count                int default 0,
   user_id              int comment '主键',
   time                 int,
   ip                   char(15),
   primary key (page)
)
ENGINE = MYISAM;

alter table page_statistics comment '用户信息表';

/*==============================================================*/
/* Index: INDEX_Time                                            */
/*==============================================================*/
create index INDEX_Time on page_statistics
(
   time
);

