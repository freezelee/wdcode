/**
 * 主要页面的JS
 */

/**
 * 文档加载事件
 */
$(function() {
	// 全选框
	$("#all").click(
			function() {
				$(".checkbox").attr("checked",
						$("#all").attr("checked") == undefined ? false : true);
			});

	// 删除
	$("#dels").click(function() {
		// 验证
		if ($(".checkbox").is(":checked")) {
			if (confirm($(this).attr("accesskey"))) {
				$("#currentPage").attr("name", "");
			} else {
				return false;
			}
		} else {
			alert($(this).attr("title"));
			return false;
		}
	});

	// 显示
	$("#show").click(function() {
		$("#hide").show(600);
		return false;
	});

	// 元素动画效果时间
	var speed = 500;
	// 给菜单TD 添加单击事件
	$(".menu_main_td").click(function() {
		// 获得点击的隐藏菜单
		var obj = $(this).parent().next().find(".hide");
		// 判断是否隐藏的
		if (obj.css("display") == "none") {
			// 隐藏所有菜单
			$(".hide").hide();
			// 隐藏的 显示
			obj.slideDown(speed);
		} else {
			// 显示的隐藏
			obj.hide();
		}
	});
 
	// 清空
	$("#trun").click(function() {
		if (confirm($(this).attr("title"))) {
			$("#currentPage").attr("name", "");
			if ($(".checkbox").size() == 1) {
				$(".checkbox").attr("checked", "true");
			}
		} else {
			return false;
		}
	});

	// 开始日期
	// $("#startDate").calendar();
	// 结束日期
	// $("#endDate").calendar();

	// 给切换左边菜单显示/隐藏td添加事件
	$(".middle").click(function() {
		// 获得标识对象
		var obj = $("#main");
		// 获得标识为3
		if (obj.hasClass("leftArrow")) {
			// 隐藏菜单
			$("#left_td").hide();
			obj.removeClass("leftArrow");
			obj.addClass("rightArrow");
		} else {
			// 显示菜单
			$("#left_td").show();
			obj.removeClass("rightArrow");
			obj.addClass("leftArrow");
		}
	});

	// 商品类型
	goodsType();
	// 商品规格
	goodsSpec();
	// 商品图片
	goodsImage();
	// 选择商品类型
	goodsTypeSelect();
});

// 选择商品类型
var goodsTypeSelect = function() {
	$("#goodsType").after('<div class="blank"></div><table id="goodsParameterTable" class="inputTable"></table>');
	$("#goodsType").after('<div class="blank"></div><table id="goodsAttributeTable" class="inputTable"></table>');	
	
	var goodsTypeId = $("#goodsTypeId");	
	// 修改商品类型  
	var goodsAttributeTable = $("#goodsAttributeTable"); 
	var goodsParameterTable = $("#goodsParameterTable");
	goodsTypeId.change(function() { 
		goodsParameterTable.empty();
		goodsAttributeTable.empty();
		if (goodsTypeId.val() == "0") {
			goodsAttributeTable.hide();
			goodsParameterTable.hide();
			return;
		}
		$.getJSON("type_theme_ajax.htm",{"entity.id" : goodsTypeId.val()},function(data) {
			if (data != null) { 
				var attributes = data.attributes;
				var goodsAttributeTrHtml = '<tr class="title"><th>商品属性</th><td>&nbsp;</td></tr>';
				$.each(attributes,function(i) {
					if (attributes[i].type == "0") { 
						var optionHtml = '<option value="">请选择...</option>'; 
						var opts = attributes[i].options.split(",");
						$.each(opts,function(j) {
								optionHtml += '<option value="'
										+ opts[j]
										+ '">'
										+ opts[j]
										+ '</option>';
							});
						goodsAttributeTrHtml += '<tr class="goodsAttributeTr"> <th>'
								+ attributes[i].name
								+ ': </th> <td> <select name="entity.attributes['
								+ i
								+ '].val">'
								+ optionHtml
								+ ' </select><input type="hidden" name="entity.attributes['
								+ i
								+ '].name" value="'
								+ attributes[i].name
								+ '"/> </td> </tr>';
					} else {
						goodsAttributeTrHtml += '<tr class="goodsAttributeTr"> <th>'
								+ attributes[i].name
								+ ': </th> <td> <input type="text" name="entity.attributes['
								+ i
								+ '].val" class="formText" /> <input type="hidden" name="entity.attributes['
								+ i
								+ '].name" value="'
								+ attributes[i].name
								+ '"/></td> </tr>';
					}
				});
				goodsAttributeTable.append(goodsAttributeTrHtml);
				goodsAttributeTable.show();
				
				var goodsParameterTrHtml = '<tr class="title"><th>商品参数</th><td>&nbsp;</td></tr>'; 
				var params = data.params; 
				$.each(params,function(i) {
					goodsParameterTrHtml += '<tr class="goodsParameterTr"> <th>'
						+ params[i].name
						+ ': </th> <td> <input type="text" name="entity.params['
						+ i
						+ '].value"'						
						+ ' class="formText" /><input type="hidden" name="entity.params['
						+ i
						+ '].name" value="'
						+ params[i].name
						+ '" /></td> </tr>';
					}); 
				goodsParameterTable.append(goodsParameterTrHtml); 
				goodsParameterTable.show();  
			} else {  
				goodsAttributeTable.hide();
				goodsParameterTable.hide();  
			} 
		}); 
	});
};

// 增加商品图片
var goodsImage = function() {
	var goodsImageTable = $("#goodsImageTable");
	var addGoodsImageButton = $("#addGoodsImageButton");
	var goodsImageIndex = 0;	
	addGoodsImageButton.click(function() {
		var goodsImageTrHtml = '<tr class="goodsImageTr"> <td> <input type="file" name="uploads" class="goodsImageFileList" /> </td> <td> <input type="text" name="entity.images['
			+ goodsImageIndex
			+ '].detail" class="formText" /> </td><td> <span class="deleteIcon deleteGoodsImage" title="删 除">&nbsp;</span> </td> </tr>';
		goodsImageTable.append(goodsImageTrHtml);
		goodsImageIndex++;
	});

	// 删除商品图片
	$("#goodsImageTable .deleteGoodsImage").live("click", function() {
		$.dialog({
			type : "warn",
			content : "您确定要删除吗?",
			ok : "确 定",
			cancel : "取 消",
			modal : true,
			okCallback : deleteGoodsImage
		});
		function deleteGoodsImage() {
			$(this).parent().parent().remove();
		}
	});
};

// 商品规格
var goodsSpec = function() {
	var specificationTable = $("#specificationTable");
	var specificationSpecificationType = $("#specificationSpecificationType");
	var addSpecificationValueButton = $("#addSpecificationValueButton");

	// 变更商品规格类型
	specificationSpecificationType.change(function() {
		if ($(this).val() == "0") {
			$("#specificationTable :file").attr("disabled", true);
		} else {
			$("#specificationTable :file").attr("disabled", false);
		}
	});
	// 增加商品规格值
	var specificationValueIndex = $(".specificationValueTr").size();
	addSpecificationValueButton.click(function() {
		var specificationValueTrHtml = '<tr class="specificationValueTr"> <td> &nbsp; </td> <td> <input type="text" name="entity.specificationValues['
			+ specificationValueIndex
			+ '].name" class="formText specificationValueListName" /> </td> <td> <input type="file" name="uploads'
			+ '" class="specificationValueImageList"';
		if (specificationSpecificationType.val() == "0") {
			specificationValueTrHtml+='disabled';  
		} 
		specificationValueTrHtml+='/> </td>'
			+ '<td> <span class="deleteIcon deleteSpecificationValueIcon" title="删 除">&nbsp;</span> </td> </tr>';				
		specificationTable.append(specificationValueTrHtml);
		specificationValueIndex++;
	});
	 
	// 删除商品规格值
	$("#specificationTable .deleteSpecificationValueIcon").live("click",function() {
		if (specificationTable.find(".specificationValueTr").length <= 1) {
			$.dialog({
				type : "warn",
				content : "必须至少保留一个商品规格值!",
				modal : true,
				autoCloseTime : 3000
			});
		} else {
			$(this).parent().parent().remove();
		}
	});
	if (specificationTable.find(".specificationValueTr").length < 1) {
		addSpecificationValueButton.click();
		specificationValueIndex = 1;
	}	
};

// 商品类型
var goodsType = function() {
	// 增加商品属性
	var addGoodsAttributeButton = $("#addGoodsAttributeButton");
	var goodsAttributeTable = $("#goodsAttributeTable");
	var goodsAttributeIndex = 0;
	addGoodsAttributeButton
			.click(function() {
				var goodsAttributeTrHtml = '<tr class="goodsAttributeTr"> <td> <input type="text" name="entity.attributes['
						+ goodsAttributeIndex
						+ '].name" class="formText goodsAttributeListName" /> </td> <td> <select name="entity.attributes['
						+ goodsAttributeIndex
						+ '].state" class="attributeType"> <option value="0"> 筛选项 </option> <option value="1"> 输入项 </option> </select> </td> <td> <input type="text" name="entity.attributes['
						+ goodsAttributeIndex
						+ '].options" class="formText optionText goodsAttributeListOptionText" title="多个可选值.请使用“,”分隔" /> </td> <td> <span class="deleteIcon deleteGoodsAttributeIcon" title="删 除">&nbsp;</span> </td> </tr>';
				goodsAttributeTable.append(goodsAttributeTrHtml);
				goodsAttributeIndex++;
			});

	// 修改商品属性类型
	$("#goodsAttributeTable .attributeType").live("click", function() {
		var $this = $(this);
		var $optionText = $this.parent().parent().find(".optionText");
		if ($this.val() == "0") {
			$optionText.attr("disabled", false).show();
		} else {
			$optionText.attr("disabled", true).hide();
		}
	});

	// 删除商品属性
	$("#goodsAttributeTable .deleteGoodsAttributeIcon").live("click",
			function() {
				$(this).parent().parent().remove();
			});

	// 增加商品参数
	var addGoodsParameterButton = $("#addGoodsParameterButton");
	var goodsParameterTable = $("#goodsParameterTable");
	var goodsParameterTrHtml = '<tr class="goodsParameterTr"> <td> <input type="text" name="entity.params.name" class="formText goodsParameterListName" /> </td><td> <span class="deleteIcon deleteGoodsParameterIcon" title="删 除">&nbsp;</span> </td> </tr>';
	addGoodsParameterButton.click(function() {
		goodsParameterTable.append(goodsParameterTrHtml);
	});

	// 删除商品参数
	$("#goodsParameterTable .deleteGoodsParameterIcon").live("click",
			function() {
				$(this).parent().parent().remove();
			});
};