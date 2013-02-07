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
<@s.form id="fom" validate="false" method="post" action="%{#attr.template.get('action')}">
	<table id="theme_table" cellpadding="2" cellspacing="1">
		<tr>
			<td class="td_title" colspan="18">${action.getText(template.get('title'))}</td>
		</tr>
		<tr>
			<td class="td_content" colspan="18">
				<table id="select_table">
					<tr>
						<@s.iterator id="temMap" value="%{#attr.template.get('search')}"> 
							<td class="td_show">${action.getText(temMap.get('text'))}</td> 
							<td class="td_input">
								<@wd.type value="#temMap"/> 
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
						<@wd.type value="#temMap"/> 
						</@s.iterator>				
						</td>
					</tr>
				</table> 	
			</td>
		</tr>
		<tr>
			<td class="td_content" colspan="18">&nbsp;<@s.actionerror /><@s.actionmessage /><@s.fielderror/></td>
		</tr>
		<tr> 
			<td width="5%" class="td_head">
				<@s.checkbox id="all" name="all"/>
			</td>
			<td width="5%" class="td_head"><@s.text name="id" /></td>			
			<@s.iterator id="th" value="%{#attr.template.get('th').split(';')}"> 
				<td class="td_head">${action.getText(th)}</td>
			</@s.iterator>
		</tr>
		<@s.iterator value="entitys">
		<tr>		
			<td class="td_content">
				<@s.checkbox cssClass="checkbox" name="ids" fieldValue="%{id}" value="false" />
			</td>
			<td class="td_content"><a href="<@s.url action="%{#attr.temTempls.get('edit')}"><@s.param name="entity.id" value="id"/></@s.url>"><@s.property value="id"/></a></td>			
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
