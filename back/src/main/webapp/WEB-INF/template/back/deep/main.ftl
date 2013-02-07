<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><@s.text name="titles" /></title>    
	<link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
	<@wd.js name="jquery"/>
    <script type="text/javascript" src="${base }/back/${themeBack}/js/js.js"></script>
</head>
<frameset rows="108px,*,30px" border="1" noresize="noresize" bordercolor="#000">
		<frame src="<@s.url action="top" />" scrolling="no"/>
		<frameset cols="220px,*" border="1" noresize="noresize" bordercolor="#000">
			<frame src="<@s.url action="left" />" scrolling="no"/>
			<frame src="<@s.url action="%{#attr.url}" />" scrolling="no" id="right" name="right"/>
		</frameset> 
	</frameset> 
<body> 
</body>
</html>