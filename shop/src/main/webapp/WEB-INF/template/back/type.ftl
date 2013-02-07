<table id="${fields.get('table')!}" class="inputTable">
	<#list fields.get('field') as temMap> 
		<#if temMap.get('specificationValues')?? && entity?? && entity.specificationValues??> 
			<#list entity.specificationValues as spec> 
				<tr class="specificationValueTr">
					<td>&nbsp;</td>
					<td>
						<@s.hidden name="entity.specificationValues[${spec_index}].id"/>
						<@s.textfield name="entity.specificationValues[${spec_index}].name" class="formText specificationValueListName"/>
					</td>
					<td>
						<@s.file name="uploads" class="specificationValueImageList" disabled="%{#attr.entity.state==0?true:false}" value="${base}/${spec.val}" />
					</td>
					<td>
						<span class="deleteIcon deleteSpecificationValueIcon" title="删 除">&nbsp;</span>
					</td>
				</tr>
			</#list>
		<#elseif temMap.get('typeAttribute')??>
			<#list entity.attributes as attribute> 
				<tr class="goodsAttributeTr"> 
					<td>
						<@s.hidden name="entity.attributes[${attribute_index}].id"/> 
						<@s.textfield name="entity.attributes[${attribute_index}].name" cssClass="formText goodsAttributeListName"/>
					</td> 					
					<td>
					<!--	<@s.select list="{0:'筛选项',1:'输入项'}" name="entity.attributes[${attribute_index}].state" cssClass="attributeType"/> --> 
					</td>
					<td>
					<#if attribute.state==0>					
							<@s.textfield name="entity.attributes[${attribute_index}].options" cssClass="formText optionText goodsAttributeListOptionText" title="多个可选值请使用“,”分隔"/>
					</#if> &nbsp;
					</td> 
					<td> 
						<span class="deleteIcon deleteGoodsAttributeIcon" title="删 除">&nbsp;</span> 
					</td> 
				</tr>  
			</#list>	
		<#elseif temMap.get('typeParam')??>
			<#list entity.params as param> 
				<tr class="goodsParameterTr">
					<td>
						<@s.hidden name="entity.params[${param_index}].id"/> 
						<@s.textfield  name="entity.params[${param_index}].name" cssClass="formText goodsParameterListName"/> 
					</td> 
					<td> 
						<span class="deleteIcon deleteGoodsParameterIcon" title="删 除">&nbsp;</span> 
					</td> 
				</tr> 
			</#list>
		<#elseif temMap.get('goodsPhoto')?? && entity?? && entity.images??>
			<#list entity.images as image> 
				<tr class="goodsImageTr"> 
						<td> 							  
							<a href="${image.path}" title="点击查看" target="_blank"> 
								<img src="${image.path}" style="width: 50px; height: 50px;" /> 
							</a> 
						</td> 
						<td> 
							<@s.textfield name="entity.images[${image_index}].detail" cssClass="text"/> 
						</td>  
						<td> 
							<span class="deleteIcon deleteGoodsImage" title="删 除">&nbsp;</span> 
						</td> 
					</tr> 
			</#list>
		<#elseif temMap.get('goodsAttributeParam')??>
			<#if entity?? && entity.typeId?default(0) &gt; 0> 
			<div class="blank"></div>
			<table id="goodsAttributeTable" class="inputTable">
				<tr class="title">
					<th>商品属性</th>
					<td>&nbsp;</td>
				</tr> 
				<#list entity.type.attributes as a> 
				 	<tr class="goodsAttributeTr"> 
						<th>${a.name}:</th> 
						<td>
							<#if a.state==0>
								<@s.select list="options.split(',')" name="entity.attributes[${a_index}].value" />
							<#else>
								<@s.textfield name="entity.attributes[${a_index}].value" cssClass="text" />	
							</#if> 
						</td>
					</tr>
				</#list>	  
				<div class="blank"></div>
				<table id="goodsParameterTable" class="inputTable">
					<tr class="title">
						<th>商品参数</th>
						<td>&nbsp;</td>
					</tr>
					<#list entity.params as p> 
				 		<tr class="goodsParameterTr">
								<th>${p.name}:</th>
								<td>
									<@s.textfield name="entity.params[${p_index}.value" cssClass="text" />									
								</td> 
							</tr> 
					</#list>
				</table> 
				</#if>
		<#elseif temMap.get('role')??>
			<tr>
				<td colspan="2">
					&nbsp;
				</td>
			</tr>
			<tr class="authorityList"> 
				<th>
					<a href="#" class="allChecked" title="点击全选此类权限">商品管理: </a>
				</th>
				<td>
					<label>
						<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_GOODS"<#if (isAddAction || role.authorityList.contains("ROLE_GOODS"))!> checked</#if> />商品管理
					</label>
					<label>
						<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_GOODS_NOTIFY"<#if (isAddAction || role.authorityList.contains("ROLE_GOODS_NOTIFY"))!> checked</#if> />到货通知
					</label>
					<label>
						<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_GOODS_CATEGORY"<#if (isAddAction || role.authorityList.contains("ROLE_GOODS_CATEGORY"))!> checked</#if> />商品分类管理
					</label>
					<label>
						<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_GOODS_TYPE"<#if (isAddAction || role.authorityList.contains("ROLE_GOODS_TYPE"))!> checked</#if> />商品类型管理
					</label>
					<label>
						<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_SPECIFICATION"<#if (isAddAction || role.authorityList.contains("ROLE_SPECIFICATION"))!> checked</#if> />商品规格管理
					</label>
					<label>
						<input type="checkbox" name="role.authorityList" class="roleAuthorityList" value="ROLE_BRAND"<#if (isAddAction || role.authorityList.contains("ROLE_BRAND"))!> checked</#if> />商品品牌管理
					</label>
				</td>
			</tr>
		<#else>
			<tr class="${temMap.get('tr')!}">
				<#if temMap.get('th')??> 
					<#list temMap.get('th').split(';') as th> 
						<th>${action.getText(th)}</th>
					</#list>
				</#if>
				<#if temMap.get('td')??> 
					<#list temMap.get('td').split(';') as td> 
						<td>${action.getText(td)}</td>
					</#list>
				</#if> 
				<#if temMap.get('text')??>	 
					<th>${action.getText(temMap.get('text'))}<#if temMap.get('text')!=''>:</#if></th> 
				</#if>
				<#if temMap.get('type')??> 
					<td> 
						<@wd.type field=temMap/> 
					</td>   
				</#if>
			</tr>  
		</#if>  
	</#list>  
</table> 