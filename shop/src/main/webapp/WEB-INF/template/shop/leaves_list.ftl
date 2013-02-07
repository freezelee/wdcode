<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>在线留言</title>
<meta name="Author" content="SHOP Team" />
<meta name="Copyright" content="SHOP" />
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
<style type="text/css">
<!--
.leaveMessageItem {
	line-height: 18px;
	padding: 5px 10px;
	border: 1px solid #c7dbe5;
	background-color: #ecf2f8;
}

.leaveMessageItem .reply {
	line-height: 18px;
	padding: 5px 10px;
	margin: 3px 0px;
	border: 1px solid #c7dbe5;
	background-color: #ffffff;
}

.sendTable {
	width: 100%;
	line-height: 30px;
	border: 1px solid #c7dbe5;
}

.sendTable .title td {
	height: 30px;
	padding-left: 10px;
	text-align: left;
	font-weight: bold;
	background-color: #ecf2f8;
}

.sendTable th {
	text-align: right;
	font-weight: normal;
}

.sendTable td {
	padding: 5px;
}

.sendTable .captcha {
	width: 95px;
	margin-right: 5px;
	text-transform: uppercase;
}

.sendTable .captchaImage {
	vertical-align: middle;
	cursor: pointer;
}
-->
</style>
</head>
<body class="singlePage">
	<#include "include/header.ftl" >
	<div class="body leaveMessage">
		<div class="titleBar">
			<div class="left"></div>
			<div class="middle">
				<span class="icon">&nbsp;</span>在线留言
			</div>
			<div class="right"></div>
		</div>
		<div class="blank"></div>
		<div class="singlePageDetail">
			<div id="leaveMessage"> 
				<#list entitys as leave> 
					<div class="leaveMessageItem">
						<p>
							<span class="red">${leave.name!}</span> ${leave.date}
						</p>
						<p>
							<pre>${leave.content}</pre>
						</p>
						<#list query.list('revert','leaveId',leave.id) as revert> 
							<div class="reply">
								<p>
									<span class="red">管理员</span> ${revert.date}
								</p>
								<p>
									<pre>${revert.content}</pre>
								</p>
							</div>
						</#list>
					</div>
					<#if leave_index%2==0>					 
						<div class="blank"></div>
					</#if>
				</#list>
				<#if entitys?? && entitys?size &gt; 0>				 
					<div class="blank"></div>
					<@wd.pagination />
				<#else>
					<div class="leaveMessageItem">
						暂无留言!
					</div>
				</#if>
				<div class="blank"></div>
				<@s.form id="leaveMessageForm" action="leaves_page_list"> 
					<table class="sendTable">
						<tr class="title">
							<td width="100">
								发布留言
							</td>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<th>
								标题: 
							</th>
							<td>
								<input type="text" id="leaveMessageTitle" name="entity.name" class="formText" />
							</td>
						</tr>
						<tr>
							<th>
								留言内容: 
							</th>
							<td>
								<textarea id="leaveMessageContent" name="entity.content" class="formTextarea"></textarea>
							</td>
						</tr>
						<tr>
							<th>
								联系方式: 
							</th>
							<td>
								<input type="text" name="entity.contact" class="formText" />
							</td>
						</tr> 
							<tr>
			                	<th>
			                		验证码: 
			                	</th>
			                    <td>
			                    	<input type="text" id="leaveMessageCaptcha" name="j_captcha" class="formText captcha" />
			                   		<img id="leaveMessageCaptchaImage" class="captchaImage" src="${base }/verify.htm" alt="换一张" />
			                    </td>
			                </tr> 
						<tr>
							<th>
								&nbsp;
							</th>
							<td>
								<input type="submit" class="formButton" value="提 交"/>
							</td>
						</tr>
					</table>
				</@s.form>
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
		$().ready(function() {
		
			var $leaveMessage = $("#leaveMessage");
			var $leaveMessageForm = $("#leaveMessageForm");
			var $leaveMessageTitle = $("#leaveMessageTitle");
			var $leaveMessageContent = $("#leaveMessageContent");
			var $leaveMessageCaptcha = $("#leaveMessageCaptcha");
			var $leaveMessageCaptchaImage = $("#leaveMessageCaptchaImage");
			
			// 刷新在线留言验证码图片
			function leaveMessageCaptchaImageRefresh() {
				$leaveMessageCaptchaImage.attr("src", "${base }/verify.htm");
			}
			
			// 刷新在线留言验证码图片
			$leaveMessageCaptchaImage.click( function() {
				leaveMessageCaptchaImageRefresh();
			});
			
			$leaveMessageForm.submit( function() {
				if ($.trim($leaveMessageTitle.val()) == "") {
					$.dialog({type: "warn", content: "请输入标题!", modal: true, autoCloseTime: 3000});
					return false;
				}
				if ($.trim($leaveMessageContent.val()) == "") {
					$.dialog({type: "warn", content: "请输入留言内容!", modal: true, autoCloseTime: 3000});
					return false;
				} 
				if ($.trim($leaveMessageCaptcha.val()) == "") {
					$.dialog({type: "warn", content: "请输入验证码!", modal: true, autoCloseTime: 3000});
					return false;
				}  
				$.ajax({
					url: "${base}/shop/leaves_add_ajax.htm",
					data: $leaveMessageForm.serialize(),
					type: "POST",
					dataType: "json",
					cache: false,
					beforeSend: function() {
						$leaveMessageForm.find("submit").attr("disabled", true);
					},
					success: function(data) {
						$.dialog({type: "success", content: "留言成功!", modal: true, autoCloseTime: 3000});							 
								var username = ${token.name!};
								if (username == '') {
									username = "游客";
								}
								var leaveMessageItemHtml = '<div class="leaveMessageItem"><p><span class="red">' + username + '</span> ' + new Date().toLocaleDateString() + '</p><p><pre>' + htmlEscape($leaveMessageContent.val()) + '</pre></p></div><div class="blank"></div>';
								$leaveMessage.prepend(leaveMessageItemHtml);
							 
							$leaveMessageContent.val(""); 
					},
					complete: function() {
						$leaveMessageForm.find("submit").attr("disabled", false);
						$leaveMessageCaptcha.val("");
						leaveMessageCaptchaImageRefresh();
					}
				});
				return false;
			}); 
		});
	</script>
</body>
</html>