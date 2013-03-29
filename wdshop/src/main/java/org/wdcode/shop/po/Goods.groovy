package org.wdcode.shop.po

import java.io.Serializable
import java.util.List

import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.Type
import org.hibernate.search.annotations.Field
import org.hibernate.search.annotations.Indexed
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.EntityFiles
import org.wdcode.common.constants.ArrayConstants
import org.wdcode.common.lang.Lists
import org.wdcode.common.util.EmptyUtil
import org.wdcode.site.entity.EntityPage
import org.wdcode.site.entity.base.BaseEntityIdTime

/**
 * 物品
 * @author WD
 * @since JDK7
 * @version 1.0 2010-12-13
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Indexed
class Goods extends BaseEntityIdTime implements EntityFiles{
	// 名称
	@Field
	String					name
	// 编号
	String					sn
	// 价格
	BigDecimal				price
	// 成本价
	BigDecimal				cost
	// 市场价
	BigDecimal				market
	// 库存
	Integer					store
	// 重量
	Integer					weight
	// 上架
	Boolean					markeTable
	// 类别
	Integer					sortId
	// 品牌ID
	Integer					brandId
	// 类型
	Integer					typeId
	// 简介
	String					brief
	// 描述
	String					detail
	// 精品
	Boolean					best
	// 新品
	Boolean					newIn
	// 热销
	Boolean					hot
	// 是否启用规格
	Boolean					specificationEnabled
	// 页面信息
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	EntityPage				page
	// 商品图片
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	List<GoodsImage>		images
	// 商品属性
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	List<GoodsAttribute>	attributes
	// 商品参数
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	List<GoodsParam>		params
	// 商品规格
	@ManyToMany
	@JoinTable(name = "goods_specification", joinColumns = @JoinColumn(name = "goods_id"), inverseJoinColumns = @JoinColumn(name = "sid"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	List<Specification>		specifications

	/**
	 * 获得路径数组
	 */
	public String[] getPaths() {
		if (!EmptyUtil.isEmpty(images)) {
			// 获得值长度
			int size = images.size()
			// 声明数组
			String[] paths = new String[size]
			// 循环赋值
			for (int i = 0; i < size; i++) {
				paths[i] = images.get(i).getPath()
			}
			// 返回数组
			return paths
		} else {
			return ArrayConstants.STRING_EMPTY
		}
	}

	/**
	 * 设置路径数组
	 */
	public void setPaths(String[] paths) {
		// 设置路径
		for (int i = 0; i < paths.length; i++) {
			images.get(i).setPath(paths[i])
		}
	}

	/**
	 * 商品参数
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-03-28
	 */
	static  class GoodsParam implements Serializable {
		// 值
		String				value
		// 名称
		String				name
	}

	/**
	 * 商品属性
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-03-28
	 */
	static class GoodsAttribute implements Serializable {
		// 值
		String				value
		// 名称
		String				name
	}

	/**
	 * 商品图片
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-04-19
	 */
	static class GoodsImage implements Serializable {
		// 图片路径
		String				path
		// 描述
		String				description
	}
}
