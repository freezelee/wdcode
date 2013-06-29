<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>查询物品列表</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<meta name="keywords" content="查询物品列表" />
<meta name="description" content="查询物品列表" />
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
<#assign goods=query.search('goods','name',entity.name)>
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
							搜索 "${entity.name}" 结果列表
						</div>					
						<div class="total">共计: ${goods?size} 款商品</div>
					</div>
					<div class="right"></div>
				</div>  
				<div class="blank"></div>
				<div class="operateBar">
					<div class="left"></div>
					<div class="middle"> 
						<span class="separator">&nbsp;</span>
						<select id="orderType" name="orderType">
							<option value="default">默认排序</option>
							<option value="priceAsc">价格从低到高</option>
							<option value="priceDesc">价格从高到低</option>
							<option value="dateAsc">按上架时间排序</option>
	                    </select>
	                    <span class="separator">&nbsp;</span> 显示数量: 
	                    <select name="pager.pageSize" id="pageSize">
							<option value="12">
								12
							</option>
							<option value="20" selected="selected">
								20
							</option>
							<option value="60">
								60
							</option>
							<option value="120">
								120
							</option>
						</select>
					</div>
					<div class="right"></div>
				</div>
				<div class="blank"></div>
				<div class="goodsPictureList">
					<ul class="goodsListDetail"> 
						<#list goods as g>						
							<li<#if (g_index+1)%4==0> class="end" </#if>>
								<a href="${base }/goods/${g.id}.html" class="goodsImage" target="_blank">
									<img src="${base }/${g.images[0].path.replaceAll('#path','thumbnail')}" alt="${g.name}" />
								</a>
								<div class="goodsTitle">
									<a href="${base }/goods/${g.id}.html" alt="${g.name}" target="_blank">
										${g.name}
									</a>
								</div>
								<div class="goodsBottom">
									<div class="goodsPrice">
										<span class="price">${g.price}</span>										
										<span class="marketPrice">${g.market}</span>										
									</div>
									<div class="buttonArea">
										<a href="/" class="addCartItemButton">购买</a>
										<input type="button" class="addFavoriteButton addFavorite" value="收 藏" goodsId="${g.id}" hidefocus />
									</div>
								</div>
							</li>
						</#list>
						<#if !goods??> 				
                			<li class="noRecord">非常抱歉,没有找到相关商品!</li>
                		</#if>
					</ul>
					<div class="blank"></div>
				</div> 
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