package org.wdcode.web.webemail.email;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import org.wdcode.common.crypto.Digest;

import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.StringUtil;
import org.wdcode.core.xml.Document;
import org.wdcode.core.xml.Element;
import org.wdcode.core.xml.builder.XmlBuilder;
import org.wdcode.web.http.Http;
import org.wdcode.web.webemail.base.BaseWebEmail;
import org.wdcode.web.webemail.bean.Person;

/**
 * 获得Sohu联系人
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public class Sohu extends BaseWebEmail {
	// 登录地址
	private final static String	LOGINURL;
	// 获得 通讯录地址
	private final static String	CONTACTLISTURLURL;
	// 第二次提交
	private final static String	LOGINTWO;
	// 登录的编号
	private String				number;

	static {
		LOGINURL = "http://passport.sohu.com/sso/login.jsp";
		CONTACTLISTURLURL = "http://mail.sohu.com/bapp/%num/contact?action=export&type=xml";
		LOGINTWO = "http://login.mail.sohu.com/servlet/LoginServlet";
	}

	/**
	 * 构造函数
	 * @param email
	 * @param password
	 */
	public Sohu(String email, String password) {
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
		return CONTACTLISTURLURL.replaceAll("%num", number);
	}

	/*
	 * 模拟登录
	 */
	@Override
	protected void login(Http client) {
		// 声明Map 保存提交参数
		Map<String, String> data = Maps.getMap(8);
		// 添加提交参数
		data.put("userid", getEmail());
		data.put("password", Digest.md5(getPassword()));
		data.put("appid", "1000");
		data.put("persistentcookie", "0");
		data.put("s", Conversion.toString(DateUtil.getTime() * 1000));
		data.put("b", "2");
		data.put("w", "1024");
		data.put("pwdtype", "1");

		// 登录失败
		if (!StringUtil.toString(client.post(LOGINURL, data)).contains("success")) {
			return;
		}

		// 第二次登录
		client.get(LOGINTWO);
		// 获得编号
		number = StringUtil.sub(Conversion.toString(client.getAttribute("http.cookie-origin")), "bapp/(\\d*)/main");
	}

	/**
	 * 解析流为联系人列表
	 * @param is
	 * @return
	 */
	protected List<Person> parseContacts(byte[] b) {
		// 根据流生成XML Document
		Document doc = XmlBuilder.readDocument(new ByteArrayInputStream(b));
		// 获得根节点
		Element root = doc.getRootElement();

		// 获得所有联系人列表
		List<Element> lsCards = root.getElement("cards").getElements("card");
		// 获得联系人数量
		int size = lsCards.size();
		// 声明联系人列表
		List<Person> lsContacts = Lists.getList(size);
		// 声明元素 保存联系人
		Element card = null;

		// 循环解析出联系人
		for (int i = 0; i < size; i++) {
			// 获得单个联系人
			card = lsCards.get(i);
			// 添加到联系人列表
			lsContacts.add(new Person(card.getAttributeValue("fullname"), card.getAttributeValue("personalemail")));
		}
		// 返回联系人
		return lsContacts;
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
	 * 判断是否是Sohu邮箱
	 * @param email
	 * @return
	 */
	public static boolean isSohu(String email) {
		String[] domains = { "sohu.com" };
		return BaseWebEmail.isConformingEmail(email, domains);
	}
}
