package org.wdcode.common.codec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.log.Logs;
import org.wdcode.common.util.StringUtil;

/**
 * 编码类
 * @author WD
 * @since JDK7
 * @version 1.0 2012-2-17
 */
public final class Codes {
	/**
	 * 字节码转换成16进制字符串
	 * @param b 要编码的字节数组
	 * @return 转换后的字符串
	 */
	public static String encodeHex(byte[] b) {
		return Hex.encodeHexString(b);
	}

	/**
	 * 字节码转换成16进制字符串
	 * @param s 转换的字符串
	 * @return 转换后的字符串
	 */
	public static String encodeHex(String s) {
		return encodeHex(StringUtil.toBytes(s));
	}

	/**
	 * Base64编码
	 * @param s 要编码的字符串
	 * @return 编码后字符串
	 */
	public static String encodeBase64(String s) {
		return encodeBase64(StringUtil.toBytes(s));
	}

	/**
	 * Base64编码
	 * @param b 要编码的字节数组
	 * @return 编码后字符串
	 */
	public static String encodeBase64(byte[] b) {
		return Base64.encodeBase64String(b);
	}

	/**
	 * 16进制字符串转换成字节码
	 * @param h 16进制字符串
	 * @return 转换后的字节数组
	 */
	public static byte[] decodeHex(String h) {
		try {
			return Hex.decodeHex(h.toCharArray());
		} catch (Exception e) {
			// 记录日志
			Logs.warn(e);
			// 返回空字节数组
			return ArrayConstants.BYTES_EMPTY;
		}
	}

	/**
	 * Base64解码
	 * @param s 要解码的字符串
	 * @return 解码后字符串
	 */
	public static byte[] decodeBase64(String s) {
		return Base64.decodeBase64(s);
	}

	/**
	 * Base64解码
	 * @param b 要解码的字节数组
	 * @return 解码后字符串
	 */
	public static byte[] decodeBase64(byte[] b) {
		return Base64.decodeBase64(b);
	}

	/**
	 * 私有构造
	 */
	private Codes() {}
}
