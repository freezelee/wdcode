package org.wdcode.web.webemail.email.hotmail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.lang.Validate;

import org.wdcode.common.util.StringUtil;
import org.wdcode.web.http.Http;
import org.wdcode.web.webemail.base.BaseWebEmail;
import org.wdcode.web.webemail.bean.Person;

/**
 * Hotmail邮箱获得联系人抽象实现
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public abstract class AbstractHotmail extends BaseWebEmail {
	// Hotmail模拟登录需要的登录码
	private final static String	PWDPAD		= "IfYouAreReadingThisYouHaveTooMuchFreeTime";
	// 登录地址
	private final static String	loginURL	= "http://login.live.com/login.srf?id=2";

	/**
	 * 构造函数
	 * @param username
	 * @param password
	 */
	public AbstractHotmail(String username, String password) {
		super(username, password);
	}

	/**
	 * 获得登录地址
	 */
	protected String getLoginURL() {
		return loginURL;
	}

	/**
	 * 模拟登录
	 * @param client
	 */
	protected void login(Http client) {
		// 登录
		loginAndRedirect(client);
	}

	/*
	 * 登录
	 */
	protected byte[] loginAndRedirect(Http client) {
		// 获得模拟提交后的Html正文
		String content = StringUtil.toString(client.get(loginURL, null));

		// 获得需要的参数
		String ppsx = getInputValue("PPSX", content);
		String ppft = getInputValue("PPFT", content);
		String formUrl = getFormUrl(content);

		// 获得微软登录码
		String pwdPad = PWDPAD.substring(0, PWDPAD.length() - getPassword().length());

		// 声明Map 保存提交参数
		Map<String, String> data = Maps.getMap(6);
		// 添加提交参数
		data.put("PPSX", ppsx);
		data.put("PwdPad", pwdPad);
		data.put("login", getEmail());
		data.put("passwd", getPassword());
		data.put("LoginOptions", "2");
		data.put("PPFT", ppft);

		// 获得第二次模拟提交后的Html正文
		content = StringUtil.toString(client.post(formUrl, data, loginURL));

		// 邮箱用户名密码错误
		if (content.contains("password is incorrect")) {
			return ArrayConstants.BYTES_EMPTY;
		}

		// 邮箱格式不正确
		if (content.contains("type your e-mail address in the following format")) {
			return ArrayConstants.BYTES_EMPTY;
		}

		// 返回第三次模拟提交返回Html正文
		return client.get(getJSRedirectLocation(content), formUrl);

	}

	/*
	 * 获得JS跳转地址
	 */
	protected String getJSRedirectLocation(String content) {
		// 截取出正确的地址 返回地址
		return StringUtil.subStringEnd(StringUtil.subString(content, "window.location.replace(\""), "\"");
	}

	/*
	 * 获得联系人地址
	 */
	protected List<Person> parseContacts(byte[] b) {
		List<Person> contacts = null;

		try {
			contacts = new ArrayList<Person>();
			BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(b), "UTF-8"));// CP1252

			String separator = ";";
			String line;
			int i = 0;

			// 读取联系人
			while ((line = in.readLine()) != null) {
				if (i > 0) {
					if (i == 1 && !line.contains(separator)) {
						separator = ",";
					}
					String[] values = line.split(separator);
					if (values.length < 47)
						continue;
					String email = parseValue(values[46]);
					int atIndex = email.indexOf("@");
					// only add real contacts with a email adress
					if (email.length() == 0 || atIndex == -1)
						continue;

					String name = parseValue(values[1]);
					if (values[2].length() > 0)
						name += " " + parseValue(values[2]);
					if (values[3].length() > 0)
						name += " " + parseValue(values[3]);
					if (name.length() == 2)
						name = email.substring(0, atIndex);

					email = email.toLowerCase();

					if (Validate.isEmail(email)) {
						contacts.add(new Person(name, email));
					}
				}
				i++;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return contacts;
	}

	/*
	 * 获得字符串值
	 */
	private String parseValue(String value) {
		// chop off quotes
		if (value.length() > 0 && value.charAt(0) == '"') {
			value = value.substring(1, value.length() - 1);
		}
		return value;
	}

	/*
	 * 获得转换后URL
	 */
	protected String getFormUrl(String content) {
		int begin = content.indexOf("<form") + 5;
		int end = content.indexOf("</form>", begin);
		content = content.substring(begin, end);
		String[] attributes = content.split("\\s+");
		Pattern p = Pattern.compile("action=\"([^\\s\"]+)\"");
		for (int i = 0; i < attributes.length; i++) {
			Matcher matcher = p.matcher(attributes[i]);
			if (matcher.find()) {
				return matcher.group(1);
			}
		}
		return null;
	}

	/*
	 * 获得表单值
	 */
	protected String getInputValue(String name, String content) {
		Pattern p = Pattern.compile("^.*value=\"([^\\s\"]+)\"");
		int index = content.indexOf(name) + name.length() + 2;
		content = content.substring(index, index + 200 > content.length() ? content.length() : index + 200);
		Matcher matcher = p.matcher(content);
		if (matcher.find()) {
			return matcher.group(1);
		} else {
			return StringConstants.EMPTY;
		}

	}

	/**
	 * 判断是否是Hotmail邮箱
	 * @param email
	 * @return
	 */
	public static boolean isHotmail(String email) {
		return (email.indexOf("@live.") != -1) || (email.indexOf("@hotmail.") != -1) || (email.indexOf("@msn.com") != -1);
	}
}
