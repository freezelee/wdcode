package org.wdcode.base.bean;

import org.wdcode.core.json.JsonEngine;

/**
 * 回执消息体
 * @author WD
 * @since JDK6
 * @version 1.0 2013-10-14
 */
public final class Inform {
	// 1：成功
	public static final int	SUCCESS	= 1;
	// 2：失败
	public static final int	FAIL	= 2;
	// 1：成功 2：失败
	private int				type;
	// 返回的消息参数
	private String			param;

	/**
	 * 构造方法
	 */
	public Inform() {}

	/**
	 * 构造方法
	 * @param type 标识
	 * @param param 消息参数
	 */
	public Inform(int type, String param) {
		this.type = type;
		this.param = param;
	}

	/**
	 * 获得标识
	 * @return 标识
	 */
	public int getType() {
		return type;
	}

	/**
	 * 设置标识
	 * @param type 标识
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 获得消息参数
	 * @return 消息参数
	 */
	public String getParam() {
		return param;
	}

	/**
	 * 设置消息参数
	 * @param param 消息参数
	 */
	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return JsonEngine.toJson(this);
	}
}
