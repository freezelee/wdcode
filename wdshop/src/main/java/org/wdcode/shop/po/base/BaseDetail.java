package org.wdcode.shop.po.base;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

import org.wdcode.base.entity.EntityUser;
import org.wdcode.base.entity.base.BaseEntityIdTime;

/**
 * 基础账单
 * @author WD
 * @since JDK6
 * @version 1.0 2010-12-1
 */
@MappedSuperclass
public abstract class BaseDetail extends BaseEntityIdTime implements EntityUser {
	private static final long	serialVersionUID	= 8351520680559252370L;
	// 入账金钱
	protected BigDecimal		money;
	// 上次余额
	protected BigDecimal		overage;
	// 总数
	protected BigDecimal		amount;
	// 类型
	protected Short				type;
	// 操作用户ID
	protected Integer			opUid;
	// 说明
	protected String			description;
	// 描述
	protected String			detail;
	// 用户ID
	protected Integer			userId;

	@Override
	public Integer getUserId() {
		return userId;
	}

	@Override
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获得上次余额
	 * @return 上次余额
	 */
	public BigDecimal getOverage() {
		return overage;
	}

	/**
	 * 设置上次余额
	 * @param overage 上次余额
	 */
	public void setOverage(BigDecimal overage) {
		this.overage = overage;
	}

	/**
	 * 获得总数
	 * @return 总数
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * 设置总数
	 * @param amount 总数
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 获得类型
	 * @return 类型
	 */
	public Short getType() {
		return type;
	}

	/**
	 * 设置类型
	 * @param type 类型
	 */
	public void setType(Short type) {
		this.type = type;
	}

	/**
	 * 获得操作用户ID
	 * @return 操作用户ID
	 */
	public Integer getOpUid() {
		return opUid;
	}

	/**
	 * 设置操作用户ID
	 * @param opUid 操作用户ID
	 */
	public void setOpUid(Integer opUid) {
		this.opUid = opUid;
	}

	/**
	 * 获得说明
	 * @return 说明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 设置说明
	 * @param description 说明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 获得描述
	 * @return 描述
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置描述
	 * @param detail 描述
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * 获得金钱
	 * @return 金钱
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * 设置金钱
	 * @param money 金钱
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
}