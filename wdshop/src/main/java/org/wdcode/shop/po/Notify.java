package org.wdcode.shop.po;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityUser;
import org.wdcode.base.entity.base.BaseEntityId;

/**
 * 收藏夹
 * @author WD
 * @since JDK7
 * @version 1.0 2012-9-3
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Notify extends BaseEntityId implements EntityUser {
	// 序列化ID
	private static final long	serialVersionUID	= -3794189451106402802L;
	// 用户ID
	private Integer				userId;
	// 商品ID
	private Integer				goodsId;
	// 产品ID
	private Integer				productId;

	/**
	 * 获得用户ID
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 设置用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
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
}
