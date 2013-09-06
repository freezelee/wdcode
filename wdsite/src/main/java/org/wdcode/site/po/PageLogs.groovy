package org.wdcode.site.po

import javax.persistence.Entity

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityIp;
import org.wdcode.base.entity.EntityUser;
import org.wdcode.common.util.DateUtil
import org.wdcode.site.entity.base.BaseEntityIdTime;

/**
 * 登录日志实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-03
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
class PageLogs extends BaseEntityIdTime implements EntityUser, EntityIp {
	// 页面
	String				page
	// 来源
	String				referrer
	// 离开时间
	Integer				outTime
	// 登录IP
	String				ip
	// 用户User_Agent
	String				userAgent
	// 语言
	String				language
	// 用户ID
	Serializable				userId
	// 名称
	String				name
}
