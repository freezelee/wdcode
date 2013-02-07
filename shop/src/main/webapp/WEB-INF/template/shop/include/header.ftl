<div id="header" class="header belatedPNG">
	<div class="headerTop belatedPNG">
		<div class="headerTopContent"> 
			<div class="headerLoginInfo">
				您好<span id="headerLoginMemberUsername">
				<#if token.isLogin()> 
					${token.name }
				</#if>			
				</span>, 欢迎来到${query.get('property','name').value} 
				<#if token.isLogin()> 
					<a href="<@s.url action="user_to_center" />" id="headerMemberCenter">会员中心</a>
					<a href="<@s.url action="logout"/>" id="headerLogout">[退出]</a> 
				<#else>
					<a href="javascript: void(0);" id="headerShowLoginWindow">登录</a>
					<a href="javascript: void(0);" id="headerShowRegisterWindow">注册</a>	
				</#if>
			</div> 			
			<div class="headerTopNav">
				<#list query.list('navigation','state',0) as nav>
					<a href="${nav.url?replace('#path',path)}">${nav.name}</a>
				</#list>
			</div>
		</div>
	</div>
	<div class="headerMiddle">
		<div class="headerInfo"> 
			7×24小时服务热线: <strong>${query.get('property','phone').value}</strong>
		</div>
		<div class="headerLogo">
			<a href="${base}/"><img class="belatedPNG" src="${base}/${query.get('property','logo').value}" alt="${query.get('property','name').value}" /></a>
		</div>
		<div class="headerSearch belatedPNG">			
			<@s.form id="goodsSearchForm" action="goods_to_search" validate="false">
				<div class="headerSearchText">
					<@s.textfield id="goodsSearchKeyword" name="entity.name"/> 
				</div>
				<@s.submit cssClass="headerSearchButton" value="" /> 
				<div class="hotKeyword">
					热门关键词: 
					<#list query.get('property','search').value.split(',') as search> 
						<a href="${base}<@s.url action="goods_to_search"/>?entity.name=${search}">${search}</a>
					</#list>
				</div>
			</@s.form>
		</div>
	</div>
	<div class="headerBottom">
		<input type="button" class="cartItemListButton" value="" onclick="window.open('${base}/trolley_list.htm')" />
		<input type="button" class="orderButton" value="" onclick="window.open('${base}/trolley_list.htm')" />
		<div class="headerMiddleNav">
			<div class="headerMiddleNavLeft belatedPNG"></div>
			<ul class="headerMiddleNavContent belatedPNG">
				<#list query.list('navigation','state',1) as nav>
					<li>
						<a href="${nav.url?replace('#path',path)}">${nav.name}</a>
					</li>
				</#list> 
			</ul>
			<div class="headerMiddleNavRight belatedPNG"></div>
		</div>
	</div>
</div>