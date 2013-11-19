/* 初始化顶级菜单 */
INSERT INTO menu(name) VALUES('商品管理');
INSERT INTO menu(name) VALUES('订单管理');
INSERT INTO menu(name) VALUES('会员管理');
INSERT INTO menu(name) VALUES('页面内容');
INSERT INTO menu(name) VALUES('管理员');
INSERT INTO menu(name) VALUES('网站设置');

/* 初始化二级菜单 */
/* 商品管理 */
INSERT INTO menu(name,menu_id) VALUES('商品管理',1);
INSERT INTO menu(name,menu_id) VALUES('商品分类管理',1);
INSERT INTO menu(name,menu_id) VALUES('商品类型管理',1);
INSERT INTO menu(name,menu_id) VALUES('商品规格管理',1);
INSERT INTO menu(name,menu_id) VALUES('品牌管理',1);

/* 订单管理 */
INSERT INTO menu(name,menu_id) VALUES('订单管理',2);
INSERT INTO menu(name,menu_id) VALUES('快递单管理',2);

/* 会员管理 */
INSERT INTO menu(name,menu_id) VALUES('会员管理',3);
INSERT INTO menu(name,menu_id) VALUES('商品评论',3);
INSERT INTO menu(name,menu_id) VALUES('在线留言',3);

/* 页面内容 */
INSERT INTO menu(name,menu_id) VALUES('内容管理',4);
INSERT INTO menu(name,menu_id) VALUES('模板管理',4);
INSERT INTO menu(name,menu_id) VALUES('缓存管理',4);
INSERT INTO menu(name,menu_id) VALUES('网站静态管理',4);

/* 管理员 */
INSERT INTO menu(name,menu_id) VALUES('管理员',5);
INSERT INTO menu(name,menu_id) VALUES('站内消息',5);
INSERT INTO menu(name,menu_id) VALUES('查看日志',5);

/* 网站设置 */
INSERT INTO menu(name,menu_id) VALUES('网站设置',6);
INSERT INTO menu(name,menu_id) VALUES('支付管理',6);
INSERT INTO menu(name,menu_id) VALUES('配送管理',6);

/* 初始化操作菜单 */
/* 商品管理 */
INSERT INTO menu(name,menu_id,url) VALUES('商品列表',7,'goods_page');
INSERT INTO menu(name,menu_id,url) VALUES('添加商品',7,'goods_theme_add');
INSERT INTO menu(name,menu_id,url) VALUES('到货通知',7,'notify_page'); 

INSERT INTO menu(name,menu_id,url) VALUES('分类列表',8,'sort_page');
INSERT INTO menu(name,menu_id,url) VALUES('添加分类',8,'sort_theme_add'); 

INSERT INTO menu(name,menu_id,url) VALUES('类型列表',9,'type_page');
INSERT INTO menu(name,menu_id,url) VALUES('添加类型',9,'type_theme_add'); 

INSERT INTO menu(name,menu_id,url) VALUES('商品规格',10,'specification_page');
INSERT INTO menu(name,menu_id,url) VALUES('添加规格',10,'specification_theme_add'); 
 
INSERT INTO menu(name,menu_id,url) VALUES('品牌列表',11,'brand_page');
INSERT INTO menu(name,menu_id,url) VALUES('添加品牌',11,'brand_theme_add'); 

/* 订单管理 */
INSERT INTO menu(name,menu_id,url) VALUES('订单列表',12,'orders_page');
INSERT INTO menu(name,menu_id,url) VALUES('收款单',12,''); 
INSERT INTO menu(name,menu_id,url) VALUES('退款单',12,''); 
INSERT INTO menu(name,menu_id,url) VALUES('发货单',12,''); 
INSERT INTO menu(name,menu_id,url) VALUES('退货单',12,''); 

INSERT INTO menu(name,menu_id,url) VALUES('发货点管理',13,'');
INSERT INTO menu(name,menu_id,url) VALUES('快递单模板',13,'');

/* 会员管理 */
INSERT INTO menu(name,menu_id,url) VALUES('会员管理',14,'user_page');
INSERT INTO menu(name,menu_id,url) VALUES('会员等级',14,''); 
 
INSERT INTO menu(name,menu_id,url) VALUES('评论列表',15,'comment_page');
INSERT INTO menu(name,menu_id,url) VALUES('评论设置',15,'');

INSERT INTO menu(name,menu_id,url) VALUES('留言列表',16,'leaves_page');
INSERT INTO menu(name,menu_id,url) VALUES('留言设置',16,'');

/* 页面内容 */ 
INSERT INTO menu(name,menu_id,url) VALUES('导航管理',17,'navigation_page');
INSERT INTO menu(name,menu_id,url) VALUES('文章管理',17,'article_page');
INSERT INTO menu(name,menu_id,url) VALUES('文章分类',17,'category_page');
INSERT INTO menu(name,menu_id,url) VALUES('友情链接',17,'friendLink_page');

INSERT INTO menu(name,menu_id,url) VALUES('页面模板管理',18,'');
INSERT INTO menu(name,menu_id,url) VALUES('邮件模板管理',18,'');
INSERT INTO menu(name,menu_id,url) VALUES('打印模板管理',18,''); 

INSERT INTO menu(name,menu_id,url) VALUES('缓存管理',19,'system_to_cache');
 
INSERT INTO menu(name,menu_id,url) VALUES('一键网站更新',20,'statics_index_all');
INSERT INTO menu(name,menu_id,url) VALUES('文章更新',20,'statics_index_article');
INSERT INTO menu(name,menu_id,url) VALUES('商品更新',20,'statics_index_goods');

/* 管理员 */
INSERT INTO menu(name,menu_id,url) VALUES('管理员列表',21,'admin_page');
INSERT INTO menu(name,menu_id,url) VALUES('角色管理',21,'role_page');

INSERT INTO menu(name,menu_id,url) VALUES('系统消息',22,'systemMessage_page');
INSERT INTO menu(name,menu_id,url) VALUES('用户消息',22,'message_page');

INSERT INTO menu(name,menu_id,url) VALUES('操作日志',23,'operateLogs_page');
INSERT INTO menu(name,menu_id,url) VALUES('登录日志',23,'loginLogs_page');
INSERT INTO menu(name,menu_id,url) VALUES('登录统计',23,'loginStatistics_page');
 
/* 网站设置 */ 
INSERT INTO menu(name,menu_id,url) VALUES('系统设置',24,'property_page');
INSERT INTO menu(name,menu_id,url) VALUES('在线客服',24,'customer_page');
 
INSERT INTO menu(name,menu_id,url) VALUES('支付方式',25,'payment_page');

INSERT INTO menu(name,menu_id,url) VALUES('配送方式',26,'dispatch_page');
INSERT INTO menu(name,menu_id,url) VALUES('地区管理',26,'area_entity');
INSERT INTO menu(name,menu_id,url) VALUES('物流公司',26,'corp_page');

/* 操作信息 */
/* 商品管理 */
INSERT INTO operate(name,link) VALUES('添加商品','goods_add');
INSERT INTO operate(name,link) VALUES('修改商品','goods_edit');
INSERT INTO operate(name,link) VALUES('删除商品','goods_dels'); 

INSERT INTO operate(name,link) VALUES('添加商品分类','sort_add');
INSERT INTO operate(name,link) VALUES('修改商品分类','sort_edit');
INSERT INTO operate(name,link) VALUES('删除商品分类','sort_dels');

INSERT INTO operate(name,link) VALUES('添加商品类型','type_add');
INSERT INTO operate(name,link) VALUES('修改商品类型','type_edit');
INSERT INTO operate(name,link) VALUES('删除商品类型','type_dels');
 
INSERT INTO operate(name,link) VALUES('添加商品规格','specification_add');
INSERT INTO operate(name,link) VALUES('修改商品规格','specification_edit');
INSERT INTO operate(name,link) VALUES('删除商品规格','specification_dels');
  
INSERT INTO operate(name,link) VALUES('添加商品品牌','brand_add');
INSERT INTO operate(name,link) VALUES('修改商品品牌','brand_edit');
INSERT INTO operate(name,link) VALUES('删除商品品牌','brand_dels');

/* 页面内容 */ 
INSERT INTO operate(name,link) VALUES('添加导航','navigation_add');
INSERT INTO operate(name,link) VALUES('修改导航','navigation_edit');
INSERT INTO operate(name,link) VALUES('删除导航','navigation_dels');

INSERT INTO operate(name,link) VALUES('添加文章','article_add');
INSERT INTO operate(name,link) VALUES('修改文章','article_edit');
INSERT INTO operate(name,link) VALUES('删除文章','article_dels');

INSERT INTO operate(name,link) VALUES('添加文章分类','category_add');
INSERT INTO operate(name,link) VALUES('修改文章分类','category_edit');
INSERT INTO operate(name,link) VALUES('删除文章分类','category_dels');

INSERT INTO operate(name,link) VALUES('添加友情链接','friendLink_add');
INSERT INTO operate(name,link) VALUES('修改友情链接','friendLink_edit');
INSERT INTO operate(name,link) VALUES('删除友情链接','friendLink_dels');
  
/* 管理员 */ 
INSERT INTO operate(name,link) VALUES('添加角色','role_add');
INSERT INTO operate(name,link) VALUES('修改角色','role_edit');
INSERT INTO operate(name,link) VALUES('删除角色','role_dels');
   
INSERT INTO operate(name,link) VALUES('添加支付方式','payment_add');
INSERT INTO operate(name,link) VALUES('修改支付方式','payment_edit');
INSERT INTO operate(name,link) VALUES('删除支付方式','payment_dels');

INSERT INTO operate(name,link) VALUES('添加配送方式','dispatch_add');
INSERT INTO operate(name,link) VALUES('修改配送方式','dispatch_edit');
INSERT INTO operate(name,link) VALUES('删除配送方式','dispatch_dels');

INSERT INTO operate(name,link) VALUES('添加地区','area_add');
INSERT INTO operate(name,link) VALUES('修改地区','area_edit');
INSERT INTO operate(name,link) VALUES('删除地区','area_dels');

INSERT INTO operate(name,link) VALUES('添加物流公司','corp_add');
INSERT INTO operate(name,link) VALUES('添加物流公司','corp_edit');
INSERT INTO operate(name,link) VALUES('添加物流公司','corp_dels');

/* 初始化权限 */
INSERT INTO authority(authority) VALUES('ROLE_ADMIN');  
 
/* 修改管理员密码 */
INSERT INTO admin(name,password,roleId) VALUES('admin','2ffd0a519768e5a51ff20c8652f80ec178dec2bc',1);