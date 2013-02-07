package org.wdcode.back.po;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.base.BaseEntityId;

/**
 * 角色实体
 * @author WD
 * @since JDK7
 * @version 1.0 2009-11-23
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Role extends BaseEntityId {
	// 序列化ID
	private static final long	serialVersionUID	= 1991102007435349353L;
	// 名称
	private String				name;
	// 操作列表
	@ManyToMany
	@JoinTable(name = "role_operate", joinColumns = @JoinColumn(name = "operate_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Operate>		operates;
	// 菜单
	@ManyToMany
	@JoinTable(name = "role_menu", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Menu>			menus;
	// 菜单
	@ManyToMany
	@JoinTable(name = "role_authority", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "authority_id"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Authority>		authorities;

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

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
	 * 获得菜单
	 * @return 菜单
	 */
	public List<Menu> getMenus() {
		return menus;
	}

	/**
	 * 设置菜单
	 * @param menus 菜单
	 */
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	/**
	 * 获得操作列表
	 * @return 操作列表
	 */
	public List<Operate> getOperates() {
		return operates;
	}

	/**
	 * 设置操作列表
	 * @param operates 操作列表
	 */
	public void setOperates(List<Operate> operates) {
		this.operates = operates;
	}
}