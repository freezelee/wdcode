<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${entity.page.title}</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<meta name="keywords" content="${entity.page.keyWords}" />
<meta name="description" content="${entity.page.description}" />
<link rel="icon" href="favicon.ico" type="image/x-icon" /> 
<@wd.css name="jquery.zoomimage" />
<link href="${base}/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/shop/css/shop.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
	<script type="text/javascript" src="${base}/shop/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
		DD_belatedPNG.fix(".belatedPNG");
	</script>
<![endif]-->
</head>
<body id="goodsContent" class="goodsContent"> 
<@s.hidden id="id" value="%{#attr.entity.id}" />
	<#include "include/header.ftl" >
	<div class="body">
		<div class="bodyLeft">
			<#include "include/goods_sort.ftl" > 
			<div class="blank"></div>
			<#include "include/goods_hot.ftl" > 
			<div class="blank"></div>
			<div id="goodsHistory" class="goodsHistory">
				<div class="top">浏览记录</div>
				<div class="middle">
					<ul id="goodsHistoryListDetail"></ul>
				</div>
				<div class="bottom"></div>
			</div>
		</div>
		<div class="bodyRight">
			<div class="listBar">
				<div class="left"></div>
				<div class="middle">
					<div class="path">
						<a href="${base}/" class="shop"><span class="icon">&nbsp;</span>首页</a> &gt;
						<#list query.prev('sort','sortId',entity.sortId) as s> 
							<a href="${base }/goods_list/${s.id}.html">${s.name}</a> &gt;
						</#list> 
					</div>
				</div>
				<div class="right"></div>
			</div>
			<div class="blank"></div>
			<div class="goodsTop">
				<div class="goodsTopLeft">
					<div class="goodsImage">
						<#if entity.images??> 
							<#list entity.images as image>							 
		                		<a href="${base}/${image.path.replaceAll('#path','big')}" class="tabContent zoom">
									<img src="${base}/${image.path.replaceAll('#path','small')}" alt="点击放大" />
								</a>
							</#list>
						</#if>
                	</div>
					<div class="thumbnailGoodsImage">
						<a class="prev browse" href="javascript: void(0);" hidefocus></a>
						<div id="thumbnailGoodsImageScrollable" class="scrollable">
							<ul id="goodsImageTab" class="items goodsImageTab">
								<#if entity.images??> 
									<#list entity.images as image>		
										<li>
											<img src="${base}/${image.path.replaceAll('#path','thumbnail')}" alt="${image.description}"/> 
										</li>		 
									</#list>
								</#if> 
							</ul>
						</div>
						<a class="next browse" href="javascript: void(0);" hidefocus></a>
					</div>
				</div>
				<div class="goodsTopRight">
					<h1 class="title">${entity.name}</h1>
					<ul class="goodsAttribute">
						<li>商品编号: ${entity.sn}</li>  
						<li>货品编号: <span id="productSn">${query.get('product','goodsId',entity.id).sn}</span></li>
						<#list entity.attributes as a> 
	                    	<li>${a.name}: ${action.substring(a.value,26)}</li> 
						</#list>
					</ul>
					<div class="blank"></div>
					<div class="goodsPrice">
						<div class="left"></div>
						<div class="right">
							<div class="top">
								销 售 价:<span id="price" class="price">￥${entity.price}元</span>
								<@s.hidden id="hprice" value="%{#attr.entity.price}" /> 
							</div>
							<div class="bottom">
								市 场 价:								
								<span id="marketPrice" class="marketPrice">￥${entity.market}元</span>
									-								
							</div>
						</div>
					</div>
					<div class="blank"></div>
					<table id="buyInfo" class="buyInfo">    					
						<#if entity.specificationEnabled> 
							<#assign products = query.list('product','goodsId',entity.id)>
							<#assign specificationValues = action.getFields(products,'specificationValues')> 
							<tr class="specificationTips">
								<th id="tipsTitle">请选择:</th>
								<td>
									<div id="tipsContent" class="tipsContent">
										<#list entity.specifications as s>
											${s.name} 
										</#list>
									</div>
									<div id="closeHighlight" class="closeHighlight" title="关闭"></div>
								</td>
							</tr>
							<#list entity.specifications as s>
								<#if s.state == 0>
									<tr class="text">
										<th>${s.name}:</th>
										<td>
											<ul>
												<#list s.specificationValues as v> 
													<#list specificationValues as val>
														<#if val.name.equals(v.name)>
															<li title="${v.name}">
																${v.name}
																<span title="点击取消选择"></span>
															</li> 
															<#break>
														</#if> 
													</#list>
												</#list>
											</ul>
										</td>
									</tr>
								<#else>
									<tr class="image">
										<th>${s.name}:</th>
										<td>
											<ul>
												<#list s.specificationValues as v>    
													<#list specificationValues as val>
														<#if val.name.equals(v.name)>
															<li title="${v.name}">
																<#if v.path??>
																	<img src="${base}${v.path}" alt="${v.name}" />
																<#else>
																	<img src="${base}/template/shop/images/default_specification.gif" />
																</#if>
																<span title="点击取消选择"></span>
															</li> 
															<#break>
														</#if> 
													</#list>
												</#list>
											</ul>
										</td>
									</tr>
								</#if>
							</#list>
						</#if>
						<tr>
							<th>购买数量:</th>
							<td>
								<input type="text" id="quantity" value="1" />  
							</td>
						</tr>
						<tr>
							<th>&nbsp;</th>
							<td>  
								<#if entity.store==0> 
									<input type="button" id="goodsButton" class="notifyButton" value="" hidefocus />
								<#else>
									<input type="button" id="goodsButton" class="addCartItemButton" value="" hidefocus />
								</#if>
								 <input type="button" id="addFavorite" class="addFavoriteButton" goodsId="${entity.id}" hidefocus />
							</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="blank"></div>
			<div class="goodsBottom">
				<ul id="goodsParameterTab" class="goodsParameterTab">
					<li>
						<a href="javascript: void(0);" class="current" hidefocus>商品介绍</a>
					</li>
					<li>
						<a href="javascript: void(0);" name="goodsAttribute" hidefocus>商品参数</a>
					</li> 
						<li>
							<a href="javascript: void(0);" hidefocus>商品评论</a>
						</li> 
				</ul> 
				<div class="tabContent goodsIntroduction">
					${entity.detail}
				</div>
				<div class="tabContent goodsAttribute">
					<table class="goodsParameterTable"> 
						<#list entity.params as p> 
							<tr>
								<th>
									${p.name}									
								</th>
								<td>
									${p.value}
								</td>
							</tr> 
						</#list>
					</table>
				</div> 
				<div id="comment" class="tabContent comment"> 
					<#assign comment=query.list('comment','goodsId',entity.id,5)>
					<#list comment as c> 
						<div class="commentItem" id="commentItem${c.id}">
							<p><span class="red">{${c.userName}}</span> ${c.date}<a href="#commentForm" class="commentReply" forCommentId="${c.id}">[回复此评论]</a></p>
							<p><pre>${c.content}</pre></p>
							<#list query.list('comment','commentId',c.id) as cc> 
								<div class="reply">
									<p><span class="red">${cc.userName}</span>${cc.date}</p>
									<p><pre>${cc.content}</pre></p>
								</div> 
							</#list>
						</div>			
						<#if c_index%2==0>						
							<div class="blank"></div>
						</#if>
					</#list>
					<#if comment??> 
						<div class="info">
							<a href="${base}/shop/comment_list/${entity.id}.htm">查看所有评论&gt;&gt;</a>
						</div>
					</#if> 
					<@s.form action="comment_add"> 
						<@s.hidden id="goodsId" name="entity.goodsId" value="%{#attr.entity.id}" /> 
						<@s.hidden id="goodsName" name="entity.goodsName" value="%{#attr.entity.name}" /> 
						<table class="sendTable">
							<tr class="title">
								<td width="100">
									<span id="sendTitle">发表评论</span>
								</td>
								<td>
									<a href="javascript: void(0);" id="sendComment" class="sendComment">切换到发表新评论&gt;&gt;</a>
								</td>
							</tr>
							<tr>
								<th>
									评论内容: 
								</th>
								<td>
									<textarea id="commentContent" name="entity.content" class="formTextarea"></textarea>
								</td>
							</tr>
							<tr>
								<th>
									联系方式: 
								</th>
								<td>
									<input type="text" name="entity.contact" class="formText" />
								</td>
							</tr> 
							<tr>
			                	<th>
			                		验证码: 
			                	</th>
			                    <td>
			                    	<input type="text" id="commentCaptcha" name="j_captcha" class="formText captcha" />
			                    	<@wd.verifycode css="captchaImage" url="verify"/> 
			                    </td>
			                </tr> 
							<tr>
								<th>
									&nbsp;
								</th>
								<td>
									<input type="submit" class="formButton" value="提交评论" />
								</td>
							</tr>
						</table>
					</@s.form>
				</div>
			</div>
		</div>
		<div class="blank"></div>
		<#include "include/friend_link.ftl" > 
	</div>
	<div class="blank"></div>
	<#include "include/footer.ftl" > 
	<@wd.js name="jquery" /> 
	<@wd.js name="jquery.tools" />
	<@wd.js name="jquery.zoomimage" />
	<script type="text/javascript" src="${base}/shop/js/base.js"></script>
	<script type="text/javascript" src="${base}/shop/js/shop.js"></script> 
	<script type="text/javascript">
	$().ready( function() {
	
		$productSn = $("#productSn");
		$price = $("#price");
		$marketPrice = $("#marketPrice");
		$buyInfo = $("#buyInfo");
		$tipsTitle = $("#tipsTitle");
		$tipsContent = $("#tipsContent");
		$closeHighlight = $("#closeHighlight");
		$specificationValue = $("#buyInfo li");
		$quantity = $("#quantity");
		$goodsButton = $("#goodsButton"); 
		// 添加商品浏览记录
		$.addGoodsHistory("${action.substring(entity.name, 24)}", "${base }/goods/${entity.id}.html"); 
		<#if entity.specificationEnabled> 
			<#assign products = query.list('product','goodsId',entity.id) >
			var productDatas = {};
			<#list products as product>
				<#if product.markeTable>
					productDatas['${product.id}'] = {
						sn: "${product.sn}",
						price: "${product.price}",
						store: "${product.store}",
						marketPrice: "${product.market}"
					};
				</#if>
			</#list>
			
			var specificationValueDatas = {};
			<#list products as product>
				<#if product.markeTable>
					specificationValueDatas['${product.id}'] = new Array(<#list product.specificationValues as specificationValue>"${specificationValue.name}"<#if specificationValue_has_next>, </#if></#list>);
				</#if>
			</#list>
			
			var specificationValueSelecteds = new Array();
			var selectedProductId = null;
			
			$specificationValue.click(function () {
				var $this = $(this);
				if ($this.hasClass("notAllowed")) {
					return false;
				}
				
				if ($this.hasClass("selected")) {
					$this.removeClass("selected");
				} else {
					$this.addClass("selected");
				}
				$this.siblings("li").removeClass("selected");
				//$specificationValue.addClass("notAllowed");
				
				var $specificationValueSelected = $("#buyInfo li.selected");
				if ($specificationValueSelected.length == 1) {
					$specificationValueSelected.siblings("li").removeClass("notAllowed");
				}
				
				var specificationValueSelecteds = new Array();
				selectedProductId = null;
				var tipsContentText = "";
				$specificationValueSelected.each(function(i) {
					var $this = $(this);
					tipsContentText += $this.attr("title") + " ";
					specificationValueSelecteds.push($this.attr("title"));
				});
				if (tipsContentText != "") {
					$tipsTitle.text("已选择: ");
					$tipsContent.text(tipsContentText);
				} else {
					$tipsTitle.text("请选择: ");
					$tipsContent.text("<#list entity.specifications as specification>${specification.name} </#list>");
				}
				$.each(specificationValueDatas, function(i) {
					if (arrayContains(specificationValueDatas[i], specificationValueSelecteds)) {
						for (var j in specificationValueDatas[i]) {
							$("." + specificationValueDatas[i][j]).removeClass("notAllowed");
						}
					}
					if (arrayEqual(specificationValueDatas[i], specificationValueSelecteds)) {
						$productSn = $productSn.text(productDatas[i].sn);
						$price = $price.text(productDatas[i].price);
						$marketPrice = $marketPrice.text(productDatas[i].market);
						selectedProductId = i;
						$buyInfo.removeClass("highlight"); 
						if (productDatas[i].store < 1) {
							$goodsButton.addClass("goodsNotifyButton");
							$goodsButton.removeClass("addCartItemButton");
						} else {
							$goodsButton.addClass("addCartItemButton");
							$goodsButton.removeClass("goodsNotifyButton");
						}
					}
				});
			});
			
			// 添加商品至购物车/到货通知
			$goodsButton.click(function () {
				var $this = $(this);
				if (selectedProductId != null) {
					if ($this.hasClass("addCartItemButton")) {
						$.addCartItem(selectedProductId,$("#id").val(), $quantity.val(),$("#hprice").val()); 
					} else {
					    $.getJSON("${base}/shop/notify_add_ajax.htm","entity.goodsId="+${entity.id}+"&entity.productId="+selectedProductId,function(data) { 
							if (data != null) {
								$.dialog({type: "success", content: "<span class=\"red\">添加成功</span><br />", width: 360, modal: true, autoCloseTime: 3000});
							} else {
								$.dialog({type: "error", content:  "<span class=\"red\">添加失败</span><br />", modal: true, autoCloseTime: 3000});
							}
						}); 
					}
				} else {
					$buyInfo.addClass("highlight");
					$tipsTitle.text('系统提示:');
					$tipsContent.text('请选择商品信息!');
				}
			});
			
			// 关闭购买信息提示
			$closeHighlight.click(function () {
				$buyInfo.removeClass("highlight");
				$tipsTitle.html("请选择: ");
				$tipsContent.html("<#list entity.specifications as specification>${specification.name} </#list>");
			});
			
			// 判断数组是否包含另一个数组中所有元素
			function arrayContains(array1, array2) {
				if(!(array1 instanceof Array) || !(array2 instanceof Array)) {
					return false;
				}
				if(array1.length < array2.length) {
					return false;
				}
				for (var i in array2) {
					if ($.inArray(array2[i], array1) == -1) {
						return false;
					}
				}
				return true;
			}
			
			// 判断两个数组中的所有元素是否相同
			function arrayEqual(array1, array2) {
				if(!(array1 instanceof Array) || !(array2 instanceof Array)) {
					return false;
				}
				if(array1.length != array2.length) {
					return false;
				}
				for (var i in array2) {
					if ($.inArray(array2[i], array1) == -1) {
						return false;
					}
				}
				return true;
			}
		<#else> 
			var selectedProductId = ${entity.id};
			
			// 添加商品至购物车/到货通知
			$goodsButton.click(function () {
				var $this = $(this);
				if ($this.hasClass("addCartItemButton")) {
					$.addCartItem(selectedProductId,$("#id").val(), $quantity.val(),$("#hprice").val()); 
				} else {
					location.href = '${base}/shop/notify_add.htm?entity.goodsId=' + ${entity.id}+'&entity.productId='+selectedProductId;
				}
			});
		</#if>
	
	})
	</script>
</body>
</html>