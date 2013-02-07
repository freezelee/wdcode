<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head> 
<link rel="stylesheet" rev="stylesheet" href="${base }/back/${themeBack}/css/style.css" type="text/css" media="all" />
<style type="text/css">
<!--
.atten {font-size:12px;font-weight:normal;color:#F00;}
-->
</style>
</head>

<body class="ContentBody"> 
<@s.form validate="true" method="post" action="%{#attr.template.get('action')}" enctype="%{#attr.template.get('enctype')}">
<@s.iterator id="temHidden" value="#attr.template.get('hidden')">
	<@s.hidden name="%{#temHidden.get('name')}"/>
</@s.iterator>
<div class="MainDiv">
<table width="99%" border="0" cellpadding="0" cellspacing="0" class="CContent">
  <tr>
      <th class="tablestyle_title" >${action.getText('title')}</th>
  </tr>
  <tr>
    <td class="CPanel"> 
		<table border="0" cellpadding="0" cellspacing="0" style="width:100%"> 
		<TR>
			<TD width="100%">
				<fieldset style="height:100%;">
				<legend>${action.getText('title')}</legend>
				<@s.actionerror /><@s.actionmessage /><@s.fielderror/>								
					  <table border="0" cellpadding="2" cellspacing="1" style="width:100%">
					 <tr> 
					 <@s.iterator id="temMap" value="#attr.template.get('field')" status="st"> 
				<td nowrap align="right" width="13%">${action.getText(temMap.get('text'))}</td> 
				<td width="41%">
					<@wd.type field=temMap/> 
				</td> 
				<@s.if test="!#st.isOdd()"> 
						</tr>
						<tr>					
				</@s.if>					
			</@s.iterator>
			 </tr> 
					  </table>
			 <br />
				</fieldset>			</TD>
		</TR>
		
		</TABLE>
	
	
	 </td>
  </tr> 
		<TR>
			<TD colspan="2" align="center" height="50px">
				<#list template.get('submit')! as temMap> 
					<@wd.type field=temMap/> 
				</#list>
		</TR>
		</TABLE>
	
	
	 </td>
  </tr> 
  </table>

</div>
</@s.form>
</body>
</html>
