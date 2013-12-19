package org.wdcode.pay.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.wdcode.common.codec.Hex;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.MathUtil;
import org.wdcode.pay.base.BaseOnlinePay;
import org.wdcode.pay.bean.PayBean;
import org.wdcode.pay.params.PayParams;

/**
 * 易宝支付
 * @author WD
 * @since JDK6
 * @version 1.0 2012-12-04
 */
@Component
public final class Yeepay extends BaseOnlinePay {
	@Override
	public String getName() {
		return "易宝支付";
	}

	@Override
	public String getDetail() {
		return "中国领先的独立第三方支付平台，致力于为广大商家和消费者提供“安全、简单、快乐”的专业电子支付解决方案和服务。";
	}

	@Override
	public String getLogo() {
		return "/wdstatic/images/payment/yeepay_icon.gif";
	}

	@Override
	public String trade(HttpServletRequest request) {
		// // 商家密钥
		// String keyValue = PayParams.PAY_YEEPAY_KEY;
		// // 业务类型
		// String r0_Cmd = formatString(request.getParameter("r0_Cmd"));
		// // 商户编号
		// String p1_MerId = formatString(Configuration.getInstance().getValue("p1_MerId"));
		// // 支付结果
		// String r1_Code = formatString(request.getParameter("r1_Code"));
		// // 易宝支付交易流水号
		// String r2_TrxId = formatString(request.getParameter("r2_TrxId"));
		// // 支付金额
		// String r3_Amt = formatString(request.getParameter("r3_Amt"));
		// // 交易币种
		// String r4_Cur = formatString(request.getParameter("r4_Cur"));
		// // 商品名称
		// String r5_Pid = new
		// String(formatString(request.getParameter("r5_Pid")).getBytes("iso-8859-1"), "gbk");
		// // 商户订单号
		// String r6_Order = formatString(request.getParameter("r6_Order"));
		// // 易宝支付会员ID
		// String r7_Uid = formatString(request.getParameter("r7_Uid"));
		// // 商户扩展信息
		// String r8_MP = new
		// String(formatString(request.getParameter("r8_MP")).getBytes("iso-8859-1"), "gbk");
		// // 交易结果返回类型
		// String r9_BType = formatString(request.getParameter("r9_BType"));
		// // 签名数据
		// String hmac = formatString(request.getParameter("hmac"));
		// boolean isOK = false;
		// // 校验返回数据包
		// isOK = PaymentForOnlineService.verifyCallback(hmac, p1_MerId, r0_Cmd, r1_Code, r2_TrxId,
		// r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid, r8_MP, r9_BType, keyValue);
		// if (isOK) {
		// // 在接收到支付结果通知后，判断是否进行过业务逻辑处理，不要重复进行业务逻辑处理
		// if (r1_Code.equals("1")) {
		// // 产品通用接口支付成功返回-浏览器重定向
		// if (r9_BType.equals("1")) {
		// out.println("callback方式:产品通用接口支付成功返回-浏览器重定向");
		// // 产品通用接口支付成功返回-服务器点对点通讯
		// } else if (r9_BType.equals("2")) {
		// // 如果在发起交易请求时 设置使用应答机制时，必须应答以"success"开头的字符串，大小写不敏感
		// out.println("SUCCESS");
		// // 产品通用接口支付成功返回-电话支付返回
		// }
		// // 下面页面输出是测试时观察结果使用
		// out.println("<br>交易成功!<br>商家订单号:" + r6_Order + "<br>支付金额:" + r3_Amt + "<br>易宝支付交易流水号:" +
		// r2_TrxId);
		// }
		// } else {
		// out.println("交易签名被篡改!");
		// }
		return null;
	}

	@Override
	protected Map<String, String> getParameters(PayBean pay) {
		// 设置提交参数
		Map<String, String> data = Maps.getMap();
		data.put("p0_Cmd", "Buy");
		data.put("p1_MerId", PayParams.PAY_YEEPAY_ID);
		data.put("p2_Order", pay.getNo());
		data.put("p3_Amt", MathUtil.scale(pay.getTotal(), 2).toPlainString());
		data.put("p4_Cur", "CNY");
		data.put("p5_Pid", pay.getSubject());
		data.put("p6_Pcat", StringConstants.EMPTY);
		data.put("p7_Pdesc", pay.getBody());
		data.put("p8_Url", pay.getReturnUrl());
		data.put("p9_SAF", "0");
		data.put("pa_MP", StringConstants.EMPTY);
		data.put("pd_FrpId", StringConstants.EMPTY);
		data.put("pr_NeedResponse", "1");
		data.put("hmac", sign(data));
		// 返回参数列表
		return data;
	}

	@Override
	protected String getUrl() {
		return "https://www.yeepay.com/app-merchant-proxy/node";
	}

	/**
	 * 签名
	 * @param data
	 * @return
	 */
	protected String sign(Map<String, String> data) {
		StringBuffer sValue = new StringBuffer();
		// 业务类型
		sValue.append(data.get("p0_Cmd"));
		// 商户编号
		sValue.append(data.get("p1_MerId"));
		// 商户订单号
		sValue.append(data.get("p2_Order"));
		// 支付金额
		sValue.append(data.get("p3_Amt"));
		// 交易币种
		sValue.append(data.get("p4_Cur"));
		// 商品名称
		sValue.append(data.get("p5_Pid"));
		// 商品种类
		sValue.append(data.get("p6_Pcat"));
		// 商品描述
		sValue.append(data.get("p7_Pdesc"));
		// 商户接收支付成功数据的地址
		sValue.append(data.get("p8_Url"));
		// 送货地址
		sValue.append(data.get("p9_SAF"));
		// 商户扩展信息
		sValue.append(data.get("pa_MP"));
		// 银行编码
		sValue.append(data.get("pd_FrpId"));
		// 应答机制
		sValue.append(data.get("pr_NeedResponse"));
		return hmacSign(sValue.toString(), PayParams.PAY_YEEPAY_KEY);
	}

	/**
	 * @param aValue
	 * @param aKey
	 * @return
	 */
	private String hmacSign(String aValue, String aKey) {
		byte k_ipad[] = new byte[64];
		byte k_opad[] = new byte[64];
		byte keyb[];
		byte value[];
		try {
			keyb = aKey.getBytes(PayParams.PAY_YEEPAY_CHARSET);
			value = aValue.getBytes(PayParams.PAY_YEEPAY_CHARSET);
		} catch (UnsupportedEncodingException e) {
			keyb = aKey.getBytes();
			value = aValue.getBytes();
		}

		Arrays.fill(k_ipad, keyb.length, 64, (byte) 54);
		Arrays.fill(k_opad, keyb.length, 64, (byte) 92);
		for (int i = 0; i < keyb.length; i++) {
			k_ipad[i] = (byte) (keyb[i] ^ 0x36);
			k_opad[i] = (byte) (keyb[i] ^ 0x5c);
		}

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		md.update(k_ipad);
		md.update(value);
		byte dg[] = md.digest();
		md.reset();
		md.update(k_opad);
		md.update(dg, 0, 16);
		dg = md.digest();
		return Hex.encode(dg);
	}
}
