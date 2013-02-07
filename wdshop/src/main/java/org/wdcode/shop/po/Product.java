package org.wdcode.shop.po;

import java.util.List;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.shop.po.Specification.SpecificationValue;
import org.wdcode.shop.po.base.BaseGoods;

/**
 * 商品实体
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-30
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Product extends BaseGoods {
	// 序列化ID
	private static final long			serialVersionUID	= -6212881532970956336L;
	// 规格值
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	private List<SpecificationValue>	specificationValues;
	// 商品ID
	private Integer						goodsId;
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
	 * 获得规格值
	 * @return 规格值
	 */
	public List<SpecificationValue> getSpecificationValues() {
		return specificationValues;
	}

	/**
	 * 设置规格值
	 * @param specificationValues 规格值
	 */
	public void setSpecificationValue(List<SpecificationValue> specificationValues) {
		this.specificationValues = specificationValues;
	}

	/**
	 * 获得商品ID
	 * @return 商品ID
	 */
	public Integer getGoodsId() {
		return goodsId;
	}

	/**
	 * 设置商品ID
	 * @param goodsId 商品ID
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
}
