package org.wdcode.site.po

import java.util.List
import java.util.Map

import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.Type
import org.hibernate.search.annotations.Indexed
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.wdcode.site.entity.base.BaseEntityIdTime;

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
class Entitys extends BaseEntityIdTime {
	// 实体名
	String				entity
	// 名称
	String				name
	// 状态
	Integer				state
	// List属性
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	List<Object>		list
	// Map属性
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	Map<Object, Object>	map

	public Entitys() {}

	public Entitys(String entity) {
		this.entity = entity
	}
}
