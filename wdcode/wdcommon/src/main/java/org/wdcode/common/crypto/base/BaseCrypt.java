package org.wdcode.common.crypto.base;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.wdcode.common.constants.ArrayConstants;

import org.wdcode.common.log.Logs;

/**
 * 加密解密基础类 内部使用
 * @author WD
 * @since JDK7
 * @version 1.0 2010-06-23
 */
public abstract class BaseCrypt {
	/**
	 * 计算密文
	 * @param b 要计算的字节数组
	 * @param key 计算密钥Key 长度有限制 DSE 为8位 ASE 为16位
	 * @param offset 偏移从第几位开始
	 * @param len 长度一共几位
	 * @param algorithm 算法
	 * @param mode 计算模式 加密和解密
	 * @return 字节数组
	 */
	protected final static byte[] doFinal(byte[] b, byte[] key, int offset, int len, String algorithm, int mode) {
		return doFinal(b, new SecretKeySpec(key, offset, len, algorithm), algorithm, mode);
	}

	/**
	 * 计算密文
	 * @param b 要计算的字节数组
	 * @param key 计算密钥Key 长度有限制 DSE 为8位 ASE 为16位
	 * @param offset 偏移从第几位开始
	 * @param len 长度一共几位
	 * @param algorithm 算法
	 * @param mode 计算模式 加密和解密
	 * @return 字节数组
	 */
	protected final static byte[] doFinal(byte[] b, Key key, String algorithm, int mode) {
		try {
			// 算法操作
			Cipher cipher = Cipher.getInstance(algorithm);
			// 初始化
			cipher.init(mode, key);
			// 返回计算结果
			return cipher.doFinal(b);
		} catch (Exception e) {
			// 记录日志
			Logs.warn(e);
			// 返回空字节数组
			return ArrayConstants.BYTES_EMPTY;
		}
	}
}
