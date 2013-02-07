<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
	<title><@s.text name="login" /><@s.text name="titles" /></title> 
	<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
.verify{
vertical-align: sub;
}
</style>
<link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="147" background="${base }/back/${themeBack}/images/top02.gif"><img src="${base }/back/${themeBack}/images/top03.gif" width="776" height="147" /></td>
  </tr>
</table>
<table width="562" border="0" align="center" cellpadding="0" cellspacing="0" class="right-table03">
  <tr>
    <td width="221"><table width="95%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
      
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
          <tr>
            <td align="center"><img src="${base }/back/${themeBack}/images/ico13.gif" width="107" height="97" /></td>
          </tr>
          <tr>
            <td height="40" align="center">&nbsp;</td>
          </tr>
          
        </table></td>
        <td><img src="${base }/back/${themeBack}/images/line01.gif" width="5" height="292" /></td>
      </tr>
    </table></td>
    <td>
    <@s.form action="login" method="post" validate="true">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="31%" height="35" class="login-text02"><@s.text name="login" /><@s.text name="name" />：<br /></td>
	        <td width="69%"><@s.textfield name="user.name" size="30" /></td>
	      </tr>
	      <tr>
	        <td height="35" class="login-text02"><@s.text name="password" />：<br /></td>
	        <td><@s.password name="user.password"  size="30"/></td>
	      </tr>
	      <tr>
	        <td height="35" class="login-text02"><@s.text name="verifyCode" />：<br /></td>
	        <td><@s.textfield name="verifyCode" maxlength="4" size="15"/>&nbsp;<@wd.verifycode css="verify"/></td>
	      </tr>
	      <tr>
	        <td height="35">&nbsp;</td>
	        <td><@s.submit cssClass="right-button01" value="%{getText('login')}" />
	        	<@s.reset cssClass="right-button02" value="%{getText('reset')}"/>
	         </td>
	      </tr>
	    </table>
    </@s.form>
    </td>
  </tr>
</table>
</body>
</html>