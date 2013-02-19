package org.wdcode.shop.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.base.BaseEntityIdTime;

/**
 * 在线客服
 * @author WD
 * @since JDK7
 * @version 1.0 2012-08-27
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Customer extends BaseEntityIdTime {
	// 序列化ID
	private static final long	serialVersionUID	= 449986053623122622L;
	// 类型
	private Integer				type;
	// 联系方式
	private String				contact;
	// 名称
	private String				name;

	/**
	 * 获得名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得类型
	 * @return 类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置类型
	 * @param type 类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获得联系方式
	 * @return 联系方式
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * 设置联系方式
	 * @param contact 联系方式
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
}
