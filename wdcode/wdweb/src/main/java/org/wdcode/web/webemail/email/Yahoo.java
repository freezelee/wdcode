package org.wdcode.web.webemail.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.StringUtil;
import org.wdcode.core.engine.CsvEngine;
import org.wdcode.web.http.Http;
import org.wdcode.web.webemail.base.BaseWebEmail;
import org.wdcode.web.webemail.bean.Person;

/**
 * 获得Yahoo联系人
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public class Yahoo extends BaseWebEmail {
	// 登录地址
	private static final String	LOGINURL;
	// 获得联系人地址
	private static final String	CONTACTLISTURL;
	// 联系人URL
	private static final String	ADDRESS_BOOK_URL;
	// 登录需要
	private String				crumb;

	static {
		LOGINURL = "https://login.yahoo.com/config/login";
		CONTACTLISTURL = "http://address.yahoo.com/index.php?VPC=import_export&A=B&submit[action_export_yahoo]=Export+Now&.crumb=";
		ADDRESS_BOOK_URL = "http://address.mail.yahoo.com/?1&VPC=import_export";
	}

	/**
	 * 构造函数
	 * @param email
	 * @param password
	 */
	public Yahoo(String email, String password) {
		super(email, password);
	}

	/*
	 * 获得联系人地址
	 */
	@Override
	protected String getContactListURL() {
		return CONTACTLISTURL + crumb;
	}

	/*
	 * 获得登录地址
	 */
	@Override
	protected String getLoginURL() {
		return LOGINURL;
	}

	/*
	 * 模拟登录
	 */
	@Override
	protected void login(Http client) {
		// 声明Map 保存提交参数
		Map<String, String> data = Maps.getMap(23);
		// 提交参数
		data.put(".tries", "2");
		data.put(".src", "ym");
		data.put(".md5", "");
		data.put(".hash", "");
		data.put(".js", "");
		data.put(".last", "");
		data.put("promo", "");
		data.put(".intl", "us");
		data.put(".bypass", "");
		data.put(".partner", "");
		data.put(".u", "4eo6isd23l8r3");
		data.put(".v", "0");
		data.put(".challenge", "gsMsEcoZP7km3N3NeI4mXkGB7zMV");
		data.put(".yplus", "");
		data.put(".emailCode", "");
		data.put(".pkg", "");
		data.put(".stepid", "");
		data.put(".ev", "");
		data.put(".hasMsgr", "1");
		data.put(".chkP", "Y");
		data.put(".done", "http://mail.yahoo.com/");
		data.put("login", getEmail());
		data.put("passwd", getPassword());

		// 登录正文
		String content = StringUtil.toString(client.post(this.getLoginURL(), data));
		// 判断登录失败
		if (content.contains("Invalid ID or password")) {
			return;
		} else if (content.contains("Sign in")) {
			return;
		} else if (content.contains("errormsg_0_logincaptcha")) {
			return;
		} else if (content.contains("Invalid request")) {
			return;
		}

		// 登录
		content = StringUtil.toString(client.post(ADDRESS_BOOK_URL, null));

		// 获得crumb
		String subSearch = "id=\"crumb2\" value=\"";
		int pos = content.indexOf(subSearch);
		crumb = content.substring(content.indexOf("value", pos) + 7, content.indexOf(">", pos) - 1);
	}

	/**
	 * 解析流为联系人列表
	 */
	protected List<Person> parseContacts(byte[] b) {
		List<Person> contacts = null;

		List<String[]> myEntries = CsvEngine.read(b);

		contacts = new ArrayList<Person>(myEntries.size());

		String name;
		String email;
		for (String[] entry : myEntries) {

			name = entry[0]; // First name
			if (entry[1] != null && entry[1].length() > 0) {
				// 2nd name
				if (name.length() > 0)
					name += " " + entry[1];
				else
					name = entry[1];
			}
			if (entry[2] != null && entry[2].length() > 0) {
				// Last name
				if (name.length() > 0)
					name += " " + entry[2];
				else
					name = entry[2];
			}
			if (entry[3] != null && entry[3].length() > 0) {
				// nickname
				if (name.length() > 0)
					name += " (" + entry[3] + ")";
				else
					name = entry[3];
			}

			if (!"".equals(entry[4])) {
				// email
				email = entry[4];
			} else {
				continue;
			}

			contacts.add(new Person(name, email));
		}
		return contacts;
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
	 * 判断是否是Yahoo邮箱
	 * @param email
	 * @return
	 */
	public static boolean isYahoo(String email) {
		return (email.indexOf("@yahoo.") != -1);
	}
}