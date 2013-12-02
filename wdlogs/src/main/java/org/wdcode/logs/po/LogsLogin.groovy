package org.wdcode.logs.po

import javax.persistence.Entity

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityIp
import org.wdcode.base.entity.EntityShards
import org.wdcode.base.entity.EntityUserId
import org.wdcode.site.entity.base.BaseEntityIdTime

/**
 * 登录日志实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-03
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@DynamicInsert
@DynamicUpdate
class LogsLogin extends BaseEntityIdTime {
	// 登录IP
	String				ip
	// 用户ID
	Integer				userId
	// 状态
	Integer				state
	// 名称
	String				name
}
