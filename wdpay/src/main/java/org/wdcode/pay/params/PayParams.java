package org.wdcode.pay.params;

import org.wdcode.common.constants.EncodingConstants;
import org.wdcode.common.constants.EncryptConstants;
import org.wdcode.common.params.CommonParams;
import org.wdcode.common.params.Params;

/**
 * 支付配置读取
 * @author WD
 * @since JDK7
 * @version 1.0 2011-11-08
 */
public final class PayParams {
	/* 回调url */
	public final static String	REDIRECT		= Params.getString("pay.redirect");
	/* 支付宝 */
	/**
	 * 合作身份者ID
	 */
	public final static String	ALIPAY_ID		= Params.getString("pay.alipay.id");
	/**
	 * 密钥
	 */
	public final static String	ALIPAY_KEY		= Params.getString("pay.alipay.key");
	/**
	 * 编码类型
	 */
	public final static String	ALIPAY_CHARSET	= Params.getString("pay.alipay.charset", CommonParams.ENCODING);
	/**
	 * 签名类型
	 */
	public final static String	ALIPAY_SIGNTYPE	= Params.getString("pay.alipay.signtype", EncryptConstants.ALGO_MD5);

	/* 财付通 */
	/**
	 * 合作身份者ID
	 */
	public final static String	TENPAY_ID		= Params.getString("pay.tenpay.id");
	/**
	 * 密钥
	 */
	public final static String	TENPAY_KEY		= Params.getString("pay.tenpay.key");
	/**
	 * 编码类型
	 */
	public final static String	TENPAY_CHARSET	= Params.getString("pay.tenpay.charset", CommonParams.ENCODING);

	/* 易宝 */
	/**
	 * 合作身份者ID
	 */
	public final static String	YEEPAY_ID		= Params.getString("pay.yeepay.id");
	/**
	 * 密钥
	 */
	public final static String	YEEPAY_KEY		= Params.getString("pay.yeepay.key");
	/**
	 * 编码类型
	 */
	public final static String	YEEPAY_CHARSET	= Params.getString("pay.yeepay.charset", EncodingConstants.UTF_8);

	/**
	 * 私有构造
	 */
	private PayParams() {}
}
