package org.wdcode.site.po;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Indexed;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.wdcode.base.entity.base.BaseEntityIdTime;

/**
 * 通用实体类
 * @author WD
 * @since JDK7
 * @version 1.0 2013-01-10
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Indexed
public final class Entitys extends BaseEntityIdTime {
	private static final long	serialVersionUID	= 8454766463153787074L;
	// 实体名
	private String				entity;
	// 名称
	private String				name;
	// 状态
	private Integer				state;
	// List属性
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	private List<Object>		list;
	// Map属性
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	private Map<Object, Object>	map;

	public Entitys() {}

	public Entitys(String entity) {
		this.entity = entity;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public Map<Object, Object> getMap() {
		return map;
	}

	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}
}
