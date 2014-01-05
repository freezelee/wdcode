package org.wdcode.security.po

import javax.persistence.Entity

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.annotation.Cache
import org.wdcode.site.entity.base.BaseEntityId

/**
 * 菜单实体
 * @author WD
 * @since JDK7
 * @version 1.0 2009-11-23
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@DynamicInsert
@DynamicUpdate
@Cache
class Menu extends BaseEntityId {
	// 上级菜单ID
	Integer		menuId
	// 链接
	String		url
	// 名称
	String		name
	//类型
	Integer		type
}
