package org.wdcode.cms.po

import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.Type
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.base.BaseEntityIdTime
import org.wdcode.site.entity.EntityPage;

/**
 * 文章实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-17
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
class Article extends BaseEntityIdTime {
	// 栏目ID
	Integer				categoryId
	// 作者
	String				author
	// 内容
	String				content
	// 推荐
	Integer				recommend
	// 置顶
	Integer				top
	// 页面信息
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	EntityPage			page
	// 状态
	Integer				state
	// 名称
	String				name
}
