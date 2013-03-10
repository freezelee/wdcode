<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="Author" content="SHOP Team" />
<meta name="Copyright" content="SHOP" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/back/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/back/css/admin.css" rel="stylesheet" type="text/css" />
<@wd.css name="datetimepicker" /> 
</head>
<body class="list"> 
	<div class="bar">
		${action.getText("title")}&nbsp;总记录数: ${pager.totalSize} (共${pager.totalPage}页)
		<@s.actionerror /><@s.actionmessage /><@s.fielderror/>
	</div>
	<div class="body">
		<@s.form id="pagerForm" validate="false" method="post" action="%{#attr.template.get('action')}"> 
			<div class="listBar"> 
				<#if template.get('submit')??>
					<#list template.get('submit') as temMap>					
						<@wd.type field=temMap/>
					</#list> 
				</#if>
				<#if template.get('search')??>
					<#list template.get('search') as temMap> 
						${action.getText(temMap.get("text"))}:
						<@wd.type field=temMap/>
					</#list>  
				</#if> 
			</div>
			<table id="listTable" class="listTable">
				<#assign url=template.get('url')!''> 
				<#if url?? && url != ''>
					<#include url>				
				<#else> 
					<tr>
					<th class="check">
						<@s.checkbox id="all" name="all" /> 
					</th> 
					<#list template.get('th').split(';') as th> 
						 <th>
							<a href="#" class="sort" name="name" hidefocus>${action.getText(th)}</a>
						</th> 
					</#list>  
					<th>
						<span>操作</span>
					</th>
					</tr>			
					<#assign edit=(template.get('edit')!)=='true'>	
					<#list entitys as e> 
						<tr>
							<td> 
								<@s.checkbox cssClass="checkbox" name="keys" fieldValue="${e.key}"/>
							</td>
							<#list template.get('td').split(';') as td>  
								<td>${(e[td])!?string}</td>
							</#list> 
							<td>
								<#if edit> 
									<@s.a action="${module}_theme_edit" ><@s.param name="entity.key" value="${e.key}"/>[编辑]</@s.a>
								</#if>																					
							</td>
						</tr>
					 </#list>	
					 </table>
					<#if pager?? && pager?size == 0>			
						<div class="noRecord">没有找到任何记录!</div>
					</#if>  
					<div class="pagerBar"> 
						<div class="pager">
							<@wd.pagination/>
						</div>
					</div>
				</#if>  
		</@s.form>
	</div>
</body>
<@wd.js name="jquery" /> 
<@wd.js name="datetimepicker" /> 
<script type="text/javascript" src="${base}/back/js/base.js"></script>
<script type="text/javascript" src="${base}/back/js/admin.js"></script>
<script type="text/javascript" src="${base}/back/js/js.js"></script>
</html>