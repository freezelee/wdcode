<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
   <link href="${base}/back/css/base.css" rel="stylesheet" type="text/css" />
   <link href="${base}/back/css/admin.css" rel="stylesheet" type="text/css" />
</head>

<body class="menu"> 
	<div class="body"> 
		<#assign menus=token.getMenus()>
		<#list menus.get(entity.id) as menu>
			<dl>
				<dt>
					<span>${menu.name}</span>
				</dt>
				<#list menus.get(menu.id) as m>
					<dd>
						<a href="<@s.url action='${m.url}'/>" target="main_frame">${m.name}</a>
					</dd>
				</#list> 		
			</dl>
		</#list> 
	</div>
</body>
</html>