/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50614
Source Host           : 127.0.0.1:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2013-12-03 13:22:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `password` char(40) DEFAULT NULL COMMENT '用户密码',
  `state` tinyint(4) DEFAULT '1' COMMENT 'Email',
  `time` int(11) DEFAULT '0',
  `role_id` int(11) DEFAULT NULL COMMENT '用户状态 0 无效 1 有效 2 已删除',
  `login_ip` char(15) DEFAULT NULL,
  `login_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_Name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------

-- ----------------------------
-- Table structure for `area`
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `area_id` int(11) DEFAULT NULL,
  `display` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of area
-- ----------------------------

-- ----------------------------
-- Table structure for `article`
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `author` varchar(50) DEFAULT NULL,
  `content` text,
  `time` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `recommend` tinyint(4) DEFAULT NULL,
  `top` tinyint(4) DEFAULT NULL,
  `page` text COMMENT '产品页标题',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of article
-- ----------------------------

-- ----------------------------
-- Table structure for `authority`
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `authority` varchar(50) DEFAULT NULL COMMENT '角色名',
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of authority
-- ----------------------------

-- ----------------------------
-- Table structure for `brand`
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `path` varchar(200) DEFAULT NULL,
  `detail` text,
  `time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物品品牌表';

-- ----------------------------
-- Records of brand
-- ----------------------------

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `page` text COMMENT '产品页标题',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='信息表';

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `ip` char(15) DEFAULT NULL,
  `content` text,
  `contact` varchar(100) DEFAULT NULL,
  `comment_id` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品评论';

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for `corp`
-- ----------------------------
DROP TABLE IF EXISTS `corp`;
CREATE TABLE `corp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='快递公司';

-- ----------------------------
-- Records of corp
-- ----------------------------

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `contact` varchar(50) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客服';

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for `dispatch`
-- ----------------------------
DROP TABLE IF EXISTS `dispatch`;
CREATE TABLE `dispatch` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `corp_id` int(11) DEFAULT NULL,
  `first_weight` decimal(16,2) DEFAULT NULL,
  `last_weight` decimal(16,2) DEFAULT NULL,
  `first_price` decimal(16,2) DEFAULT NULL,
  `last_price` decimal(16,2) DEFAULT NULL,
  `detail` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dispatch
-- ----------------------------

-- ----------------------------
-- Table structure for `entitys`
-- ----------------------------
DROP TABLE IF EXISTS `entitys`;
CREATE TABLE `entitys` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entity` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `list` text,
  `map` text,
  PRIMARY KEY (`id`),
  KEY `Index_Time` (`time`),
  KEY `Index_Name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='通用实体表';

-- ----------------------------
-- Records of entitys
-- ----------------------------

-- ----------------------------
-- Table structure for `favorite`
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏夹';

-- ----------------------------
-- Records of favorite
-- ----------------------------

-- ----------------------------
-- Table structure for `friend_link`
-- ----------------------------
DROP TABLE IF EXISTS `friend_link`;
CREATE TABLE `friend_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `logo` varchar(100) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='友情链接表';

-- ----------------------------
-- Records of friend_link
-- ----------------------------

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL,
  `sort_id` int(11) DEFAULT NULL COMMENT ' 产品分类',
  `sn` varchar(20) DEFAULT NULL,
  `brand_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `price` decimal(16,2) DEFAULT NULL COMMENT '菜单名',
  `cost` decimal(16,2) DEFAULT NULL,
  `market` decimal(16,2) DEFAULT NULL COMMENT ' 打折价格',
  `store` int(11) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `marke_table` tinyint(1) DEFAULT NULL,
  `best` tinyint(1) DEFAULT NULL,
  `new_in` tinyint(1) DEFAULT NULL,
  `hot` tinyint(1) DEFAULT NULL,
  `specification_enabled` tinyint(1) DEFAULT NULL,
  `brief` text COMMENT '简略说明',
  `weight` int(11) DEFAULT NULL,
  `detail` text COMMENT '详细描述',
  `page` text COMMENT '产品页标题',
  `attributes` text,
  `images` text,
  `params` text,
  PRIMARY KEY (`id`),
  KEY `Index_SN` (`sn`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='物品表';

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for `goods_specification`
-- ----------------------------
DROP TABLE IF EXISTS `goods_specification`;
CREATE TABLE `goods_specification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品与规格关系表';

-- ----------------------------
-- Records of goods_specification
-- ----------------------------

-- ----------------------------
-- Table structure for `goods_type_brand`
-- ----------------------------
DROP TABLE IF EXISTS `goods_type_brand`;
CREATE TABLE `goods_type_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) DEFAULT NULL,
  `brand_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类型与品牌关系表';

-- ----------------------------
-- Records of goods_type_brand
-- ----------------------------

-- ----------------------------
-- Table structure for `leaves`
-- ----------------------------
DROP TABLE IF EXISTS `leaves`;
CREATE TABLE `leaves` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '留言名称 标题',
  `user_id` int(11) DEFAULT NULL,
  `ip` char(15) DEFAULT NULL,
  `content` text COMMENT '留言内容',
  `time` int(11) DEFAULT NULL COMMENT '留言时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='留言表';

-- ----------------------------
-- Records of leaves
-- ----------------------------

-- ----------------------------
-- Table structure for `logs_login`
-- ----------------------------
DROP TABLE IF EXISTS `logs_login`;
CREATE TABLE `logs_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `ip` char(15) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_Date` (`time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs_login
-- ----------------------------

-- ----------------------------
-- Table structure for `logs_operate`
-- ----------------------------
DROP TABLE IF EXISTS `logs_operate`;
CREATE TABLE `logs_operate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '用户主键',
  `time` int(11) DEFAULT NULL COMMENT '操作时间',
  `content` text,
  `state` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_Date` (`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志信息表';

-- ----------------------------
-- Records of logs_operate
-- ----------------------------

-- ----------------------------
-- Table structure for `logs_page`
-- ----------------------------
DROP TABLE IF EXISTS `logs_page`;
CREATE TABLE `logs_page` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `page` varchar(200) DEFAULT NULL,
  `referrer` varchar(200) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `ip` char(15) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `out_time` int(11) DEFAULT NULL,
  `user_agent` varchar(200) DEFAULT NULL,
  `language` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_Date` (`time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs_page
-- ----------------------------

-- ----------------------------
-- Table structure for `menu`
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名',
  `menu_id` int(11) DEFAULT '0',
  `url` varchar(100) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(200) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` text COMMENT '手机',
  `time` int(11) DEFAULT NULL,
  `ip` char(15) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='信息表';

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `money`
-- ----------------------------
DROP TABLE IF EXISTS `money`;
CREATE TABLE `money` (
  `user_id` int(11) NOT NULL COMMENT '主键',
  `money` decimal(20,2) DEFAULT NULL COMMENT '菜单名',
  `state` tinyint(4) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='金钱表';

-- ----------------------------
-- Records of money
-- ----------------------------

-- ----------------------------
-- Table structure for `navigation`
-- ----------------------------
DROP TABLE IF EXISTS `navigation`;
CREATE TABLE `navigation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='导航表';

-- ----------------------------
-- Records of navigation
-- ----------------------------

-- ----------------------------
-- Table structure for `notify`
-- ----------------------------
DROP TABLE IF EXISTS `notify`;
CREATE TABLE `notify` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物品缺货记录';

-- ----------------------------
-- Records of notify
-- ----------------------------

-- ----------------------------
-- Table structure for `operate`
-- ----------------------------
DROP TABLE IF EXISTS `operate`;
CREATE TABLE `operate` (
  `link` varchar(50) NOT NULL COMMENT '操作连接',
  `name` varchar(50) DEFAULT NULL COMMENT '操作名称 用于显示',
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`link`),
  KEY `Index_ID` (`link`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作信息表';

-- ----------------------------
-- Records of operate
-- ----------------------------

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `sn` char(40) NOT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '分类名',
  `products` text COMMENT '属于那个分类，没有就是顶级分类',
  `dispatch_id` int(11) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  `pay` varchar(20) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `detail` text,
  `status` tinyint(4) DEFAULT '0',
  `total` decimal(16,2) DEFAULT NULL,
  PRIMARY KEY (`sn`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='订单';

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL,
  `sn` varchar(20) DEFAULT NULL,
  `price` decimal(16,2) DEFAULT NULL COMMENT '菜单名',
  `cost` decimal(16,2) DEFAULT NULL,
  `market` decimal(16,2) DEFAULT NULL COMMENT ' 打折价格',
  `store` int(11) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `marke_table` tinyint(1) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `specification_values` text,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_SN` (`sn`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of product
-- ----------------------------

-- ----------------------------
-- Table structure for `property`
-- ----------------------------
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `name` varchar(100) NOT NULL COMMENT '获得属性的Key',
  `value` varchar(200) DEFAULT NULL COMMENT '属性的Value',
  PRIMARY KEY (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='属性表';

-- ----------------------------
-- Records of property
-- ----------------------------

-- ----------------------------
-- Table structure for `receiver`
-- ----------------------------
DROP TABLE IF EXISTS `receiver`;
CREATE TABLE `receiver` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL COMMENT '分类名',
  `area_id` int(11) DEFAULT NULL,
  `mobile` char(11) DEFAULT '0' COMMENT '属于那个分类，没有就是顶级分类',
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `zip_code` int(11) DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='收货地址';

-- ----------------------------
-- Records of receiver
-- ----------------------------

-- ----------------------------
-- Table structure for `revert`
-- ----------------------------
DROP TABLE IF EXISTS `revert`;
CREATE TABLE `revert` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '留言名称 标题',
  `user_id` int(11) DEFAULT NULL,
  `ip` char(15) DEFAULT NULL,
  `leave_id` int(11) DEFAULT NULL COMMENT '留言ID',
  `content` text COMMENT '回复内容',
  `time` int(11) DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of revert
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_authority`
-- ----------------------------
DROP TABLE IF EXISTS `role_authority`;
CREATE TABLE `role_authority` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `authority_id` int(11) DEFAULT '0',
  `role_id` int(11) DEFAULT '0' COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与权限关系表';

-- ----------------------------
-- Records of role_authority
-- ----------------------------

-- ----------------------------
-- Table structure for `role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_Menu` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单关系表';

-- ----------------------------
-- Records of role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `role_operate`
-- ----------------------------
DROP TABLE IF EXISTS `role_operate`;
CREATE TABLE `role_operate` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `operate` varchar(50) DEFAULT '0',
  `role_id` int(11) DEFAULT '0' COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与操作关系表';

-- ----------------------------
-- Records of role_operate
-- ----------------------------

-- ----------------------------
-- Table structure for `screen`
-- ----------------------------
DROP TABLE IF EXISTS `screen`;
CREATE TABLE `screen` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '屏蔽字',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='屏蔽词';

-- ----------------------------
-- Records of screen
-- ----------------------------

-- ----------------------------
-- Table structure for `sort`
-- ----------------------------
DROP TABLE IF EXISTS `sort`;
CREATE TABLE `sort` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '产品页标题',
  `time` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `sort_id` int(11) DEFAULT NULL COMMENT '产品页关键字',
  `page` text COMMENT '产品页标题',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='物品分类表';

-- ----------------------------
-- Records of sort
-- ----------------------------

-- ----------------------------
-- Table structure for `specification`
-- ----------------------------
DROP TABLE IF EXISTS `specification`;
CREATE TABLE `specification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `specification_values` text,
  `remark` varchar(100) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格表';

-- ----------------------------
-- Records of specification
-- ----------------------------

-- ----------------------------
-- Table structure for `statistics_login`
-- ----------------------------
DROP TABLE IF EXISTS `statistics_login`;
CREATE TABLE `statistics_login` (
  `user_id` int(11) NOT NULL COMMENT '主键',
  `name` varchar(50) DEFAULT NULL,
  `count` int(11) DEFAULT '0',
  `time` int(11) DEFAULT NULL,
  `ip` char(15) DEFAULT NULL,
  `last_time` int(11) DEFAULT NULL COMMENT '手机',
  `last_ip` char(15) DEFAULT NULL COMMENT '电话',
  PRIMARY KEY (`user_id`),
  KEY `INDEX_Time` (`time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of statistics_login
-- ----------------------------

-- ----------------------------
-- Table structure for `statistics_page`
-- ----------------------------
DROP TABLE IF EXISTS `statistics_page`;
CREATE TABLE `statistics_page` (
  `page` varchar(200) NOT NULL COMMENT '主键',
  `count` int(11) DEFAULT '0',
  `user_id` int(11) DEFAULT NULL COMMENT '主键',
  `time` int(11) DEFAULT NULL,
  `ip` char(15) DEFAULT NULL,
  PRIMARY KEY (`page`),
  KEY `INDEX_Time` (`time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of statistics_page
-- ----------------------------

-- ----------------------------
-- Table structure for `trolley`
-- ----------------------------
DROP TABLE IF EXISTS `trolley`;
CREATE TABLE `trolley` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '分类名',
  `goods_id` int(11) DEFAULT '0',
  `product_id` int(11) DEFAULT '0',
  `count` int(11) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `total` decimal(16,2) DEFAULT NULL,
  `price` decimal(16,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='购物车';

-- ----------------------------
-- Records of trolley
-- ----------------------------

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `time` int(11) DEFAULT NULL,
  `params` text,
  `attributes` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类型表';

-- ----------------------------
-- Records of type
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` char(40) DEFAULT NULL COMMENT '用户密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '用于显示',
  `mobile` char(11) DEFAULT NULL COMMENT '手机',
  `email` varchar(50) DEFAULT NULL COMMENT 'Email',
  `time` int(11) DEFAULT '0',
  `state` tinyint(4) DEFAULT '1' COMMENT '用户状态 0 无效 1 有效 2 已删除',
  `sex` tinyint(4) DEFAULT '0',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `ip` char(15) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `login_ip` char(15) DEFAULT NULL,
  `login_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Index_Name_Password` (`name`,`password`),
  KEY `INDEX_Email` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_message`
-- ----------------------------
DROP TABLE IF EXISTS `user_message`;
CREATE TABLE `user_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT '0',
  `message_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `INDEX_User_ID` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='信息与用户关系表';

-- ----------------------------
-- Records of user_message
-- ----------------------------
