package org.wdcode.pay.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.wdcode.pay.Pay;
import org.wdcode.pay.bean.PayBean;
import org.wdcode.pay.bean.TradeBean;
import org.wdcode.pay.constants.PayConstants;

/**
 * 短信支付
 * @author WD
 * @since JDK7
 * @version 1.0 2014-1-13
 */
@Component
public final class Sms implements Pay {
	@Override
	public int type() {
		return PayConstants.TYPE_SMS;
	}

	@Override
	public String pay(HttpServletRequest request, HttpServletResponse response, PayBean pay) {
		return null;
	}

	@Override
	public String getCharset() {
		return null;
	}

	@Override
	public TradeBean trade(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}
}
