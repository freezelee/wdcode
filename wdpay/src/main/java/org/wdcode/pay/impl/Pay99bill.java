package org.wdcode.pay.impl;

import org.springframework.stereotype.Component;
import org.wdcode.pay.base.BaseOnlinePay;

/**
 * 快钱
 * @author WD
 * @since JDK6
 * @version 1.0 2012-12-04
 */
@Component
public final class Pay99bill extends BaseOnlinePay {
	@Override
	public String getName() {
		return "快钱";
	}

	@Override
	public String getDetail() {
		return "快钱是国内领先的独立第三方支付企业，旨在为各类企业及个人 提供安全、便捷和保密的综合电子支付服务。";
	}

	@Override
	public String getLogo() {
		return "/wdstatic/images/payment/pay99bill_icon.gif";
	}

	@Override
	protected String getUrl() {
		return "https://www.99bill.com/";
	}
}
