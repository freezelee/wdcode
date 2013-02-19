package org.wdcode.web.webemail.base;

import java.util.List;

import org.wdcode.common.lang.Validate;
import org.wdcode.common.util.CloseUtil;
import org.wdcode.web.http.Http;
import org.wdcode.web.http.factory.HttpFactory;
import org.wdcode.web.webemail.WebEmail;
import org.wdcode.web.webemail.bean.Person;

/**
 * 模拟网页Email邮件的抽象类，提供公用的方法实现
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public abstract class BaseWebEmail implements WebEmail {
	// 用户名
	protected String	email;
	// 密码
	protected String	password;
	// WebClient对象
	protected Http		client;

	/**
	 * 构造方法
	 * @param email 用户名
	 * @param password 密码
	 */
	public BaseWebEmail(String email, String password) {
		// 设置用户名
		this.email = email;
		// 设置密码
		this.password = password;
		// 生成WebClient对象
		client = HttpFactory.getHttp();
	}

	/**
	 * 获得联系人列表
	 * @return List 联系人列表
	 */
	public final List<Person> getContactList() {
		// 登录
		login(client);
		// 解析联系人列表
		return parseContacts(client.get(getContactListURL()));
	}

	/**
	 * Web发送Email
	 * @param email 发送地址
	 * @param title 邮件标题
	 * @param content 邮件内容
	 * @发送失败
	 */
	public final void sendEmail(List<String> lsEmail, String title, String content) {
		sendEmail(lsEmail.toArray(new String[lsEmail.size()]), title, content);
	}

	/**
	 * Web发送Email
	 * @param email 发送地址
	 * @param title 邮件标题
	 * @param content 邮件内容
	 * @发送失败
	 */
	public final void sendEmail(String email, String title, String content) {
		// 登录
		login(client);
		// 发送Email
		send(email, title, content);
	}

	/**
	 * Web发送Email
	 * @param email 发送地址
	 * @param title 邮件标题
	 * @param content 邮件内容
	 * @发送失败
	 */
	public final void sendEmail(String[] emails, String title, String content) {
		// 登录
		login(client);

		// 循环发送
		for (int i = 0; i < emails.length; i++) {
			// 发送Email
			send(emails[i], title, content);
		}
	}

	/**
	 * 关闭资源
	 */
	public final void close() {
		// 关闭HttpClient
		CloseUtil.close(client);
	}

	/**
	 * 获得HttpClient
	 * @return HttpClient
	 */
	public final Http getHttpClient() {
		return client;
	}

	/**
	 * 设置HttpClient
	 * @param client HttpClient
	 */
	public final void setHttpClient(Http client) {
		this.client = client;
	}

	/**
	 * 获得用户名
	 */
	protected final String getEmail() {
		return email;
	}

	/**
	 * 获得密码
	 */
	protected final String getPassword() {
		return password;
	}

	/**
	 * 判断是否是指定的Email类型
	 * @param email Email地址
	 * @param domains 指定后缀
	 * @return true false
	 */
	protected final static boolean isConformingEmail(String email, String[] domains) {
		// 判断是否email
		if (!Validate.isEmail(email)) {
			// 返回false
			return false;
		}

		// 循环domains
		for (int i = 0; i < domains.length; i++) {
			// 判断是否是指定的Email后缀
			if (email.indexOf(domains[i]) == email.length() - domains[i].length()) {
				return true;
			}
		}
		// 返回false
		return false;
	}

	/**
	 * 发送Email
	 * @param email Email地址
	 * @param title Email标题
	 * @param content Email内容
	 */
	private void send(String email, String title, String content) {
		// 判断是Email地址
		if (Validate.isEmail(email)) {
			// 发送Email
			toEmail(client, email, title, content);
		}
	}

	/**
	 * 获得登录地址
	 * @return 登录地址
	 */
	protected abstract String getLoginURL();

	/**
	 * 获得联系人地址
	 * @return 联系人地址
	 */
	protected abstract String getContactListURL();

	/**
	 * 模拟登录
	 * @param client HttpClient
	 * @登录失败
	 */
	protected abstract void login(Http client);

	/**
	 * 解析流为联系人列表
	 * @param is 输入流
	 * @return 联系人列表
	 * @解析失败
	 */
	protected abstract List<Person> parseContacts(byte[] b);

	/**
	 * 发送Email
	 * @param client HttpClient
	 * @param email Email地址
	 * @param title Email标题
	 * @param content Email内容
	 */
	protected abstract void toEmail(Http client, String email, String title, String content);
}
