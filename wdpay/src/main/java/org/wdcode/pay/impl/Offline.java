package org.wdcode.pay.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.wdcode.pay.bean.Payment; 
import org.wdcode.pay.interfaces.Pay;

/**
 * 线下支付
 * @author WD
 * @since JDK6
 * @version 1.0 2012-12-04
 */
@Component
public final class Offline implements Pay {
	@Override
	public String getName() {
		return "线下支付";
	}

	@Override
	public String getDetail() {
		return "您可以通过现金付款或银行转帐的方式进行收款";
	}

	@Override
	public String getLogo() {
		return "/wdstatic/images/payment/offline_icon.gif";
	}

	@Override
	public boolean isOnline() {
		return false;
	}

	@Override
	public boolean isAuto() {
		return false;
	}

	@Override
	public Object pay(Payment pay) {
		return false;
	}

	@Override
	public String trade(HttpServletRequest request) {
		return null;
	}
}
