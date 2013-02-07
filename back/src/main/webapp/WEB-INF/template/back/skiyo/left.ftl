<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@s.text name="welcome" /><@s.text name="use" /><@s.text name="title" /></title>	
<@wd.js name="jquery"/>
<script type="text/javascript" src="${base }/back/${themeBack}/js/left.js"></script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(${base }/back/${themeBack}/images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
	border:solid 1px #ffffff;
}
#navigation li li a:hover {
	background:url(${base }/back/${themeBack}/images/tab_bg.gif) repeat-x;
	border:solid 1px #adb9c2;
}
-->
</style>
</head>
<body id="left_body">
<div>
	<ul id="navigation"> 
		<@s.iterator id="menu" value="%{#attr.query.list('menu','menuId',0)}">
			<li>
				<a class="head"><@s.property value="%{#menu.name}"/></a>
				<ul style="display: none;">
					<@s.iterator value="%{#attr.query.list('menu','menuId',#menu.id)}" id="m"> 
					<li>
						<a href="<@s.url action="%{#m.url}"/>" target="main_frame"><@s.property value="#m.name"/></a>
					</li>
					</@s.iterator>
				</ul>
			</li>			
		</@s.iterator>
	</ul>
</div>
</body>
</html>