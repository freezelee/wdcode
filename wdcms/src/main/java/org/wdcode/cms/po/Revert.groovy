package org.wdcode.cms.po

import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityIp;
import org.wdcode.base.entity.EntityUser;
import org.wdcode.site.entity.base.BaseEntityIdTime;

/**
 * 回复留言实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-25
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
class Revert extends BaseEntityIdTime implements EntityIp, EntityUser {
	// 留言ID
	Integer				leaveId
	// IP
	String				ip
	// 内容
	String				content
	// 用户ID
	Serializable				userId
	// 名称
	String				name
}