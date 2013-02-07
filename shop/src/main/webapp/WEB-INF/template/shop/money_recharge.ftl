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
				<div class="top">预存款充值</div>
				<div class="middle">
					<div id="validateErrorContainer" class="validateErrorContainer">
						<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
						<ul></ul>
					</div>
					<div class="blank"></div>
					<form id="depositRechargeForm" action="payment!submit.action" method="post">
						<input type="hidden" name="paymentType" value="recharge" />
						<table class="inputTable">
							<tr>
								<th>
									充值金额: 
								</th>
								<td>
									<input type="text" name="rechargeAmount" class="formText" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									支付方式: 
								</th>
								<td>
									<table class="paymentConfigTable">
										<#list pays.list() as pay>											 
											<tr>
												<td class="nameTd">
													<label>
														<input type="radio" name="pay" value="${pay.name}" /> ${pay.name}
													</label>
												</td>
												<td> 
													[${pay.detail}]  
												</td>
											</tr>
										</#list>
									</table>
								</td>
							</tr>
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" class="submitButton" value="立刻充值" hidefocus />
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
	var $depositRechargeForm = $("#depositRechargeForm");
	
	// 表单验证
	$depositRechargeForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"rechargeAmount": {
				required: true,
				positive: true
			},
			"paymentConfig.id": "required"
		},
		messages: {
			"rechargeAmount": {
				required: "请填写充值金额",
				positive: "充值金额必须为正数"
			},
			"paymentConfig.id": "请选择支付方式"
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