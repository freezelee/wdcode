<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><@s.text name="welcome" /><@s.text name="use" /><@s.text name="title" /></title>
    <link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<@s.set var="user" value="#attr.query.get('admin',#attr.token.id)" />
	<div id="main_top">
		<span><@s.text name="welcome" /><@s.text name="use" /><@s.text name="titles" /></span>
		<span><@s.text name="welcome" /><@s.text name="you" />:&nbsp;&nbsp;<@s.property value="#attr.user.name"/></span>
		<span><@s.text name="ip" />:&nbsp;&nbsp;<@s.label value="%{getIp()}" /></span>
		<span><@s.text name="date"/>:&nbsp;&nbsp;<@s.label value="%{@org.wdcode.common.util.DateTimeUtil@getShortDate()}" /></span>
		<span><@s.text name="week" /><@s.text name="%{@org.wdcode.common.util.DateTimeUtil@getDayOfWeek()}" /></span>
	</div>
</body>
</html>