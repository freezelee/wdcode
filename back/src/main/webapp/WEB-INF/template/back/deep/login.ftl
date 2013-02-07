<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript" src="${base }/wdstatic/js/jquery.js"></script>
	<title><@s.text name="login" /><@s.text name="titles" /></title>
	<link rel="stylesheet" type="text/css" href="${base }/back/${themeBack}/css/showMsg.css">
	<style type="text/css">
body{
	background-image: url("${base }/back/${themeBack}/img/h1.gif");font-family: "微软雅黑","Arial";
}
.submit{
	margin-left: 50px;margin-top: 10px;
}
.div1{
	width: 400px;height: 250px;position: absolute;left: 50%;top: 50%;margin-left: -200px;margin-top: -110px;border-radius: 15px;
	background: -moz-radial-gradient(#ace, #f96, #1E90FF); 
	background: -webkit-radial-gradient(#ace, #333, #666);
	border: 0px solid #1873a0;background-color: #EEEEEE;box-shadow:5px 5px 5px #002A54;
}
.div2{
	margin: 10px;background-color: #ccc;width: 380px;height: 230px;border-radius: 15px;
}
.name{
	width: 100%;text-align: center;padding-top: 10px;font-size: 22px;color: #236D98;margin-bottom: 15px;
}
.F{
	color: #319DDB;font-size: 20px;width: 70px;padding-left: 40px;height: 38px;
}
.S{
	color: #666;font-size: 12px;width: 50px;
}
	</style>
  </head>
  
  <body>
    <div class="div1">
    	<div class="div2">
    		<div class="name"><@s.text name="titles" /></div>
    		<@s.form action="login" validate="true"> 
    		<table>
    			<tr>
    				<td class="F"><@s.text name="login" /><@s.text name="name" /></td> 
    				<td>
    					<@s.textfield name="user.name" />
    				</td>
    			</tr>
    			<tr>
    				<td class="F"><@s.text name="password" /></td> 
    				<td>
    					<@s.password name="user.password"  size="20"/>
    				</td>
    			</tr>
    			<tr>
    				<td class="F"><@s.text name="verifyCode" />:</td> 
    				<td>
    					<@s.textfield name="verifyCode" maxlength="4" /><@wd.verifycode url="back/verify.htm"/>
    				</td>
    			</tr>
    		</table>
    		<@s.submit cssClass="submit" value="%{getText('login')}" /> 
    		<@s.reset cssClass="submit" value="%{getText('reset')}"/>
    		</@s.form>
    	</div>
    </div>
  </body>
</html> 