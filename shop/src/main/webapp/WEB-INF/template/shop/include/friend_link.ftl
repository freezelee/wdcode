<div id="friendLink" class="friendLink">
	<div class="pictureFriendLink">
		<div class="left"></div>
		<div class="middle">
			<ul> 
				<#list query.list('friendLink','state',1) as f> 
					<li>
						<a href="${f.url}" title="${f.name}" target="_blank">
							<img src="${base }/${f.logo}">
						</a>
					</li>
				</#list>				
			</ul> 
		</div>
		<div class="right"></div>
	</div>
	<div class="textFriendLink">
		<div class="left"></div>
		<div class="middle">
			<ul>
				<#list query.list('friendLink','state',0) as f>  
					<li>
						<a href="${f.url}" title="${f.name}" target="_blank">${f.name}</a>
					</li>
				</#list> 
			</ul>
		</div>
		<div class="right"></div>
	</div>
</div>