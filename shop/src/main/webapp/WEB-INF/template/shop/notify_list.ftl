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
				<div class="top">缺货登记</div>
				<div class="middle">
					<div class="blank"></div>
					<table id="notifyTable" class="listTable">
						<tr>
							<th>商品图片</th>
							<th>商品名称</th>
							<th>商品价格</th>
							<th>操作</th>
						</tr>
						<#list entitys as notify> 
							<#assign product=query.get('product',notify.productId)> 
							<#assign goods=query.get('goods',notify.goodsId)> 
							<tr>
								<td>
									<a href="${base }/goods/${goods.id}.html" class="goodsImage" target="_blank">
										<img src="${base }/${goods.images[0].path.replaceAll('#path','thumbnail')}" />
									</a>
								</td>
								<td>
									<a href="${base }/goods/${goods.id}.html" target="_blank">
										${product.name}
									</a>
								</td>
								<td>
									${product.price}
								</td>
								<td>
									<a href="#" class="deletenotify" notifyId="${goods.id}">删除</a>
								</td>
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
<script type="text/javascript">
$().ready( function() {
	
	var $deletenotify = $("#notifyTable .deletenotify");
	
	// 到货通知删除
	$deletenotify.click( function() {
		var $this = $(this);
		var notifyId = $this.attr("notifyId");
		$.dialog({type: "warn", content: "您确定要删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: deletenotify});
		function deletenotify() {
			$.ajax({
				url: "goods_notify!ajaxDelete.action",
				data: {id: notifyId},
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
	
});
</script>
</body>
</html>