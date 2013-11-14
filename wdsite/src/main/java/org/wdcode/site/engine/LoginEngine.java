package org.wdcode.site.engine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.wdcode.base.entity.EntityLogin;
import org.wdcode.common.codec.Hex;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.crypto.Decrypts;
import org.wdcode.common.crypto.Digest;
import org.wdcode.common.crypto.Encrypts;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.StringUtil;
import org.wdcode.core.json.JsonEngine;
import org.wdcode.site.params.SiteParams;
import org.wdcode.site.token.AuthToken;
import org.wdcode.site.token.LoginToken;
import org.wdcode.web.util.AttributeUtil;
import org.wdcode.web.util.IpUtil;

/**
 * 登录信息Bean
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-20
 */
public final class LoginEngine {
	// 空登录信息
	private final static LoginToken	EMPTY		= new LoginToken();
	// 登录信息标识
	private final static String		INFO		= "_info";
	// 游客IP
	private static int				GUEST_ID	= SiteParams.LOGIN_GUEST_ID;

	/**
	 * 是否登录
	 * @param request HttpServletRequest
	 * @param key 登录标识
	 * @return true 登录 false 未登录
	 */
	public static boolean isLogin(HttpServletRequest request, HttpServletResponse response, String key) {
		return getLoginBean(request, response, key).isLogin();
	}

	/**
	 * 添加登录信息
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param login 登录实体
	 * @param maxAge 保存时间
	 */
	public static void addLogin(HttpServletRequest request, HttpServletResponse response, EntityLogin login, int maxAge) {
		// 获得登录token实体
		LoginToken token = new LoginToken(login, IpUtil.getIp(request), IpUtil.getIp());
		// 保存登录信息
		AttributeUtil.set(request, response, login.getClass().getSimpleName() + INFO, SiteParams.LOGIN_SAVE_TOKEN ? encrypt(token) : Encrypts.encrypt(token.toString()), maxAge);
	}

	/**
	 * 获得用户信息
	 * @param request HttpServletRequest
	 * @param key 登录标识
	 * @return 用户信息
	 */
	public static LoginToken getLoginBean(HttpServletRequest request, HttpServletResponse response, String key) {
		// 读取用户信息
		String info = Conversion.toString(AttributeUtil.get(request, key + INFO));
		// 如果用户信息为空
		if (EmptyUtil.isEmpty(info)) {
			return guest(request, response, key);
		} else {
			// 解密登录凭证
			LoginToken token = SiteParams.LOGIN_SAVE_TOKEN ? decrypt(info) : JsonEngine.toBean(Decrypts.decryptString(info), LoginToken.class);
			// 如果登录凭证为null返回空
			return token == null ? guest(request, response, key) : token;
		}
	}

	/**
	 * 移除登录信息
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param key 登录标识
	 */
	public static void removeLogin(HttpServletRequest request, HttpServletResponse response, String key) {
		// 写入用户信息
		AttributeUtil.remove(request, response, key + INFO);
		// 销毁用户session
		// SessionUtil.close(request.getSession());
	}

	/**
	 * 加密信息
	 * @param token 登录凭证
	 * @return 加密信息
	 */
	public static String encrypt(AuthToken token) {
		return Hex.encode(Encrypts.rc4(token.toBytes()));
	}

	/**
	 * 获得登录凭证
	 * @param token
	 * @return
	 */
	public static String token(AuthToken token) {
		// 加密登录凭证字符串
		String info = encrypt(token);
		// 返回加密字符串
		return Digest.absolute(info, 5) + StringConstants.MIDLINE + info;
	}

	/**
	 * 验证登录凭证
	 * @return 登录实体
	 */
	public static LoginToken verifyToken(String info) {
		try {
			// 验证去掉"""
			info = StringUtil.replace(info, StringConstants.DOUBLE_QUOT, StringConstants.EMPTY);
			// 判断验证串是否符合标准
			if (!EmptyUtil.isEmpty(info) && info.length() > 5 && info.indexOf(StringConstants.MIDLINE) == 5) {
				// 分解信息
				String[] temp = info.split(StringConstants.MIDLINE);
				// 分解的信息不为空并且只有2组
				if (!EmptyUtil.isEmpty(temp) && temp.length == 2) {
					// 判断校验串是否合法
					if (temp[0].equals(Digest.absolute(temp[1], 5))) {
						return decrypt(temp[1]);
					}
				}
			}
		} catch (Exception ex) {}
		// 返回一个空的登录凭证
		return empty();
	}

	/**
	 * 解密信息
	 * @param value 登录信息
	 * @return 解析后的对象
	 */
	public static LoginToken decrypt(String value) {
		return new LoginToken().toBean(Decrypts.rc4(Hex.decode(value)));
	}

	/**
	 * 获得一样空登录信息
	 * @return
	 */
	public static LoginToken guest(HttpServletRequest request, HttpServletResponse response, String key) {
		// 获得游客凭证
		LoginToken token = new LoginToken(GUEST_ID--, "游客", IpUtil.getIp(request), IpUtil.getIp());
		// 设置游客凭证
		AttributeUtil.set(request, response, key + INFO, encrypt(token), -1);
		// 返回游客凭证
		return token;
	}

	/**
	 * 获得一样空登录信息
	 * @return
	 */
	public static LoginToken empty() {
		return EMPTY;
	}

	/**
	 * 私有构造
	 */
	private LoginEngine() {}
}