package org.wdcode.cms.po

import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.wdcode.site.entity.base.BaseEntityId;

/**
 * 友情链接
 * @author WD
 * @since JDK7
 * @version 1.0 2012-05-30
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
class FriendLink extends BaseEntityId {
	// 链接
	String				url
	// LOGO
	String				logo
	// 状态
	Integer				state
	// 名称
	String				name
}
