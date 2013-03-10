<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>JEECMS Administrator's Control Panel</title>
<#include "head.ftl"/>
<script type="text/javascript">
if(top!=this) {
	top.location=this.location;
}
$(function() {
	$("#username").focus();
	$("#jvForm").validate();
});
</script>
<style type="text/css">
body{margin:0;padding:0;font-size:12px;background:url(${base}/res/jeecms/img/login/bg.jpg) top repeat-x;}
.input{width:150px;height:17px;border-top:1px solid #404040;border-left:1px solid #404040;border-right:1px solid #D4D0C8;border-bottom:1px solid #D4D0C8;}
</style>
</head>
<body>
<@s.form action="login" validate="true"> 
<#if returnUrl??><input type="hidden" name="returnUrl" value="${returnUrl}"/></#if>
<#if processUrl??><input type="hidden" name="processUrl" value="${processUrl}"/></#if>
<table width="750" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="200">&nbsp;</td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="423" height="280" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td><img src="${base}/res/jeecms/img/login/ltop.jpg" /></td>
              </tr>
              <tr>
                <td><img src="${base}/res/jeecms/img/login/llogo.jpg" /></td>
              </tr>
            </table></td>
          <td width="40" align="center" valign="bottom"><img src="${base}/res/jeecms/img/login/line.jpg" width="23" height="232" /></td>
          <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="90" align="center" valign="bottom"><img src="${base}/res/jeecms/img/login/ltitle.jpg" /></td>
              </tr>
              <tr>
                <td>
                <div>
	<ul>
		<li>
			<@s.actionerror /><@s.actionmessage /><@s.fielderror/> 
		</li>
	</ul>
                </div>
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="5">
                    <tr>
                      <td width="91" height="40" align="right"><strong> 用户名：</strong></td>
                      <td width="211"><@s.textfield name="user.name" cssClass="input"/></td>
                    </tr>
                    <tr>
                      <td height="40" align="right"><strong>密&nbsp;&nbsp;&nbsp;码：</strong></td>
                      <td><@s.password name="user.password" cssClass="input"/></td>
                    </tr> 
                    <tr>
                    	<td align="center">&nbsp;</td>
                    	<td><@wd.verifycode css="input"/></td>
                    </tr>
                    <tr>
                      <td height="40" align="right"><strong>验证码：</strong></td>
                      <td><@s.textfield id="captcha" name="verifyCode" maxlength="4" css="input"/></td>
                    </tr> 
                    <tr>
                      <td height="40" colspan="2" align="center">
					    <input type="image" src="${base}/res/jeecms/img/login/login.jpg" name="submit" />
                        &nbsp; &nbsp; <img name="reg" style="cursor: pointer" src="${base}/res/jeecms/img/login/reset.jpg" onclick="document.forms[0].reset()" /> </td>
                    </tr>
                  </table></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
</table>
</@s.form> 
</body>
</html>
