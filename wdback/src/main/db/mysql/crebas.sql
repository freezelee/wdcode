/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013-1-14 19:51:45                           */
/*==============================================================*/


/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   id                   int not null auto_increment,
   name                 varchar(50) not null comment '用户名',
   password             char(40) comment '用户密码',
   state                tinyint default 1 comment 'Email',
   time                 int default 0,
   role_id              int comment '用户状态 0 无效 1 有效 2 已删除',
   primary key (id)
)
ENGINE = MYISAM;

alter table admin comment '管理员表';

/*==============================================================*/
/* Table: authority                                             */
/*==============================================================*/
create table authority
(
   id                   int not null auto_increment comment '主键',
   authority            varchar(50) comment '角色名',
   name                 varchar(50),
   primary key (id)
)
ENGINE = MYISAM;

alter table authority comment '权限表';

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(50) comment '菜单名',
   menu_id              int default 0,
   url                  varchar(100),
   primary key (id)
)
ENGINE = MYISAM;

alter table menu comment '菜单表';

/*==============================================================*/
/* Table: operate                                               */
/*==============================================================*/
create table operate
(
   id                   int not null auto_increment,
   link                 varchar(50) not null comment '操作连接',
   name                 varchar(50) comment '操作名称 用于显示',
   primary key (id)
)
ENGINE = MYISAM;

alter table operate comment '操作信息表';

/*==============================================================*/
/* Index: Index_ID                                              */
/*==============================================================*/
create index Index_ID on operate
(
   link
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
   content              varchar(500),
   state                tinyint,
   primary key (id)
)
ENGINE = MYISAM;

alter table operate_logs comment '日志信息表';

/*==============================================================*/
/* Index: Index_Date                                            */
/*==============================================================*/
create index Index_Date on operate_logs
(
   time
);

/*==============================================================*/
/* Table: role                                                  */
/*==============================================================*/
create table role
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(50) comment '角色名',
   primary key (id)
)
ENGINE = MYISAM;

alter table role comment '角色信息表';

/*==============================================================*/
/* Table: role_authority                                        */
/*==============================================================*/
create table role_authority
(
   id                   int not null auto_increment comment '主键',
   authority_id         int default 0,
   role_id              int default 0 comment '角色ID',
   primary key (id)
)
ENGINE = MYISAM;

alter table role_authority comment '角色与权限关系表';

/*==============================================================*/
/* Table: role_menu                                             */
/*==============================================================*/
create table role_menu
(
   id                   int not null auto_increment,
   role_id              int,
   menu_id              int,
   primary key (id)
)
ENGINE = MYISAM;

alter table role_menu comment '角色与菜单关系表';

/*==============================================================*/
/* Index: Index_Menu                                            */
/*==============================================================*/
create index Index_Menu on role_menu
(
   menu_id
);

/*==============================================================*/
/* Table: role_operate                                          */
/*==============================================================*/
create table role_operate
(
   id                   int not null auto_increment comment '主键',
   operate_id           int default 0,
   role_id              int default 0 comment '角色ID',
   primary key (id)
)
ENGINE = MYISAM;

alter table role_operate comment '角色与操作关系表';

