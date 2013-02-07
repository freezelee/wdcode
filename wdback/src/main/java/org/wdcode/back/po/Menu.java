package org.wdcode.back.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.base.BaseEntityId;

/**
 * 菜单实体
 * @author WD
 * @since JDK7
 * @version 1.0 2009-11-23
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Menu extends BaseEntityId {
	// 序列化ID
	private static final long	serialVersionUID	= -1221717566590951843L;
	// 上级菜单ID
	private Integer				menuId;
	// 链接
	private String				url;
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
	 * 获得上级菜单ID
	 * @return 上级菜单ID
	 */
	public Integer getMenuId() {
		return menuId;
	}

	/**
	 * 设置上级菜单ID
	 * @param menuId 上级菜单ID
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	/**
	 * 获得URL
	 * @return URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置URL
	 * @param url URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}
