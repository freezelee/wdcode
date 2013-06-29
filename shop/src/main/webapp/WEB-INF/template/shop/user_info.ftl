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
<#assign user=query.get('user',token.id)>
<body class="memberCenter">
	<#include "include/header.ftl" > 
	<div class="body memberCenterIndex">
		<div class="bodyLeft">
			<#include "include/left.ftl" > 
		</div>
		<div class="bodyRight">
			<div class="memberCenterDetail">
				<div class="top">个人信息</div>
				<div class="middle">
					<div id="validateErrorContainer" class="validateErrorContainer">
						<div class="validateErrorTitle">以下信息填写有误,请重新填写</div>
						<ul></ul>
					</div>
					<div class="blank"></div>
					<form id="validateForm" action="<@s.url action="user_edit"/>" method="post">
						<table class="inputTable">
							<tr>
								<th>
									E-mail: 
								</th>
								<td>
									<input type="text" name="entity.email" class="formText" value="${user.email!}" />
									<label class="requireField">*</label>
								</td>
							</tr> 
							<tr>
								<th>
									昵称: 
								</th>
								<td>
									<input type="text" name="entity.nickName" class="formText" value="${user.nickName!}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									手机: 
								</th>
								<td>
									<input type="text" name="entity.mobile" class="formText" value="${user.mobile!}" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									性别: 
								</th>
								<td>
											<label><input type="radio" name="entity.sex" value="male" />男</label>
											<label><input type="radio" name="entity.sex" value="female" />女</label>
											
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
	var $validateForm = $("#validateForm");

	var $areaSelect = $("#areaSelect");

	// 地区选择菜单
	$areaSelect.lSelect({
		url: "${base}/shop/area!ajaxArea.action"// AJAX数据获取url
	});
	
	// 表单验证
	$validateForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"member.email": {
				required: true,
				email: true
			},
			"member.score": {
				required: true,
				digits: true
			},
			"member.deposit": {
				required: true,
				min: 0
			} 
		},
		messages: {
			"member.email": {
				required: "请填写E-mail",
				email: "E-mail格式不正确"
			},
			"member.score": {
				required: "请填写积分",
				digits: "积分必须为零或正整数"
			},
			"member.deposit": {
				required: "请填写预存款",
				min: "预存款必须为零或正数"
			} 
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