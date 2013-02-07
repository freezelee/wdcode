<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
    <@wd.js name="jquery"/>
    <script type="text/javascript" src="${base }/back/${themeBack}/js/js.js"></script>
</head>
<body> 
<@s.form validate="true" method="post" action="%{#attr.template.get('action')}" enctype="%{#attr.template.get('enctype')}">
<@s.iterator var="temHidden" value="#attr.template.get('hidden')">
	<@s.hidden id="%{#temHidden.get('id')}" name="%{#temHidden.get('name')}" value="#temHidden.get('value')"/>
</@s.iterator>
	<table id="theme_table" cellpadding="2" cellspacing="1">
		<tr>
			<td class="td_title" colspan="4"><@s.text name="title"/></td>
		</tr>
		<tr>
			<td class="td_content" colspan="4">&nbsp;
				<@s.actionerror /><@s.actionmessage /><@s.fielderror/>
			</td>
		</tr> 
		<tr>
			 <@s.iterator var="temMap" value="#attr.template.get('field')" status="st"> 
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
		<tr>
			<td class="td_show">&nbsp;</td>
			<td class="td_input" colspan="3">
				<@s.iterator var="temMap" value="#attr.template.get('submit')"> 
						<@wd.type value="#temMap" />
				</@s.iterator>
			</td>
		</tr>
	</table>
</@s.form>
</body>
</html>