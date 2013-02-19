package org.wdcode.web.webemail.email;

import org.wdcode.web.webemail.email.hotmail.HotmailProxy;

/**
 * 获得Hotmail联系人
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public class Hotmail extends HotmailProxy {
	/**
	 * 构造函数
	 * @param username
	 * @param password
	 */
	public Hotmail(String username, String password) {
		super(username, password);
	}
}
