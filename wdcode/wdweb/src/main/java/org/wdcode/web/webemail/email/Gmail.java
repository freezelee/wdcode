package org.wdcode.web.webemail.email;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONTokener;

import org.wdcode.common.lang.Maps;
import org.wdcode.common.util.StringUtil;
import org.wdcode.web.http.Http;
import org.wdcode.web.webemail.base.BaseWebEmail;
import org.wdcode.web.webemail.bean.Person;

/**
 * 获得gmail联系人
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public class Gmail extends BaseWebEmail {
	// 登录地址
	private final static String	LOGINURL;
	// 获得联系人地址
	private final static String	CONTACTLISTURL;
	// json的开始字符
	private final static String	STARTTAG;
	// json的结束字符
	private final static String	ENDTAG;

	static {
		LOGINURL = "https://www.google.com/accounts/ServiceLoginAuth";
		CONTACTLISTURL = "https://mail.google.com/mail/contacts/data/contacts?thumb=true&show=ALL&enums=true&psort=Name&max=10000&out=js&rf=&jsx=true";
		STARTTAG = "&&&START&&&";
		ENDTAG = "&&&END&&&";
	}

	/**
	 * 构造函数
	 * @param email
	 * @param password
	 */
	public Gmail(String email, String password) {
		super(email, password);
	}

	/*
	 * 获得联系人地址
	 */
	@Override
	protected String getContactListURL() {
		return CONTACTLISTURL;
	}

	/*
	 * 模拟登录地址
	 */
	@Override
	protected String getLoginURL() {
		return LOGINURL;
	}

	/*
	 * 模拟登录
	 */
	protected void login(Http client) {
		// 声明Map 用于保存提交参数
		Map<String, String> data = Maps.getMap(10);
		// 添加参数
		data.put("ltmpl", "yj_blanco");
		data.put("continue", "https://mail.google.com/mail/");
		data.put("ltmplcache", "2");
		data.put("service", "mail");
		data.put("rm", "false");
		data.put("hl", "en");
		data.put("Email", getEmail());
		data.put("Passwd", getPassword());
		data.put("rmShown", "1");
		data.put("null", "Sign in");

		// 获得登录后正文
		String content = StringUtil.toString(client.post(getLoginURL(), data));

		// 判断登录失败
		if (content.contains("Username and password do not match")) {
			return;
		} else if (content.contains("Required field must not be blank")) {
			return;
		} else if (content.contains("errormsg_0_logincaptcha")) {
			return;
		} else if (content.contains("Invalid request")) {
			return;
		}
	}

	/*
	 * 解析流为联系人列表
	 */
	protected List<Person> parseContacts(byte[] b) {
		// 获得json字符串
		String s = new String(b);
		String json = s.substring(s.indexOf(STARTTAG), s.indexOf(ENDTAG));
		// 获得JSON实例
		JSONObject util = ((JSONObject) new JSONTokener(json).nextValue()).getJSONObject("Body");
		// 获得联系人的
		List<Map<String, Object>> list = getArray(util, "Contacts");
		// 获得联系人数量
		int size = list.size();
		// 声明联系人列表
		List<Person> lsContact = new ArrayList<Person>(size);
		// 声明Map 保存联系人信息
		Map<String, Object> map = null;
		// 循环联系人
		for (int i = 0; i < size; i++) {
			// 获得Map
			map = list.get(i);
			// 获得姓名
			String name = map.get("Name").toString();
			// 获得Email
			s = map.get("Emails").toString();
			String email = s.substring(s.indexOf(":\""), s.indexOf("\","));
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
	 * 读取属性
	 * @param json
	 * @param key
	 * @return
	 */
	private List<Map<String, Object>> getArray(JSONObject json, String key) {
		try {
			// 获得属性
			JSONArray array = json.getJSONArray(key);
			// 总数量
			int size = array.size();
			// 获得List实例
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(size);
			// 声明Map
			Map<String, Object> map = null;
			// 循环属性
			for (int i = 0; i < size; i++) {
				// 获得JSONObject
				JSONObject jo = array.getJSONObject(i);
				// 获得Map实例
				map = new HashMap<String, Object>(jo.size());
				// 把数据写到Map中
				jo.accumulateAll(map);
				// 把Map添加到列表中
				list.add(map);
			}
			// 返回列表
			return list;
		} catch (JSONException e) {
			return Collections.emptyList();
		}
	}

	/**
	 * 判断是否是Gmail
	 * @param email
	 * @return
	 */
	public static boolean isGmail(String email) {
		String[] domains = { "gmail.com", "googlemail.com" };
		return BaseWebEmail.isConformingEmail(email, domains);
	}
}
