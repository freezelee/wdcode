package org.wdcode.cms.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.base.BaseEntityId;
import org.wdcode.base.entity.simple.EntityPage;

/**
 * 栏目实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-17
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Category extends BaseEntityId {
	// 序列化ID
	private static final long	serialVersionUID	= -7705356910929929129L;
	// 上级栏目ID
	private Integer				categoryId;
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
	 * 获得页面信息
	 * @return 页面信息PageInfoBean
	 */
	public EntityPage getPage() {
		return page;
	}

	/**
	 * 设置页面信息
	 * @param page 页面信息 PageInfoBeanpublic void setPage(PageInfoBean page) { this.page = page; } /**
	 * 获得上级栏目ID
	 * @return 上级栏目ID
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * 设置上级栏目ID
	 * @param categoryId 上级栏目ID
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
}
