package org.wdcode.shop.po;

import java.math.BigDecimal;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.base.BaseEntityId;

/**
 * 配送方式
 * @author WD
 * @since JDK7
 * @version 1.0 2012-01-10
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public final class Dispatch extends BaseEntityId {
	// 序列化ID
	private static final long	serialVersionUID	= -4651833571330932984L;
	// 配送类型
	private Integer				type;
	// 物流公司
	private Integer				corpId;
	// 首重量
	private Double				firstWeight;
	// 续重量
	private Double				lastWeight;
	// 首价格
	private BigDecimal			firstPrice;
	// 续价格
	private BigDecimal			lastPrice;
	// 介绍
	private String				detail;
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
	 * 获得配送类型
	 * @return 配送类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 设置配送类型
	 * @param type 配送类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 获得物流公司
	 * @return 物流公司
	 */
	public Integer getCorpId() {
		return corpId;
	}

	/**
	 * 设置物流公司
	 * @param companyId 物流公司
	 */
	public void setCorpId(Integer companyId) {
		this.corpId = companyId;
	}

	/**
	 * 获得首重量
	 * @return 首重量
	 */
	public Double getFirstWeight() {
		return firstWeight;
	}

	/**
	 * 设置续重量
	 * @param firstWeight 续重量
	 */
	public void setFirstWeight(Double firstWeight) {
		this.firstWeight = firstWeight;
	}

	/**
	 * 获得首价格
	 * @return 首价格
	 */
	public Double getLastWeight() {
		return lastWeight;
	}

	/**
	 * 设置首价格
	 * @param lastWeight 首价格
	 */
	public void setLastWeight(Double lastWeight) {
		this.lastWeight = lastWeight;
	}

	/**
	 * 获得首价格
	 * @return 首价格
	 */
	public BigDecimal getFirstPrice() {
		return firstPrice;
	}

	/**
	 * 设置首价格
	 * @param firstPrice 首价格
	 */
	public void setFirstPrice(BigDecimal firstPrice) {
		this.firstPrice = firstPrice;
	}

	/**
	 * 获得续价格
	 * @return 续价格
	 */
	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	/**
	 * 设置续价格
	 * @param lastPrice 续价格
	 */
	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	/**
	 * 获得介绍
	 * @return 介绍
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置介绍
	 * @param detail 介绍
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
