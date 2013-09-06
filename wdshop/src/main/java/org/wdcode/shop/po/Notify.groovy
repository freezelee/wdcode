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
 * 收藏夹
 * @author WD
 * @since JDK7
 * @version 1.0 2012-9-3
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
class Notify extends BaseEntityIdTime implements EntityUser {
	// 用户ID
	Serializable				userId
	// 商品ID
	Integer				goodsId
	// 产品ID
	Integer				productId
}
