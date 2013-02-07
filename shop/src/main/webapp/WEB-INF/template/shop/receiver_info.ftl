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
					<div id="validateErrorContainer" class="validateErrorContainer">
						<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
						<ul></ul>
					</div>
					<div class="blank"></div>
					<form id="receiverForm" action="<#if isAddAction>receiver!save.action<#else>receiver!update.action</#if>" method="post">
						<input type="hidden" name="id" value="${id}" />
						<table class="inputTable">
							<tr>
								<th>
									收货人姓名: 
								</th>
								<td>
									<input type="text" name="receiver.name" class="formText" value="${receiver.name}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									地区: 
								</th>
								<td>
									<input type="text" id="areaSelect" name="areaId" class="hidden" value="${receiver.areaId}" defaultSelectedPath="${receiver.areaId}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									地址: 
								</th>
								<td>
									<input type="text" name="receiver.address" class="formText" value="${receiver.address}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									电话: 
								</th>
								<td>
									<input type="text" id="receiverPhone" name="receiver.phone" class="formText" value="${receiver.phone}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									手机: 
								</th>
								<td>
									<input type="text" name="receiver.mobile" class="formText" value="${receiver.mobile}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									邮编: 
								</th>
								<td>
									<input type="text" name="receiver.zipCode" class="formText" value="${receiver.zipCode}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" class="submitButton" value="提 交" hidefocus /> 
								</td>
							</tr>
						</table>
					</form>
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

	var $validateErrorContainer = $("#validateErrorContainer");
	var $validateErrorLabelContainer = $("#validateErrorContainer ul");
	var $receiverForm = $("#receiverForm");

	var $areaSelect = $("#areaSelect");

	// 地区选择菜单
	$areaSelect.lSelect({
		url: "${base}/shop/area!ajaxArea.action"// AJAX数据获取url
	});
	
	// 表单验证
	$receiverForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"receiver.name": "required",
			"areaId": "required",
			"receiver.address": "required",
			"receiver.mobile": {
				"requiredOne": "#receiverPhone"
			},
			"receiver.zipCode": "required"
		},
		messages: {
			"receiver.name": "请输入收货人姓名",
			"areaId": "请选择地区",
			"receiver.address": "请输入地址",
			"receiver.mobile": {
				"requiredOne": "电话或手机必须填写其中一项"
			},
			"receiver.zipCode": "请输入邮编"
		},
		submitHandler: function(form) {
			$(form).find(":submit").attr("disabled", true);
			form.submit();
		}
	});

});
</script>
</body>
</html>