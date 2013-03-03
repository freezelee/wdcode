package org.wdcode.shop.po

import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.Type
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.base.BaseEntityIdTime
import org.wdcode.base.entity.simple.EntityPage

/**
 * 物品分类
 * @author WD
 * @since JDK7
 * @version 1.0 2012-01-19
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
class Sort extends BaseEntityIdTime {
	// 分类ID
	Integer				sortId
	// 商品类型
	Integer				typeId
	// 页面信息
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	EntityPage			page
	// 名称
	String				name
}
