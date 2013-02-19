package org.wdcode.pay.interfaces;

import javax.servlet.http.HttpServletRequest;

import org.wdcode.pay.bean.Payment;

/**
 * 支付接口
 * @author WD
 * @since JDK6
 * @version 1.0 2012-11-30
 */
public interface Pay {
	/**
	 * 支付名称
	 * @return 支付名称
	 */
	String getName();

	/**
	 * 获得描述
	 * @return 描述
	 */
	String getDetail();

	/**
	 * 获得LOGO
	 * @return LOGO
	 */
	String getLogo();

	/**
	 * 是否是在线支付
	 * @return true 是 false 否
	 */
	boolean isOnline();

	/**
	 * 是否自动支付
	 * @return true 是 false 否
	 */
	boolean isAuto();

	/**
	 * 支付
	 * @param pay 支付实体
	 * @return 如果 isOnline 为 true 返回String url 用于跳转支付 如果 isAuto 为 true 返回是否成功
	 */
	Object pay(Payment pay);

	/**
	 * 支付返回调用方法 用着在线支付才会使用
	 * @param request HttpServletRequest
	 * @return 如果交易成功返回订单号 如果失败返回''
	 */
	String trade(HttpServletRequest request);
}
