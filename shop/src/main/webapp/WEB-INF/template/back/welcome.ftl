<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心首页 - Powered By SHOP</title>
<meta name="Author" content="SHOP Team" />
<meta name="Copyright" content="SHOP" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/back/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/back/css/admin.css" rel="stylesheet" type="text/css" /> 
</head>
<body class="index">
	<div class="bar">
		欢迎使用SHOP网店管理系统！
	</div>
	<div class="body">
		<div class="bodyLeft">
			<table class="listTable">
				<tr>
					<th colspan="2">
						软件信息
					</th>
				</tr>
				<tr>
					<td width="110">
						系统名称: 
					</td>
					<td>
						SHOP网店管理系统
					</td>
				</tr>
				<tr>
					<td>
						系统版本: 
					</td>
					<td>
						1.0 体验版
					</td>
				</tr>
				<tr>
					<td>
						官方网站: 
					</td>
					<td>
						<a href="http://shop.wdcode.org">http://shop.wdcode.org</a>
					</td>
				</tr>
				<tr>
					<td>
						交流论坛: 
					</td>
					<td>
						<a href="http://bbs.wdcode.org">http://bbs.wdcode.org</a>
					</td>
				</tr>
				<tr>
					<td>
						BUG反馈邮箱: 
					</td>
					<td>
						<a href="mailto:bug@wdcode.org">bug@wdcode.org</a>
					</td>
				</tr>
				<tr>
					<td>
						商业授权: 
					</td>
					<td>
						未取得商业授权之前,您不能将本软件应用于商业用途
						<a class="red" href="http://shop.wdcode.org/license.html" target="_blank">[授权查询]</a>
					</td>
				</tr>
			</table>
			<div class="blank"></div>  
			<table class="listTable">
				<tr>
					<th colspan="2">
						待处理事务
					</th>
				</tr>
				<tr>
					<td width="110">
						未处理订单: 
					</td>
					<td> 
						${action.query.count('orders')}<a href="<@s.url action="orders_page" />">[订单列表]</a>
					</td>
				</tr>
				<tr>
					<td width="110">
						等待发货订单数: 
					</td>
					<td>
						${action.query.count('orders')}<a href="<@s.url action="orders_page" />">[订单列表]</a>
					</td>
				</tr>
				<tr>
					<td>
						未读消息: 
					</td>
					<td>
						${action.query.count('message')}<a href="<@s.url action="message_page" />">[收件箱]</a>
					</td>
				</tr>
				<tr>
					<td>
						未处理缺货登记数: 
					</td>
					<td>
						${action.query.count('notify')}<a href="<@s.url action="notify_page" />">[到货通知]</a>
					</td>
				</tr>
			</table>
		</div> 
		<div class="bodyRight">
			<table class="listTable">
				<tr>
					<th colspan="2">
						系统信息
					</th>
				</tr>
				<tr>
					<td width="110">
						Java版本: 
					</td>
					<td>
						${stack.findValue("@org.wdcode.common.constants.SystemConstants@JAVA_VERSION")} 
					</td>
				</tr>
				<tr>
					<td>
						操作系统名称: 
					</td>
					<td>
						${stack.findValue("@org.wdcode.common.constants.SystemConstants@OS_NAME")}  
					</td>
				</tr>
				<tr>
					<td>
						操作系统构架: 
					</td>
					<td>
						${stack.findValue("@org.wdcode.common.constants.SystemConstants@OS_ARCH")} 
					</td>
				</tr>
				<tr>
					<td>
						操作系统版本: 
					</td>
					<td>
						${stack.findValue("@org.wdcode.common.constants.SystemConstants@OS_VERSION")} 
					</td>
				</tr>
				<tr>
					<td>
						Server信息: 
					</td>
					<td>
						${servletContext.serverInfo}
					</td>
				</tr>
				<tr>
					<td>
						Servlet版本: 
					</td>
					<td>
						${servletContext.majorVersion}.${servletContext.minorVersion}
					</td>
				</tr>
			</table>
			<div class="blank"></div> 
			<table class="listTable">
				<tr>
					<th colspan="2">
						信息统计
					</th>
				</tr>
				<tr>
					<td width="110">
						已上架商品: 
					</td>
					<td>
						${action.query.count('goods','markeTable',true)} 
					</td>
				</tr>
				<tr>
					<td>
						已下架商品: 
					</td>
					<td>
						${action.query.count('goods','markeTable',false)} 
					</td>
				</tr>
				<tr>
					<td>
						会员总数: 
					</td>
					<td>
						${action.query.count('user')} 
					</td>
				</tr>
				<tr>
					<td>
						文章总数: 
					</td>
					<td>
						${action.query.count('article')}  
					</td>
				</tr>
			</table>
		</div>
		<p class="copyright">Copyright © 2005-2011 wdcode.org All Rights Reserved.</p>
	</div>
</body>
<@wd.js name="jquery" />  
<script type="text/javascript" src="${base}/back/js/base.js"></script>
<script type="text/javascript" src="${base}/back/js/admin.js"></script>
</html>