package org.wdcode.pay.base;

import java.util.Map;

import org.wdcode.pay.bean.PayBean;
import org.wdcode.pay.interfaces.Pay;
import org.wdcode.web.util.HttpUtil;

/**
 * 在线支付基础类
 * @author WD
 * @since JDK6
 * @version 1.0 2013-01-21
 */
public abstract class BaseOnlinePay implements Pay {
	@Override
	public boolean isOnline() {
		return true;
	}

	@Override
	public boolean isAuto() {
		return false;
	}

	@Override
	public Object pay(PayBean pay) {
		return HttpUtil.toUrl(getUrl(), getParameters(pay));
	}

	/**
	 * 获得提交参数
	 * @param pay 支付实体
	 * @return 支付参数
	 */
	protected abstract Map<String, String> getParameters(PayBean pay);

	/**
	 * 提交URL
	 * @return url
	 */
	protected abstract String getUrl();
}
