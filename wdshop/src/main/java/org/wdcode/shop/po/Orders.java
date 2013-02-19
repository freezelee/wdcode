package org.wdcode.shop.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityUser;
import org.wdcode.base.entity.base.BaseEntityTime;
import org.wdcode.common.lang.Conversion;

/**
 * 物品订单
 * @author WD
 * @since JDK7
 * @version 1.0 2012-08-08
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
public final class Orders extends BaseEntityTime implements EntityUser {
	// 序列化ID
	private static final long	serialVersionUID	= -3180493969291864355L;
	// 编号
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	private String				sn;
	// 用户ID
	private Integer				userId;
	// 配送方式
	private Integer				dispatchId;
	// 支付类型
	private String				pay;
	// 收货地址
	private Integer				receiverId;
	// 备注
	private String				detail;
	// 状态
	private Integer				status;
	// 总价格
	private BigDecimal			total;
	// 物品列表
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	private List<Products>		products;

	/**
	 * 获得收货地址
	 * @return 收货地址
	 */
	public Integer getReceiverId() {
		return receiverId;
	}

	/**
	 * 设置收货地址
	 * @param receiverId 收货地址
	 */
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	/**
	 * 获得物品列表
	 * @return 物品列表
	 */
	public List<Products> getProducts() {
		return products;
	}

	/**
	 * 设置物品列表
	 * @param products 物品列表
	 */
	public void setProducts(List<Products> products) {
		this.products = products;
	}

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
	 * 获得配送方式
	 * @return 配送方式
	 */
	public Integer getDispatchId() {
		return dispatchId;
	}

	/**
	 * 设置配送方式
	 * @param dispatchId 配送方式
	 */
	public void setDispatchId(Integer dispatchId) {
		this.dispatchId = dispatchId;
	}

	/**
	 * 获得支付类型
	 * @return 支付类型
	 */
	public String getPay() {
		return pay;
	}

	/**
	 * 设置支付类型
	 * @param pay 支付类型
	 */
	public void setPay(String pay) {
		this.pay = pay;
	}

	/**
	 * 获得备注
	 * @return 备注
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置备注
	 * @param detail 备注
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * 获得状态
	 * @return 状态
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 设置状态
	 * @param status 状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	 * 收货人
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-08-08
	 */
	public static final class Products implements Serializable {
		// 序列化ID
		private static final long	serialVersionUID	= 6928825363625135992L;
		// 主键
		private int					id;
		// 名称
		private String				name;
		// 价钱
		private BigDecimal			price;
		// 总价钱
		private BigDecimal			total;
		// 数量
		private int					count;

		/**
		 * 获得主键
		 * @return 主键
		 */
		public int getId() {
			return id;
		}

		/**
		 * 设置主键
		 * @param id 主键
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * 获得名称
		 * @return 名称
		 */
		public String getName() {
			return name;
		}

		/**
		 * 设置名称
		 * @param name 名称
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 获得总价钱
		 * @return 总价钱
		 */
		public BigDecimal getTotal() {
			return total;
		}

		/**
		 * 设置总价钱
		 * @param total 总价钱
		 */
		public void setTotal(BigDecimal total) {
			this.total = total;
		}

		/**
		 * 获得价钱
		 * @return 价钱
		 */
		public BigDecimal getPrice() {
			return price;
		}

		/**
		 * 设置价钱
		 * @param price 价钱
		 */
		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		/**
		 * 获得数量
		 * @return 数量
		 */
		public int getCount() {
			return count;
		}

		/**
		 * 设置数量
		 * @param count 数量
		 */
		public void setCount(int count) {
			this.count = count;
		}
	}

	@Override
	public Serializable getKey() {
		return sn;
	}

	@Override
	public void setKey(Serializable key) {
		this.sn = Conversion.toString(key);
	}
}
