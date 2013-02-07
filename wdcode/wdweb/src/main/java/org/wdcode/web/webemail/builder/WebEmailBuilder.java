package org.wdcode.web.webemail.builder;

import org.wdcode.web.webemail.WebEmail;
import org.wdcode.web.webemail.email.Email126;
import org.wdcode.web.webemail.email.Email163;
import org.wdcode.web.webemail.email.Gmail;
import org.wdcode.web.webemail.email.Hotmail;
import org.wdcode.web.webemail.email.Sina;
import org.wdcode.web.webemail.email.Sohu;
import org.wdcode.web.webemail.email.Tom;
import org.wdcode.web.webemail.email.Yahoo;
import org.wdcode.web.webemail.email.Yeah;

/**
 * 模拟网页Email邮件的工厂
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public final class WebEmailBuilder {
	/**
	 * 获得ContactList实例
	 * @param email email地址
	 * @param password email密码
	 * @return WebEmail
	 */
	public static WebEmail build(String email, String password) {
		// 判断邮箱类型
		if (Hotmail.isHotmail(email)) {
			// Hotmail邮箱
			return hotmail(email, password);
		} else if (Gmail.isGmail(email)) {
			// Gmail邮箱
			return gmail(email, password);
		} else if (Yahoo.isYahoo(email)) {
			// Yahoo邮箱
			return yahoo(email, password);
		} else if (Email163.isEmail163(email)) {
			// 163邮箱
			return email163(email, password);
		} else if (Email126.isEmail126(email)) {
			// 126邮箱
			return email126(email, password);
		} else if (Sina.isSina(email)) {
			// Sina邮箱
			return sina(email, password);
		} else if (Sohu.isSohu(email)) {
			// Sohu邮箱
			return sohu(email, password);
		} else if (Tom.isTom(email)) {
			// Tom邮箱
			return tom(email, password);
		} else if (Yeah.isYeah(email)) {
			// Yeah邮箱
			return yeah(email, password);
		} else {
			// 不支持的邮箱
			return null;
		}
	}

	/**
	 * 获得Hotmail联系人
	 * @param email email地址
	 * @param password email密码
	 * @return WebEmail @ 不支持的邮箱
	 */
	public static WebEmail hotmail(String email, String password) {
		return new Hotmail(email, password);
	}

	/**
	 * 获得Gmail联系人
	 * @param email email地址
	 * @param password email密码
	 * @return WebEmail @ 不支持的邮箱
	 */
	public static WebEmail gmail(String email, String password) {
		return new Gmail(email, password);
	}

	/**
	 * 获得Yahoo联系人
	 * @param email email地址
	 * @param password email密码
	 * @return WebEmail @ 不支持的邮箱
	 */
	public static WebEmail yahoo(String email, String password) {
		return new Yahoo(email, password);
	}

	/**
	 * 获得163联系人
	 * @param email email地址
	 * @param password email密码
	 * @return WebEmail @ 不支持的邮箱
	 */
	public static WebEmail email163(String email, String password) {
		return new Email163(email, password);
	}

	/**
	 * 获得126联系人
	 * @param email email地址
	 * @param password email密码
	 * @return WebEmail @ 不支持的邮箱
	 */
	public static WebEmail email126(String email, String password) {
		return new Email126(email, password);
	}

	/**
	 * 获得Sina联系人
	 * @param email email地址
	 * @param password email密码
	 * @return WebEmail @ 不支持的邮箱
	 */
	public static WebEmail sina(String email, String password) {
		return new Sina(email, password);
	}

	/**
	 * 获得Sohu联系人
	 * @param email email地址
	 * @param password email密码
	 * @return WebEmail @ 不支持的邮箱
	 */
	public static WebEmail sohu(String email, String password) {
		return new Sohu(email, password);
	}

	/**
	 * 获得Tom联系人
	 * @param email email地址
	 * @param password email密码
	 * @return WebEmail @ 不支持的邮箱
	 */
	public static WebEmail tom(String email, String password) {
		return new Tom(email, password);
	}

	/**
	 * 获得Yeah联系人
	 * @param email email地址
	 * @param password email密码
	 * @return WebEmail @ 不支持的邮箱
	 */
	public static WebEmail yeah(String email, String password) {
		return new Yeah(email, password);
	}

	/**
	 * 静态初始化
	 */
	private WebEmailBuilder() {}
}