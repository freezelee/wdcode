package org.wdcode.shop.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.base.BaseEntityIdTime;
import org.wdcode.base.entity.simple.EntityPage;

/**
 * 物品分类
 * @author WD
 * @since JDK7
 * @version 1.0 2012-01-19
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Sort extends BaseEntityIdTime {
	// 序列化ID
	private static final long	serialVersionUID	= 7360257780840331020L;
	// 分类ID
	private Integer				sortId;
	// 商品类型
	private Integer				typeId;
	// 页面信息
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	private EntityPage			page;
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
	 * 设置页面信息
	 * @param page 页面信息
	 */
	public void setPage(EntityPage page) {
		this.page = page;
	}

	/**
	 * 获得页面信息
	 * @return 页面信息PageInfoBean
	 */
	public EntityPage getPage() {
		return page;
	}

	/**
	 * 获得商品类型
	 * @return 商品类型
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * 设置商品类型
	 * @param typeId 商品类型
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	/**
	 * 获得分类ID
	 * @return 分类ID
	 */
	public Integer getSortId() {
		return sortId;
	}

	/**
	 * 设置分类ID
	 * @param sortId 分类ID
	 */
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}
}
