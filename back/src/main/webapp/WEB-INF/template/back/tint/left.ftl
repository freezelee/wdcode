<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>  
    <@wd.js name="jquery"/> 
	<style type="text/css">
body{
	background: #E8F6FF;font-family: "微软雅黑","Arial";
}
.main{
	width: 210px;margin-top: -5px;margin-left: -5px;border-radius: 10px;background: #FFF;
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
	background: -webkit-gradient(linear, left top, left bottom, color-stop(30%,#FFFFFF),color-stop(100%,#BFDDF6));
	padding: 5px 134px 6px 20px;
	border: 1px solid #B7CCE7;
}
.default{
	background: -moz-linear-gradient(top, #f00, #BFDDF6 50%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#ACD8F1),color-stop(100%,#0490DD));
	color: white;border: 1px solid #0490DD;text-shadow: 1px 1px 0px #444;
}
.k2{
	display: none;background: none;clear: both;z-index: 2;
}
.k2 li{
	background: none;line-height: 25px;
}
.k2 li a{
	text-decoration: none;line-height: 25px;padding: 5px 96px 6px 19px;background: none;color: black;
}
.k2 div{
	width: 90%;border: 1px solid #ADB9C2;margin-left: 1px;margin-top: 2px;
	background: -moz-linear-gradient(top, #f00, #BFDDF6 50%); /* firefox */
	background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#FFF),color-stop(100%,#D8DFE3));
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
    	<#list action.getMenus(0) as menu>
    	<li>
   			<a class="a1<#if menu_index == 0> default</#if>">${menu.name}</a>
   			<ul id="L${menu_index}" class="k2">
   				<#list action.getMenus(menu.id) as m>   				
   					<li><div><a href="<@s.url action='${m.url}'/>" target="right">${m.name}</a></div></li>
				</#list>    				
   			</ul>
   		</li> 
   		</#list> 	 
    	</ul>
    </div> 
</body>
</html>