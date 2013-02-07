package org.wdcode.web.http.factory;

import org.wdcode.common.params.CommonParams;
import org.wdcode.core.factory.Factory;
import org.wdcode.web.http.Http;
import org.wdcode.web.http.impl.HttpApache3;
import org.wdcode.web.http.impl.HttpApache4;
import org.wdcode.web.http.impl.HttpApache4Async;
import org.wdcode.web.http.impl.HttpJava;
import org.wdcode.web.params.HttpParams;

/**
 * 获得HttpClient实例工厂
 * @see org.wdcode.web.http.Http
 * @author WD
 * @since JDK7
 * @version 1.0 2009-05-26
 */
public final class HttpFactory extends Factory<Http> {
	// 工厂
	private final static HttpFactory	FACTORY	= new HttpFactory();

	/**
	 * 返回工厂
	 * @return 工厂
	 */
	public static Http getHttp() {
		return FACTORY.getInstance();
	}

	/**
	 * 实例化一个新的HttpClient实例
	 * @return HttpClient
	 */
	public Http newInstance() {
		return newInstance(CommonParams.ENCODING);
	}

	/**
	 * 实例化一个新的HttpClient实例
	 * @param encoding 请求头的编码
	 * @return HttpClient
	 */
	public Http newInstance(String encoding) {
		switch (HttpParams.PARSE) {
			case "async":
				return new HttpApache4Async(encoding);
			case "apache3":
				return new HttpApache3(encoding);
			case "java":
				return new HttpJava(encoding);
			default:
				return new HttpApache4(encoding);
		}
	}

	/**
	 * 私有构造
	 */
	private HttpFactory() {}
}
