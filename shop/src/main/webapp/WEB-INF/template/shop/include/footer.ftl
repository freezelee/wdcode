<div class="footer">
	<div class="bottomNavigation">
		<dl>
		<#list query.list('navigation','state',2) as nav>
			<dd>
				<a href="${nav.url.replaceAll('#path',path)}">${nav.name}</a>
			</dd> 
			<#if (nav_index + 1) % 3 == 0 && nav_has_next>
				</dl>
				<dl>
			</#if> 
		</#list>
		</dl> 
	</div>
	<div class="footerInfo">
		<ul>
			<li><a href="#">关于商城</a>|</li>
			<li><a href="#">帮助中心</a>|</li>
			<li><a href="#">网站地图</a>|</li>
			<li><a href="#">诚聘英才</a>|</li>
			<li><a href="#">联系我们</a>|</li>
			<li><a href="#">版权说明</a></li>
		</ul>
		<p>Copyright &copy; 2011 SHOP++. All rights reserved. 长沙鼎诚软件有限公司</p>
		<p>Powered by <a class="systemName" href="http://www.shopxx.net" target="_blank">SHOP<span>++</span> V2.0</a></p>		
	</div>
</div>