package org.wdcode.shop.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityUser;
import org.wdcode.base.entity.base.BaseEntityId;

/**
 * 收货地址
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-26
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Receiver extends BaseEntityId implements EntityUser {
	// 序列化ID
	private static final long	serialVersionUID	= 8668888858190077491L;
	// 名称
	private String				name;
	// 用户ID
	private Integer				userId;
	// 地区ID
	private Integer				areaId;
	// 手机
	private String				mobile;
	// 电话
	private String				phone;
	// 收货地址
	private String				address;
	// 邮编
	private Integer				zipCode;

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
	 * 获得用户ID
	 * @return 用户ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获得地区ID
	 * @return 地区ID
	 */
	public Integer getAreaId() {
		return areaId;
	}

	/**
	 * 设置地区ID
	 * @param areaId 地区ID
	 */
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	/**
	 * 获得手机
	 * @return 手机
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机
	 * @param mobile 手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获得电话
	 * @return 电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置电话
	 * @param phone 电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获得收货地址
	 * @return 收货地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置收货地址
	 * @param address 收货地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获得邮编
	 * @return 邮编
	 */
	public Integer getZipCode() {
		return zipCode;
	}

	/**
	 * 设置邮编
	 * @param zipCode 邮编
	 */
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
}
