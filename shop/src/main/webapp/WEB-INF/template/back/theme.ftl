<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${action.getText("title")} - Powered By SHOP++</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<link rel="icon" href="favicon.ico" type="image/x-icon" /> 
<link href="${base}/back/css/css.css" rel="stylesheet" type="text/css" />
<link href="${base}/back/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/back/css/admin.css" rel="stylesheet" type="text/css" /> 
</head>
<body class="input"> 
	<div class="bar">
		${action.getText(template.get("title"))}
		<@s.actionmessage/>
		<@s.fielderror />
		<@s.actionerror/>
	</div> 
	<div class="body">
		<@s.form validate="true" method="post" action="${template.get('action')!}" enctype="${template.get('enctype')!}"> 
			<#if template?? && template.get('hidden')??>
				<#list template.get('hidden') as temHidden> 
					<@s.hidden name="${temHidden.get('name')}" />
				</#list>
			</#if>		 
			<#if template.get('tabs')??>
			<#assign tabs=template.get('tabs').split(';')> 
	 			<ul id="tab" class="tab">
	 				<#list tabs as tab>					
					<li> 
						<input type="button" value="${action.getText(tab)}" hidefocus /> 
					</li>
	 				</#list>
	 			</ul>
	 			<#list tabs as tab>
	 				<#assign fields=template.get(tab).get(0) >
					<div class="tabContent">
						<#include "type.ftl" > 
					</div>	 
				</#list>
	 		<#else>	 		  
	 		<#assign fields=template>
	 		<#include "type.ftl" >
	 		</#if>   
			<div class="buttonArea">
				<#list template.get('submit')! as temMap> 
					<@wd.type field=temMap/> 
				</#list>
			</div>
		</@s.form> 
	</div>
</body>
<@wd.js name="jquery" /> 
<@wd.js name="jquery.tools" />
<@wd.editor name="kindeditor" />
<script type="text/javascript" src="${base}/back/js/base.js"></script>
<script type="text/javascript" src="${base}/back/js/admin.js"></script>
<script type="text/javascript" src="${base}/back/js/js.js"></script>
<script type="text/javascript"> 
$().ready(function() { 
	var $tab = $("#tab");	// Tab效果
	$tab.tabs(".tabContent", { 
		tabs: "input" 
	});
});
</script>
</html>