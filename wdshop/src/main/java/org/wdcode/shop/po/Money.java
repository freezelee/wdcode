package org.wdcode.shop.po;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.base.BaseEntity;
import org.wdcode.common.lang.Conversion;

/**
 * 金币 直接与现金有挂钩的
 * @author WD
 * @since JDK6
 * @version 1.0 2010-11-10
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
public final class Money extends BaseEntity implements Serializable {
	// 序列化ID
	private static final long	serialVersionUID	= 5986708906147018618L;
	// 用户ID
	@Id
	private int					userId;
	// 金钱
	private BigDecimal			money;
	// 状态
	private Integer				state;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	@Override
	public Serializable getKey() {
		return userId;
	}

	@Override
	public void setKey(Serializable key) {
		this.userId = Conversion.toInt(userId);
	}
}
