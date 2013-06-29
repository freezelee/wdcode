<#import "/wd.ftl" as wd> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>填写订单信息</title>
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
<body class="orderInfo">
	<#include "include/header.ftl" >
	<div class="body">
		<div id="validateErrorContainer" class="validateErrorContainer">
			<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
			<ul></ul>
		</div>
		<div class="orderInfoDetail">
			<@s.form id="orderInfoForm" action="orderAdd">
				<table class="orderInfoTable">
					<tr id="receiverTr">
						<th>收货信息</th>
						<td>
							<ul>
								<#assign receivers = query.list('receiver','userId',token.id)>
								<#list receivers as receiver> 
									<li>
										<label>
											<input type="radio" name="receiver.id" class="receiverId" value="${receiver.id}" checked="checked"/>
											<strong>收货人: </strong>${receiver.name}&nbsp;&nbsp; 
											<strong>手机: </strong>${receiver.mobile}&nbsp;&nbsp; 
											<strong>电话: </strong>${receiver.phone}&nbsp;&nbsp; 
											<strong>收货地址: </strong>${query.get('area',receiver.areaId).display} ${receiver.address}
										</label>
									</li>
								</#list> 
								<li>
									<label>
										<input type="radio" id="otherReceiver" name="receiver.id" value="0"  class="receiverId"/>
										<strong>填写收货地址</strong>
									</label>
									<div class="blank"></div> 
									<table id="otherReceiverTable" class="otherReceiverTable">
										<@s.hidden name="receiver.userId" value="%{#attr.token.id}" />
										<tr>
											<th>
												收货人姓名: 
											</th>
											<td>
												<input type="text" name="receiver.name" class="formText" />
												<label class="requireField">*</label>
											</td>
										</tr>
										<tr>
											<th>
												地区: 
											</th>
											<td>
												<@s.select list="#attr.query.list('area')" listKey="id" listValue="name" name="receiver.areaId" /> 
												<label class="requireField">*</label>
											</td>
										</tr>
										<tr>
											<th>
												地址: 
											</th>
											<td>
												<input type="text" name="receiver.address" class="formText" />
												<label class="requireField">*</label>
											</td>
										</tr>
										<tr>
											<th>
												电话: 
											</th>
											<td>
												<input type="text" id="receiverPhone" name="receiver.phone" class="formText" />
												<label class="requireField">*</label>
											</td>
										</tr>
										<tr>
											<th>
												手机: 
											</th>
											<td>
												<input type="text" name="receiver.mobile" class="formText" />
												<label class="requireField">*</label>
											</td>
										</tr>
										<tr>
											<th>
												邮编: 
											</th>
											<td>
												<input type="text" name="receiver.zipCode" class="formText" />
												<label class="requireField">*</label>
											</td>
										</tr> 
										<tr>
											<th>
												设置: 
											</th>
											<td>
												<@s.checkbox name="isSave" value="true"/>保存收货地址												
											</td>
										</tr>
									</table> 
								</li>
							</ul>
						</td>
					</tr>
					<tr>
						<th>配送方式</th>
						<td>
							<table id="deliveryTypeTable" class="deliveryTypeTable">
								<#list query.list('dispatch') as dispatch> 						
									<tr>
										<th>
											<label>
												<input type="radio" name="order.dispatchId" value="${dispatch.id}" deliveryFee="${dispatch.firstPrice}" /> ${dispatch.name}
											</label>
										</th>
										<td>
											<strong class="red">+ ${dispatch.firstPrice}</strong> 
											<p>${dispatch.detail!}</p> 
										</td>
									</tr>
								</#list>
							</table>
						</td>
					</tr>
					<tr id="paymentConfigTr" class="paymentConfigTr">
						<th>支付方式</th>
						<td>
							<table id="paymentConfigTable" class="paymentConfigTable">
								<#list pays.keys() as k>
									<#assign p=pays.get(k)> 
									<tr>
										<th>
											<label>
												<input type="radio" name="order.pay" value="${k}"/> ${p.name}
											</label>
										</th>
										<td>  									 
											<p>${p.detail}</p> 
										</td>
									</tr>
								</#list>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<th>附言</th>
						<td>
							<input type="text" name="order.detail" class="formText" />
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
				<div class="blank"></div>
				<table class="cartItemTable">
					<tr>
						<th>商品图片</th>
						<th>商品名称</th>
						<th>销售价格</th> 
						<th>商品重量</th>
						<th>小计</th>
						<th>数量</th>
					</tr>
					<#list entity.products as product>
						<#assign goods=query.get('goods',product.id)> 
						<@s.hidden name="order.products[${product_index}].id" value="${product.id}" />
						<@s.hidden name="order.products[${product_index}].name" value="${product.name}" />
						<@s.hidden name="order.products[${product_index}].price" value="${product.price}" />
						<@s.hidden name="order.products[${product_index}].count" value="${product.count}" />			
						<@s.hidden name="order.products[${product_index}].total" value="${product.total}" />		
						<tr>
							<td class="goodsImage">
								<a href="${base }/goods/${goods.id}.html" target="_blank">
									<img src="${base}/${goods.paths[0].replaceAll('#path','thumbnail')}" />
								</a>
							</td>
							<td>
								<a href="${base }/goods/${goods.id}.html" target="_blank">
									${product.name}
								</a>
							</td> 
							<td class="priceTd">
								${product.price}
							</td> 
							<td>
								${goods.weight} 克
							</td>
							<td>
								<span class="subtotalPrice">${product.count*product.price}</span>
							</td>
							<td>
								${product.count}
							</td>
						</tr>
					</#list>
					<tr>
						<td class="info" colspan="6">
							商品共计: <span class="red">${action.add(action.getFields(entity.products,'count'))}</span> 件&nbsp;&nbsp; 
							商品总金额: <span id="totalProductPrice" class="red">${action.add(action.getFields(entity.products,'total'))}</span>&nbsp;&nbsp;
							配送费用: <span id="deliveryFee" class="red">0</span>&nbsp;&nbsp; 
							<#assign total=action.add(action.getFields(entity.products,'total'))> 
							订单总金额: <span id="orderAmount" class="red">${total}</span>
							<@s.hidden id="total" name="order.total" value="${total}" />
						</td>
					</tr>
				</table>
				<div class="blank"></div>
				<a class="backCartItem" href="${base}/trolley_list/${token.id}.html"><span class="icon">&nbsp;</span>返回购物车</a>
				<@s.submit cssClass="formButton" value="去结算"/> 
				<div class="blank"></div>
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
<script type="text/javascript"> 
$().ready( function() {
	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $orderInfoForm = $("#orderInfoForm");
	
	var $receiverId = $(".receiverId"); 
	var $otherReceiver = $("#otherReceiver");
	var $otherReceiverTable = $("#otherReceiverTable");
	var $otherReceiverInput = $("#otherReceiverTable :input");
	var $deliveryTypeId = $("#deliveryTypeTable input"); 
	var $deliveryFee = $("#deliveryFee");
	var $paymentFee = $("#paymentFee");
	var $orderAmount = $("#orderAmount");
	var totalProductPrice = ${total};// 商品总价格
	var deliveryFee = 0;// 配送费用 
 
	$otherReceiverTable.hide();
	$otherReceiverInput.attr("disabled", true);
 
	// 隐藏“其它收货地址输入框”
	$receiverId.click( function() { 
		$this = $(this);
		if ($this.val() == "") {
			$otherReceiverTable.show();
			$otherReceiverInput.attr("disabled", false);
		} else {
			$otherReceiverTable.hide();
			$otherReceiverInput.attr("disabled", true);
		}
	});
	
	// 显示“其它收货地址输入框”
	$otherReceiver.click( function() { 
		$otherReceiverTable.show();
		$otherReceiverInput.attr("disabled", false);
	});
 	
	// 根据配送方式修改配送费用、订单总金额,并显示/隐藏支付方式
	$deliveryTypeId.click(function() {
		var $this = $(this);
		deliveryFee = $this.attr("deliveryFee");
		$deliveryFee.text(deliveryFee);
		var total = parseInt(totalProductPrice)+parseInt(deliveryFee);
		$orderAmount.text(total);
		$("#total").val(total); 
	});
	
	// 根据支付方式修改订单总金额
	$paymentId.click( function() {
		var $this = $(this);
		var paymentFeeType = $this.attr("paymentFeeType");
		var paymentFee = $this.attr("paymentFee");
		if (paymentFeeType == "scale") {
			paymentFee = floatMul(floatAdd(totalProductPrice, deliveryFee), floatDiv(paymentFee, 100));
		}
		$paymentFee.text(currencyFormat(paymentFee));
		$orderAmount.text(currencyFormat(floatAdd(totalProductPrice, floatAdd(deliveryFee, paymentFee))));
	});
	
	// 表单验证
	$orderInfoForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"receiver.id": "required",
			"receiver.name": "required",
			"areaId": "required",
			"receiver.address": "required",
			"receiver.mobile": {
				"requiredOne": "#receiverPhone"
			},
			"receiver.zipCode": "required",
			"deliveryType.id": "required",
			"payment.id": "required",
			"memo": {
				maxlength: 200
			}
		},
		messages: {
			"receiver.id": "请选择收货地址",
			"receiver.name": "请填写收货人姓名",
			"areaId": "请选择地区",
			"receiver.address": "请填写地址",
			"receiver.mobile": {
				"requiredOne": "电话或手机必须填写其中一项"
			},
			"receiver.zipCode": "请填写邮编",
			"deliveryType.id": "请选择配送方式",
			"payment.id": "请选择支付方式",
			"memo": {
				maxlength: "附言长度必须小于等于200"
			}
		},
		submitHandler: function(form) {
			$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});

});
</script>
</html>