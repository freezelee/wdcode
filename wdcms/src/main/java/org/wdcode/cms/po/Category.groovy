package org.wdcode.cms.po

import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.Type
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.base.BaseEntityId
import org.wdcode.site.entity.EntityPage;

/**
 * 栏目实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-17
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
class Category extends BaseEntityId {
	// 上级栏目ID
	Integer				categoryId
	// 页面信息
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	EntityPage			page
	// 名称
	String				name
}
