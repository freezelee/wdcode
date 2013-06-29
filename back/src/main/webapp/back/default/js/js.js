/**
 * 主要页面的JS
 */

/**
 * 文档加载事件
 */
$( function() {	
	//全选框
	$("#all").click(function(){
		$(".checkbox").attr("checked",$("#all").attr("checked")==undefined?false:true); 
	});
	
	//删除
	$("#dels").click(function(){
		//验证
		if($(".checkbox[checked]").size() > 0){
			if(confirm($(this).attr("accesskey"))){
				$("#currentPage").attr("name",""); 
			}else{
				return false;
			}
		}else{
			alert($(this).attr("title"));
			return false;
		}
	});
	
	//显示
	$("#show").click(function(){		
		$("#hide").show(600);
		return false;
	});
	
	//元素动画效果时间
	var speed = 500;
	//给菜单TD 添加单击事件 
	$(".menu_main_td").click(function(){
		//获得点击的隐藏菜单
		var obj = $(this).parent().next().find(".hide");
		//判断是否隐藏的
		if(obj.css("display") == "none"){
			//隐藏所有菜单
			$(".hide").hide(); 
			//隐藏的	显示	
			obj.slideDown(speed);
		}else{
			//显示的隐藏
			obj.hide();
		}		
	});
	
	//公司部门联动	
	//$.ajaxLink("back/ajax_depaByComp.htm","comp","depa");
	//$.ajaxLink("back/ajax_depaByComp.htm","compT","depa",true);
	
	//角色操作联动
	//$.ajaxLink("back/ajax_operByRole.htm","role",new Array("oper","notOper"));
	
	//清空
	$("#trun").click(function(){
		if(confirm($(this).attr("title"))){
			$("#currentPage").attr("name","");
			if($(".checkbox").size() == 1){
				$(".checkbox").attr("checked","true");			
			}
		}else{
			return false;
		}						
	});
	
	//开始日期
	//$("#startDate").calendar();	
	//结束日期
	//$("#endDate").calendar();	
	
	//给切换左边菜单显示/隐藏td添加事件
	$("#left_exp").click(function(){
		//获得标识对象
		var obj = $("#switch");
		//获得标识为3
		if(obj.text() == 3){
			//隐藏菜单
			$("#left_td").hide();
			//设置标识为4
			obj.text(4);
		}else{
			//显示菜单
			$("#left_td").show();
			//设置标识为3
			obj.text(3);
		}
	});
});