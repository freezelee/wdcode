<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><@s.text name="titles" /></title>    
	<link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
	<@wd.js name="jquery"/>
    <script type="text/javascript" src="${base }/back/${themeBack}/js/js.js"></script>
</head>
<body id="body">
<table id="main_table">
	<tr> 
		<td id="left_td" rowspan="2">
			<iframe id="left_frame" src="<@s.url action="left" />" frameBorder="0"></iframe>
		</td>
		<td id="left_exp" rowspan="2">
			<span id="switch">3</span>
		</td>
		<td id="top_td">
			<iframe id="top_frame" src="<@s.url action="top" />" frameBorder="0"></iframe>
		</td>
	</tr>
	<tr>
		<td id="main_td">
			<iframe id="main_frame" name="main_frame" src="<@s.url action="%{#attr.url}" />" frameBorder="0"></iframe>
		</td>
	</tr>
</table>
</body>
</html>