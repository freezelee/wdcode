package org.wdcode.common.codec;

import org.apache.commons.codec.net.URLCodec;
import org.wdcode.common.params.CommonParams;

/**
 * 基础编码类
 * @author WD
 * @since JDK7
 * @version 1.0 2012-2-17
 */
public final class URLCode {
	// URLCodec
	private final static URLCodec	CODEC	= new URLCodec();

	/**
	 * url编码
	 * @param url 要编码的URL
	 * @return 编码后字符串
	 */
	public static String encode(String url) {
		return encode(url, CommonParams.ENCODING);
	}

	/**
	 * url编码
	 * @param url 要编码的URL
	 * @param encoding 编码
	 * @return 编码后字符串
	 */
	public static String encode(String url, String encoding) {
		try {
			return CODEC.encode(url, encoding);
		} catch (Exception e) {
			return url;
		}
	}

	/**
	 * url解码
	 * @param url 要解码的URL
	 * @return 解码后字符串
	 */
	public static String decode(String url) {
		return decode(url, CommonParams.ENCODING);
	}

	/**
	 * url解码
	 * @param url 要解码的URL
	 * @param encoding 解码
	 * @return 解码后字符串
	 */
	public static String decode(String url, String encoding) {
		try {
			return CODEC.decode(url, encoding);
		} catch (Exception e) {
			return url;
		}
	}

	/**
	 * 私有构造
	 */
	private URLCode() {}
}
