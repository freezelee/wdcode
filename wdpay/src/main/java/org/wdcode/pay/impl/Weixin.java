package org.wdcode.pay.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wdcode.common.crypto.Digest;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.MathUtil;
import org.wdcode.pay.Pay;
import org.wdcode.pay.bean.PayBean;
import org.wdcode.pay.bean.TradeBean;
import org.wdcode.pay.constants.PayConstants;
import org.wdcode.pay.params.PayParams;
import org.wdcode.web.util.HttpUtil;
import org.wdcode.web.util.IpUtil;

/**
 * 微信支付
 * @author WD 2014-1-2
 */
public final class Weixin implements Pay {
	@Override
	public int type() {
		return PayConstants.TYPE_WEIXIN;
	}

	@Override
	public String pay(HttpServletRequest request, PayBean pay) {
		return HttpUtil.toUrl(getUrl(), getParameters(request, pay));
	}

	@Override
	public String getCharset() {
		return PayParams.WEIXIN_CHARSET;
	}

	@Override
	public String getUrl() {
		return PayParams.WEIXIN_URL;
	}

	@Override
	public Map<String, String> getParameters(HttpServletRequest request, PayBean pay) {
		// 设置支付参数
		Map<String, String> params = Maps.getMap();
		params.put("partner", PayParams.WEIXIN_ID); // 商户号
		params.put("out_trade_no", pay.getNo()); // 商家订单号
		params.put("total_fee", MathUtil.scale(pay.getTotal(), 2).toPlainString()); // 商品金额,以分为单位
		params.put("return_url", PayParams.WEIXIN_REDIRECT); // 交易完成后跳转的URL
		params.put("notify_url", PayParams.WEIXIN_REDIRECT); // 接收通知的URL
		params.put("body", pay.getBody()); // 商品描述
		params.put("bank_type", "WX"); // 银行类型
		params.put("spbill_create_ip", IpUtil.getIp(request)); // 订单生成的机器IP，指用户浏览器端IP
		params.put("fee_type", "1"); // 币种，1人民币

		// 系统可选参数
		params.put("sign_type", PayParams.WEIXIN_SIGNTYPE); // 签名类型,默认：MD5
		params.put("service_version", "1.0"); // 版本号，默认为1.0
		params.put("input_charset", PayParams.WEIXIN_CHARSET); // 字符编码
		params.put("sign_key_index", "1"); // 密钥序号
		params.put("sign", sign(params)); // 签名
		return params;
	}

	@Override
	public TradeBean trade(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	private String sign(Map<String, String> params) {
		// 声明签名信息
		StringBuffer sb = new StringBuffer();
		// 循环参数签名
		for (Map.Entry<String, String> e : params.entrySet()) {
			String k = e.getKey();
			String v = e.getValue();
			if (!EmptyUtil.isEmpty(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		// 添加key
		sb.append("key=" + PayParams.WEIXIN_KEY);
		// 添加签名
		return Digest.md5(sb.toString()).toLowerCase();
	}
}
