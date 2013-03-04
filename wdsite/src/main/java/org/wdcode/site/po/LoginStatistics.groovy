package org.wdcode.site.po

import java.io.Serializable

import javax.persistence.Entity
import javax.persistence.Id

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityIp
import org.wdcode.base.entity.base.BaseEntityTime
import org.wdcode.common.constants.StringConstants
import org.wdcode.common.lang.Conversion
import org.wdcode.common.util.DateUtil
import org.wdcode.common.util.EmptyUtil

/**
 * 登录统计实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-25
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
class LoginStatistics extends BaseEntityTime implements EntityIp {
	// 用户ID
	@Id
	int					userId
	// 上次登录时间
	Integer				lastTime
	// 上次登录IP
	String				lastIp
	// 登录次数
	Integer				count
	// 登录IP
	String				ip
	// 名称
	String				name

	/**
	 * 获得主键
	 */
	public Serializable getKey() {
		return getUserId()
	}

	/**
	 * 设置主键
	 */
	public void setKey(Serializable key) {
		setUserId(Conversion.toInt(key))
	}
}
