/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/1/11 10:44:01                           */
/*==============================================================*/


/*==============================================================*/
/* Table: entitys                                               */
/*==============================================================*/
create table entitys
(
   id                   int not null auto_increment,
   entity               varchar(50),
   name                 varchar(50),
   time                 int,
   state                int,
   list                 text,
   map                  text,
   primary key (id)
)
ENGINE = MYISAM;

alter table entitys comment '通用实体表';

/*==============================================================*/
/* Index: Index_Time                                            */
/*==============================================================*/
create index Index_Time on entitys
(
   time
);

/*==============================================================*/
/* Index: Index_Name                                            */
/*==============================================================*/
create index Index_Name on entitys
(
   name
);

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
   user_agent           varchar(200),
   language             varchar(50),
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

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(50) comment '用户名',
   password             char(40) comment '用户密码',
   nick_name            varchar(50) comment '用于显示',
   mobile               char(11) comment '手机',
   email                varchar(50) comment 'Email',
   time                 int default 0,
   state                tinyint default 1 comment '用户状态 0 无效 1 有效 2 已删除',
   sex                  tinyint default 0,
   phone                varchar(20) comment '电话',
   register_ip          char(15),
   photo                varchar(100),
   primary key (id)
)
ENGINE = MYISAM;

alter table user comment '用户表';

/*==============================================================*/
/* Index: Index_Name_Password                                   */
/*==============================================================*/
create index Index_Name_Password on user
(
   name,
   password
);

/*==============================================================*/
/* Index: INDEX_Email                                           */
/*==============================================================*/
create index INDEX_Email on user
(
   email
);

