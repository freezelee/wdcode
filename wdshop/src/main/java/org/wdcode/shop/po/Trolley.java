package org.wdcode.shop.po;

import java.math.BigDecimal;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityUser;
import org.wdcode.base.entity.base.BaseEntityIdTime;

/**
 * 购物车实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-10-24
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Trolley extends BaseEntityIdTime implements EntityUser {
	// 序列化ID
	private static final long	serialVersionUID	= 312764391651849920L;
	// 购买物品ID
	private Integer				goodsId;
	// 产品ID
	private Integer				productId;
	// 购买物品数量
	private Integer				count;
	// 用户ID
	private Integer				userId;
	// 价格
	private BigDecimal			price;
	// 总价格
	private BigDecimal			total;

	/**
	 * 获得价格
	 * @return 价格
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置价格
	 * @param price 价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 获得总价格
	 * @return 总价格
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * 设置总价格
	 * @param total 总价格
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * 获得用户ID
	 * @return 用户ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获得购买物品数量
	 * @return 购买物品数量
	 */
	public Integer getGoodsId() {
		return goodsId;
	}

	/**
	 * 设置购买物品数量
	 * @param goodsId 购买物品数量
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * 获得产品ID
	 * @return 产品ID
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * 设置产品ID
	 * @param productId 产品ID
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * 获得购买物品数量
	 * @return 购买物品数量
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 设置购买物品数量
	 * @param count 购买物品数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
}
