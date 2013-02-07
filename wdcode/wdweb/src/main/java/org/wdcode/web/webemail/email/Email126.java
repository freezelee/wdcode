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
 * 获得126邮箱联系人实现类
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public class Email126 extends BaseWebEmail {
	// 登录地址
	private final static String	LOGINURL;
	// 获取联系人地址
	private final static String	CONTACTLISTURL;
	// 模拟登录需要的sid
	private String				sid;

	static {
		LOGINURL = "http://reg.163.com/login.jsp?type=1&product=mail126&url=http://entry.mail.126.com;/cgi/ntesdoor?hid%3D10010102%26lightweight%3D1%26language%3D0%26style%3D3";
		CONTACTLISTURL = "http://tg1a75.mail.126.com/jy3/address/addrlist.jsp?sid=";
	}

	/**
	 * 构造参数
	 * @param email
	 * @param password
	 */
	public Email126(String email, String password) {
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
		return (new StringBuilder(CONTACTLISTURL)).append(sid).toString();
	}

	/*
	 * 模拟登录
	 */
	@Override
	protected void login(Http client) {
		// 声明Map 保存提交参数
		Map<String, String> data = Maps.getMap(12);
		// 添加提交参数
		data.put("style", "3");
		data.put("domain", "126.com");
		data.put("language", "0");
		data.put("user", StringUtil.subStringEnd(getEmail(), "@"));
		data.put("username", getEmail());
		data.put("password", getPassword());

		// 读取第一次提交后的正文
		String content = StringUtil.toString(client.post(LOGINURL, data));
		// 判断登录失败
		if (!content.contains("window.location.replace")) {
			return;
		}

		// 获得url
		String reUrl = StringUtil.subStringEnd(StringUtil.subString(content, "href=\""), "\"").replace("|", "%40");
		// 提交
		content = StringUtil.toString(client.get(reUrl));

		// 获得url
		reUrl = StringUtil.subStringEnd(StringUtil.subString(content, "href=\""), "\"").replaceAll(";", "");
		// 获得提交正文
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
		// 声明联系人列表
		List<Person> contactList = Lists.getList();
		// 读取正文流
		String source = StringUtil.toString(b, EncodingConstants.GBK);

		// 开始
		int startI = 0;
		// 姓名截取
		String nameStr = "</a></td><td class=\"Ibx_Td_addrEmail\">";
		// Email截取
		String emailStr = "</a></td><td class=\"Ibx_Td_addrMoblie\">";

		// 循环截取
		while ((startI = source.indexOf(nameStr)) != -1) {
			// 截取姓名
			String sb = source.substring(0, startI);
			String name = sb.substring(sb.lastIndexOf(">") + 1);

			// 截取正文
			source = source.substring(startI + nameStr.length());
			startI = source.indexOf(emailStr);

			// 截取Email
			String email = source.substring(0, startI);
			email = email.substring(email.lastIndexOf(">") + 1);

			// 截取正文
			source = source.substring(startI + emailStr.length());

			// 添加联系人
			contactList.add(new Person(name, email));
		}

		// 返回联系人
		return contactList;
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
	 * 判断是否是126邮箱
	 * @param email
	 * @return true false
	 */
	public static boolean isEmail126(String email) {
		String[] domains = { "126.com" };
		return BaseWebEmail.isConformingEmail(email, domains);
	}
}
