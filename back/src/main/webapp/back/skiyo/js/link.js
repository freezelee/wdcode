$(function(){
	//公司部门联动	
	$.ajaxLink("back/ajax_depaByComp.htm","comp","depa");
	$.ajaxLink("back/ajax_depaByComp.htm","compT","depa",true);
	
	//角色操作联动
	$.ajaxLink("back/ajax_operByRole.htm","role",new Array("oper","notOper"));
});