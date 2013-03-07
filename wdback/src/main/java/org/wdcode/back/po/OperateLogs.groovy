package org.wdcode.back.po

import javax.persistence.Entity

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.base.BaseEntityIdTime
import org.wdcode.site.entity.EntityUser

/**
 * 操作日志实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-03
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
class OperateLogs extends BaseEntityIdTime implements EntityUser {
	// 内容
	String				content
	// 用户ID
	Integer				userId
	// 状态
	Integer				state
	// 名称
	String				name
}