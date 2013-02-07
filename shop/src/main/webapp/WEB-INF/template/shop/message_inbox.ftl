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
				<div class="top">收件箱</div>
				<div class="middle">
					<div class="blank"></div>
					<table id="messageTable" class="listTable">
						<tr>
							<th>标题</th>
							<th>发件人</th>
							<th>已读</th>
							<th>时间</th>
							<th>操作</th>
						</tr>
						<#list query.list('message','author',token.id,pager) as message>						
							<tr>
								<td>
									<span class="downIcon">&nbsp;</span>
									<a href="#" class="showMessage" messageId="${message.id}">${message.title}</a>
								</td>
								<td>
									${message.author}
								</td>
								<td>
									<#if message.isRead>
										是
									<#else>
										<span class="red">否</span>
									</#if>
								</td>
								<td>
									${message.date}
								</td>
								<td>
									<a href="${base}/shop/message!reply.action?id=${message.id}">回复</a>
									<a href="#" class="deleteMessage" messageId="${message.id}">删除</a>
								</td>
							</tr>
							<tr class="messageContentTr">
								<td colspan="5"></td>
							</tr>
						</#list>
					</table>
					<div class="blank"></div>
					<@wd.pagination />
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