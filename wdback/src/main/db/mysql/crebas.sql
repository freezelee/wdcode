/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/11/7 14:53:33                           */
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
   login_ip             char(15),
   login_time           int,
   primary key (id)
);

alter table admin comment '管理员表';

/*==============================================================*/
/* Index: Index_Name                                            */
/*==============================================================*/
create unique index Index_Name on admin
(
   name
);

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

