package org.wdcode.common.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import org.wdcode.common.constants.EncryptConstants;
import org.wdcode.common.params.CommonParams;

/**
 * 生成密钥对用
 * @author WD
 * @since JDK7
 * @version 1.0 2011-06-18
 */
public final class KeyUtil {
	// 密钥对
	private final static KeyPair	RSA_KEY_PAIR;
	// 密钥对
	private final static KeyPair	DSA_KEY_PAIR;
	// 密钥对
	private final static KeyPair	DH_KEY_PAIR;

	/**
	 * 静态初始化
	 */
	static {
		RSA_KEY_PAIR = getKeyPair(EncryptConstants.ALGO_RSA);
		DSA_KEY_PAIR = getKeyPair(EncryptConstants.ALGO_DSA);
		DH_KEY_PAIR = getKeyPair(EncryptConstants.ALGO_DH);
	}

	/**
	 * 获得密钥对 里面包含 公钥 密钥
	 * @param algorithm 加密算法
	 * @return 密钥对
	 */
	public static KeyPair getKeyPair(String algorithm) {
		return getKeyPair(algorithm, CommonParams.ENCRYPT_KEY_LENGTH, CommonParams.ENCRYPT_KEY_STRING);
	}

	/**
	 * 获得密钥对 里面包含 公钥 密钥
	 * @param algorithm 加密算法
	 * @param size 密钥大小
	 * @param key 生成密钥的Key
	 * @return 密钥对
	 */
	public static KeyPair getKeyPair(String algorithm, int keysize, String key) {
		try {
			// 生成KeyPaire
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
			// 初始化大小
			keyPairGenerator.initialize(keysize, new SecureRandom(StringUtil.toBytes(key)));
			// 返回密钥对
			return keyPairGenerator.genKeyPair();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获得默认公钥
	 * @param algorithm 加密算法
	 * @return 公钥
	 */
	public static PublicKey getPublicKey(String algorithm) {
		// 判断算法
		if (EncryptConstants.ALGO_DSA.equals(algorithm)) {
			// DSA
			return DSA_KEY_PAIR.getPublic();
		} else if (EncryptConstants.ALGO_DH.equals(algorithm)) {
			// DH
			return DH_KEY_PAIR.getPublic();
		} else {
			// RSA
			return RSA_KEY_PAIR.getPublic();
		}
	}

	/**
	 * 获得默认私钥
	 * @param algorithm 加密算法
	 * @return 私钥
	 */
	public static PrivateKey getPrivateKey(String algorithm) {
		// 判断算法
		if (EncryptConstants.ALGO_DSA.equals(algorithm)) {
			// DSA
			return DSA_KEY_PAIR.getPrivate();
		} else if (EncryptConstants.ALGO_DH.equals(algorithm)) {
			// DH
			return DH_KEY_PAIR.getPrivate();
		} else {
			// RSA
			return RSA_KEY_PAIR.getPrivate();
		}
	}

	/**
	 * 私有构造
	 */
	private KeyUtil() {}
}
