/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/10/15 12:10:28                          */
/*==============================================================*/


/*==============================================================*/
/* Table: article                                               */
/*==============================================================*/
create table article
(
   id                   int not null auto_increment,
   name                 varchar(50),
   category_id          int,
   author               varchar(50),
   content              text,
   time                 int,
   state                tinyint,
   recommend            tinyint,
   top                  tinyint,
   page                 text comment '产品页标题',
   primary key (id)
)
ENGINE = MYISAM;

alter table article comment '文章表';

/*==============================================================*/
/* Table: category                                              */
/*==============================================================*/
create table category
(
   id                   int not null auto_increment,
   name                 varchar(50),
   category_id          int,
   page                 text comment '产品页标题',
   primary key (id)
)
ENGINE = MYISAM;

alter table category comment '信息表';

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
/* Table: friend_link                                           */
/*==============================================================*/
create table friend_link
(
   id                   int not null auto_increment,
   name                 varchar(50),
   url                  varchar(100),
   logo                 varchar(100),
   state                tinyint,
   primary key (id)
)
ENGINE = MYISAM;

alter table friend_link comment '友情链接表';

/*==============================================================*/
/* Table: leaves                                                */
/*==============================================================*/
create table leaves
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(100) comment '留言名称 标题',
   user_id              int,
   ip                   char(15),
   content              text comment '留言内容',
   time                 int comment '留言时间',
   primary key (id)
)
ENGINE = MYISAM;

alter table leaves comment '留言表';

/*==============================================================*/
/* Table: message                                               */
/*==============================================================*/
create table message
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(200),
   user_id              int,
   content              text comment '手机',
   time                 int,
   ip                   char(15),
   type                 int,
   primary key (id)
)
ENGINE = MYISAM;

alter table message comment '信息表';

/*==============================================================*/
/* Table: navigation                                            */
/*==============================================================*/
create table navigation
(
   id                   int not null auto_increment,
   name                 varchar(50),
   url                  varchar(100),
   state                tinyint,
   primary key (id)
)
ENGINE = MYISAM;

alter table navigation comment '导航表';

/*==============================================================*/
/* Table: property                                              */
/*==============================================================*/
create table property
(
   name                 varchar(100) not null comment '获得属性的Key',
   value                varchar(200) comment '属性的Value',
   primary key (name)
)
ENGINE = MYISAM;

alter table property comment '属性表';

/*==============================================================*/
/* Table: revert                                                */
/*==============================================================*/
create table revert
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(100) comment '留言名称 标题',
   user_id              int,
   ip                   char(15),
   leave_id             int comment '留言ID',
   content              text comment '回复内容',
   time                 int comment '回复时间',
   primary key (id)
)
ENGINE= MYISAM;

/*==============================================================*/
/* Table: screen                                                */
/*==============================================================*/
create table screen
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(100) comment '屏蔽字',
   primary key (id)
)
ENGINE = MYISAM;

alter table screen comment '屏蔽词';

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
   ip                   char(15),
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

/*==============================================================*/
/* Table: user_message                                          */
/*==============================================================*/
create table user_message
(
   id                   int not null auto_increment comment '主键',
   user_id              int default 0,
   message_id           int,
   primary key (id)
)
ENGINE = MYISAM;

alter table user_message comment '信息与用户关系表';

/*==============================================================*/
/* Index: INDEX_User_ID                                         */
/*==============================================================*/
create unique index INDEX_User_ID on user_message
(
   user_id
);

