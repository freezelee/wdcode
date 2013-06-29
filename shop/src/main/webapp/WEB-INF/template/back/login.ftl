<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>管理中心 - Powered By SHOP</title>
<meta name="Author" content="SHOP Team" />
<meta name="Copyright" content="SHOP" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base }/back/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base }/back/css/admin.css" rel="stylesheet" type="text/css" />
 
</head>
<body class="login">
	<script type="text/javascript"> 
		// 登录页面若在框架内，则跳出框架
		if (self != top) {
			top.location = self.location;
		}; 
	</script> 
	<div class="blank"></div> 
	<div class="blank"></div> 
	<div class="blank"></div> 
	<div class="body">
		<@s.form action="login" method="post" validate="true">
			<input type="hidden" name="url" value="welcome"/>			
			<@s.fielderror />
            <table class="loginTable"> 
            	<tr> 
            		<td rowspan="3"> 
            			<img src="${base }/back/images/login_logo.gif" alt="SHOP管理中心" /> 
            		</td> 
                    <th> 
                    	用户名: 
                    </th> 
					<td> 
						<@s.textfield name="user.name" cssClass="formText"/> 
                    </td>
                </tr>
                <tr>
					<th>
						密&nbsp;&nbsp;&nbsp;码:
					</th>
                    <td>
                    	<@s.password name="user.password" cssClass="formText"/> 
                    </td>
                </tr>
                <tr>
                	<th>
                		验证码:
                	</th>
                    <td>
                    	<@s.textfield name="verifyCode" cssClass="formText captcha" maxlength="4" />
                    	<@wd.verifycode css="captchaImage"/>
                    </td>
                </tr>
                <tr>
                	<td>
                		&nbsp;
                	</td>
                	<th>
                		&nbsp;
                	</th> 
                </tr>
                <tr>
                	<td>
                		&nbsp;
                	</td>
                	<th>
                		&nbsp;
                	</th>
                    <td>
                        <input type="button" class="homeButton" value="" onclick="window.open('${base }')" hidefocus />
                        <@s.submit cssClass="submitButton" value="登 录" /> 
                    </td>
                </tr>
            </table>
            <div class="powered">
            	COPYRIGHT © 2005-2011 SHOP.NET ALL RIGHTS RESERVED.
            </div>
            <div class="link">
            	<a href="${base }">前台首页</a> |
				<a href="###">官方网站</a> |
				<a href="###">交流论坛</a> |
				<a href="###">关于我们</a> |
				<a href="###">联系我们</a> |
				<a href="###">授权查询</a>
            </div>
        </@s.form>
	</div>
</body>
<@wd.js name="jquery" /> 
<script type="text/javascript" src="${base }/back/js/base.js"></script>
<script type="text/javascript" src="${base }/back/js/admin.js"></script>
</html>