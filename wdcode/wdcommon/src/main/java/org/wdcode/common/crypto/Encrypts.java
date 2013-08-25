package org.wdcode.common.crypto;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.wdcode.common.codec.Hex;
import org.wdcode.common.constants.EncryptConstants;
import org.wdcode.common.crypto.base.BaseCrypt;
import org.wdcode.common.log.Logs;
import org.wdcode.common.params.CommonParams;
import org.wdcode.common.util.KeyUtil;
import org.wdcode.common.util.StringUtil;

/**
 * 加密类
 * @author WD
 * @since JDK7
 * @version 1.0 2010-06-22
 */
public final class Encrypts extends BaseCrypt {
	/**
	 * 使用HMAC-SHA1进行消息签名, 返回字节数组,长度为20字节.
	 * @param text 原始输入字符串
	 */
	public static String hmacSha1(String text) {
		try {
			// 实例化SecretKey
			SecretKey secretKey = new SecretKeySpec(CommonParams.ENCRYPT_KEY_BYTES, EncryptConstants.ALGO_HMAC_SHA1);
			// 获得Mac
			Mac mac = Mac.getInstance(EncryptConstants.ALGO_HMAC_SHA1);
			// 初始化算法
			mac.init(secretKey);
			// 返回加密串
			return Hex.encode(mac.doFinal(text.getBytes()));
		} catch (Exception e) {
			// 记录日志
			Logs.warn(e);
			// 返回文本
			return text;
		}
	}

	/**
	 * 加密字符串
	 * @param text 要加密的字符串
	 * @return 加密后的字节数组
	 */
	public static String encrypt(String text) {
		return Hex.encode(encrypt(StringUtil.toBytes(text)));
	}

	/**
	 * 加密字符串
	 * @param b 要加密的字节数组
	 * @return 加密后的字节数组
	 */
	public static byte[] encrypt(byte[] b) {
		// 判断加密方式
		if (EncryptConstants.ALGO_AES.equals(CommonParams.ENCRYPT_ALGO)) {
			// AES加密
			return aes(b);
		} else if (EncryptConstants.ALGO_DES.equals(CommonParams.ENCRYPT_ALGO)) {
			// DES加密
			return des(b);
		} else if (EncryptConstants.ALGO_RC2.equals(CommonParams.ENCRYPT_ALGO)) {
			// RC2加密
			return rc2(b);
		} else if (EncryptConstants.ALGO_RC4.equals(CommonParams.ENCRYPT_ALGO)) {
			// RC4加密
			return rc4(b);
		} else if (EncryptConstants.ALGO_RSA.equals(CommonParams.ENCRYPT_ALGO)) {
			// RSA加密
			return rsa(b);
		}
		// 默认返回AES
		return aes(b);
	}

	/**
	 * 可逆的加密算法 DES算法
	 * @param b 需要加密的字节数组
	 * @return 返回加密后的字节数组
	 */
	public static byte[] des(byte[] b) {
		return encrypt(b, CommonParams.ENCRYPT_KEY_BYTES, 0, CommonParams.ENCRYPT_KEY_LENGTH_DES, EncryptConstants.ALGO_DES);
	}

	/**
	 * 可逆的加密算法 AES算法
	 * @param b 需要加密的字节数组
	 * @return 返回加密后的字节数组
	 */
	public static byte[] aes(byte[] b) {
		return encrypt(b, CommonParams.ENCRYPT_KEY_BYTES, 0, CommonParams.ENCRYPT_KEY_LENGTH_AES, EncryptConstants.ALGO_AES);
	}

	/**
	 * 可逆的加密算法 RC2算法
	 * @param b 需要加密的字节数组
	 * @return 返回加密后的字节数组
	 */
	public static byte[] rc2(byte[] b) {
		return encrypt(b, CommonParams.ENCRYPT_KEY_BYTES, 0, CommonParams.ENCRYPT_KEY_LENGTH_RC2, EncryptConstants.ALGO_RC2);
	}

	/**
	 * 可逆的加密算法 RC4算法
	 * @param b 需要加密的字节数组
	 * @return 返回加密后的字节数组
	 */
	public static byte[] rc4(byte[] b) {
		return encrypt(b, CommonParams.ENCRYPT_KEY_BYTES, 0, CommonParams.ENCRYPT_KEY_LENGTH_RC4, EncryptConstants.ALGO_RC4);
	}

	/**
	 * 可逆的非对称加密算法 RSA算法
	 * @param b 需要加密的字节数组
	 * @return 返回加密后的字节数组
	 */
	public static byte[] rsa(byte[] b) {
		return rsa(b, KeyUtil.getPublicKey(EncryptConstants.ALGO_RSA));
	}

	/**
	 * 可逆的非对称加密算法 RSA算法
	 * @param b 需要加密的字节数组
	 * @param key 加密密钥
	 * @return 返回加密后的字节数组
	 */
	public static byte[] rsa(byte[] b, Key key) {
		return doFinal(b, key, EncryptConstants.ALGO_RSA, Cipher.ENCRYPT_MODE);
	}

	/**
	 * 可逆的非对称加密算法 DSA算法
	 * @param b 需要加密的字节数组
	 * @return 返回加密后的字节数组
	 */
	public static byte[] dsa(byte[] b) {
		return dsa(b, KeyUtil.getPublicKey(EncryptConstants.ALGO_DSA));
	}

	/**
	 * 可逆的非对称加密算法 DSA算法
	 * @param b 需要加密的字节数组
	 * @param key 加密密钥
	 * @return 返回加密后的字节数组
	 */
	public static byte[] dsa(byte[] b, Key key) {
		return doFinal(b, key, EncryptConstants.ALGO_DSA, Cipher.ENCRYPT_MODE);
	}

	/**
	 * 可逆的非对称加密算法 DH算法
	 * @param b 需要加密的字节数组
	 * @return 返回加密后的字节数组
	 */
	public static byte[] dh(byte[] b) {
		return dh(b, KeyUtil.getPublicKey(EncryptConstants.ALGO_DH));
	}

	/**
	 * 可逆的非对称加密算法 DH算法
	 * @param b 需要加密的字节数组
	 * @param key 加密密钥
	 * @return 返回加密后的字节数组
	 */
	public static byte[] dh(byte[] b, Key key) {
		return doFinal(b, key, EncryptConstants.ALGO_DH, Cipher.ENCRYPT_MODE);
	}

	/**
	 * 加密字符串
	 * @param text 要加密的字符串
	 * @param key 加密密钥Key 长度有限制 DSE 为8位 ASE 为16位
	 * @param offset 偏移从第几位开始
	 * @param len 长度一共几位
	 * @param algorithm 算法
	 * @return 加密后的字节数组
	 */
	private static byte[] encrypt(byte[] b, byte[] key, int offset, int len, String algorithm) {
		return doFinal(b, key, offset, len, algorithm, Cipher.ENCRYPT_MODE);
	}

	/**
	 * 私有构造
	 */
	private Encrypts() {}
}
