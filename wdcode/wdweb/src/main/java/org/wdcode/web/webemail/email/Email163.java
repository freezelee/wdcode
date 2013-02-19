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
 * 获得163联系人
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public class Email163 extends BaseWebEmail {
	// 登录地址
	private final static String	LOGINURL;
	// 获得联系人地址
	private final static String	CONTACTLISTURL;
	// 登录需要的sid
	private String				sid;

	static {
		LOGINURL = "http://reg.163.com/login.jsp?type=1&url=http://fm163.163.com/coremail/fcg/ntesdoor2?lightweight%3D1%26verifycookie%3D1%26language%3D-1%26style%3D35";
		CONTACTLISTURL = "http://tg1a128.mail.163.com/jy3/address/addrlist.jsp?sid=";
	}

	/**
	 * 构造函数
	 * @param email
	 * @param password
	 */
	public Email163(String email, String password) {
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
	 * 模拟登录
	 */
	@Override
	protected void login(Http client) {
		// 提交
		client.get("http://mail.163.com/", null);
		client.get("http://adgeo.163.com/ad_cookies", null);

		// 声明参数
		Map<String, String> data = Maps.getMap(8);
		data.put(".verifycookie", "1");
		data.put("style", "35");
		data.put("product", "mail163");
		data.put("username", getEmail());
		data.put("password", getPassword());
		data.put("selType", "jy");
		data.put("remUser", "on");
		data.put("secure", "on");

		// 登录
		String content = StringUtil.toString(client.post(getLoginURL(), data));
		if (!content.contains("window.location.replace")) {
			return;
		}

		// 获得跳转url
		String reUrl = StringUtil.subStringEnd(StringUtil.subString(content, "href=\""), "\"").replace("|", "%40");

		// 提交获得正文
		content = StringUtil.toString(client.get(reUrl));

		// 获得sid
		if (content.contains("options.jsp?sid=")) {
			sid = StringUtil.subStringEnd(StringUtil.subString(content, "options.jsp?sid="), "\"");
		}
	}

	/*
	 * 解析流为联系人列表
	 */
	protected List<Person> parseContacts(byte[] b) {
		// 获得HTML
		String source = StringUtil.toString(b, EncodingConstants.GBK);
		// 声明联系人列表
		List<Person> lsContact = Lists.getList();

		// 开始位置
		int startI = 0;
		// 开始字符串
		String nameStr = "</a></td><td class=\"Ibx_Td_addrEmail\">";
		// 结束字符串
		String emailStr = "</a></td><td class=\"Ibx_Td_addrMoblie\">";
		while ((startI = source.indexOf(nameStr)) != -1) {
			// 获得姓名
			String sb = source.substring(0, startI);
			String name = sb.substring(sb.lastIndexOf(">") + 1);

			// 获得正文
			source = source.substring(startI + nameStr.length());
			startI = source.indexOf(emailStr);

			// 获得Email
			String email = source.substring(0, startI);
			email = email.substring(email.lastIndexOf(">") + 1);
			source = source.substring(startI + emailStr.length());

			// 添加联系人
			lsContact.add(new Person(name, email));
		}
		// 返回列表
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
	 * 判断是否是163邮箱
	 * @param email
	 * @return
	 */
	public static boolean isEmail163(String email) {
		String[] domains = { "163.com" };
		return BaseWebEmail.isConformingEmail(email, domains);
	}
}
