package org.wdcode.pay.constants;

/**
 * 支付常量
 * @author WD
 * @since JDK7
 * @version 1.0 2011-11-08
 */
public final class PayConstants {
	/* 通用常量 */
	public final static String	PAY_KEY_RETURN_URL;			// 返回处理URL
	public final static String	PAY_KEY_NOTIFY_URL;			// 异步处理URL
	public final static String	PAY_KEY_OUT_TRADE_NO;			// 订单号
	public final static String	PAY_KEY_TRADE_NO;				// 交易号
	public final static String	PAY_KEY_BODY;					// 内容
	public final static String	PAY_KEY_TOTAL_FEE;				// 支付金额
	public final static String	PAY_KEY_SUBJECT;				// 标题
	public final static String	PAY_KEY_TRADE_STATUS;			// 交易状态
	public final static String	PAY_KEY_NOTIFY_ID;				// 校验ID

	/* 支付宝常量 */
	public final static String	PAY_ALIPAY_URL;				// 提交URL
	public final static String	PAY_ALIPAY_KEY_SERVICE;		// 服务
	public final static String	PAY_ALIPAY_KEY_PARTNER;		// 合作用户
	public final static String	PAY_ALIPAY_KEY_CHARSET;		// 提交编码
	public final static String	PAY_ALIPAY_KEY_PAYMENT_TYPE;	// 支付类型

	public final static String	PAY_ALIPAY_KEY_PAYMETHOD;		// 提交方法
	public final static String	PAY_ALIPAY_KEY_SIGN;			// 签名
	public final static String	PAY_ALIPAY_KEY_SIGN_TYPE;		// 签名类型
	public final static String	PAY_ALIPAY_VALUE_SERVICE;		// 服务
	public final static String	PAY_ALIPAY_VALUE_PAYMENT_TYPE;	// 支付类型
	public final static String	PAY_ALIPAY_VALUE_PAYMETHOD;	// 提交方法
	public final static String	PAY_ALIPAY_VERIFY_URL;			// 校验URL
	public final static String	PAY_ALIPAY_TRADE_FINISHED;		// 交易状态
	public final static String	PAY_ALIPAY_TRADE_SUCCESS;		// 交易状态

	/**
	 * 静态初始化
	 */
	static {
		/* 通用常量 */
		PAY_KEY_BODY = "body";// 内容
		PAY_KEY_TOTAL_FEE = "total_fee"; // 支付金额
		PAY_KEY_OUT_TRADE_NO = "out_trade_no"; // 订单号
		PAY_KEY_TRADE_NO = "trade_no"; // 交易号
		PAY_KEY_RETURN_URL = "return_url"; // 返回处理URL
		PAY_KEY_NOTIFY_URL = "notify_url"; // 异步处理URL
		PAY_KEY_TRADE_STATUS = "trade_status"; // 交易状态
		PAY_KEY_NOTIFY_ID = "notify_id"; // 校验ID

		PAY_ALIPAY_URL = "https://mapi.alipay.com/gateway.do"; // 提交URL
		PAY_ALIPAY_KEY_SERVICE = "service"; // 服务
		PAY_ALIPAY_KEY_PARTNER = "partner"; // 合作用户
		PAY_ALIPAY_KEY_CHARSET = "_input_charset"; // 提交编码
		PAY_ALIPAY_KEY_PAYMENT_TYPE = "payment_type"; // 支付类型
		PAY_KEY_SUBJECT = "subject"; // 标题
		PAY_ALIPAY_KEY_PAYMETHOD = "paymethod"; // 提交方法
		PAY_ALIPAY_KEY_SIGN = "sign"; // 签名
		PAY_ALIPAY_KEY_SIGN_TYPE = "sign_type"; // 签名类型
		PAY_ALIPAY_VALUE_SERVICE = "create_direct_pay_by_user"; // 服务
		PAY_ALIPAY_VALUE_PAYMENT_TYPE = "1"; // 支付类型
		PAY_ALIPAY_VALUE_PAYMETHOD = "post"; // 提交方法
		PAY_ALIPAY_VERIFY_URL = "https://www.alipay.com/cooperate/gateway.do?service=notify_verify&"; // 校验URL
		PAY_ALIPAY_TRADE_FINISHED = "TRADE_FINISHED"; // 交易状态
		PAY_ALIPAY_TRADE_SUCCESS = "TRADE_SUCCESS"; // 交易状态
	}

	/**
	 * 私有构造
	 */
	private PayConstants() {}
}