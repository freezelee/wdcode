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
<body class="articleContent">
	<#include "include/header.ftl" > 
	<div class="body">
		<div class="bodyLeft">
			<#include "include/article_rec.ftl" > 
			<div class="blank"></div>
			<#include "include/article_top.ftl" > 
		</div>
		<div class="bodyRight">
			<div class="listBar">
				<div class="left"></div>
				<div class="middle">
					<div class="path">
						<a href="${base}/" class="shop"><span class="icon">&nbsp;</span>首页</a> &gt;
						<#list query.prev('category','categoryId',entity.categoryId) as c>
							<a href="${base}/article_list/${c.id}.html">${c.name}</a> &gt;
						</#list>
					</div>
					<div id="articleSearch" class="articleSearch">
						<form id="articleSearchForm" action="${base}/shop/entity!search.action" method="post">
							<input type="text" name="pager.keyword" id="articleSearchKeyword" class="keyword" value="请输入关键词..." />
							<input type="submit" class="searchButton" value="" />
						</form>
					</div>
				</div>
				<div class="right"></div>
			</div>
			<div class="blank"></div>
			<div class="articleContentDetail">
				<div class="articleContentTop"></div>
				<div class="articleContentMiddle">
					<div class="title">${entity.name}</div>
                    <div class="blank"></div>
                    <div class="info">
                    	<span class="createDate">日期: ${entity.date}</span> 
                   		<span class="author">作者: ${entity.author}</span> 
                    	点击: <span id="hits"></span> 次
                    	<span class="fontSize">【<a id="changeBigFontSize" href="javascript: void(0);">大</a> <a id="changeNormalFontSize" href="javascript: void(0);">中</a> <a id="changeSmallFontSize" href="javascript: void(0);">小</a>】</span>
                    </div>
					<div id="articleContent" class="content">
						${entity.content}
             			<div class="blank"></div> 
                    </div>
				</div>
				<div class="articleContentBottom"></div>
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