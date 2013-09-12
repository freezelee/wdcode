package org.wdcode.shop.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.wdcode.base.entity.Entity;
import org.wdcode.cms.po.User;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.ArrayUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.pay.bean.PayBean;
import org.wdcode.pay.interfaces.Pay;
import org.wdcode.pay.lang.Pays;
import org.wdcode.shop.po.Orders;
import org.wdcode.shop.po.Receiver;
import org.wdcode.shop.po.Trolley;
import org.wdcode.site.action.UserAction;

/**
 * 商店Action
 * @author WD
 * @since JDK7
 * @version 1.0 2012-08-15
 */
@Controller
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShopAction extends UserAction<Entity, User> {
	// 序列化ID
	private static final long	serialVersionUID	= 1509252817532163575L;
	// 支付构造器
	@Resource
	private Pays				pays;
	// 收货地址
	private Receiver			receiver;
	// 订单
	private Orders				order;

	/**
	 * 添加订单
	 */
	@Transactional
	public String orderAdd() throws Exception {
		// 添加的实体和收货地址为空
		if (receiver == null || order == null) {
			return ERROR;
		}
		// 处理收货地址
		if (receiver.getId() == 0) {
			// 添加
			service.insert(receiver);
		}
		order.setReceiverId(receiver.getId());
		service.insert(add(order));
		// 清空自己的购物车购物车
		Trolley trolley = new Trolley();
		trolley.setUserId(token.getId());
		service.delete(ArrayUtil.toArray(service.list(trolley, -1, -1)));
		// 返回成功
		return SUCCESS;
	}

	/**
	 * 支付订单
	 */
	@Transactional
	public String orderPay() throws Exception {
		// 判断有订单
		if (order != null && !EmptyUtil.isEmpty(order.getSn())) {
			// 查询出自己的订单
			order = service.get(Orders.class, order.getKey());
			// 是自己的订单
			if (order.getUserId() == token.getId()) {
				// 配置支付属性
				PayBean payment = new PayBean();
				payment.setBody(StringConstants.EMPTY);
				payment.setNo(order.getSn());
				payment.setNotifyUrl(StringConstants.EMPTY);
				payment.setReturnUrl(StringConstants.EMPTY);
				payment.setSubject(StringConstants.EMPTY);
				payment.setTotal(order.getTotal());
				// 获得支付器
				Pay pay = pays.get(order.getPay());
				// 判断支付方式
				if (pay.isOnline()) {
					// 在线支付
					getResponse().sendRedirect(Conversion.toString(pay.pay(payment)));
				} else if (pay.isAuto()) {
					// 自动支付 是否支付成功
					if (Conversion.toBoolean(pay.pay(payment))) {
						// 支付成功
						order.setStatus(1);
						service.update(order);
					}
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 在线支付回调
	 */
	@Transactional
	public String orderTrade() throws Exception {
		// 获得支付器
		Pay pay = pays.get(Conversion.toString(key));
		// 获得支付订单号
		String no = pay.trade(getRequest());
		// 是否支付成功
		if (!EmptyUtil.isEmpty(no)) {
			// 查询出自己的订单
			order = service.get(Orders.class, no);
			// 更新成功
			order.setStatus(1);
			service.update(order);
		}
		return SUCCESS;
	}

	/**
	 * 获得订单
	 * @return 订单
	 */
	public Orders getOrder() {
		return order;
	}

	/**
	 * 设置订单
	 * @param order
	 */
	public void setOrder(Orders order) {
		this.order = order;
	}

	/**
	 * 获得收货地址
	 * @return 收货地址
	 */
	public Receiver getReceiver() {
		return receiver;
	}

	/**
	 * 设置收货地址
	 * @param receiver 收货地址
	 */
	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	/**
	 * 获得支付构造器
	 * @return 支付构造器
	 */
	public Pays getPays() {
		return pays;
	}
}
