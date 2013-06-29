<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>订单详情</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" /> 
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/shop/css/shop.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
	<script type="text/javascript" src="${base}/common/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
		DD_belatedPNG.fix(".belatedPNG");
	</script>
<![endif]--> 
</head>
<body class="orderResult">
	<#include "include/header.ftl" > 
	<div class="body">
		<div class="blank"></div>
		<div class="orderResultDetail">
			<div class="message"><span class="icon">&nbsp;</span>&nbsp;&nbsp;您的订单已经成功提交!</div>
			<div class="blank"></div>
			<table class="listTable">
				<tr>
					<th colspan="2">订单信息</th>
				</tr>
				<tr>
					<td class="title">订单编号</td>
					<td>
						${order.sn}
						<a href="${base}/shop/entity!view.action?id=${order.sn}">[查看订单详情]</a>
					</td>
				</tr>
				<tr>
					<td class="title">订单总金额</td>
					<td><span class="red">${order.total}元</span></td>
				</tr>
			</table>
			<div class="blank"></div>
			<table class="listTable">
				<tr>
					<th colspan="2">配送信息</th>
				</tr>
				<tr>
					<td class="title">配送方式</td>
					<td>${order.dispatchId}</td>
				</tr>
			</table> 
			<@s.form action="orderPay">
				<input type="hidden" name="order.sn" value="${order.sn}" />
				<input type="hidden" name="order.pay" value="${order.pay}" />
				<div class="blank"></div>
				<table class="listTable">
					<tr>
						<th colspan="2">支付信息</th>
					</tr>
					<tr>
						<td class="title">支付方式</td>
						<td>${order.pay}</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" class="formButton" value="立即支付" />
						</td>
					</tr>
				</table>
			</@s.form> 
		</div>
		<div class="blank"></div>
		<#include "include/friend_link.ftl" > 
	</div>
	<div class="blank"></div>
	<#include "include/footer.ftl" >   
</body>
<@wd.js name="jquery" /> 
<@wd.js name="jquery.tools" />
<script type="text/javascript" src="${base}/shop/js/base.js"></script>
<script type="text/javascript" src="${base}/shop/js/shop.js"></script>
</html>