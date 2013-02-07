<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>${action.getText('welcome,use,title')}</title>
    <link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
</head>
<body> 
<table id="theme_table" cellpadding="2" cellspacing="1">
	<tr>
		<td class="td_title" colspan="4">${action.getText('welcome,use,titles')}</td>
	</tr>
	<tr>
		<td class="td_content" colspan="4">&nbsp;</td>
	</tr>
	<tr> 
		<td class="td_show"><@s.text name="comp" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.token.company.name}"/></td>		
		<td class="td_show"><@s.text name="depa" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.token.department.name}"/></td>
	</tr>
	<tr>
		<td class="td_show"><@s.text name="role"/>:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.token.role.name}"/></td>
		<td class="td_show"><@s.text name="state" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{getText(#attr.token.stateString)}"/></td>		
	</tr>
	<tr>
		<td class="td_show"><@s.text name="id" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.token.id}"/></td>
		<td class="td_show"><@s.text name="user" /><@s.text name="name" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.token.name}"/></td>
	</tr>	
	<tr> 
		<td class="td_show"><@s.text name="nickName" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.token.nickName}"/></td>
		<td class="td_show"><@s.text name="sex" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{getText(#attr.token.sexString)}"/></td>
	</tr>
	<tr> 
		<td class="td_show"><@s.text name="mobile" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.token.mobile}"/></td>		
		<td class="td_show"><@s.text name="phone" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.token.phone}"/></td>
	</tr>	
	<tr> 
		<td class="td_show"><@s.text name="email" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.token.email}"/></td>
		<td class="td_show"><@s.text name="im" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.token.im}"/></td>
	</tr>
</table>
</body>
</html>