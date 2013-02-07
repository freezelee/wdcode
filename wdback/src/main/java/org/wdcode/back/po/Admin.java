package org.wdcode.back.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityLogin;
import org.wdcode.base.entity.base.BaseEntityIdTime;
import org.wdcode.common.crypto.Digest;

/**
 * 管理员
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-29
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Admin extends BaseEntityIdTime implements EntityLogin {
	// 序列化ID
	private static final long	serialVersionUID	= 1332903464708011107L;
	// 名称
	private String				name;
	// 密码
	private String				password;
	// 是否启用
	private Integer				state;
	// 权限
	private Integer				roleId;

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
	 * 设置用户密码
	 * @param password 用户密码
	 */
	public void setPassword(String password) {
		this.password = Digest.absolute(password);
	}

	/**
	 * 获得密码
	 * @return 用户密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 获得角色ID
	 * @return 角色ID
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * 设置角色ID
	 * @param roleId 角色ID
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
