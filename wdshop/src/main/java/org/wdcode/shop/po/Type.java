package org.wdcode.shop.po;

import java.io.Serializable;
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
import org.wdcode.base.entity.base.BaseEntityIdTime;

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
public final class Type extends BaseEntityIdTime {
	// 序列化ID
	private static final long			serialVersionUID	= 7400001160740524339L;
	// 参数
	@org.hibernate.annotations.Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	private List<GoodsTypeParam>		params;
	// 属性
	@org.hibernate.annotations.Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	private List<GoodsTypeAttribute>	attributes;
	// 品牌
	@ManyToMany
	@JoinTable(name = "goods_type_brand", joinColumns = @JoinColumn(name = "type_id"), inverseJoinColumns = @JoinColumn(name = "brand_id"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Brand>					brands;
	// 名称
	private String						name;

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
	 * 获得品牌
	 * @return 品牌
	 */
	public List<Brand> getBrands() {
		return brands;
	}

	/**
	 * 设置品牌
	 * @param brands 品牌
	 */
	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	/**
	 * 获得 参数
	 * @return 参数
	 */
	public List<GoodsTypeParam> getParams() {
		return params;
	}

	/**
	 * 设置 参数
	 * @param params 参数
	 */
	public void setParams(List<GoodsTypeParam> params) {
		this.params = params;
	}

	/**
	 * 获得属性
	 * @return 属性
	 */
	public List<GoodsTypeAttribute> getAttributes() {
		return attributes;
	}

	/**
	 * 设置属性
	 * @param attributes 属性
	 */
	public void setAttributes(List<GoodsTypeAttribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * 商品类型属性
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-05-18
	 */
	public static final class GoodsTypeAttribute implements Serializable {
		// 序列化ID
		private static final long	serialVersionUID	= -1800585332407824878L;
		// 名称
		private String				name;
		// 选项值
		private String				options;
		// 类型
		private Integer				type;

		/**
		 * 获得类型
		 * @return 类型
		 */
		public Integer getType() {
			return type;
		}

		/**
		 * 设置类型
		 * @param type 类型
		 */
		public void setType(Integer type) {
			this.type = type;
		}

		/**
		 * 获得选项值
		 * @return 选项值
		 */
		public String getOptions() {
			return options;
		}

		/**
		 * 设置选项值
		 * @param option 选项值
		 */
		public void setOptions(String option) {
			this.options = option;
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
	}

	/**
	 * 商品类型参数
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-05-18
	 */
	public static final class GoodsTypeParam implements Serializable {
		// 序列化ID
		private static final long	serialVersionUID	= -2879142228931583259L;
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
	}

}
