<#if entity?? && entity.sortId?default(0) &gt; 0>
<#assign goodsHotList=query.list('goods',{"hot":[true],"sortId":action.getFields(query.next('sort','sortId',entity.sortId),'id')},8)>
<#else>
<#assign goodsHotList=query.list('goods','hot',true,8) >
</#if>
<#if goodsHotList??>
	<div class="hotGoods">
		<div class="top">热销排行</div>
		<div class="middle">
			<ul>
				<#list goodsHotList as g>				   
					<li class="number${g_index+1}">
					<span class="icon">&nbsp;</span>
					<a href="${base }/goods/${g.id}.html" title="${g.name}">${action.substring(g.name,15)}</a>
					</li> 
				</#list> 
			</ul>
		</div>
		<div class="bottom"></div>
	</div> 
</#if>