package org.wdcode.cms.po;

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
 * 文章实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-04-17
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Article extends BaseEntityIdTime {
	// 序列化ID
	private static final long	serialVersionUID	= -6046838685302048812L;
	// 栏目ID
	private Integer				categoryId;
	// 作者
	private String				author;
	// 内容
	private String				content;
	// 推荐
	private Integer				recommend;
	// 置顶
	private Integer				top;
	// 页面信息
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	private EntityPage			page;
	// 状态
	private Integer				state;
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
	 * 获得状态
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 设置状态
	 */
	public void setState(Integer state) {
		this.state = state;
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
	 * 获得作者
	 * @return 作者
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 设置作者
	 * @param author 作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * 获得推荐
	 * @return 推荐
	 */
	public Integer getRecommend() {
		return recommend;
	}

	/**
	 * 设置推荐
	 * @param recommend 推荐
	 */
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	/**
	 * 获得置顶
	 * @return 置顶
	 */
	public Integer getTop() {
		return top;
	}

	/**
	 * 设置置顶
	 * @param top 置顶
	 */
	public void setTop(Integer top) {
		this.top = top;
	}

	/**
	 * 获得栏目ID
	 * @return 栏目ID
	 */
	public Integer getCategoryId() {
		return categoryId;
	}

	/**
	 * 设置栏目ID
	 * @param categoryId 栏目ID
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * 获得内容
	 * @return 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
