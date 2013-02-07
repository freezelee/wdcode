package org.wdcode.web.webemail.email.hotmail;

import org.wdcode.web.http.Http;

/**
 * 老版的Hotmail获得联系人实现类
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public class OldHotmail extends AbstractHotmail {

	/**
	 * 构造函数
	 * @param username
	 * @param password
	 */
	public OldHotmail(String username, String password) {
		super(username, password);
	}

	/**
	 * 获得登录地址
	 */
	protected String getContactListURL() {
		return "http://%s/mail/GetContacts.aspx";
	}

	/**
	 * 发送Email
	 */
	protected void toEmail(Http client, String email, String title, String content) {}
}
