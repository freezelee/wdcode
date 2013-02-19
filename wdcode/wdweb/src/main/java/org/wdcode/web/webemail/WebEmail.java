package org.wdcode.web.webemail;

import java.util.List;

import org.wdcode.common.interfaces.Close;
import org.wdcode.web.webemail.bean.Person;

/**
 * 模拟网页Email邮件的接口
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public interface WebEmail extends Close {
	/**
	 * Web发送Email
	 * @param email 发送地址
	 * @param title 邮件标题
	 * @param content 邮件内容
	 */
	void sendEmail(String[] emails, String title, String content);

	/**
	 * Web发送Email
	 * @param title 邮件标题
	 * @param content 邮件内容
	 */
	void sendEmail(List<String> lsEmail, String title, String content);

	/**
	 * Web发送Email
	 * @param title 邮件标题
	 * @param content 邮件内容
	 */
	void sendEmail(String email, String title, String content);

	/**
	 * 获得联系人列表
	 * @return List 联系人列表
	 */
	List<Person> getContactList();
}
