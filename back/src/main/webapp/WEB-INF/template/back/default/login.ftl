<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript" src="${base }/wdstatic/js/jquery.js"></script>
	<title><@s.text name="login" /><@s.text name="titles" /></title>
	<link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="login_top">
		<span id="login_caption"><@s.text name="login" /><@s.text name="titles" /></span>
	</div>
	<div id="login_main">
		<div id="login_content">		
			<@s.form action="login" validate="true"> 
				<@s.actionmessage/>
				<@s.fielderror />
				<@s.actionerror/>
				<div><@s.text name="login" /><@s.text name="name" />: <@s.textfield name="user.name" /></div>
				<div><@s.text name="password" />: <@s.password name="user.password"  size="22"/></div>
				<div id="verify"><@s.text name="verifyCode" />: <@s.textfield name="verifyCode" maxlength="4" /><@wd.verifycode/></div>
				<div><@s.submit cssClass="submit" value="%{getText('login')}" /></div>
				<@s.hidden name="autoLogin" value="true" />
			</@s.form>
		</div>
	</div>
	<div id="login_foot"></div> 
</body>
</html>