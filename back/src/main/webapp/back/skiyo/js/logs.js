$(function(){
	//清空
	$("#trun").click(function(){
		if(confirm($("#trunok").val())){
			$("#currentPage").attr("name","");
			if($(".checkbox").size() == 1){
				$(".checkbox").attr("checked","true");			
			}
		}else{
			return false;
		}						
	});
	
	//开始日期
	$("#startDate").calendar();	
	//结束日期
	$("#endDate").calendar();	
});