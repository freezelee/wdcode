package org.wdcode.site.po

import java.io.Serializable

import javax.persistence.Entity
import javax.persistence.Id

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityIp
import org.wdcode.base.entity.EntityUser
import org.wdcode.common.lang.Conversion
import org.wdcode.common.util.EmptyUtil
import org.wdcode.site.entity.base.BaseEntityTime

/**
 * 页面统计实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-02
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
class PageStatistics extends BaseEntityTime implements EntityIp, EntityUser {
	// 页面
	@Id
	String				page
	// 用户ID
	Serializable				userId
	// 登录次数
	Integer				count
	// 登录IP
	String				ip

	/**
	 * 获得键
	 */
	public Serializable getKey() {
		return page
	}

	/**
	 * 设置键
	 */
	public void setKey(Serializable key) {
		this.page = Conversion.toString(key)
	}
}
