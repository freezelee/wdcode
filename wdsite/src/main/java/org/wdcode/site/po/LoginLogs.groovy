package org.wdcode.site.po

import javax.persistence.Entity

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityIp
import org.wdcode.base.entity.EntityUser
import org.wdcode.base.entity.base.BaseEntityIdTime

/**
 * 登录日志实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-03
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
class LoginLogs extends BaseEntityIdTime implements EntityUser, EntityIp {
	// 登录IP
	String				ip
	// 用户User_Agent
	String				userAgent
	// 语言
	String				language
	// 用户ID
	Integer				userId
	// 状态
	Integer				state
	// 名称
	String				name
}
