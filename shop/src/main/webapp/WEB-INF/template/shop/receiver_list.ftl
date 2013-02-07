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
				<div class="top">收货地址</div>
				<div class="middle">
					<div class="blank"></div>
					<a class="addButton" href="<@s.url action="receiver_to_info"/>" hidefocus>添加地址</a>
					<div class="blank"></div>
					<table id="receiverTable" class="listTable">
						<tr>
							<th>收货人</th>
							<th>地址</th>
							<th>电话</th>
							<th>默认</th>
							<th>操作</th>
						</tr>
						<#list entitys as receiver> 
							<tr>
								<td>
									${receiver.name}
								</td>
								<td>
									${query.get('area',receiver.id).display}${receiver.address} 
								</td>
								<td>
									${receiver.phone}
								</td> 
								<td>
									<a href="${base}/shop/receiver!edit.action?id=${receiver.id}">编辑</a>
									<a href="#" class="deleteReceiver" receiverId="${receiver.id}">删除</a>
								</td>
							</tr>
						</#list>
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
	<script type="text/javascript">
$().ready( function() {

	var $deleteReceiver = $("#receiverTable .deleteReceiver");
	
	// 删除
	$deleteReceiver.click( function() {
		var $this = $(this);
		var receiverId = $this.attr("receiverId");
		$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: deleteReceiver});
		function deleteReceiver() {
			$.ajax({
				url: "receiver!ajaxDelete.action",
				data: {id: receiverId},
				type: "POST",
				dataType: "json",
				cache: false,
				success: function(data) {
					$.message({type: data.status, content: data.message});
					$this.parent().parent().remove();
				}
			});
		}
		return false;
	});

})
</script>
</body>
</html>