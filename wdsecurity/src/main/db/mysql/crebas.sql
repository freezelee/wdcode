/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2014/1/12 19:56:20                           */
/*==============================================================*/


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
/* Table: role_authority                                        */
/*==============================================================*/
create table role_authority
(
   id                   int not null auto_increment comment '主键',
   authority_id         int default 0,
   role_id              int default 0 comment '角色ID',
   primary key (id)
);

alter table role_authority comment '角色与权限关系表';

