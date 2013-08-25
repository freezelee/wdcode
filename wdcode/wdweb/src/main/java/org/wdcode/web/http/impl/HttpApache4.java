package org.wdcode.web.http.impl;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.constants.StringConstants;

import org.wdcode.common.io.StreamUtil;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.log.Logs;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.web.http.Http;
import org.wdcode.web.http.base.BaseHttp;
import org.wdcode.web.params.HttpParams;

/**
 * Apache HttpComponents 4 实现HTTP模拟浏览器提交的实现类
 * @author WD
 * @since JDK7
 * @version 1.0 2009-06-03
 */
public final class HttpApache4 extends BaseHttp implements Http {
	// Http客户端
	private DefaultHttpClient	client;

	/**
	 * 构造方法
	 * @param encoding 编码
	 */
	public HttpApache4(String encoding) {
		super(encoding);
		// Http连接池
		PoolingClientConnectionManager pool = new PoolingClientConnectionManager();
		pool.setDefaultMaxPerRoute(HttpParams.POOL);
		pool.setMaxTotal(HttpParams.POOL);
		// 实例化客户端
		client = new DefaultHttpClient(pool);
		// 设置参数
		client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, HttpParams.TIMEOUT);
		client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, HttpParams.TIMEOUT);
		client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, false);
		client.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		client.getParams().setParameter(ClientPNames.DEFAULT_HEADERS, Lists.getList(new BasicHeader(USER_AGENT_KEY, USER_AGENT_VAL)));
		client.getParams().setParameter(CONTENT_CHARSET, encoding);
		client.getParams().setParameter(COOKIE_HEADER, true);
		// 设置CookieStore对象
		client.setCookieStore(new BasicCookieStore());
	}

	/**
	 * 获得HttpContext的属性
	 * @param id 属性ID
	 * @return 获得的对象
	 */
	public Object getAttribute(String id) {
		return client.getParams().getParameter(id);
	}

	/**
	 * 获得当前的URL
	 * @return URL地址
	 */
	public String getCurrentURL() {
		return currentURL;
	}

	/**
	 * 添加Cookie
	 * @param name Cookie名
	 * @param value Cookie值
	 */
	public void addCookie(String name, String value) {
		client.getCookieStore().addCookie(new BasicClientCookie(name, value));
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
	 * 获得所有Cookie列表
	 * @return Cookie列表
	 */
	public List<Map<String, String>> getCookies() {
		// 获得Cookie列表
		List<Cookie> lsCookie = client.getCookieStore().getCookies();
		// 获得列表大小
		int size = lsCookie.size();
		// 声明Map列表
		List<Map<String, String>> lsMap = Lists.getList(size);
		// 声明Cookie
		Cookie cookie = null;
		// 声明Map
		Map<String, String> map = null;
		// 循环Cookie
		for (int i = 0; i < size; i++) {
			// 获得Cookie
			cookie = lsCookie.get(i);
			// 获得Map
			map = Maps.getMap(2);
			// 设置属性
			map.put("name", cookie.getName());
			map.put("value", cookie.getValue());
			// 添加到列表中
			lsMap.add(map);
		}
		// 返回Map列表
		return lsMap;
	}

	/**
	 * 关闭资源
	 */
	public void close() {
		// 关闭连接
		client.getConnectionManager().shutdown();
	}

	/**
	 * 模拟get提交
	 * @param url get提交地址
	 * @param referer referer地址
	 * @return InputStream 提交后的流
	 */
	public byte[] get(String url, String referer) {
		// 声明HttpGet对象
		HttpGet get = null;
		try {
			// 获得HttpGet对象
			get = new HttpGet(url);
			// 设置头信息
			setHeaders(get, referer);
			// 获得HttpResponse
			HttpResponse response = client.execute(get);
			// 更新Url
			updateCurrentUrl(get);
			// 判断状态
			if (response.getStatusLine().getStatusCode() == 302) {
				// 如果是302重定向 那么重新提交
				return get(((DefaultRedirectStrategy) client.getRedirectStrategy()).getLocationURI(get, response, null).toString(), referer);
			} else {
				// 其它状态获得字节数组
				byte[] b = StreamUtil.read(response.getEntity().getContent());
				// 返回字节数组
				return b;
			}
		} catch (Exception e) {
			// 记录日志
			Logs.error(e);
			// 返回空字节数组
			return ArrayConstants.BYTES_EMPTY;
		} finally {
			// 销毁get
			get.abort();
		}
	}

	/**
	 * 模拟post提交
	 * @param url post提交地址
	 * @param data 提交参数
	 * @param referer referer地址
	 * @param encoding 提交参数的编码格式
	 * @return InputStream 提交后的流
	 */
	public byte[] post(String url, Map<String, String> data, String referer, String encoding) {
		// 声明HttpPost
		HttpPost post = null;
		try {
			// 获得HttpPost
			post = new HttpPost(url);
			// 设置头信息
			setHeaders(post, referer);
			// 添加post Content-Type头
			post.addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VAL);
			// 如果参数列表为空 data为空map
			if (!EmptyUtil.isEmpty(data)) {
				// 声明参数列表
				List<NameValuePair> list = Lists.getList(data.size());
				// 设置参数
				for (Map.Entry<String, String> entry : data.entrySet()) {
					// 添加参数
					list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				// 设置参数与 编码格式
				post.setEntity(new UrlEncodedFormEntity(list, encoding));
			}
			// 获得HttpResponse参数
			HttpResponse response = client.execute(post);
			// 更新Url
			updateCurrentUrl(post);
			// 获得状态码
			int status = response.getStatusLine().getStatusCode();
			// 判断状态
			if (status == 302 || status == 301) {
				// 如果是302重定向 那么重新提交
				return post(((DefaultRedirectStrategy) client.getRedirectStrategy()).getLocationURI(post, response, null).toString(), data, referer);
			} else if (status == 200 || (status > 200 && status < 300)) {
				// 返回字节数组
				return StreamUtil.read(response.getEntity().getContent());
			}
		} catch (Exception e) {
			// 记录日志
			Logs.error(e);
			// 返回空字节数组
			return ArrayConstants.BYTES_EMPTY;
		} finally {
			// 销毁post
			post.abort();
		}
		// 返回空字节数组
		return ArrayConstants.BYTES_EMPTY;
	}

	/**
	 * 设置头
	 * @param req
	 * @param referer
	 */
	private void setHeaders(HttpRequest req, String referer) {
		// 添加头信息
		req.addHeader(ACCEPT_KEY, ACCEPT_VAL);
		req.addHeader(ACCEPT_LANGUAGE_KEY, ACCEPT_LANGUAGE_VAL);
		req.addHeader(ACCEPT_CHARSET_KEY, ACCEPT_CHARSET_VAL);
		// 添加头Referer信息
		if (!EmptyUtil.isEmpty(referer)) {
			req.addHeader(REFERER_KEY, referer);
		}
	}

	/*
	 * 更新当前Url
	 */
	private void updateCurrentUrl(HttpUriRequest request) {
		// 获得当前Url
		currentURL = request.getURI().toString();
	}
}
