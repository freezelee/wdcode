package org.wdcode.common.crypto;

import java.security.Key;

import javax.crypto.Cipher;

import org.wdcode.common.codec.Base64;
import org.wdcode.common.codec.Hex;
import org.wdcode.common.constants.EncryptConstants;
import org.wdcode.common.crypto.base.BaseCrypt;
import org.wdcode.common.params.CommonParams;
import org.wdcode.common.util.KeyUtil;
import org.wdcode.common.util.StringUtil;

/**
 * 解密类
 * @author WD
 * @since JDK7
 * @version 1.0 2010-06-22
 */
public final class Decrypts extends BaseCrypt {
	/**
	 * 解密字符串
	 * @param text 要解密的字符串
	 * @return 解密后的字符串
	 */
	public static String decryptHex(String text) {
		return StringUtil.toString(decrypt(Hex.decode(text)));
	}

	/**
	 * 解密字符串
	 * @param text 要解密的字符串
	 * @return 解密后的字符串
	 */
	public static String decryptBase64(String text) {
		return StringUtil.toString(decrypt(Base64.decode(text)));
	}

	/**
	 * 解密字符串
	 * @param b 要解密的字节数组
	 * @return 解密后的字节数组
	 */
	public static byte[] decrypt(byte[] b) {
		// 判断解密方式
		switch (CommonParams.ENCRYPT_ALGO) {
			case EncryptConstants.ALGO_AES:
				// AES解密
				return aes(b);
			case EncryptConstants.ALGO_DES:
				// DES解密
				return des(b);
			case EncryptConstants.ALGO_RC2:
				// RC2解密
				return rc2(b);
			case EncryptConstants.ALGO_RC4:
				// RC4解密
				return rc4(b);
			case EncryptConstants.ALGO_RSA:
				// RSA解密
				return rsa(b);
			default:
				// 默认返回AES
				return aes(b);
		}
	}

	/**
	 * 针对encode方法的解密 DES算法
	 * @param text 需要解密的字节数组
	 * @return 返回解密后的字符串
	 */
	public static byte[] des(byte[] b) {
		return decrypt(b, CommonParams.ENCRYPT_KEY_BYTES, 0, CommonParams.ENCRYPT_KEY_LENGTH_DES, EncryptConstants.ALGO_DES);
	}

	/**
	 * 针对encrypt方法的解密 AES算法
	 * @param b 需要解密的字节数组
	 * @return 返回解密后的字符串 text为空或发生异常返回原串
	 */
	public static byte[] aes(byte[] b) {
		return decrypt(b, CommonParams.ENCRYPT_KEY_BYTES, 0, CommonParams.ENCRYPT_KEY_LENGTH_AES, EncryptConstants.ALGO_AES);
	}

	/**
	 * 针对encrypt方法的解密 RC2算法
	 * @param text 需要解密的字符串
	 * @return 返回解密后的字符串 text为空或发生异常返回原串
	 */
	public static byte[] rc2(byte[] b) {
		return decrypt(b, CommonParams.ENCRYPT_KEY_BYTES, 0, CommonParams.ENCRYPT_KEY_LENGTH_RC2, EncryptConstants.ALGO_RC2);
	}

	/**
	 * 针对encrypt方法的解密 RC4算法
	 * @param b 需要解密的字节数组
	 * @return 返回解密后的字符串 text为空或发生异常返回原串
	 */
	public static byte[] rc4(byte[] b) {
		return decrypt(b, CommonParams.ENCRYPT_KEY_BYTES, 0, CommonParams.ENCRYPT_KEY_LENGTH_RC4, EncryptConstants.ALGO_RC4);
	}

	// /**
	// * 针对encrypt方法的解密 RC4算法
	// * @param b 需要解密的字节数组
	// * @return 返回解密后的字符串 text为空或发生异常返回原串
	// */
	// public static byte[] rc5(byte[] b) {
	// return decrypt(b, CommonParams.ENCRYPT_KEY_BYTES, 0, CommonParams.ENCRYPT_KEY_LENGTH_RC5,
	// EncryptConstants.ALGO_RC5);
	// }

	/**
	 * 可逆的非对称解密算法 RSA算法
	 * @param b 需要解密的字节数组
	 * @return 返回解密后的字节数组
	 */
	public static byte[] rsa(byte[] b) {
		return rsa(b, KeyUtil.getPrivateKey(EncryptConstants.ALGO_RSA));
	}

	/**
	 * 可逆的非对称解密算法 RSA算法
	 * @param b 需要解密的字节数组
	 * @param key 解密密钥
	 * @return 返回解密后的字节数组
	 */
	public static byte[] rsa(byte[] b, Key key) {
		return doFinal(b, key, EncryptConstants.ALGO_RSA, Cipher.DECRYPT_MODE);
	}

	// /**
	// * 可逆的非对称解密算法 DSA算法
	// * @param b 需要解密的字节数组
	// * @return 返回解密后的字节数组
	// */
	// public static byte[] dsa(byte[] b) {
	// return dsa(b, KeyUtil.getPrivateKey(EncryptConstants.ALGO_DSA));
	// }
	//
	// /**
	// * 可逆的非对称解密算法 RSA算法
	// * @param b 需要解密的字节数组
	// * @param key 解密密钥
	// * @return 返回解密后的字节数组
	// */
	// public static byte[] dsa(byte[] b, Key key) {
	// return doFinal(b, key, EncryptConstants.ALGO_DSA, Cipher.DECRYPT_MODE);
	// }

	// /**
	// * 可逆的非对称解密算法 DH算法
	// * @param b 需要解密的字节数组
	// * @return 返回解密后的字节数组
	// */
	// public static byte[] dh(byte[] b) {
	// return dh(b, KeyUtil.getPrivateKey(EncryptConstants.ALGO_DH));
	// }
	//
	// /**
	// * 可逆的非对称解密算法 DH算法
	// * @param b 需要解密的字节数组
	// * @param key 解密密钥
	// * @return 返回解密后的字节数组
	// */
	// public static byte[] dh(byte[] b, Key key) {
	// return doFinal(b, key, EncryptConstants.ALGO_DH, Cipher.DECRYPT_MODE);
	// }

	/**
	 * 解密字符串
	 * @param text 要解密的字符串
	 * @param key 解密密钥Key 长度有限制 DSE 为8位 ASE 为16位
	 * @param offset 偏移从第几位开始
	 * @param len 长度一共几位
	 * @param algorithm 算法
	 * @return 解密后的字符串
	 */
	private static byte[] decrypt(byte[] b, byte[] key, int offset, int len, String algorithm) {
		return doFinal(b, key, offset, len, algorithm, Cipher.DECRYPT_MODE);
	}

	/**
	 * 私有构造
	 */
	private Decrypts() {}
}
