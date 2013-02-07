<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><@s.text name="welcome" /><@s.text name="use" /><@s.text name="titles" /></title>
    <link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
</head>
<body>  
<@s.set var="user" value="#attr.query.get('admin',#attr.token.id)" />
<table id="theme_table" cellpadding="2" cellspacing="1">
	<tr>
		<td class="td_title" colspan="4"><@s.text name="welcome" /><@s.text name="use" /><@s.text name="titles" /></td>
	</tr>
	<tr>
		<td class="td_content" colspan="4">&nbsp;</td>
	</tr>
	<tr>
		<td class="td_show"><@s.text name="id" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.user.id}"/></td>
		<td class="td_show"><@s.text name="user" /><@s.text name="name" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.user.name}"/></td>
	</tr>	
	<tr>
		<td class="td_show"><@s.text name="email" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.user.email}"/></td>
		<td class="td_show"><@s.text name="nickName" />:</td>
		<td class="td_input"><@s.label cssClass="label" value="%{#attr.user.nickName}"/></td>				
	</tr>  
</table>
</body>
</html>