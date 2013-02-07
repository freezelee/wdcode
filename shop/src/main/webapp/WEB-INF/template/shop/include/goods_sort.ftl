<div class="goodsCategory">
       	<div class="top">商品分类</div>
       	<div class="middle">
       		<ul id="goodsCategoryMenu" class="menu">
       			<#list query.list('sort','sortId',0,3) as sort>       			 
       				<li class="mainCategory">     
						<a href="${base }/goods_list/${sort.id}.html">${sort.name}</a>
					</li>
					<#list query.list('sort','sortId',sort.id) as s> 
						<li>
							<a href="${base }/goods_list/${s.id}.html">
								<span class="icon">&nbsp;</span>${s.name}
							</a>
						</li> 
					</#list>   
       			</#list> 
	</ul>
       	</div>
           <div class="bottom"></div>
</div>