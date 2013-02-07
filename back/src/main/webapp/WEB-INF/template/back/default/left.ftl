<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
    <link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" /> 
    <@wd.js name="jquery"/>
    <script type="text/javascript" src="${base }/back/${themeBack}/js/js.js"></script>
</head>
<body id="left_body">
<table>
	<tr>
		<td>
			<table id="title_table">
				<tr><td><img src="${base }/back/${themeBack}/images/title.gif"></td></tr>
				<tr>
					<td id="exit">
						<a href="<@s.url action="welcome"/>" target="main_frame"><@s.text name="main" /></a>|
						<a href="<@s.url action="logout"/>" target="_parent"><@s.text name="exit" /></a>
	      			</td>
	      		</tr>	
				<tr><td id="userInfo">
				    <table>
				      <tr>
				        <td><a href="<@s.url action="userInfo_theme_changeInfo"/>" target="main_frame"><@s.text name="edit" /><@s.text name="info" /></a></td>
				        <td><a href="<@s.url action="user_theme_changePwd"/>"  target="main_frame"><@s.text name="edit" /><@s.text name="pwd" /></a></td>
				      </tr>
				    </table>
			    </td></tr>
			</table>
		</td>
	</tr> 
<@s.iterator id="menu" value="%{#attr.query.list('menu','menuId',0)}">
	<tr><td>
		<table class="menu_main_table" cellspacing="1">
			<tr><td class="menu_main_td"><@s.property value="%{#menu.name}"/></td></tr>
			<tr><td>
				<div class="hide">
					<table class="menu_table" cellspacing="1">
						<@s.iterator value="%{#attr.query.list('menu','menuId',#menu.id)}" id="m">
							<tr><td class="menu_td">
								<a href="<@s.url action="%{#m.url}"/>" target="main_frame"><@s.property value="#m.name"/></a>
							</td></tr>	
						</@s.iterator>					
					</table>
				</div>
			</td></tr>
		</table>
	</td></tr>
</@s.iterator>
</table>
</body>
</html>