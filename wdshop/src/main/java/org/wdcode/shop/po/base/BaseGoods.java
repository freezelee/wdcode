package org.wdcode.shop.po.base;

import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

import org.wdcode.base.entity.base.BaseEntityIdTime;

/**
 * 基础物品实体
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-30
 */
@MappedSuperclass
public abstract class BaseGoods extends BaseEntityIdTime {
	// 序列化ID
	private static final long	serialVersionUID	= 4750630915629790582L;
	// 编号
	private String				sn;
	// 价格
	private BigDecimal			price;
	// 成本价
	private BigDecimal			cost;
	// 市场价
	private BigDecimal			market;
	// 库存
	private Integer				store;
	// 重量
	private Integer				weight;
	// 上架
	private Boolean				markeTable;

	/**
	 * 获得是否上架
	 * @return 是否上架
	 */
	public Boolean getMarkeTable() {
		return markeTable;
	}

	/**
	 * 设置是否上架
	 * @param markeTable 是否上架
	 */
	public void setMarkeTable(Boolean markeTable) {
		this.markeTable = markeTable;
	}

	/**
	 * 获得编号
	 * @return 编号
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * 设置编号
	 * @param sn 编号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * 获得市场价
	 * @return 市场价
	 */
	public BigDecimal getMarket() {
		return market;
	}

	/**
	 * 设置市场价
	 * @param market 市场价
	 */
	public void setMarket(BigDecimal market) {
		this.market = market;
	}

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
	 * 获得成本
	 * @return 成本
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * 设置成本
	 * @param cost 成本
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	/**
	 * 获得库存
	 * @return 库存
	 */
	public Integer getStore() {
		return store;
	}

	/**
	 * 设置库存
	 * @param store 库存
	 */
	public void setStore(Integer store) {
		this.store = store;
	}

	/**
	 * 获得重量
	 * @return 重量
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * 设置重量
	 * @param weight 重量
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
