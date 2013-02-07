<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>  
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.tabfont01 {	
	font-family: "宋体";
	font-size: 9px;
	color: #555555;
	text-decoration: none;
	text-align: center;
}
.font051 {font-family: "宋体";
	font-size: 12px;
	color: #333333;
	text-decoration: none;
	line-height: 20px;
}
.font201 {font-family: "宋体";
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.button {
	font-family: "宋体";
	font-size: 14px;
	height: 37px;
}
html { overflow-x: auto; overflow-y: auto; border:0;} 
-->
</style>

<link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/JavaScript">

</script>
<link href="${base }/back/${themeBack}/css/style.css" rel="stylesheet" type="text/css" />
<@wd.css name="datetimepicker" /> 
<@wd.js name="jquery" /> 
<@wd.js name="datetimepicker" /> 
</head>
<SCRIPT language=JavaScript>
function sousuo(){
	window.open("gaojisousuo.htm","","depended=0,alwaysRaised=1,width=800,height=510,location=0,menubar=0,resizable=0,scrollbars=0,status=0,toolbar=0");
}
function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "keys"){
			obj[i].checked = true;
		}
	}
}

function unselectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "keys"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}

function link(){
    document.getElementById("fom").action="addrenwu.htm";
   document.getElementById("fom").submit();
}

</SCRIPT> 
<body> 
<@s.form id="fom" validate="false" method="post" action="%{#attr.template.get('action')}">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  
  <tr>
    <td height="30">      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="62" background="${base }/back/${themeBack}/images/nav04.gif">
            
		   <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		    <tr>
			  <td width="21"><img src="${base }/back/${themeBack}/images/ico07.gif" width="20" height="18" /></td>
			  <td width="538">  
			  <@s.iterator id="temMap" value="%{#attr.template.get('search')}">
				<span class="newfont06">${action.getText(temMap.get('text'))}</span> 
				<@wd.type field=temMap/>  	
			  </@s.iterator> 
			</td> 
		    </tr>
          </table></td>
        </tr>
    </table></td></tr>
  <tr>
    <td><table id="subtree1" style="DISPLAY: " width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          	 <tr>
               <td height="20"><span class="newfont07">选择：<a href="#" class="right-font08" onclick="selectAll();">全选</a>-<a href="#" class="right-font08" onclick="unselectAll();">反选</a></span>
               <@s.iterator id="temMap" value="#attr.template.get('submit')">  
						<@wd.type field=temMap/> 	
				</@s.iterator>
				</td>
          	 </tr>
              <tr>
                <td height="40" class="font42">
				<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#464646" class="newfont03">
				 <tr class="CTitle" >
                    	<td height="22" colspan="7" align="center" style="font-size:16px">${action.getText(template.get('title'))}</td>
                  </tr>  
                  <tr bgcolor="#EEEEEE">
				    <td width="4%" align="center" height="30">选择</td> 
				    <@s.iterator id="th" value="%{#attr.template.get('th').split(';')}"> 
						<td align="center">${action.getText(th)}</td>
					</@s.iterator> 
					<td width="12%">操作</td>
                  </tr>
                  <@s.iterator value="#attr.entitys">
                  	<tr bgcolor="#FFFFFF">
				    <td height="20">
				    <@s.checkbox cssClass="checkbox" name="keys" fieldValue="%{key}" value="false" />
				    </td> 
				    <@s.iterator id="td" value="%{#attr.template.get('td').split(';')}">  
						<td align="center">${stack.findString(td)!""}</td>
					</@s.iterator>                
                    <td align="center">
                    <@s.if test="%{#attr.template.get('edit')}">
                    	<@s.a action="%{#attr.module}_theme_edit" ><@s.param name="entity.key" value="key"/>编辑|</@s.a>
                    </@s.if>                    
                    <@s.if test="%{#attr.template.get('del')}">
                    	<@s.a action="%{#attr.module}_del" ><@s.param name="entity.key" value="key"/>删除</@s.a>                    	
                    </@s.if>					
                    </td>
                  </tr>
                  </@s.iterator>
            </table></td>
        </tr>
      </table>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="6"><img src="${base }/back/${themeBack}/images/spacer.gif" width="1" height="1" /></td>
        </tr>
        <tr>
          <td height="33">
          	<@wd.pagination />
          </td>
        </tr>
      </table></td>
  </tr>
</table>
</@s.form>
</body>
</html>