<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查看购物车</title>
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
<body class="cartItemList">
	<div id="changeQuantityTip" class="changeQuantityTip">
		<div class="tipArrow"></div>
		<div class="tipDetail">
			<div id="changeQuantityTipClose" class="tipClose"></div>
			<p id="changeQuantityTipTopMessage"></p>
			<p id="changeQuantityTipBottomMessage" class="red"></p>
		</div>
	</div>
	<#include "include/header.ftl" >   
	<div class="body">
		<div class="cartItemListBar"></div>
		<div class="blank"></div>
		<div class="cartItemListDetail">
			<div class="top">
				<div class="topLeft"></div>
				<div class="topMiddle">已放入购物车的商品: </div>
				<div class="topRight"></div>
			</div> 
			<@s.form action="orders_to_info" validate="false">
			<div class="middle">
				<table id="cartItemTable" class="listTable">
					<tr>
						<th>商品图片</th>
						<th>商品名称</th>
						<th>销售价格</th> 
						<th>数量</th>
						<th>小计</th>
						<th>删除</th>
					</tr>					
					<#assign trolleys=entitys!> 
					<#list trolleys as trolley> 
						<#assign goods=query.get('goods',trolley.goodsId)> 
						<@s.hidden name="entity.products[${trolley_index}].id" value="${goods.id}" /> 
						<@s.hidden name="entity.products[${trolley_index}].name" value="${goods.name}" />
						<@s.hidden name="entity.products[${trolley_index}].price" value="${goods.price}" />	
						<@s.hidden name="entity.products[${trolley_index}].total" value="${goods.price*trolley.count}" />			
						<tr>
							<td class="goodsImage">
								<a href="${base }/goods/${goods.id}.html" target="_blank">
									<img src="${base }/${goods.paths[0].replaceAll('#path','thumbnail')}" alt="点击放大" />
								</a> 
							</td>
							<td>
								<a href="${base }/goods/${goods.id}.html" target="_blank">
									${goods.name}
								</a>
							</td> 
							<td class="priceTd">
								${goods.price}
							</td>  
							<td>
								<@s.textfield name="entity.products[${trolley_index}].count" cssClass="formText quantity" value="${trolley.count}"/>
								<#if trolley.count &gt; goods.store> 
									<strong class="storeInfo red">[库存不足]</strong>
								</#if>
							</td>
							<td>
								<span class="subtotalPrice">${trolley.count*goods.price}</span>
							</td>
							<td>
								<@s.a action="trolley_del_list" cssClass="deleteCartItem"><@s.param name="key" value="${trolley.id}"/>删除</@s.a> 
							</td>
						</tr> 
					</#list>
					<#if trolleys?? && trolleys?size==0> 
						<tr>
							<td class="noRecord" colspan="6">购物车目前没有加入任何商品!</td>
						</tr>
					<#else>
						<tr>
							<td class="info" colspan="6">
								商品共计: <span id="totalProductQuantity" class="red">${action.add(action.getFields(trolleys,'count'))}</span> 件&nbsp;&nbsp;
								商品总金额(不含运费): <span id="totalProductPrice" class="red">￥${action.add(action.getFields(trolleys,'total'))}元</span>
							</td>
						</tr>
					</#if>									
				</table>
				<div class="blank"></div>
				<a class="continueShopping" href="${base}/"><span class="icon">&nbsp;</span>继续购物</a>
				<#if trolleys?? && trolleys?size &gt; 0>				 
					<@s.a action="trolley_del_list" id="clearCartItem" cssClass="clearCartItem"><@s.param name="entity.userId" value="#attr.token.id"/><span class="icon">&nbsp;</span>清空购物车</@s.a>
					<@s.submit id="orderInfoButton" cssClass="formButton" value="去结算"/>  
				</#if>
			</div>
			</@s.form>
			<div class="bottom">
				<div class="bottomLeft"></div>
				<div class="bottomMiddle"></div>
				<div class="bottomRight"></div>
			</div>
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
	<script type="text/javascript">
$().ready( function() {

	var $listTable = $("#listTable"); 
	var $changeQuantityTip = $("#changeQuantityTip");
	var $changeQuantityTipClose = $("#changeQuantityTipClose");
	var $changeQuantityTipTopMessage = $("#changeQuantityTipTopMessage");
	var $changeQuantityTipBottomMessage = $("#changeQuantityTipBottomMessage");
	var $deleteCartItem = $("#cartItemTable .deleteCartItem");
	var $clearCartItem = $("#clearCartItem");
	var $totalProductQuantity = $("#totalProductQuantity");
	var $totalProductPrice = $("#totalProductPrice");
	var $totalScore = $("#totalScore");
	var $orderInfoButton = $("#orderInfoButton");
	 
	// 修改商品数量提示框隐藏
	$changeQuantityTipClose.click( function() {
		$changeQuantityTip.fadeOut();
		return false;
	});
	
	// 删除购物车项
	$deleteCartItem.click( function() {
		$this = $(this);
		var productId = $this.attr("productId");
		$.dialog({type: "warn", content: "您确定要移除此商品吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: deleteCartItem});
		function deleteCartItem() {
			$.ajax({
				url: "cart_item!ajaxDelete.action",
				data: {"id": productId},
				type: "POST",
				dataType: "json",
				cache: false,
				success: function(data) {
					if (data.status == "success") {
						$this.parent().parent().remove();
						$totalProductQuantity.text(data.totalProductQuantity);
						$totalProductPrice.text(currencyFormat(data.totalProductPrice));
						$totalScore.text(currencyFormat(data.totalScore));
					}
					$.message({type: data.status, content: data.message});
				}
			});
		}
	});
	
	// 清空购物车项
	$clearCartItem.click( function() {
		$.dialog({type: "warn", content: "您确定要清空购物车吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: clearCartItem});
		function clearCartItem() {
			$.ajax({
				url: "cart_item!ajaxClear.action",
				type: "POST",
				dataType: "json",
				cache: false,
				success: function(data) {
					if (data.status == "success") {
						$(".listTable tr:gt(0)").remove(); 
						$orderInfoButton.remove();
						$clearCartItem.remove();
					}
					$.message({type: data.status, content: data.message});
				}
			});
		}
	});
	
	// 结算前检测购物车状态
	$orderInfoButton.click( function() { 
		if (parseInt($totalProductQuantity.text()) < 1) {
			$.message({type: "warn", content: "购物车目前没有加入任何商品!"});
			return false;
		}
		if (!$.memberVerify()) {
			$.showLoginWindow("${base}/shop/order!info.action");
			return false; 
		}
	});

});
</script>
</html>