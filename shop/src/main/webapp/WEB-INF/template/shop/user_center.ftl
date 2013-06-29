<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>会员中心</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/shop/css/shop.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
	<script type="text/javascript" src="${base}/template/common/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
		DD_belatedPNG.fix(".belatedPNG");
	</script>
<![endif]-->
</head>
<body class="memberCenter">
	<#include "include/header.ftl" > 
	<div class="body memberCenterIndex">
		<div class="bodyLeft">
			<#include "include/left.ftl" >  
		</div>
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">会员中心首页</div>
				<div class="middle">
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<td colspan="4">
								您目前是[等级] 
								<span class="red">[优惠百分比: 0%]</span> 
							</td>
						</tr>
						<tr>
							<th>帐户总积分</th>
							<td>1</td>
							<th>订单总数量</th>
							<td>
								${query.count('orders','userId',token.id)}&nbsp;&nbsp;
								<a href="<@s.url action="orders_list"/>">[查看订单列表]</a>
							</td>
						</tr>
						<tr>
							<th>预存款余额</th>
							<td>1</td>
							<th>未读消息数</th>
							<td>
								${query.count('message','userId',token.id)}&nbsp;&nbsp;
								<a href="<@s.url action="message_to_inbox"/>">[查看收件箱]</a>
							</td>
						</tr>
						<tr>
							<th>注册日期</th>
							<td>${query.get('user',token.id).date}</td>
							<th>最后登录IP</th>
							<td>1</td>
						</tr>
					</table>
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<th>商品名称</th>
							<th>订单编号</th>
							<th>下单时间</th>
							<th>订单金额</th>
							<th>订单状态</th>
						</tr>
						<#list query.list('orders','userId',token.id,10) as order> 
							<tr>
								<td width="350">
									<a href="order!view.action?id=${order.sn}">
										<span>
											${action.getFields(order.products,'name')!}
										</span>
									</a>
								</td>
								<td>
									<a href="order!view.action?id=${order.sn}">${order.sn!}</a>
								</td>
								<td>
									<span title="${order.date}">${order.date}</span>
								</td>
								<td>
									${order.total}
								</td>
								<td>
									${order.status!}
								</td>
							</tr>
						</#list>
						<tr>
							<td colspan="5">
								<a href="<@s.url action="orders_list"/>">更多订单>></a>
							</td>
						</tr>
					</table>
					<div class="blank"></div>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="blank"></div>
		<#include "include/friend_link.ftl" >  
	</div>
	<div class="blank"></div>
	<#include "include/footer.ftl" >  
	<@wd.js name="jquery" /> 
	<@wd.js name="jquery.tools" />
	<script type="text/javascript" src="${base}/shop/js/base.js"></script>
	<script type="text/javascript" src="${base}/shop/js/shop.js"></script>
</body>
</html>