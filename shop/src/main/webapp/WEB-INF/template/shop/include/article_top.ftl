<#assign articleTopList=query.list('article','top',1)> 
<#if articleTopList??> 
	<div class="hotArticle">
			<div class="top">热点文章</div>
			<div class="middle">
				<ul>
					<#list articleTopList as a> 
						<li class="number${a_index+1}">
							<span class="icon">&nbsp;</span>
							<a href="${base }/article/${a.id}.html" title="${a.name}">${action.substring(a.name,8)}</a>
						</li>
					</#list>
				</ul>
			</div>
			<div class="bottom"></div>
		</div>
</#if>