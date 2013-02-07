<#import "/wd.ftl" as wd>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${base }/back/${themeBack}/css/showMsg.css" media="screen">
    <link rel="stylesheet" href="${base }/back/${themeBack}/css/all.css">
    <@wd.css name="jquery-ui"/> 
    <@wd.js name="jquery"/>
    <@wd.js name="jquery-ui"/> 
    <style type="text/css">
		body{
			font-family: "微软雅黑","Arial";background-image: url("${base }/back/${themeBack}/img/h3.png");
		}
		table{
			padding-left: 30px;
		}
		table td{
			font-size: 13px;
		}
		input{
			background: -moz-linear-gradient(top, #EAEAEA, #FFF 50%); /* firefox */
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#EAEAEA),color-stop(50%,#FFF));
			border: 1px solid #ADABB3;line-height: 20px;z-index: 1;
		}
		.boxB1{
			background: -moz-linear-gradient(top, #f00, #BFDDF6 50%); /* firefox */
			background: -webkit-gradient(linear, left top, left bottom, color-stop(0%,#888),color-stop(100%,#444));
			border-top-left-radius: 5px;border-top-right-radius: 5px;border: 1px solid #444;margin-top: 10px;
			padding: 5px 3px 5px 20px;font-size: 14px;color: white;box-shadow: 1px 0px 0px #9FC4FF;
		}
		.boxB2{
			height: 450px;border: 1px solid #888;border-top-color: white;background-color: #eee;
			border-bottom-left-radius: 5px;border-bottom-right-radius: 5px;box-shadow: 1px 1px 0px #BBB;
		}
		.B_title{
			font-size: 13px;padding: 15px 5px 5px 50px;
		}
		.B_line{
			background: -moz-linear-gradient(left, #666, #FFF 99%); /* firefox */
			background: -webkit-gradient(linear, left top, right top, color-stop(0%,#666),color-stop(99%,#FFF)); /* webkit */
			width: 75%;height: 2px;margin-left: 30px;margin-bottom: 5px;
		}
		.right{
			text-align: right;width: 93px;
		}
		.jiguanD,.minZuD,.xueLiD{
			position: absolute;display: none;border: 1px solid #999;z-index: 9;background-color: white;border-radius: 5px;
		}
		.jiguanI{
			position: relative;
		}
		.jiguanT{
			padding-left: 0px;
		}
		.jiguanT td{
			width: 80px;text-align: center;cursor: pointer;border-radius: 4px;
		}
		.file_td{
			position:relative;width: 40%;
		}
		.txt{width:100px;} 
		.btn{ background-color:#FFF; border: 1px solid #ADABB3;height:24px; width:50px;font-family: "微软雅黑","Arial";}
		.file{ position:absolute; top:0; left:0px; height:24px; filter:alpha(opacity:0);opacity: 0;width:155px }
		.over{
			background-color: #ddd;
		}
	</style>
	<script type="text/javascript">
		var flag = true;
		$(function(){
			$(".addbody").slideDown(300);
			$( "#datepicker,#datepicker2").datepicker();
			$("body").click(function(){
				if(flag){
					$(".jiguanD,.minZuD,.xueLiD").fadeOut(200);
				}else{
					flag = true;
				}
			});
			$(".jiguanT td").mouseover(function(){
				$(this).addClass("over");
			}).mouseout(function(){
				$(this).removeClass("over");
			});
			$(".jiguanT td").click(function(){
				this.parentNode.parentNode.parentNode.parentNode.parentNode.childNodes[1].value = this.innerHTML;
			});
		});
		function shouJG(){
			flag = false;
			$(".minZuD,.xueLiD").fadeOut(200);
			$(".jiguanD").fadeIn(200);
		}
		function shouMZ(){
			flag = false;
			$(".jiguanD,.xueLiD").fadeOut(200);
			$(".minZuD").fadeIn(200);
		}
		function shouXL(){
			flag = false;
			$(".jiguanD,.minZuD,").fadeOut(200);
			$(".xueLiD").fadeIn(200);
		}
	</script>
</head>
<body> 
<@s.form validate="true" method="post" action="%{#attr.template.get('action')}" enctype="%{#attr.template.get('enctype')}">
<@s.iterator var="temHidden" value="#attr.template.get('hidden')">
	<@s.hidden id="%{#temHidden.get('id')}" name="%{#temHidden.get('name')}" value="#temHidden.get('value')"/>
</@s.iterator> 
	<div class="boxA">
		<div class="boxB1">
			<@s.text name="title"/>
		</div>
		<div class="boxB2">
			<div class="B_title"><@s.text name="title"/></div>
			<div class="B_line">
			<@s.actionerror /><@s.actionmessage /><@s.fielderror/>
			</div> 
			<table class="table"> 
			<tr>
			<@s.iterator var="temMap" value="#attr.template.get('field')" status="st">
					<td class="right">${action.getText(temMap.get('text'))}:</td>
					<td><@wd.type value="#temMap" /></td>  
				<@s.if test="!#st.isOdd()"> 
						</tr>
						<tr>					
				</@s.if>
			</@s.iterator>
			</tr> 
			<tr>
				<td>&nbsp;</td>
				<td>
				<@s.iterator var="temMap" value="#attr.template.get('submit')">
					<@wd.type value="#temMap" />
				</@s.iterator>
				</td>
			</tr>
			</table> 
	</div> 
	</div>   
</@s.form>
</body>
</html>