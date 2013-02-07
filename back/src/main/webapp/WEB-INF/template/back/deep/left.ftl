<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
    <link href="${base }/back/${themeBack}/css/css.css" rel="stylesheet" type="text/css" /> 
    <@wd.js name="jquery"/>
    <script type="text/javascript" src="${base }/back/${themeBack}/js/js.js"></script>
    <style type="text/css">
body{
	font-family: "微软雅黑","Arial";background: #222;
}
.main{
	width: 210px;margin-top: -5px;margin-left: -5px;border-radius: 10px;
	padding: 4px 0px 1px 4px;
}
.logo{
	width: 90%;height: 100px;margin-top: 30px;margin-left: -8px;
}
.k1{
	margin-top: 0px;
}
.k1 li{
	list-style-type: none;line-height: 34px;z-index: -1;margin-left: -40px;font-size: 13px;
	width: 215px;cursor: pointer;
}
.a1{
	background: -moz-linear-gradient(top, #FFFFFF, #BFDDF6 50%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(30%,#555555),color-stop(100%,#444444));
	padding: 5px 134px 6px 20px;border: 1px solid #444444;color: white;
}
.default{
	background: -moz-linear-gradient(top, #f00, #BFDDF6 50%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#888),color-stop(100%,#666));
	color: white;border: 1px solid #333;text-shadow: 1px 1px 0px #444;
}
.k2{
	display: none;background: none;clear: both;z-index: 2;
}
.k2 li{
	background: none;line-height: 25px;
}
.k2 li a{
	text-decoration: none;line-height: 25px;padding: 5px 96px 6px 19px;background: none;color:white; 
}
.k2 div{
	background: -moz-linear-gradient(top, #f00, #BFDDF6 50%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#444),color-stop(100%,#333));
	width: 90%;border: 1px solid #333;margin-left: 1px;margin-top: 2px;color: white;
}
	</style>
<script type="text/javascript">
$(function(){
	$(".a1").click(function(){
		$(".a1").attr("class","a1");
		this.className += " default";
		$(".k2").slideUp(300);
		if($("#"+this.parentNode.childNodes[3].id+"").is(":hidden")){
			$("#"+this.parentNode.childNodes[3].id+"").slideDown(300);
		}
	});
	$(".k2 div").mouseenter(function(){
		$(this).animate({
			marginLeft: "10px",
		}, 200 );
	});
	$(".k2 div").mouseleave(function(){
		$(this).animate({
			marginLeft: "1px",
		}, 200 ); 
	});
});
</script>
</head>
<body>
<div class="main">
    	<ul class="k1">
    	<@s.iterator id="menu" value="%{#attr.query.list('menu','menuId',0)}" status="st">
    	<li>
   			<a class="a1"><@s.property value="name"/></a>
   			<ul id="L<@s.property value="#st.index+1"/>" class="k2">
   				<@s.iterator value="%{#attr.query.list('menu','menuId',#menu.id)}" id="m">
   					<li><div><a href="<@s.url action="%{#m.url}"/>" target="right"><@s.property value="name"/></a></div></li>
				</@s.iterator>    				
   			</ul>
   		</li> 
   		</@s.iterator>   	 
    	</ul>
    </div> 
</body>
</html>