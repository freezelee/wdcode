package org.wdcode.shop.po


import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.site.entity.base.BaseEntityId;

/**
 * 配送方式
 * @author WD
 * @since JDK7
 * @version 1.0 2012-01-10
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
class Dispatch extends BaseEntityId {
	// 配送类型
	Integer				type
	// 物流公司
	Integer				corpId
	// 首重量
	Double				firstWeight
	// 续重量
	Double				lastWeight
	// 首价格
	BigDecimal			firstPrice
	// 续价格
	BigDecimal			lastPrice
	// 介绍
	String				detail
	// 名称
	String				name
}
