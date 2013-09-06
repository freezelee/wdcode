<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>${action.getText('titles')}</title>    
	<link href="${base}/back/css/css.css" rel="stylesheet" type="text/css" />
	<link href="${base}/back/css/base.css" rel="stylesheet" type="text/css" />
	<link href="${base}/back/css/admin.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
html, body {
	height: 100%;
	overflow:hidden;
}
-->
</style>
</head>
<body>
<table id="main_table" cellpadding="0" cellspacing="0">
	<tr>  
		<td id="top_td" colspan="3"> 
			<iframe id="top_frame" name="top_frame" src="<@s.url action="top" />" frameBorder="0"></iframe>
		</td>
	</tr>
	<tr>
		<td id="left_td" >
			<iframe id="left_frame" name="left_frame" src="<@s.url action='menu'><@s.param name='entity.id' value='6' /></@s.url>" frameBorder="0"></iframe>
		</td>
		<td class="middle">
			<div id="main" class="main leftArrow"></div>
		</td>
		<td id="main_td">
			<iframe id="main_frame" name="main_frame" src="<@s.url action="%{#attr.url}" />" frameBorder="0"></iframe>
		</td>
	</tr>
</table>
</body>
<wd:js name="jquery" /> 
<script type="text/javascript" src="${base }/back/js/js.js"></script>
<script type="text/javascript">
$().ready(function() { 
	var $main = $("#main"); 
	$main.click( function() {
		var mainFrameset = window.parent.window.document.getElementById("mainFrameset");
		if(mainFrameset.cols == "130,6,*") {
			mainFrameset.cols = "0,6,*";
			$main.removeClass("leftArrow");
			$main.addClass("rightArrow");
		} else {
			mainFrameset.cols = "130,6,*";
			$main.removeClass("rightArrow");
			$main.addClass("leftArrow");
		}
	});
});
</script>
</html>