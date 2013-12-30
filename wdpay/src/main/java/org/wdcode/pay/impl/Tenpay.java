package org.wdcode.pay.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.wdcode.pay.Pay;
import org.wdcode.pay.bean.PayBean;
import org.wdcode.pay.bean.TradeBean;
import org.wdcode.pay.constants.PayConstants;

/**
 * 财付通（即时交易）
 * @author WD
 * @since JDK6
 * @version 1.0 2012-12-04
 */
@Component
public final class Tenpay implements Pay {
	@Override
	public int type() {
		return PayConstants.TYPE_TENPAY;
	}

	@Override
	public String pay(HttpServletRequest request, PayBean pay) {
		return null;
	}

	@Override
	public TradeBean trade(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	@Override
	public String getUrl() {
		return null;
	}

	@Override
	public Map<String, String> getParameters(HttpServletRequest request, PayBean pay) {
		return null;
	}
}
