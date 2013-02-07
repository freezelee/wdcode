package org.wdcode.pay.impl;

import org.springframework.stereotype.Component;
import org.wdcode.pay.base.BaseOnlinePay;

/**
 * 支付宝即时到帐
 * @author WD
 * @since JDK6
 * @version 1.0 2012-12-04
 */
@Component
public final class AlipayDirect extends BaseOnlinePay {
	@Override
	public String getName() {
		return "支付宝（即时交易）";
	}

	@Override
	public String getDetail() {
		return "支付宝即时交易，付款后立即到账，无预付/年费，单笔费率阶梯最低0.7%，无流量限制。";
	}

	@Override
	public String getLogo() {
		return "/wdstatic/images/payment/alipay_direct_icon.gif";
	}

	@Override
	protected String getUrl() {
		return "https://mapi.alipay.com/gateway.do";
	}
}
