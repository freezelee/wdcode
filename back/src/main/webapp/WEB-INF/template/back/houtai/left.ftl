<html>
<head>
<title><@s.text name="titles" /></title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(${base }/back/${themeBack}/images/left.gif);
}
-->
</style>
<link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" />
</head>
<SCRIPT language=JavaScript>
function tupian(idt){
    var nametu="xiaotu"+idt;
    var tp = document.getElementById(nametu);
    tp.src="${base }/back/${themeBack}/images/ico05.gif";//图片ico04为白色的正方形
	
	for(var i=1;i<100;i++)
	{ 
	  if(i!=idt*1)
	  {
	    var tp2=document.getElementById('xiaotu'+i);
		if(tp2!=undefined)
	    {tp2.src="${base }/back/${themeBack}/images/ico06.gif";}//图片ico06为蓝色的正方形
	  }
	}
}

function list(idstr){
	var name1="subtree"+idstr;
	var name2="img"+idstr;
	var objectobj=document.all(name1);
	var imgobj=document.all(name2);
	
	
	//alert(imgobj);
	
	if(objectobj.style.display=="none"){
		for(var i=1;i<100;i++){
			var name3="img"+i;
			var name="subtree"+i;
			var o=document.all(name);
			if(o!=undefined){
				o.style.display="none";
				var image=document.all(name3);
				//alert(image);
				image.src="${base }/back/${themeBack}/images/ico04.gif";
			}
		}
		objectobj.style.display="";
		imgobj.src="${base }/back/${themeBack}/images/ico03.gif";
	}
	else{
		objectobj.style.display="none";
		imgobj.src="${base }/back/${themeBack}/images/ico04.gif";
	}
}

</SCRIPT>

<body> 
<table width="198" border="0" cellpadding="0" cellspacing="0" class="left-table01">
  <tr>
    <TD>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		  <tr>
			<td width="207" height="55" background="${base }/back/${themeBack}/images/nav01.gif">
				<table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
				  <tr>
					<td width="25%" rowspan="2"><img src="${base }/back/${themeBack}/images/ico02.gif" width="35" height="35" /></td>
					<td width="75%" height="22" class="left-font01">您好，<span class="left-font02">${token.username}</span></td>
				  </tr>
				  <tr>
					<td height="22" class="left-font01">
						[&nbsp;<a href="<@s.url action="logout"/>" target="_top" class="left-font01">退出</a>&nbsp;]</td>
				  </tr>
				</table>
			</td>
		  </tr>
		</table>
		
<@s.iterator id="menu" value="%{#attr.query.list('menu','menuId',0)}">
	<TABLE width="100%" border="0" cellpadding="0" cellspacing="0" class="left-table03">
          <tr>
            <td height="29">
				<table width="85%" border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td width="8%"><img name="img<@s.property value="%{#menu.id}"/>" id="img8" src="${base }/back/${themeBack}/images/ico04.gif" width="8" height="11" /></td>
						<td width="92%">
								<a href="javascript:" target="mainFrame" class="left-font03" onClick="list('<@s.property value="%{#menu.id}"/>');" ><@s.property value="%{#menu.name}"/></a></td>
					</tr>
				</table>
			</td>
          </tr>		  
        </TABLE>
		<table id="subtree<@s.property value="%{#menu.id}"/>" style="DISPLAY: none" width="80%" border="0" align="center" cellpadding="0" 
				cellspacing="0" class="left-table02">
					<@s.iterator value="%{#attr.query.list('menu','menuId',#menu.id)}" id="m">
						<tr>
				  <td width="9%" height="20" ><img id="xiaotu<@s.property value="%{#m.id}"/>" src="${base }/back/${themeBack}/images/ico06.gif" width="8" height="12" /></td>
				  <td width="91%"><a href="<@s.url action="%{#m.url}"/>" target="mainFrame" class="left-font03" onClick="tupian('<@s.property value="%{#m.id}"/>');"><@s.property value="#m.name"/></a></td>
				</tr>
						</@s.iterator>		 
      </table> 
</@s.iterator> 
	  </TD>
  </tr>
  
</table>
</body>
</html>
