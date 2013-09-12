package org.wdcode.logs.po

import java.io.Serializable

import javax.persistence.Entity
import javax.persistence.Id

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityIp
import org.wdcode.base.entity.EntityUserId
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
@DynamicInsert
@DynamicUpdate
class PageStatistics extends BaseEntityTime {
	// 页面
	@Id
	String				page
	// 用户ID
	Integer				userId
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
