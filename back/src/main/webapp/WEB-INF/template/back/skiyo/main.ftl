<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><@s.text name="title" /></title>    
	<link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
	<@wd.js name="jquery"/>
    <script type="text/javascript" src="${base }/back/${themeBack}/js/main.js"></script>
</head>
<body id="main_body">
<table id="main_table" cellpadding="0" cellspacing="0">
	<tr> 
		<td id="top_td" colspan="3">
			<iframe id="top_frame" src="<@s.url action="top" />" frameBorder="0"></iframe>
		</td>
	</tr>
	<tr>
		<td id="left_td">
			<iframe id="left_frame" src="<@s.url action="left" />" frameBorder="0"></iframe>
		</td>
		<td id="left_exp">
			<span id="switch">3</span>
		</td>
		<td id="main_td">
			<iframe id="main_frame" name="main_frame" src="<@s.url action="welcome" />" frameBorder="0"></iframe>
		</td>
	</tr>
</table>
</body>
</html>