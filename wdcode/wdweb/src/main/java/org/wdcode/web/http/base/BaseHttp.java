package org.wdcode.web.http.base;

import java.util.List;
import java.util.Map;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.params.CommonParams;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.web.http.Http;

/**
 * 实现HTTP模拟浏览器提交的抽象实现类
 * @author WD
 * @since JDK7
 * @version 1.0 2011-03-08
 */
public abstract class BaseHttp implements Http {
	// 头 User-Agent 信息
	protected final static String	USER_AGENT_KEY;
	protected final static String	USER_AGENT_VAL;
	// 参数KEY
	protected final static String	CONTENT_CHARSET;
	protected final static String	COOKIE_HEADER;
	// 头 Accept 信息
	protected final static String	ACCEPT_KEY;
	protected final static String	ACCEPT_VAL;
	// 头Accept-Language信息
	protected final static String	ACCEPT_LANGUAGE_KEY;
	protected final static String	ACCEPT_LANGUAGE_VAL;
	// 头Accept-Encoding信息
	protected final static String	ACCEPT_ENCODING_KEY;
	protected final static String	ACCEPT_ENCODING_VAL;
	// 头Accept-Charset信息
	protected final static String	ACCEPT_CHARSET_KEY;
	protected final static String	ACCEPT_CHARSET_VAL;
	// 头Content-Type信息
	protected final static String	CONTENT_TYPE_KEY;
	protected final static String	CONTENT_TYPE_VAL;
	// 头Referer
	protected final static String	REFERER_KEY;
	// 头Connection
	protected final static String	CONNECTION_KEY;
	protected final static String	CONNECTION_VAL;

	// 当前URL
	protected String				currentURL;
	// 编码
	protected String				encoding;

	/**
	 * 静态初始化
	 */
	static {
		// 头Accept
		ACCEPT_KEY = "Accept";
		ACCEPT_VAL = "text/xml,text/javascript,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5";
		// 头Accept-Language信息
		ACCEPT_LANGUAGE_KEY = "Accept-Language";
		ACCEPT_LANGUAGE_VAL = "zh-cn,zh;q=0.5";
		// 头Accept-Charset信息
		ACCEPT_CHARSET_KEY = "Accept-Charset";
		ACCEPT_CHARSET_VAL = "ISO-8859-1,utf-8;q=0.7,*;q=0.7";
		// 头Content-Type信息
		CONTENT_TYPE_KEY = "Content-Type";
		CONTENT_TYPE_VAL = "application/x-www-form-urlencoded";
		// 头Referer
		REFERER_KEY = "Referer";
		// 头 User-Agent
		USER_AGENT_KEY = "User-Agent";
		USER_AGENT_VAL = "Mozilla/5.0 (Windows; U; Windows NT 5.1; nl; rv:1.8.1.13) Gecko/20080311 Firefox/2.0.0.13"; // "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt; DTS Agent;)"
		// 头Connection
		CONNECTION_KEY = "Connection";
		CONNECTION_VAL = "Keep-Alive";
		// 头
		CONTENT_CHARSET = "http.protocol.content-charset";
		COOKIE_HEADER = "http.protocol.single-cookie-header";
		// 头Accept-Encoding信息
		ACCEPT_ENCODING_KEY = "Accept-Encoding";
		ACCEPT_ENCODING_VAL = "gzip,deflate";
	}

	/**
	 * 构造方法
	 * @param encoding 编码
	 */
	public BaseHttp(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * 模拟get提交
	 * @param url get提交地址
	 * @return InputStream 提交后的流
	 */
	public byte[] get(String url) {
		return get(url, null);
	}

	/**
	 * 模拟post提交 默认使用UTF-8格式
	 * @param url post提交地址
	 * @param data 提交参数
	 * @return InputStream 提交后的流
	 */
	public byte[] post(String url, Map<String, String> data) {
		return post(url, data, null, CommonParams.ENCODING);
	}

	/**
	 * 模拟post提交
	 * @param url post提交地址
	 * @param data 提交参数
	 * @param referer referer地址
	 * @return InputStream 提交后的流
	 */
	public byte[] post(String url, Map<String, String> data, String referer) {
		return post(url, data, referer, CommonParams.ENCODING);
	}

	/**
	 * 根据name获得Cookie值
	 * @param name cookie名
	 * @return Cookie值 如果没有找到返回""
	 */
	public String getCookieValue(String name) {
		// 获得Cookie
		Map<String, String> cookie = getCookie(name);
		// 返回值
		return EmptyUtil.isEmpty(cookie) ? StringConstants.EMPTY : cookie.get("value");
	}

	/**
	 * 根据name获得Cookie
	 * @param name cookie名
	 * @return Cookie 如果没有找到返回null
	 */
	public Map<String, String> getCookie(String name) {
		// 判断Cookie Name
		if (EmptyUtil.isEmpty(name)) {
			return null;
		}
		// 获得Cookie列表
		List<Map<String, String>> lsCookie = getCookies();
		// 声明Cookie
		Map<String, String> cookie = null;
		// 循环Cookie
		for (int i = 0; i < lsCookie.size(); i++) {
			// 判断Cookie Name
			if (name.equals(lsCookie.get(i).get("name"))) {
				// 获得Cookie
				cookie = lsCookie.get(i);
				break;
			}
		}
		// 返回Cookie
		return cookie;
	}

	/**
	 * 获得当前的URL
	 * @return URL地址
	 */
	public String getCurrentURL() {
		return currentURL;
	}
}
