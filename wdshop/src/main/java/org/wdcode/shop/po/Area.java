package org.wdcode.shop.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wdcode.base.entity.base.BaseEntityId;

/**
 * 地区
 * @author WD
 * @since JDK7
 * @version 1.0 2012-01-10
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Area extends BaseEntityId {
	// 序列化ID
	private static final long	serialVersionUID	= 8048940122766081257L;
	// 地区显示名
	private String				display;
	// 地区ID
	private Integer				areaId;
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
	 * 获得地区显示名
	 * @return 地区显示名
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * 设置地区显示名
	 * @param display 地区显示名
	 */
	public void setDisplay(String display) {
		this.display = display;
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
}
