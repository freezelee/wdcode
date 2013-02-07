<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
    <@wd.css name="datetimepicker"/>
    <@wd.js name="jquery"/>
   	<@wd.js name="datetimepicker"/>
    <script type="text/javascript" src="${base }/back/${themeBack}/js/js.js"></script>
</head>
<body> 
<@s.form validate="false" method="post" action="%{#attr.template.get('action')}">
	<table id="theme_table" cellpadding="2" cellspacing="1">
		<tr>
			<td class="td_title" colspan="18"><@s.text name="title"/></td>
		</tr>
		<tr>
			<td class="td_content" colspan="18">
 
				<table id="select_table">
					<tr>
						<@s.iterator id="temMap" value="%{#attr.template.get('search')}">
							<td class="td_show">${action.getText(temMap.get('text'))}</td> 
							<td class="td_input">
								<@wd.type value="#temMap" />
							</td>						
							<@s.if test="!#st.isOdd()"> 
								</tr>
								<tr>
							</@s.if>		
						</@s.iterator>
					</tr>
					<tr align="center">
						<td colspan="4">
							<@s.iterator id="temMap" value="#attr.template.get('submit')"> 
								<@wd.type value="#temMap" />
						</@s.iterator>				
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td class="td_content" colspan="18">&nbsp;
				<@s.actionerror /><@s.actionmessage /><@s.fielderror/>
			</td>
		</tr>
		<tr> 
			<td width="5%" class="td_head">
				<@s.checkbox id="all" name="all"/>
			</td>
			<td class="td_head"><@s.text name="key" /></td>	
			<@s.iterator id="th" value="%{#attr.template.get('th').split(';')}"> 
						<td class="td_head">${action.getText(th)}</td>
					</@s.iterator> 	 
		</tr> 
		<@s.iterator value="#attr.entitys">
		<tr>		
			<td class="td_content">
				<@s.checkbox cssClass="checkbox" name="keys" fieldValue="%{key}" value="false" />
			</td>
			<td class="td_content">
			<@s.if test="#attr.template.get('edit')==''">
				<@s.property value="key"/>
			</@s.if>
			<@s.else>
				<a href="<@s.url action="%{#attr.template.get('edit')}"><@s.param name="entity.key" value="key"/></@s.url>"><@s.property value="key"/></a>
			</@s.else>			
			</td>			
			 <@s.iterator id="td" value="%{#attr.template.get('td').split(';')}">  
						<td class="td_content">${stack.findString(td)!""}</td>
					</@s.iterator>   	
		</tr>
		</@s.iterator>
		<tr>
			<td colspan="18" class="td_content"><@wd.pagination/></td>
		</tr>
	</table>
</@s.form> 
</body>
</html>
