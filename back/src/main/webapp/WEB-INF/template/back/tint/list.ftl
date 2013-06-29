<#import "/wd.ftl" as wd>
<!DOCTYPE html>
<html>
  <head>    
    <link rel="stylesheet" type="text/css" href="${base }/back/${themeBack}/css/showMsg.css" media="screen">
    <link rel="stylesheet" href="${base }/back/${themeBack}/css/all.css">
    <@wd.css name="datetimepicker"/>
    <@wd.js name="jquery"/>
   	<@wd.js name="datetimepicker"/> 
    <script type="text/javascript" src="${base }/back/${themeBack}/js/showMsg.js"></script>
  </head>
  
  <body>
	<div class="title0">
		<div class="title1">
		</div>
		<div class="title2">
		</div>
		<div class="title3">
			目前位置：1 → 2
		</div>
	</div>  
	<@s.form method="post" action="%{#attr.template.get('action')}">	
  	<div class="search">
  		<@s.iterator id="temMap" value="%{#attr.template.get('search')}">			
				<@wd.type value="#temMap" />&nbsp;				
		</@s.iterator>  		
  	</div> 
    <table class="table">
    	<tr>
    		<td style="width: auto;">
		    	<@s.iterator id="temMap" value="#attr.template.get('submit')"> 
						<@wd.type value="#temMap" />
				</@s.iterator>
    		</td>
    	</tr>
    	<tr>
			<td class="title" colspan="7">${action.getText(template.get('title'))}</td>
		</tr>
    	<tr class="tabody">
    		<td>
    		<div style="display: none;" id="list_div">
    			<table class="bl_tab" cellspacing="1">
			    	<tr class="kind">
			    		<td width="12%">
			    			<div class="iphoneDiv" id="checkall">
								<div class="iphoneON" id="iphoneON">ON</div>
								<div class="iphoneOFF">OFF</div>
							</div>全选
						</td>
			    		<@s.iterator id="th" value="%{#attr.template.get('th').split(';')}"> 
						<td>${action.getText(th)}</td>
						</@s.iterator> 	
			    		<td>操作</td>
			    	</tr>
			    	<@s.iterator value="#attr.entitys">
			    	<tr class="list">
			    		<td>
							<div class="iphoneDiv">
								<div class="iphoneON ch1" id="ch1">ON</div>
								<div class="iphoneOFF ch1">OFF</div>
							</div>
						</td>
			    		<@s.iterator id="td" value="%{#attr.template.get('td').split(';')}">  
						<td>${stack.findString(td)!""}</td>
						</@s.iterator> 
			    		<td>编辑&nbsp;|&nbsp;查看&nbsp;|&nbsp;删除</td>
			    	</tr>
			    	</@s.iterator> 
			    	<tr class="foot">
			    		<td colspan="7" align="right">
			    		<@wd.pagination/>
			    		</td>
			    	</tr>
    			</table>
    		</div>
    		</td>
    	</tr>
    </table>
    </@s.form>
  </body>
</html>