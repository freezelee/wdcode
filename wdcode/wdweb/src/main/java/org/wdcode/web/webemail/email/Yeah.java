package org.wdcode.web.webemail.email;

import java.util.List;
import java.util.Map;

import org.wdcode.common.constants.EncodingConstants;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;

import org.wdcode.common.util.StringUtil;
import org.wdcode.web.http.Http;
import org.wdcode.web.webemail.base.BaseWebEmail;
import org.wdcode.web.webemail.bean.Person;

/**
 * 获得Yeah联系人
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public class Yeah extends BaseWebEmail {
	// 登录地址
	private final static String	LOGINURL;
	// 获得联系人地址
	private final static String	CONTACTLISTURL;
	// 登录需要的sid
	private String				sid;

	// 静态初始化
	static {
		LOGINURL = "http://reg.163.com/login.jsp";
		CONTACTLISTURL = "http://g1a6.mail.yeah.net/jy3/address/addrlist.jsp?sid=";
	}

	/**
	 * 构造函数
	 * @param email
	 * @param password
	 */
	public Yeah(String email, String password) {
		super(email, password);
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
	protected String getLoginURL() {
		return LOGINURL;
	}

	/*
	 * 模拟登录
	 */
	@Override
	protected void login(Http client) {
		// 声明Map 保存提交参数
		Map<String, String> data = Maps.getMap();
		// 提交参数
		data.put(".verifycookie", "1");
		data.put("style", "9");
		data.put("product", "mailyeah");
		data.put("username", getEmail());
		data.put("password", getPassword());
		data.put("lightweight", "1");
		data.put("type", "1");
		data.put("url", "http://entry.mail.yeah.net/cgi/ntesdoor");
		data.put("user", StringUtil.subStringEnd(getEmail(), "@"));

		// 登录正文
		String content = StringUtil.toString(client.post(LOGINURL, data));
		// 判断登录失败
		if (!content.contains("window.location.replace")) {
			return;
		}

		// 获得url
		String reUrl = content.substring(content.indexOf("href=\"") + 6);
		reUrl = reUrl.substring(0, reUrl.indexOf("\""));
		reUrl = reUrl.replace("|", "%40");

		// 获得正文
		content = StringUtil.toString(client.get(reUrl));

		// 获得url
		String reUrl2 = content.substring(content.indexOf("href=\"") + 6);
		reUrl2 = reUrl2.substring(0, reUrl2.indexOf("\""));

		// 获得正文
		content = StringUtil.toString(client.get(reUrl2));
		// 获得sid
		if (content.contains("options.jsp?sid=")) {
			sid = StringUtil.subStringEnd(StringUtil.subString(content, "options.jsp?sid="), "\"");
		}
	}

	/*
	 * 解析流为联系人列表
	 */
	protected List<Person> parseContacts(byte[] b) {
		// 读取流问字符串
		String source = StringUtil.toString(b, EncodingConstants.GBK);
		// 声明联系人列表
		List<Person> lsContact = Lists.getList();

		// 开始
		int startI = 0;
		// 姓名截取
		String nameStr = "</a></td><td class=\"Ibx_Td_addrEmail\">";
		// Email截取
		String emailStr = "</a></td><td class=\"Ibx_Td_addrMoblie\">";
		// 循环截取
		while ((startI = source.indexOf(nameStr)) != -1) {
			// 姓名
			String sb = source.substring(0, startI);
			String name = sb.substring(sb.lastIndexOf(">") + 1);

			// 内容
			source = source.substring(startI + nameStr.length());
			startI = source.indexOf(emailStr);

			// Email
			String email = source.substring(0, startI);
			email = email.substring(email.lastIndexOf(">") + 1);

			// 内容
			source = source.substring(startI + emailStr.length());

			// 添加联系人
			lsContact.add(new Person(name, email));
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
	 * 判断是否是Yeah邮箱
	 * @param email
	 * @return
	 */
	public static boolean isYeah(String email) {
		return email.indexOf("@yeah.") != -1;
	}
}
