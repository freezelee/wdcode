/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/10/29 20:13:42                          */
/*==============================================================*/


/*==============================================================*/
/* Table: ips                                                   */
/*==============================================================*/
create table ips
(
   ip                   char(15) not null comment '操作连接',
   name                 varchar(50) comment '操作名称 用于显示',
   type                 int,
   primary key (ip)
)
ENGINE = MYISAM;

alter table ips comment ' ';

/*==============================================================*/
/* Index: Index_ID                                              */
/*==============================================================*/
create index Index_ID on ips
(
   ip
);

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(50) comment '菜单名',
   menu_id              int default 0,
   url                  varchar(100),
   type                 int,
   primary key (id)
)
ENGINE = MYISAM;

alter table menu comment '菜单表';

/*==============================================================*/
/* Table: operate                                               */
/*==============================================================*/
create table operate
(
   link                 varchar(50) not null comment '操作连接',
   name                 varchar(50) comment '操作名称 用于显示',
   type                 int,
   primary key (link)
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
   content              text,
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
   operate              varchar(50) default '0',
   role_id              int default 0 comment '角色ID',
   primary key (id)
)
ENGINE = MYISAM;

alter table role_operate comment '角色与操作关系表';

