<script type="text/javascript">
$().ready( function() {

	var $deleteArea = $("#listTable .deleteArea");
	
	// 地区删除
	$deleteArea.click( function() {
		var $this = $(this);
		$.dialog({type: "warn", content: "您确定删除吗?", ok: "确 定", cancel: "取 消", modal: true, okCallback: deleteArea});
		function deleteArea() {
			var id = $this.attr("areaId");
			$.ajax({
				url: "area_del_ajax.htm",
				data: {"id": id},
				type: "POST",
				dataType: "json",
				success: function(data) {
					if (data == "success") {
						$this.parent().html("&nbsp;");
					}
					$.message({type: data.status, content: data.message});
				}
			});
		}
		return false;
	});
	
})
</script>
<tr>
	<th colspan="5" class="green" style="text-align: center;">
		<#if entity?? && entity.id &gt; 0>
		<#assign area=query.get('area',entity.id)>  
		上级地区 - [${(area.name)!}] 
		<#assign areaList=query.list('area','areaId',entity.id)>
		<#else>顶级地区
		<#assign areaList=query.list('area','areaId',0)>
		</#if>
	</th>
</tr>  
	<#list areaList as area>
		<#if (area_index + 1) == 1>
			<tr>
		</#if>
		<td>
			<a href="area_entity.htm?entity.id=${area.id}" title="查看下级地区">${area.name}</a>
			<a href="area_theme_edit.htm?entity.id=${area.id}&entity.areaId=${area.id}" title="编辑">[编辑]</a>
			<a href="#" class="deleteArea" title="删除" areaId="${area.id}">[删除]</a>
		</td>
		<#if (area_index + 1) % 5 == 0 && area_has_next>
			</tr>
			<tr>
		</#if>
		<#if (area_index + 1) % 5 == 0 && !area_has_next>
			</tr>
		</#if>
		<#if (area_index + 1) % 5 != 0 && !area_has_next>
				<td colspan="${5 - areaList?size % 5}">&nbsp;</td>
			</tr>
		</#if>
	</#list>
	<#if areaList?size == 0>
		<tr>
			<td colspan="5" style="text-align: center; color: red;">
				无下级地区! <a href="area_theme_add.htm<#if entity?? && entity.id &gt; 0>?entity.id=${entity.id}</#if>" style="color: gray">点击添加</a>
			</td>
		</tr>
	</#if>
</table>
<#if entity?? && entity.id &gt; 0>
	<div class="blank"></div>	
	<#if area.areaId??>
		<input type="button" class="formButton" onclick="location.href='area_entity.htm?entity.id=${area.areaId}'" value="上级地区" hidefocus />
	<#else>
		<input type="button" class="formButton" onclick="location.href='area_entity.htm'" value="上级地区" hidefocus />
	</#if>
</#if>