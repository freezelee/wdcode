	$(function(){
		$("tr").each(function(i){this.style.backgroundColor = ["#EFEFEF","#FFFFFF"][i%2];});
		$(".list").mouseover(function(){
			$(this).addClass("over");
		}).mouseout(function(){
			$(this).removeClass("over");
		});
		$("#checkall").click(function(){
			if($("#iphoneON").is(":visible")){
				$(".iphoneON").hide(300);
				$(".iphoneOFF").show(300);
			}else{
				$(".iphoneOFF").hide(300);
				$(".iphoneON").show(300);
			}
		});
		$(".list div[class='iphoneDiv']").click(function(){
			$("."+this.childNodes[1].id+"").toggle(300);
		});
		$("#list_div").slideDown(300);
	});