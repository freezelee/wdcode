<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><@s.text name="login" /><@s.text name="title" /></title>
	<link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
</head>
<body id="login_body">
<div id="top"></div> 
<@s.form action="login" validate="true">
<div id="center">
   	<div id="center_left"></div>
    <div id="center_middle">		
		<@s.fielderror />
		<div class="user"><@s.text name="login" /><@s.text name="name" />:<@s.textfield name="user.name"/></div> 
		<div class="user"><@s.text name="password" />:<@s.password id="pwd" name="user.password" /></div>
		<div id="verify" class="verifycode">
        	<@s.text name="verifyCode" />:<@s.textfield name="verifyCode" maxlength="%{getVerifyLength()}" cssClass="verifycode_input" />         
	        <@wd.verifycode css="verifycode_img" width=60/>
	    </div>
	</div>
	<div id="center_middle_right"></div>
	<div id="center_submit">
		<div class="button"><@s.submit id="submit" type="image" src="${base}/back/skiyo/images/login.gif"/></div>						
	</div>
	<div id="center_right"></div>
</div>
</@s.form>
<div id="footer"></div>
</body>
</html>