package org.wdcode.shop.po

import java.io.Serializable
import java.util.List

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.site.entity.base.BaseEntityIdTime

/**
 * 商品类型
 * @author WD
 * @since JDK7
 * @version 1.0 2012-03-28
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@DynamicInsert
@DynamicUpdate
class Type extends BaseEntityIdTime {
	// 参数
	@org.hibernate.annotations.Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	List<GoodsTypeParam>		params
	// 属性
	@org.hibernate.annotations.Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	List<GoodsTypeAttribute>	attributes
	// 品牌
	@ManyToMany
	@JoinTable(name = "goods_type_brand", joinColumns = @JoinColumn(name = "type_id"), inverseJoinColumns = @JoinColumn(name = "brand_id"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	List<Brand>					brands
	// 名称
	String						name

	/**
	 * 商品类型属性
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-05-18
	 */
	static class GoodsTypeAttribute implements Serializable {
		// 名称
		String				name
		// 选项值
		String				options
		// 类型
		Integer				type
	}

	/**
	 * 商品类型参数
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-05-18
	 */
	static class GoodsTypeParam implements Serializable {
		// 名称
		String				name
	}

}
