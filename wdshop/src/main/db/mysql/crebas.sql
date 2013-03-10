/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2013/3/3 15:28:27                            */
/*==============================================================*/


drop table if exists area;

drop table if exists brand;

drop table if exists comment;

drop table if exists corp;

drop table if exists customer;

drop table if exists dispatch;

drop table if exists favorite;

drop index Index_SN on goods;

drop table if exists goods;

drop table if exists goods_specification;

drop table if exists goods_type_brand;

drop table if exists money;

drop index Index_Date on money_in_detail;

drop index Index_User on money_in_detail;

drop table if exists money_in_detail;

drop index Index_Date on money_out_detail;

drop index Index_User on money_out_detail;

drop table if exists money_out_detail;

drop table if exists notify;

drop table if exists orders;

drop index Index_SN on product;

drop table if exists product;

drop table if exists receiver;

drop table if exists sort;

drop table if exists specification;

drop table if exists trolley;

drop table if exists type;

/*==============================================================*/
/* Table: area                                                  */
/*==============================================================*/
create table area
(
   id                   int not null auto_increment,
   name                 varchar(50),
   area_id              int,
   display              varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: brand                                                 */
/*==============================================================*/
create table brand
(
   id                   int not null auto_increment,
   name                 varchar(50),
   url                  varchar(50),
   path                 varchar(200),
   detail               text,
   time                 int,
   primary key (id)
);

alter table brand comment '物品品牌表';

/*==============================================================*/
/* Table: comment                                               */
/*==============================================================*/
create table comment
(
   id                   int not null auto_increment,
   name                 varchar(50),
   goods_id             int,
   ip                   char(15),
   content              text,
   contact              varchar(100),
   comment_id           int,
   time                 int,
   primary key (id)
);

alter table comment comment '商品评论';

/*==============================================================*/
/* Table: corp                                                  */
/*==============================================================*/
create table corp
(
   id                   int not null auto_increment,
   name                 varchar(50),
   url                  varchar(50),
   primary key (id)
);

alter table corp comment '快递公司';

/*==============================================================*/
/* Table: customer                                              */
/*==============================================================*/
create table customer
(
   id                   int not null auto_increment,
   name                 varchar(50),
   type                 int,
   contact              varchar(50),
   time                 int,
   primary key (id)
);

alter table customer comment '客服';

/*==============================================================*/
/* Table: dispatch                                              */
/*==============================================================*/
create table dispatch
(
   id                   int not null auto_increment,
   name                 varchar(50),
   type                 int,
   corp_id              int,
   first_weight         decimal(16,2),
   last_weight          decimal(16,2),
   first_price          decimal(16,2),
   last_price           decimal(16,2),
   detail               text,
   primary key (id)
);

/*==============================================================*/
/* Table: favorite                                              */
/*==============================================================*/
create table favorite
(
   id                   int not null auto_increment,
   user_id              int,
   goods_id             int,
   primary key (id)
);

alter table favorite comment '收藏夹';

/*==============================================================*/
/* Table: goods                                                 */
/*==============================================================*/
create table goods
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(50),
   sort_id              int comment ' 产品分类',
   sn                   varchar(20),
   brand_id             int,
   type_id              int,
   price                decimal(16,2) comment '菜单名',
   cost                 decimal(16,2),
   market               decimal(16,2) comment ' 打折价格',
   store                int,
   time                 int,
   marke_table          boolean,
   best                 boolean,
   new_in               boolean,
   hot                  boolean,
   specification_enabled boolean,
   brief                text comment '简略说明',
   weight               int,
   detail               text comment '详细描述',
   page                 text comment '产品页标题',
   attributes           text,
   images               text,
   params               text,
   primary key (id)
)
ENGINE = MYISAM;

alter table goods comment '物品表';

/*==============================================================*/
/* Index: Index_SN                                              */
/*==============================================================*/
create index Index_SN on goods
(
   sn
);

/*==============================================================*/
/* Table: goods_specification                                   */
/*==============================================================*/
create table goods_specification
(
   id                   int not null auto_increment,
   goods_id             int,
   sid                  int,
   primary key (id)
);

alter table goods_specification comment '商品与规格关系表';

/*==============================================================*/
/* Table: goods_type_brand                                      */
/*==============================================================*/
create table goods_type_brand
(
   id                   int not null auto_increment,
   type_id              int,
   brand_id             int,
   primary key (id)
);

alter table goods_type_brand comment '商品类型与品牌关系表';

/*==============================================================*/
/* Table: money                                                 */
/*==============================================================*/
create table money
(
   user_id              int not null comment '主键',
   money                decimal(20,2) comment '菜单名',
   state                tinyint default 1 comment '状态',
   primary key (user_id)
);

alter table money comment '金钱表';

/*==============================================================*/
/* Table: money_in_detail                                       */
/*==============================================================*/
create table money_in_detail
(
   id                   int not null auto_increment,
   user_id              int comment '主键',
   money                decimal(20,2) comment '菜单名',
   overage              decimal(20,2),
   amount               decimal(20,2),
   time                 int default 1 comment '状态',
   type                 tinyint,
   op_uid               int,
   description          varchar(200),
   detail               varchar(500),
   primary key (id)
);

alter table money_in_detail comment '金钱入账表';

/*==============================================================*/
/* Index: Index_User                                            */
/*==============================================================*/
create index Index_User on money_in_detail
(
   user_id
);

/*==============================================================*/
/* Index: Index_Date                                            */
/*==============================================================*/
create index Index_Date on money_in_detail
(
   time
);

/*==============================================================*/
/* Table: money_out_detail                                      */
/*==============================================================*/
create table money_out_detail
(
   id                   int not null auto_increment,
   user_id              int comment '主键',
   money                decimal(20,2) comment '菜单名',
   overage              decimal(20,2),
   amount               decimal(20,2),
   time                 int default 1 comment '状态',
   type                 tinyint,
   op_uid               int,
   description          varchar(200),
   detail               varchar(500),
   primary key (id)
);

alter table money_out_detail comment '金钱出账表';

/*==============================================================*/
/* Index: Index_User                                            */
/*==============================================================*/
create index Index_User on money_out_detail
(
   user_id
);

/*==============================================================*/
/* Index: Index_Date                                            */
/*==============================================================*/
create index Index_Date on money_out_detail
(
   time
);

/*==============================================================*/
/* Table: notify                                                */
/*==============================================================*/
create table notify
(
   id                   int not null auto_increment,
   user_id              int,
   goods_id             int,
   product_id           int,
   time                 bigint,
   primary key (id)
);

alter table notify comment '物品缺货记录';

/*==============================================================*/
/* Table: orders                                                */
/*==============================================================*/
create table orders
(
   sn                   char(40) not null,
   user_id              int comment '分类名',
   products             text comment '属于那个分类，没有就是顶级分类',
   dispatch_id          int,
   receiver_id          int,
   pay                  varchar(20),
   time                 int,
   detail               text,
   status               tinyint default 0,
   total                decimal(16,2),
   primary key (sn)
)
ENGINE= MYISAM;

alter table orders comment '物品订单';

/*==============================================================*/
/* Table: product                                               */
/*==============================================================*/
create table product
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(50),
   sn                   varchar(20),
   price                decimal(16,2) comment '菜单名',
   cost                 decimal(16,2),
   market               decimal(16,2) comment ' 打折价格',
   store                int,
   time                 int,
   marke_table          boolean,
   weight               int,
   specification_values text,
   goods_id             int,
   primary key (id)
)
ENGINE = MYISAM;

alter table product comment '商品表';

/*==============================================================*/
/* Index: Index_SN                                              */
/*==============================================================*/
create index Index_SN on product
(
   sn
);

/*==============================================================*/
/* Table: receiver                                              */
/*==============================================================*/
create table receiver
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(50),
   user_id              int comment '分类名',
   area_id              int,
   mobile               char(11) default '0' comment '属于那个分类，没有就是顶级分类',
   phone                varchar(20),
   address              varchar(100),
   zip_code             int default 1 comment '状态',
   primary key (id)
)
ENGINE= MYISAM;

alter table receiver comment '收货地址';

/*==============================================================*/
/* Table: sort                                                  */
/*==============================================================*/
create table sort
(
   id                   int not null auto_increment comment '主键',
   name                 varchar(50) comment '产品页标题',
   time                 int,
   type_id              int,
   sort_id              int comment '产品页关键字',
   page                 text comment '产品页标题',
   primary key (id)
)
ENGINE = MYISAM;

alter table sort comment '物品分类表';

/*==============================================================*/
/* Table: specification                                         */
/*==============================================================*/
create table specification
(
   id                   int not null auto_increment,
   name                 varchar(50),
   time                 int,
   specification_values text,
   remark               varchar(100),
   state                tinyint,
   primary key (id)
);

alter table specification comment '商品规格表';

/*==============================================================*/
/* Table: trolley                                               */
/*==============================================================*/
create table trolley
(
   id                   int not null auto_increment comment '主键',
   user_id              int comment '分类名',
   goods_id             int default 0,
   product_id           int default 0,
   count                int,
   time                 int,
   total                decimal(16,2),
   price                decimal(16,2),
   primary key (id)
)
ENGINE= MYISAM;

alter table trolley comment '购物车';

/*==============================================================*/
/* Table: type                                                  */
/*==============================================================*/
create table type
(
   id                   int not null auto_increment,
   name                 varchar(50),
   time                 int,
   params               text,
   attributes           text,
   primary key (id)
);

alter table type comment '商品类型表';

