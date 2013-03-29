<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心 - Powered By SHOP++</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/back/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/back/css/admin.css" rel="stylesheet" type="text/css" /> 
</head>  
<body class="header">
	<div class="body">
		<div class="bodyLeft">
			<div class="logo"></div>
		</div>
		<div class="bodyRight">
			<div class="link">
				<span class="welcome">
					<strong>${token.name!""}</strong>&nbsp;您好!&nbsp;
				</span>
				<a href="<@s.url action="welcome" />" target="main_frame">后台首页</a>|
            	<a href="http://bbs.shopxx.net" target="_blank">技术支持</a>|
                <a href="http://www.shopxx.net" target="_blank">购买咨询</a>|
                <a href="http://www.shopxx.net/about.html" target="_blank">关于我们</a>|
                <a href="http://www.shopxx.net/contact.html" target="_blank">联系我们</a>
			</div>
			<div id="menu" class="menu">
				<ul>    
					<#list token.getMenus().get(0?int) as menu> 
						<li class="menuItem">
							<a href="<@s.url action='menu'><@s.param name='entity.id' value='${menu.id}'/></@s.url>" target="left_frame">${menu.name}</a>
						</li>
					</#list>
					<li class="home">
						<a href="${base}/" target="_blank">网站首页</a>
					</li>
	            </ul>
	            <div class="info">
					<a class="profile" href="<@s.url action='admin_theme_profile'><@s.param name='entity.id' value='${token.id}'/></@s.url>" target="main_frame">个人资料</a>
					<a class="logout" href="<@s.url action="logout"/>" target="_parent">退出</a>
				</div>
			</div>
		</div>
	</div>
</body>
<@wd.js name="jquery" /> 
<script type="text/javascript" src="${base}/back/js/base.js"></script>
<script type="text/javascript" src="${base}/back/js/admin.js"></script>
<script type="text/javascript"> 
$().ready( function() {
	$(".menuItem").click(function(){
		$(".current").addClass("menuItem");
		$(".current").removeClass("current");
		$(this).addClass("current");
		$(this).removeClass("menuItem");
	}); 
	$(".menuItem:last").addClass("current");
});
</script>
</html>