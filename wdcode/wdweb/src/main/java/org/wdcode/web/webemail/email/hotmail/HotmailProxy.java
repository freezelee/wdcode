package org.wdcode.web.webemail.email.hotmail;

import org.wdcode.web.http.Http;

/**
 * Hotmail获得联系人实现类
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public class HotmailProxy extends AbstractHotmail {
	// 老Hotmail
	private OldHotmail	oldImporter;
	// 新Hotmail
	private LiveHotmail	newImporter;
	// 是否新的Hotmail
	private boolean		isNewHotmailVersion	= true;

	/**
	 * 构造参数
	 * @param username
	 * @param password
	 */
	public HotmailProxy(String username, String password) {
		super(username, password);
		newImporter = new LiveHotmail(username, password);
		newImporter.setHttpClient(getHttpClient());
	}

	/*
	 * 获得联系人地址
	 */
	protected String getContactListURL() {
		if (isNewHotmailVersion) {
			return newImporter.getContactListURL();
		} else {
			return oldImporter.getContactListURL();
		}
	}

	/*
	 * 获得登录地址
	 */
	protected String getLoginURL() {
		if (isNewHotmailVersion) {
			return newImporter.getLoginURL();
		} else {
			return oldImporter.getLoginURL();
		}
	}

	/*
	 * 模拟登录
	 */
	protected void login(Http client) {
		newImporter.login(client);
		if (!getHttpClient().getCurrentURL().contains("TodayLight.aspx")) {
			isNewHotmailVersion = false;
			oldImporter = new OldHotmail(this.getEmail(), this.getPassword());
			oldImporter.setHttpClient(getHttpClient());
		} else {
			isNewHotmailVersion = true;
		}
	}

	/*
	 * 获得联系人
	 */
	protected byte[] getContactListContent(Http client, String listUrl, String referer) {
		if (isNewHotmailVersion) {
			return newImporter.getContactListContentLiveHotmail(client, listUrl, referer);
		} else {
			return client.get(listUrl, referer);
		}
	}

	protected void toEmail(Http client, String email, String title, String content) {}

}
