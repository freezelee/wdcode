<#import "/wd.ftl" as wd>  
<#assign sortId=entity.sortId> 
<#assign sort=query.get('sort',sortId) >
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${sort.page.title} 商品列表</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<meta name="keywords" content="${sort.page.keyWords}" />
<meta name="description" content="${sort.page.description}" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/shop/css/shop.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
	<script type="text/javascript" src="${base}/common/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
		DD_belatedPNG.fix(".belatedPNG");
	</script>
<![endif]-->
</head>
<body class="goodsList">
<#if entity.brandId?default(0) &gt; 0>
<#assign goodsList=entitys>
<#else>
<#assign goodsList=query.list('goods','sortId',action.getFields(query.next('sort','sortId',sortId),'id'),orders,pager)>
</#if>
<#assign attributeValues = action.getFields(entity.attributes!,'value')!> 
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
			<form id="goodsListForm" action="${base}/goods_list/${sortId}<#if entity.brandId?default(0) &gt; 0>_${entity.brandId}</#if>.html" method="post">  
				<div class="listBar">
					<div class="left"></div>
					<div class="middle">
						<div class="path"> 
							<a href="${base}/" class="shop"><span class="icon">&nbsp;</span>首页</a> &gt;
							<#list query.prev('sort','sortId',sortId) as p>
								<a href="${base }/goods_list/${p.id}.html">${p.name}</a> &gt;
							</#list>
						</div>
						<div class="total">共计: ${goodsList?size} 款商品</div>
					</div>
					<div class="right"></div>
				</div> 
				<div class="blank"></div>
				<#assign sortList=query.list('sort','sortId',sortId)>
				<#assign type=query.get('type',sort.typeId!)!> 
				<#if sortList?? && sortList?size &gt; 0 || type.brands?? || type.attributes??>
				<div id="filter" class="filter"> 
					<#if sortList??&&sortList?size &gt; 0> 
						<dl>
							<dt>商品分类: </dt>
							<dd>
								<a href="${base }/goods_list/${sortId}.html" class="current">全部</a>
							</dd>
							<#list sortList as s> 
								<dd>
									<a href="${base }/goods_list/${s.id}.html">${s.name}</a>
								</dd> 
							</#list>
						</dl>
					</#if>  
					<#if type??>
						<#if type.brands??> 
							<dl>
								<dt>商品品牌: </dt>
								<dd>
									<a href="${base }/goods_list/${sortId}.html" class="brand all <#if entity.brandId?default(0) == 0>current</#if>">全部</a>
								</dd>
								<#list type.brands as b>								 
									<dd>
										<a href="${base }/goods_list/${sortId}_${b.id}.html" class="brand <#if entity.brandId?default(0) == b.id>current</#if>">${b.name}</a>
									</dd>
								</#list>
							</dl>
						</#if>
						<#if type.attributes??>   
							<#list type.attributes as a>  
								<#if a.type?number==0>					 
									<dl>
										<dt>${a.name}: </dt>
										<dd>
											<a href="#" class="goodsAttributeOption a_${a_index} all current">全部</a>
										</dd>
										<#list a.options.split(',') as o>																				
											<dd>												
												<a name="${a.name}" href="#" class="goodsAttributeOption a_${a_index} <#if entity.attributes?? && attributeValues.contains(o)>current</#if>">${o}</a>
											</dd>
										</#list>
									</dl>
								<#else> 							
									<dl>
										<dt>${a.name}: </dt>
										<dd>
											<@s.textfield name="value"/>											
										</dd>
									</dl>
								</#if>
							</#list> 
						</#if>
					</#if>
					<div class="clear"></div>
				</div> 
				</#if>
				<div class="blank"></div>
				<div class="operateBar">
					<div class="left"></div>
					<div class="middle">
						<span class="separator">&nbsp;</span>		
						<@s.select id="orderType" name="orders" list=r"#{#{}:'默认排序',#{'price':true}:'价格从低到高',#{'price':false}:'价格从高到低',#{'time':false}:'按上架时间排序'}"/>
	                    <span class="separator">&nbsp;</span> 显示数量: 
	                    <@s.select name="pager.pageSize" id="pageSize" list="{12,20,60,120}" />	                  
					</div>
					<div class="right"></div>
				</div>
				<div class="blank"></div>
				<div class="goodsPictureList">
					<ul class="goodsListDetail"> 
						<#list goodsList as g>						
							<li class="goods <#if (g_index+1)%4==0> end</#if>" attributes="${action.getFields(g.attributes,'value')}">
								<a href="${base }/goods/${g.id}.html" class="goodsImage">
									<img src="${base }/${g.images[0].path.replaceAll('#path','thumbnail')}" alt="${g.name}" />
								</a>
								<div class="goodsTitle">
									<a href="${base }/goods/${g.id}.html" alt="${g.name}">
										${g.name}
									</a>
								</div>
								<div class="goodsBottom">
									<div class="goodsPrice">
										<span class="price">￥${g.price}元</span>										
										<span class="marketPrice">￥${g.market}元</span>										
									</div>
									<div class="buttonArea">
										<a href="${base }/goods/${g.id}.html" class="addCartItemButton">购买</a>
										<input type="button" class="addFavoriteButton addFavorite" value="收 藏" goodsId="${g.id}" hidefocus />
									</div>
								</div>
							</li>
						</#list>
						<#if !goodsList??>			
                			<li class="noRecord">非常抱歉,没有找到相关商品!</li>
                		</#if>
					</ul>
					<div class="blank"></div>
					<#if goodsList??> 				
            			<@wd.pagination id="goodsListForm"/>
            		</#if> 	
				</div>
			</form> 
		</div>
		<div class="blank"></div>
		<#include "include/friend_link.ftl" >  
	</div>
	<div class="blank"></div>
	<#include "include/footer.ftl" >  
	<@wd.js name="jquery" /> 
	<@wd.js name="jquery.tools" />
	<script type="text/javascript" src="${base}/shop/js/base.js"></script>
	<script type="text/javascript" src="${base}/shop/js/shop.js"></script>
</body>
</html>