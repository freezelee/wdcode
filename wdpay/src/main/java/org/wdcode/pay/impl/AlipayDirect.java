package org.wdcode.pay.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.crypto.Digest;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.MathUtil;
import org.wdcode.common.util.StringUtil;
import org.wdcode.pay.base.BaseOnlinePay;
import org.wdcode.pay.bean.PayBean;
import org.wdcode.pay.constants.PayConstants;
import org.wdcode.pay.params.PayParams;
import org.wdcode.web.http.HttpEngine;
import org.wdcode.web.util.HttpUtil;
import org.wdcode.web.util.RequestUtil;

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

	@Override
	public String trade(HttpServletRequest request) {
		// 交易状态
		String tradeStatus = RequestUtil.getParameter(request, "trade_status");
		// 通知校验ID
		String notifyId = RequestUtil.getParameter(request, "notify_id");
		// 通知校验码
		String sign = RequestUtil.getParameter(request, "sign");
		// 计算出校验码
		String mysign = StringUtil.toString(Digest.getMessageDigest(StringUtil.toBytes(HttpUtil.toParameters(RequestUtil.getParameters(request)) + PayParams.PAY_ALIPAY_KEY, PayParams.PAY_ALIPAY_CHARSET), PayParams.PAY_ALIPAY_SIGNTYPE));
		// 校验URL
		String veryfyUrl = PayConstants.PAY_ALIPAY_VERIFY_URL + PayConstants.PAY_ALIPAY_KEY_PARTNER + StringConstants.EQ + PayParams.PAY_ALIPAY_ID + StringConstants.AMP + PayConstants.PAY_KEY_NOTIFY_ID + StringConstants.EQ + notifyId;
		// 获得交易网站验证
		boolean verifyResponse = Conversion.toBoolean(HttpEngine.get(veryfyUrl));
		// 获得交易状态
		boolean status = PayConstants.PAY_ALIPAY_TRADE_FINISHED.equals(tradeStatus) || PayConstants.PAY_ALIPAY_TRADE_SUCCESS.equals(tradeStatus);
		// 返回实体
		return verifyResponse && status && sign.equals(mysign) ? RequestUtil.getParameter(request, PayConstants.PAY_KEY_OUT_TRADE_NO) : StringConstants.EMPTY;
	}

	/**
	 * 获得提交参数
	 * @param pay 支付实体
	 * @return 支付参数
	 */
	protected Map<String, String> getParameters(PayBean pay) {
		// 设置提交参数
		Map<String, String> data = Maps.getMap();
		data.put("service", "create_direct_pay_by_user");
		data.put("partner", PayParams.PAY_ALIPAY_ID);
		data.put("return_url", pay.getReturnUrl());
		data.put("notify_url", pay.getNotifyUrl());
		data.put("_input_charset", PayParams.PAY_ALIPAY_CHARSET);
		data.put("payment_type", "1");
		data.put("out_trade_no", pay.getNo());
		data.put("subject", pay.getSubject());
		data.put("body", pay.getBody());
		data.put("total_fee", MathUtil.scale(pay.getTotal(), 2).toPlainString());
		data.put("paymethod", "post");
		data.put("sign", sign(data));
		data.put("sign_type", PayParams.PAY_ALIPAY_SIGNTYPE);
		// 返回参数列表
		return data;
	}

	/**
	 * 加密签名
	 * @param data 参数
	 * @return 签名
	 */
	protected String sign(Map<String, String> data) {
		return StringUtil.toString(Digest.getMessageDigest(StringUtil.toBytes(HttpUtil.toParameters(data) + PayParams.PAY_ALIPAY_KEY, PayParams.PAY_ALIPAY_CHARSET), PayParams.PAY_ALIPAY_SIGNTYPE));
	}
}
