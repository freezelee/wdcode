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
				<div class="top">发送消息</div>
				<div class="middle">
					<div id="validateErrorContainer" class="validateErrorContainer">
						<@s.fielderror/><@s.actionmessage /><@s.actionerror />
						<ul></ul>
					</div>
					<div class="blank"></div>
					<@s.form id="messageForm" action="message_add_send" >
						<table class="inputTable">
							<tr>
								<th>
									发送给: 
								</th>
								<td>
									<label><input type="radio" name="messageType" class="messageType" value="member" checked="checked">其它会员</label>
									<label><input type="radio" name="messageType" class="messageType" value="admin">管理员</label>
								</td>
							</tr>
							<tr id="toMemberTr">
								<th>
									对方用户名: 
								</th>
								<td>
									<input type="text" id="toMemberUsername" name="toMemberUsername" class="formText"/>
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									标题: 
								</th>
								<td>
									<input type="text" name="message.title" class="formText" value="" />
									<label class="requireField">*</label>
								</td>
							</tr>
							<tr>
								<th>
									内容: 
								</th>
								<td>
									<textarea name="message.content" class="formTextarea" rows="5" cols="50"></textarea>
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
					</@s.form> 
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
	var $messageForm = $("#messageForm");
	
	var $messageType = $(".messageType");
	var $toMemberTr = $("#toMemberTr");
	var $toMemberUsername = $("#toMemberUsername");
	
	$messageType.click( function(event) {
		if ($(this).val() == "member") {
			$toMemberTr.show();
			$toMemberUsername.attr("disabled", false);
		} else {
			$toMemberTr.hide();
			$toMemberUsername.attr("disabled", true);
		}
	});
	
	// 表单验证
	$messageForm.validate({
		errorContainer: $validateErrorContainer,
		errorLabelContainer: $validateErrorLabelContainer,
		wrapper: "li",
		errorClass: "validateError",
		ignoreTitle: true,
		rules: {
			"toMemberUsername": {
				"required": true,
				"notEqual": "${token.name}",
				"remote": "message!checkUsername.action"
			},
			"message.title": "required",
			"message.content": "required"
		},
		messages: {
			"toMemberUsername": {
				"required": "请填写对方用户名",
				"notEqual": "对方用户名不允许为自己",
				"remote": "对方用户名不存在"
			},
			"message.title": "请填写标题",
			"message.content": "请填写消息内容"
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