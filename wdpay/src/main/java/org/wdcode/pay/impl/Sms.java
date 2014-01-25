package org.wdcode.pay.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.wdcode.common.crypto.Digest;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.params.CommonParams;
import org.wdcode.common.util.MathUtil;
import org.wdcode.pay.Pay;
import org.wdcode.pay.bean.PayBean;
import org.wdcode.pay.bean.TradeBean;
import org.wdcode.pay.constants.PayConstants;
import org.wdcode.pay.params.PayParams;
import org.wdcode.web.util.HttpUtil;
import org.wdcode.web.util.RequestUtil;

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
		return HttpUtil.toForm(getUrl(), getParameters(request, pay), getCharset());
	}

	@Override
	public String getCharset() {
		return CommonParams.ENCODING;
	}

	@Override
	public TradeBean trade(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	/**
	 * 获得支付url
	 * @return
	 */
	public String getUrl() {
		return PayParams.SMS_URL;
	}

	/**
	 * 获得支付参数
	 * @param request
	 * @param pay
	 * @return
	 */
	public Map<String, String> getParameters(HttpServletRequest request, PayBean pay) {
		// 设置提交参数
		StringBuilder sb = new StringBuilder();
		Map<String, String> data = Maps.getMap();
		data.put("sp", PayParams.SMS_ID);
		sb.append(PayParams.SMS_ID);
		data.put("od", pay.getNo());
		sb.append(pay.getNo());
		sb.append(PayParams.SMS_KEY);
		data.put("mz", MathUtil.scale(pay.getTotal(), 0).toPlainString());
		sb.append(MathUtil.scale(pay.getTotal(), 0).toPlainString());
		data.put("spzdy", "true");
		data.put("uid", RequestUtil.getParameter(request, "uid"));
		data.put("spreq", RequestUtil.getParameter(request, "spreq"));
		sb.append(RequestUtil.getParameter(request, "spreq"));
		data.put("spsuc", RequestUtil.getParameter(request, "spsuc"));
		sb.append(RequestUtil.getParameter(request, "spsuc"));
		data.put("md5", Digest.md5(sb.toString()).toUpperCase());
		// 返回参数列表
		return data;
	}
}
