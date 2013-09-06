package org.wdcode.shop.po


import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityUser;
import org.wdcode.site.entity.base.BaseEntityIdTime;

/**
 * 购物车实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-10-24
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
class Trolley extends BaseEntityIdTime implements EntityUser {
	// 购买物品ID
	Integer				goodsId
	// 产品ID
	Integer				productId
	// 购买物品数量
	Integer				count
	// 用户ID
	Serializable				userId
	// 价格
	BigDecimal			price
	// 总价格
	BigDecimal			total
}
