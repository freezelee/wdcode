package org.wdcode.shop.po

import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityUser;
import org.wdcode.site.entity.base.BaseEntityId;

/**
 * 收货地址
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-26
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
class Receiver extends BaseEntityId implements EntityUser {
	// 名称
	String				name
	// 用户ID
	Serializable				userId
	// 地区ID
	Integer				areaId
	// 手机
	String				mobile
	// 电话
	String				phone
	// 收货地址
	String				address
	// 邮编
	Integer				zipCode
}
