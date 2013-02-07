package org.wdcode.pay.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.wdcode.pay.bean.Payment; 
import org.wdcode.pay.interfaces.Pay;

/**
 * 预付款支付
 * @author WD
 * @since JDK6
 * @version 1.0 2012-12-04
 */
@Component
public final class Deposit implements Pay {
	@Override
	public String getName() {
		return "预付款支付";
	}

	@Override
	public String getDetail() {
		return "预存款是客户在您网站上的虚拟资金帐户";
	}

	@Override
	public String getLogo() {
		return "/wdstatic/images/payment/deposit_icon.gif";
	}

	@Override
	public boolean isOnline() {
		return false;
	}

	@Override
	public boolean isAuto() {
		return true;
	}

	@Override
	public Object pay(Payment pay) {
		return true;
	}

	@Override
	public String trade(HttpServletRequest request) {
		return null;
	}
}
