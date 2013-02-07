package org.wdcode.web.webemail.email.hotmail;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.wdcode.common.constants.ArrayConstants;

import org.wdcode.common.lang.Maps;
import org.wdcode.common.log.Logs;
import org.wdcode.common.params.CommonParams;
import org.wdcode.common.util.StringUtil;
import org.wdcode.web.http.Http;

/**
 * 新版Hotmail和Live邮箱获得联系人实现类
 * @author WD
 * @since JDK6
 * @version 1.0 2009-03-01
 */
public class LiveHotmail extends AbstractHotmail {
	/**
	 * 构造方法
	 * @param username
	 * @param password
	 */
	public LiveHotmail(String username, String password) {
		super(username, password);
	}

	/**
	 * 获得联系人地址
	 */
	protected String getContactListURL() {
		return "http://%s/mail/options.aspx?subsection=26";
	}

	/**
	 * 模拟登录
	 */
	protected void login(Http client) {
		dissmissMessageAtLogin(client, StringUtil.toString(loginAndRedirect(client)));
	}

	/**
	 * 解析登录信息
	 * @param client
	 * @param content
	 * @return
	 */
	private boolean dissmissMessageAtLogin(Http client, String content) {
		// 获得参数
		String ref = client.getCurrentURL();
		String messageFormName = "MessageAtLoginForm";

		if (!content.contains(messageFormName))
			return false;

		int index = content.indexOf(messageFormName) + messageFormName.length();
		Pattern p = Pattern.compile("^.*action=\"([^\\s\"]+)\"");
		String action = content.substring(index, index + 200 > content.length() ? content.length() : index + 200);
		Matcher matcher = p.matcher(action);
		action = matcher.group(1);
		if (action.indexOf("http") != 0) {
			action = ref.substring(0, ref.lastIndexOf("/") + 1) + action;
		}

		String viewState = getInputValue("__VIEWSTATE", content);
		String eventValidation = getInputValue("__EVENTVALIDATION", content);

		// 声明Map 保存提交参数
		Map<String, String> data = Maps.getMap(4);
		// 添加参数
		data.put("__VIEWSTATE", viewState);
		data.put("__EVENTVALIDATION", eventValidation);
		data.put("DontShowMessageAtLoginCheckbox", "1");
		data.put("TakeMeToInbox", "Continue");

		// 转换成字符串
		StringUtil.toString(client.post(action, data, ref));
		// 返回true
		return true;
	}

	/**
	 * 获得内容
	 */
	protected byte[] getContactListContent(Http client, String listUrl, String referer) {
		return getContactListContentLiveHotmail(client, listUrl, referer);
	}

	/**
	 * 获得内容
	 */
	byte[] getContactListContentLiveHotmail(Http client, String listUrl, String referer) {
		try {
			BufferedReader blop = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(client.get(listUrl, referer)), CommonParams.ENCODING));

			Pattern pViewState = Pattern.compile(".*name=\"__VIEWSTATE\".*value=\"([^\"]*)\".*");
			String viewState = null;

			Pattern pEventValidation = Pattern.compile(".*name=\"__EVENTVALIDATION\".*value=\"([^\"]*)\".*");
			String eventValidation = null;

			String line = blop.readLine();
			while (viewState == null || eventValidation == null) {
				line = blop.readLine();

				if (line == null)
					break;

				Matcher mViewState = pViewState.matcher(line);
				if (mViewState.matches()) {
					viewState = mViewState.group(1);
				} else {
					Matcher mEventValidation = pEventValidation.matcher(line);
					if (mEventValidation.matches()) {
						eventValidation = mEventValidation.group(1);
					}
				}
			}
			blop.close();

			if (viewState == null || eventValidation == null) {
				// NOT GOOD !
				throw new RuntimeException("Could not fill form for getting CSV from Hotmail");
			}
			String mt = client.getCookieValue("mt");

			Map<String, String> data = Maps.getMap();
			data.put("__VIEWSTATE", viewState);
			data.put("__EVENTVALIDATION", eventValidation);
			data.put("ctl02$ExportButton", "Exporter des contacts");
			data.put("mt", mt);
			return client.post(listUrl, data, listUrl);
		} catch (IOException e) {
			Logs.error(e);
			return ArrayConstants.BYTES_EMPTY;
		}
	}

	protected void toEmail(Http client, String email, String title, String content) {}
}
