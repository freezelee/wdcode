package org.wdcode.pay.impl;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.wdcode.common.codec.Hex;
import org.wdcode.common.crypto.Digest;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.MathUtil;
import org.wdcode.common.util.StringUtil;
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
@Component
public final class Weixin implements Pay {
	@Override
	public int type() {
		return PayConstants.TYPE_WEIXIN;
	}

	@Override
	public String pay(HttpServletRequest request, HttpServletResponse response, PayBean pay) {
		return HttpUtil.toForm(getUrl(), getParameters(request, pay), getCharset());
	}

	@Override
	public String getCharset() {
		return PayParams.WEIXIN_CHARSET;
	}

	/**
	 * 获得支付url
	 * @return
	 */
	public String getUrl() {
		return PayParams.WEIXIN_URL;
	}

	/**
	 * 获得支付参数
	 * @param request
	 * @param pay
	 * @return
	 */
	public Map<String, String> getParameters(HttpServletRequest request, PayBean pay) {
		// 设置支付参数
		Map<String, String> params = new TreeMap<>();
		params.put("partner", PayParams.WEIXIN_ID); // 商户号
		params.put("out_trade_no", pay.getNo()); // 商家订单号
		params.put("total_fee", Integer.toString(MathUtil.multiply(pay.getTotal(), 100).intValue())); // 商品金额,以分为单位
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
		// // 获得参数
		// Map<String, String> params = new TreeMap<>();
		// // 获得所有参数
		// for (Map.Entry<String, String[]> e : request.getParameterMap().entrySet()) {
		// params.put(e.getKey(), e.getValue()[0]);
		// }
		// // 商户订单号
		// String out_trade_no = params.get("out_trade_no");
		// // 获得签名
		// boolean isOK = sign(params).equals(params.get("sign"));
		// // 成功
		// if (isOK) {
		// // 通知id
		// String notify_id = params.get("notify_id");
		// // 通过通知ID查询，确保通知来至财付通
		// // 获得参数
		// Map<String, String> param = new TreeMap<>();
		// param.put("partner", PayParams.WEIXIN_ID);
		// param.put("notify_id", notify_id);
		// params.put("sign", sign(param)); // 签名
		// //请求
		// HttpEngine.get(HttpUtil.toUrl("https://gw.tenpay.com/gateway/simpleverifynotifyid.xml",
		// param));
		// 设置结果参数
		// queryRes.setContent(httpClient.getResContent());
		// System.out.println("验证ID返回字符串:" + httpClient.getResContent());
		// queryRes.setKey(key);
		//
		// // 获取id验证返回状态码，0表示此通知id是财付通发起
		// String retcode = queryRes.getParameter("retcode");
		//
		// // 订单号
		// String transaction_id = resHandler.getParameter("transaction_id");
		// // 金额,以分为单位
		// String total_fee = resHandler.getParameter("total_fee");
		// // 如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
		// String discount = resHandler.getParameter("discount");
		// // 支付结果
		// String trade_state = resHandler.getParameter("trade_state");
		//
		// // 判断签名及结果
		// if (queryRes.isTenpaySign() && "0".equals(retcode)) {
		// System.out.println("id验证成功");
		//
		// if ("0".equals(trade_state)) {
		// // ------------------------------
		// // 即时到账处理业务开始
		// // ------------------------------
		//
		// // 处理数据库逻辑
		// // 注意交易单不要重复处理
		// // 注意判断返回金额
		//
		// // ------------------------------
		// // 即时到账处理业务完毕
		// // ------------------------------
		//
		// System.out.println("支付成功");
		// // 给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
		// resHandler.sendToCFT("success");
		//
		// } else {
		// System.out.println("支付失败");
		// resHandler.sendToCFT("fail");
		// }
		// } else {
		// // 错误时，返回结果未签名，记录retcode、retmsg看失败详情。
		// System.out.println("查询验证签名失败或id验证失败" + ",retcode:" + queryRes.getParameter("retcode"));
		// }
		// } else {
		// System.out.println("后台调用通信失败");
		// System.out.println(httpClient.getResponseCode());
		// System.out.println(httpClient.getErrInfo());
		// // 有可能因为网络原因，请求已经处理，但未收到应答。
		// }
		//
		// }
		// 获得
		// return new TradeBean(no, isOK);
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
		return Hex.encode(Digest.md5(StringUtil.toBytes(sb.toString(), getCharset())));
	}
}
