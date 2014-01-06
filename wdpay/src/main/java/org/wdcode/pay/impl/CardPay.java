package org.wdcode.pay.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wdcode.pay.Pay;
import org.wdcode.pay.bean.PayBean;
import org.wdcode.pay.bean.TradeBean;

/**
 * 手机卡支付
 * @author WD 2014-1-6
 */
public final class CardPay implements Pay {

	@Override
	public int type() {

		return 0;
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
