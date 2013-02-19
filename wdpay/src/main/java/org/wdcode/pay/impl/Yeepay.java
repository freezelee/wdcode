package org.wdcode.pay.impl;

import org.springframework.stereotype.Component;
import org.wdcode.pay.base.BaseOnlinePay;

/**
 * 易宝支付
 * @author WD
 * @since JDK6
 * @version 1.0 2012-12-04
 */
@Component
public final class Yeepay extends BaseOnlinePay {
	@Override
	public String getName() {
		return "易宝支付";
	}

	@Override
	public String getDetail() {
		return "中国领先的独立第三方支付平台，致力于为广大商家和消费者提供“安全、简单、快乐”的专业电子支付解决方案和服务。";
	}

	@Override
	public String getLogo() {
		return "/wdstatic/images/payment/yeepay_icon.gif";
	}

	@Override
	protected String getUrl() {
		return "http://www.yeepay.com/";
	}
}
