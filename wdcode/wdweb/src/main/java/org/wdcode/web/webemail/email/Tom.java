package org.wdcode.web.webemail.email;

import java.util.List;
import java.util.Map;

import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.StringUtil;
import org.wdcode.core.engine.CsvEngine;
import org.wdcode.web.http.Http;
import org.wdcode.web.webemail.base.BaseWebEmail;
import org.wdcode.web.webemail.bean.Person;

/**
 * 获得TOM联系人
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public class Tom extends BaseWebEmail {
	// 登录地址
	private final static String	LOGINURL;
	// 获得联系人地址
	private final static String	CONTACTLISTURL;
	// 登录需要的sid
	private String				sid;
	// 获得sid的Pattern
	private final static String	REGEX;

	static {
		LOGINURL = "http://login.mail.tom.com/cgi/login";
		CONTACTLISTURL = "http://bjapp2.mail.tom.com/cgi/ldvcapp?funcid=xportadd&outformat=8&outport.x=21&outport.y=7&sid=";
		REGEX = "sid='([^\"]*)';";
	}

	/**
	 * 构造函数
	 * @param email
	 * @param password
	 */
	public Tom(String email, String password) {
		super(email, password);
	}

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
		return CONTACTLISTURL + sid;
	}

	/*
	 * 获得登录地址
	 */
	@Override
	protected void login(Http client) {
		// 声明Map 保存提交参数
		Map<String, String> data = Maps.getMap(6);
		// 添加参数
		data.put("user", getEmail());
		data.put("pass", getPassword());
		data.put("style", "0");
		data.put("verifycookie", "");
		data.put("type", "0");
		data.put("url", "http://bjweb.mail.tom.com/cgi/login2");

		// 登录正文
		String content = StringUtil.toString(client.post(LOGINURL, data));

		// 判断登录
		if (content.contains("错误的用户名/密码") || content.contains("请先填写用户名与密码")) {
			return;
		}

		// 获得sid
		sid = StringUtil.sub(content, REGEX);
	}

	/**
	 * 解析流为联系人列表
	 */
	protected List<Person> parseContacts(byte[] b) {
		// 获得所有联系人
		List<String[]> list = CsvEngine.read(b);
		// 获得所有联系人
		int size = list.size();
		// 声明列表 保存联系人
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
		for (int i = 0; i < size; i++) {
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
	 * 判断是否是Tom邮箱
	 * @param email
	 * @return
	 */
	public static boolean isTom(String email) {
		String[] domains = { "tom.com" };
		return BaseWebEmail.isConformingEmail(email, domains);
	}
}
