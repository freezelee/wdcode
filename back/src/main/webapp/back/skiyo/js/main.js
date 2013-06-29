/**
 * 给main框架页用
 */

/**
 * 文档加载事件
 */
$(function(){
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