package org.wdcode.pay.bean;

/**
 * 支付回调实体
 * @author WD
 * @since JDK7
 * @version 1.0 2013-12-23
 */
public final class TradeBean {
	// 订单号
	private String	no;
	// 价钱
	private String	amt;
	// 是否成功
	private boolean	success;
	// 是否异步
	private boolean	notify;

	/**
	 * 构造
	 * @param no
	 * @param success
	 */
	public TradeBean(String no, boolean success, boolean notify, String amt) {
		this.no = no;
		this.success = success;
		this.notify = notify;
	}

	/**
	 * 获得订单号
	 * @return
	 */
	public String getNo() {
		return no;
	}

	/**
	 * 是否成功
	 * @return
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * 是否异步
	 * @return
	 */
	public boolean isNotify() {
		return notify;
	}

	/**
	 * 交易金额
	 * @return
	 */
	public String getAmt() {
		return amt;
	}
}
