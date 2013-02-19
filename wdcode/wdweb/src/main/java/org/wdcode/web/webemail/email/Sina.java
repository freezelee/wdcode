package org.wdcode.web.webemail.email;

import java.util.List;
import java.util.Map;

import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.core.engine.CsvEngine;
import org.wdcode.web.http.Http;
import org.wdcode.web.webemail.base.BaseWebEmail;
import org.wdcode.web.webemail.bean.Person;

/**
 * 获得Sina联系人
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public class Sina extends BaseWebEmail {
	// 登录地址
	private final static String	LOGINURL;
	// 获得联系人地址
	private final static String	CONTACTLISTURL;

	static {
		LOGINURL = "http://mail.sina.com.cn/cgi-bin/login.cgi";
		CONTACTLISTURL = "http://%s/classic/addr_export.php?extype=csv";
	}

	/**
	 * 构造函数
	 * @param email
	 * @param password
	 */
	public Sina(String email, String password) {
		super(email, password);
	}

	/**
	 * Web发送Email
	 * @param email 发送地址
	 */
	public void sendEmail(String email) {}

	/*
	 * 获得登录地址
	 */
	@Override
	protected String getLoginURL() {
		return LOGINURL;
	}

	/*
	 * 获得联系人地址
	 */
	@Override
	protected String getContactListURL() {
		return CONTACTLISTURL;
	}

	/*
	 * 模拟登录
	 */
	@Override
	protected void login(Http client) {
		// 声明Map 保存提交参数
		Map<String, String> data = Maps.getMap();
		// 添加参数
		data.put("u", getEmail());
		data.put("psw", getPassword());
		data.put("logintype", "uid");

		// 登录
		client.post(this.getLoginURL(), data);
	}

	/*
	 * 解析流为联系人列表
	 */
	@Override
	protected List<Person> parseContacts(byte[] b) {
		// 获得所有联系人
		List<String[]> list = CsvEngine.read(b);
		// 联系人数量
		int size = list.size();
		// 声明联系人列表 保存联系人
		List<Person> lsContact = Lists.getList(size);
		// 声明Map 保存联系人信息
		String[] cols = list.get(0);
		// 姓名index
		int name = 0;
		// email index
		int email = 0;
		// 循环获得索引
		for (int i = 0; i < cols.length; i++) {
			if ("姓名".equals(cols[i])) {
				name = i;
			}
			if ("电子邮件地址".equals(cols[i])) {
				email = i;
			}
		}
		// 循环联系人
		for (int i = 1; i < size; i++) {
			// 获得Map
			String[] s = list.get(i);
			// 添加联系人
			lsContact.add(new Person(s[name], s[email]));
		}
		// 返回联系人
		return lsContact;
	}

	/**
	 * 发送Email
	 * @param client HttpClient
	 * @param email Email地址
	 * @param title Email标题
	 * @param content Email内容
	 */
	@Override
	protected void toEmail(Http client, String email, String title, String content) {}

	/**
	 * 判断是否是Sian邮箱
	 * @param email
	 * @return
	 */
	public static boolean isSina(String email) {
		String[] domains = { "sina.com", "sina.com.cn" };
		return BaseWebEmail.isConformingEmail(email, domains);
	}
}
