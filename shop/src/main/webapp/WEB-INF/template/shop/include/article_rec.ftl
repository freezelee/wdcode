<#assign articleTopList=query.list('article','recommend',1)>
<#if articleTopList??> 
<div class="recommendArticle">
<div class="top">推荐文章</div>
<div class="middle">
	<ul>
		<#list articleTopList as a> 
			<li>
				<span class="icon">&nbsp;</span>
				<a href="${base }/article/${a.id}.html" title="${a.name}">${action.substring(a.name,8)}</a>
			</li>
		</#list> 
	</ul>
</div>
<div class="bottom"></div>
</div>
</#if> 