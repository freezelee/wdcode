package org.wdcode.shop.po

import java.util.List

import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.Type
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.shop.po.Specification.SpecificationValue
import org.wdcode.site.entity.base.BaseEntityIdTime;

/**
 * 商品实体
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-30
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
class Product extends BaseEntityIdTime {
	// 规格值
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	List<SpecificationValue>	specificationValues
	// 商品ID
	Integer						goodsId
	// 名称
	String						name
	// 编号
	String						sn
	// 价格
	BigDecimal					price
	// 成本价
	BigDecimal					cost
	// 市场价
	BigDecimal					market
	// 库存
	Integer						store
	// 重量
	Integer						weight
	// 上架
	Boolean						markeTable
}