package org.wdcode.shop.po

import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.base.BaseEntityId
import org.wdcode.site.entity.EntityUser;

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
class Favorite extends BaseEntityId implements EntityUser {
	// 用户ID
	Integer				userId
	// 商品ID
	Integer				goodsId
}
