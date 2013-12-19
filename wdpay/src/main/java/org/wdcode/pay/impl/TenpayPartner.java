package org.wdcode.pay.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.wdcode.pay.base.BaseOnlinePay;
import org.wdcode.pay.bean.PayBean;

/**
 * 财付通（担保交易）
 * @author WD
 * @since JDK6
 * @version 1.0 2012-12-04
 */
@Component
public final class TenpayPartner extends BaseOnlinePay {
	@Override
	public String getName() {
		return "财付通（担保交易）";
	}

	@Override
	public String getDetail() {
		return "中国领先的在线支付平台，致力于为互联网用户和企业提供安全、便捷、专业的在线支付服务。";
	}

	@Override
	public String getLogo() {
		return "/wdstatic/images/payment/tenpay_partner_icon.gif";
	}

	@Override
	protected String getUrl() {
		return "https://gw.tenpay.com/gateway/pay.htm";
	}

	@Override
	public String trade(HttpServletRequest request) {
		return null;
	}

	@Override
	protected Map<String, String> getParameters(PayBean pay) {
		return null;
	}
}
