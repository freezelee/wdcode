<#import "/wd.ftl" as wd>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${entity.page.title} -- 文章列表</title>
<meta name="Author" content="SHOP++ Team" />
<meta name="Copyright" content="SHOP++" />
<meta name="keywords" content="${entity.page.keyWords}" />
<meta name="description" content="${entity.page.description}" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link href="${base}/shop/css/base.css" rel="stylesheet" type="text/css" />
<link href="${base}/shop/css/shop.css" rel="stylesheet" type="text/css" />
<!--[if lte IE 6]>
	<script type="text/javascript" src="${base}/template/common/js/belatedPNG.js"></script>
	<script type="text/javascript">
		// 解决IE6透明PNG图片BUG
		DD_belatedPNG.fix(".belatedPNG");
	</script>
<![endif]-->
</head>
<body class="articleList">
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
						<a href="${base}/" class="home"><span class="icon">&nbsp;</span>首页</a> &gt;
						<#list query.prev('category','categoryId',entity.id) as c >
							<a href="${base}/article_list/${c.id}.html">${c.name}</a> &gt;
						</#list>
					</div>
					<div id="articleSearch" class="articleSearch">
						<form id="articleSearchForm" action="${base}/shop/article!search.action" method="post">
							<input type="text" name="pager.keyword" id="articleSearchKeyword" class="keyword" value="请输入关键词..." />
							<input type="submit" class="searchButton" value="" />
						</form>
					</div>
				</div>
				<div class="right"></div>
			</div>
			<div class="blank"></div>
			<div class="articleList">
				<div class="articleListTop"></div>
				<div class="articleListMiddle">
					<ul class="articleListDetail">
						<#list query.list('article','categoryId',action.getFields(query.next('category','categoryId',entity.id),"id"),pager) as a>						
							<li>
                            	<a href="${base}/article/${a.id}.html" class="title">
                            		${action.substring(a.name,40)} 
								</a>
                                <span class="author">
                                	作者: ${a.author}
                                </span>
                                <span class="createDate">
                                	${a.date}
                                </span>
                                <div class="contentText">
                                	${action.substring(a.content,200)}  
									<a href="${base}/article/${a.id}.html">[阅读全文]</a>
								</div>
      		        		</li>
						</#list>
					</ul>
					<div class="blank"></div>
					<@wd.pagination/>
				</div>
				<div class="articleListBottom"></div>
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