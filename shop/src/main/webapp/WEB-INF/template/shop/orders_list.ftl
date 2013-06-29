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
			<@s.form action="orders_page_list">
			<@s.hidden name="entity.userId" value="%{#attr.token.id}" />
				<div class="top">订单列表</div>
				<div class="middle">
					<div class="blank"></div>
					<table class="listTable">
						<tr>
							<th>商品名称</th>
							<th>订单编号</th>
							<th>下单时间</th>
							<th>订单金额</th>
							<th>订单状态</th>
						</tr>						
						<#list entitys as order> 
							<tr>
								<td width="350">
									<a href="${base }/goods/${order.sn}.html">
										${action.getFields(order.products,'name')!}
									</a>
								</td>
								<td>
									<a href="order!view.action?id=${order.sn}">${order.sn}</a>
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
					</table>
					<div class="blank"></div>
					<@wd.pagination />
				</div>
				<div class="bottom"></div>
				</@s.form> 
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