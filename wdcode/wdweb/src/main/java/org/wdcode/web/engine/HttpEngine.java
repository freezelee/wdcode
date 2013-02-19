package org.wdcode.web.engine;

import java.util.Map;

import org.wdcode.web.http.Http;
import org.wdcode.web.http.factory.HttpFactory;

/**
 * HTTP处理器 使用默认工厂对象
 * @author WD
 * @since JDK7
 * @version 1.0 2012-08-28
 */
public final class HttpEngine {
	// HTTP接口
	private final static Http	HTTP;

	/**
	 * 私有构造
	 */
	static {
		HTTP = HttpFactory.getHttp();
	}

	/**
	 * 模拟get提交
	 * @param url get提交地址
	 * @return InputStream 提交后的流
	 */
	public static byte[] get(String url) {
		return HTTP.get(url);
	}

	/**
	 * 模拟post提交 默认使用UTF-8格式
	 * @param url post提交地址
	 * @param data 提交参数
	 * @return byte[] 提交后的流
	 */
	public static byte[] post(String url, Map<String, String> data) {
		return HTTP.post(url, data);
	}

	/**
	 * 私有构造
	 */
	private HttpEngine() {}
}
