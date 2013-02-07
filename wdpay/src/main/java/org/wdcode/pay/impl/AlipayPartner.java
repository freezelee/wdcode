package org.wdcode.pay.impl;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.wdcode.pay.base.BaseOnlinePay;
import org.wdcode.pay.bean.Payment;

/**
 * 支付宝（担保交易）
 * @author WD
 * @since JDK6
 * @version 1.0 2012-12-04
 */
@Component
public final class AlipayPartner extends BaseOnlinePay {
	@Override
	public String getName() {
		return "支付宝（担保交易）";
	}

	@Override
	public String getDetail() {
		return "支付宝担保交易，买家先付款到支付宝，支付宝收到买家付款后即时通知卖家发货，买家收到货物满意后通知支付宝付款给卖家。";
	}

	@Override
	public String getLogo() {
		return "/wdstatic/images/payment/alipay_partner_icon.gif";
	}

	@Override
	protected Map<String, String> getParameters(Payment pay) {
		// 设置提交参数
		Map<String, String> data = super.getParameters(pay);
		data.put("service", "create_partner_trade_by_buyer");
		// 返回参数
		return data;
	}

	@Override
	protected String getUrl() {
		return "https://mapi.alipay.com/gateway.do";
	}
}
