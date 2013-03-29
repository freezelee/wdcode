package org.wdcode.shop.po

import java.io.Serializable 

import javax.persistence.Entity
import javax.persistence.Id

import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.common.lang.Conversion
import org.wdcode.site.entity.base.BaseEntity;

/**
 * 金币 直接与现金有挂钩的
 * @author WD
 * @since JDK6
 * @version 1.0 2010-11-10
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
class Money extends BaseEntity implements Serializable {
	// 用户ID
	@Id
	int					userId
	// 金钱
	BigDecimal			money
	// 状态
	Integer				state

	@Override
	public Serializable getKey() {
		return userId
	}

	@Override
	public void setKey(Serializable key) {
		this.userId = Conversion.toInt(userId)
	}
}
