<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${query.get('property','name').value}</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<meta name="keywords" content="${query.get('property','keywords').value}" />
<meta name="description" content="${query.get('property','description').value}" />
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
<body class="index">
--${token}--
	<#include "include/header.ftl" > 
	<div class="body">
		<div class="bodyLeft">
			<#include "include/goods_sort.ftl" > 
		</div>
		<div class="bodyRight">
			<div class="slider">
				<div id="sliderScrollable" class="scrollable">
					<div class="items">
						<div>
							<img src="http://demo.image.shopxx.net/201101/banner1.jpg" />
						</div>
						<div>
							<img src="http://demo.image.shopxx.net/201101/banner2.jpg" />
						</div>
						<div>
							<img src="http://demo.image.shopxx.net/201101/banner3.jpg" />
						</div>
					</div>
					<div class="navi"></div>
					<div class="prevNext">
						<a class="prev browse left"></a>
						<a class="next browse right"></a>
					</div>
				</div>
			</div>
			<div class="blank"></div>
			<div class="hotGoodsSlider">
				<div class="title">
					<strong>热卖商品</strong>HOT
				</div>
				<a class="prev browse"></a> 
				<div id="hotGoodsScrollable" class="scrollable">
					<div class="items">
						<div>
							<ul>
							<#list query.list('goods','hot',true,12) as g> 
								<li>
									<a href="${base }/goods/${g.id}.html">
										<img src="${base }/${g.images[0].path?replace('#path','thumbnail')}" alt="${g.name}" />
										<p title="${g.name}">${action.substring(g.name,15)}</p>
									</a>
								</li>
								<#if (g_index + 1) % 4 == 0 && g_has_next>
									</ul>
									</div>
									<div>
									<ul>
								</#if>  
							</#list>
							</ul> 
						</div>
					</div> 
					</div>
				<a class="next browse"></a> 
			</div>
		</div>
		<div class="blank"></div>
		<img src="http://demo.image.shopxx.net/201101/banner4.jpg" />
		<div class="blank"></div>
		<div class="newGoods">			
			<#assign sortTop=query.list('sort','sortId',0,4)> 
			<div class="left">
				<ul id="newGoodsTab" class="newGoodsTab">
					<#list sortTop as s>					
						<li>${s.name}</li>
					</#list>
				</ul>
			</div>
			<div class="right">
				<#list sortTop as s> 
					<ul class="newGoodsTabContent hidden">		
						<#list query.list('goods','sortId',action.getFields(query.next('sort','sortId',s.id),"id"),4) as g>						
							<li>
								<a href="${base }/goods/${g.id}.html">
									<img src="${base}/${g.images[0].path.replaceAll('#path','thumbnail')}" alt="${g.name}" />
									<p title="${g.name}">${action.substring(g.name,15)}</p>
								</a>
							</li> 
						</#list>
					</ul>
				</#list>
			</div>
		</div>
		<div class="blank"></div>
		<div class="bodyLeft">
			<#include "include/goods_hot.ftl" > 
			<div class="blank"></div>
			<#include "include/article_top.ftl" > 
		</div>
		<div class="bodyRight">
			<#assign goodsBestList=query.list('goods','best',true,12)> 
			<#if goodsBestList??> 
				<div class="bestGoods">
					<div class="top">
						<strong>精品推荐</strong>BEST
					</div>
					<div class="middle">
						<ul>
							<#list goodsBestList as g>							
								<li>
									<a href="${base }/goods/${g.id}.html">
										<img src="${base}/${g.images[0].path.replaceAll('#path','thumbnail')}" alt="${g.name}" />
										<p title="${g.name}">${action.substring(g.name,8)}</p>
										<p class="red">￥${g.price}元</p>
									</a>
								</li>
							</#list> 
						</ul>
					</div>
					<div class="bottom"></div>
				</div>
			</#if> 
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