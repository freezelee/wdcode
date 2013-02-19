package org.wdcode.pay.bean;

import java.math.BigDecimal;

/**
 * 支付实体
 * @author WD
 * @since JDK7
 * @version 1.0 2011-11-08
 */
public final class Payment {
	// 订单号
	private String		no;
	// 支付总额
	private BigDecimal	total;
	// 返回处理URL
	private String		returnUrl;
	// 异步处理URL
	private String		notifyUrl;
	// 标题
	private String		subject;
	// 内容
	private String		body;

	/**
	 * 获得订单号
	 * @return 订单号
	 */
	public String getNo() {
		return no;
	}

	/**
	 * 设置订单号
	 * @param no 订单号
	 */
	public void setNo(String no) {
		this.no = no;
	}

	/**
	 * 获得支付总额
	 * @return 支付总额
	 */
	public BigDecimal getTotal() {
		return total;
	}

	/**
	 * 设置支付总额
	 * @param total 支付总额
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	/**
	 * 获得标题
	 * @return 标题
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 设置标题
	 * @param subject 标题
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 获得内容
	 * @return 内容
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 设置内容
	 * @param body 内容
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 获得返回处理URL
	 * @return 返回处理URL
	 */
	public String getReturnUrl() {
		return returnUrl;
	}

	/**
	 * 设置返回处理URL
	 * @param returnUrl 返回处理URL
	 */
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	/**
	 * 获得异步处理URL
	 * @return 异步处理URL
	 */
	public String getNotifyUrl() {
		return notifyUrl;
	}

	/**
	 * 设置异步处理URL
	 * @param notifyUrl 异步处理URL
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
}
