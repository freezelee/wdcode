package org.wdcode.base.action;

import java.io.File;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;

import org.wdcode.base.context.Context;
import org.wdcode.base.params.BaseParams;
import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.crypto.Encrypts;
import org.wdcode.common.io.FileUtil;
import org.wdcode.common.io.StreamUtil;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.params.CommonParams;
import org.wdcode.common.util.BeanUtil;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.MathUtil;
import org.wdcode.common.util.StringUtil;
import org.wdcode.core.json.JsonEngine;
import org.wdcode.web.constants.HttpConstants;
import org.wdcode.web.util.AttributeUtil;
import org.wdcode.web.util.CookieUtil;
import org.wdcode.web.util.IpUtil;
import org.wdcode.web.util.ResponseUtil;
import org.wdcode.web.util.VerifyCodeUtil;

/**
 * Struts2 Action 的抽象实现 其它Struts2 Action可继承此类
 * @author WD
 * @since JDK7
 * @version 1.0 2009-08-26
 */
public class BasicAction extends ActionSupport {
	// 序列化ID
	private static final long		serialVersionUID	= 3314538887531859725L;

	// LIST
	protected final static String	LIST				= "list";

	// 提交的url
	protected String				url;
	// 跨域方法
	protected String				callback;

	// 上传文件
	protected File					upload;
	// 上传文件类型
	protected String				uploadContentType;
	// 上传文件名
	protected String				uploadFileName;

	// 上传文件数组
	protected File[]				uploads;
	// 上传文件数组类型
	protected String[]				uploadsContentType;
	// 上传文件数组名
	protected String[]				uploadsFileName;

	// 模板名
	protected String				module;
	// 返回模式名
	protected String				mode;
	// 全局Context
	@Resource
	protected Context				context;

	/**
	 * 初始化方法
	 */
	@PostConstruct
	protected void init() {
		// 获得提交Action地址
		String actionName = getActionName();
		// 获得模板名
		module = StringUtil.subStringEnd(actionName, StringConstants.UNDERLINE);
		// 获得方法名
		mode = EmptyUtil.isEmpty(mode) ? StringUtil.subStringLast(actionName, StringConstants.UNDERLINE) : mode;
	}

	/**
	 * 获得验证码方法
	 * @return
	 * @throws Exception
	 */
	public String verifyCode() throws Exception {
		// 获得验证码
		VerifyCodeUtil.make(getRequest(), getResponse());
		// 返回到登录页
		return null;
	}

	/**
	 * 获得url
	 * @return 提交的URL
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置url
	 * @param url 提交的URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获得跨域方法
	 * @return 跨域方法
	 */
	public String getCallback() {
		return callback;
	}

	/**
	 * 设置跨域方法
	 * @param callback 跨域方法
	 */
	public void setCallback(String callback) {
		this.callback = callback;
	}

	/**
	 * 获得文件
	 * @return 文件
	 */
	public File getUpload() {
		return upload;
	}

	/**
	 * 设置文件
	 * @param upload 文件
	 */
	public void setUpload(File upload) {
		this.upload = upload;
	}

	/**
	 * 获得文件数组
	 * @return 文件数组
	 */
	public File[] getUploads() {
		return uploads;
	}

	/**
	 * 设置文件数组
	 * @param uploads 文件数组
	 */
	public void setUploads(File[] uploads) {
		this.uploads = uploads;
	}

	/**
	 * 获得上传文件类型
	 * @return 上传文件类型
	 */
	public String getUploadContentType() {
		return uploadContentType;
	}

	/**
	 * 设置上传文件类型
	 * @param uploadContentType 上传文件类型
	 */
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	/**
	 * 获得上传文件名
	 * @return 上传文件名
	 */
	public String getUploadFileName() {
		return uploadFileName;
	}

	/**
	 * 设置上传文件名
	 * @param uploadFileName 上传文件名
	 */
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	/**
	 * 获得上传文件数组类型
	 * @return 上传文件数组类型
	 */
	public String[] getUploadsContentType() {
		return uploadsContentType;
	}

	/**
	 * 设置上传文件数组类型
	 * @param uploadsContentType 上传文件数组类型
	 */
	public void setUploadsContentType(String[] uploadsContentType) {
		this.uploadsContentType = uploadsContentType;
	}

	/**
	 * 获得上传文件数组名
	 * @return 上传文件数组名
	 */
	public String[] getUploadsFileName() {
		return uploadsFileName;
	}

	/**
	 * 设置上传文件数组名
	 * @param uploadsFileName 上传文件数组名
	 */
	public void setUploadsFileName(String[] uploadsFileName) {
		this.uploadsFileName = uploadsFileName;
	}

	/**
	 * 设置模板名
	 * @param module 模板名
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * 设置模式名
	 * @param mode 模式名
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * 获得模板名
	 * @return
	 */
	public String getModule() {
		return module;
	}

	/**
	 * 获得模式名
	 * @return
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * 获得国际化值
	 */
	public String getText(String name) {
		// 获得数组
		String[] val = name.split(StringConstants.COMMA);
		// 设置字符串缓存类
		StringBuilder sb = new StringBuilder();
		// 循环
		for (int i = 0; i < val.length; i++) {
			// 添加内容
			sb.append(super.getText(val[i], StringConstants.EMPTY));
		}
		return sb.toString();
	}

	/**
	 * 获得国际化值
	 */
	public String add(List<Object> values) {
		return MathUtil.add(values.toArray()).toPlainString();
	}

	/**
	 * 获得程序路径
	 * @param name 文件名
	 * @return 程序路径
	 */
	public String getRealPath(String name) {
		return getServletContext().getRealPath(StringConstants.BACKSLASH) + name;
	}

	/**
	 * 获得域名路径
	 * @param name 文件名
	 * @return 域名路径
	 */
	public String getServerPath(String name) {
		return HttpConstants.HTTP + getRequest().getServerName() + getBase() + StringConstants.BACKSLASH + name;
	}

	/**
	 * 获得项目路径
	 * @return 项目路径
	 */
	public String getBase() {
		return getRequest().getContextPath();
	}

	/**
	 * 获得提交IP
	 * @return 提交IP
	 */
	public String getIp() {
		return IpUtil.getIp(getRequest());
	}

	/**
	 * 设置属性
	 * @param key 属性键
	 * @param value 属性值
	 */
	public void set(String key, Object value) {
		AttributeUtil.set(getRequest(), getResponse(), key, value);
	}

	/**
	 * 根据属性键获得属性值
	 * @param key 属性键
	 * @return 属性值
	 */
	public Object get(String key) {
		return AttributeUtil.get(getRequest(), key);
	}

	/**
	 * 删除属性
	 * @param key 属性键
	 */
	public void remove(String key) {
		AttributeUtil.remove(getRequest(), getResponse(), key);
	}

	/**
	 * 获得Request
	 * @return Request
	 */
	public HttpServletRequest getRequest() {
		return context.getRequest();
	}

	/**
	 * 获得Response
	 * @return Response
	 */
	public HttpServletResponse getResponse() {
		return context.getResponse();
	}

	/**
	 * 获得ServletContext
	 * @return ServletContext
	 */
	public ServletContext getServletContext() {
		return context.getServletContext();
	}

	/**
	 * 获得Session
	 * @return Session
	 */
	public HttpSession getSession() {
		return context.getSession();
	}

	/**
	 * 获得Session
	 * @param b
	 * @return Session
	 */
	public HttpSession getSession(boolean b) {
		return context.getSession(b);
	}

	/**
	 * 添加错误信息 错误Field=key value=国际化value
	 * @param key 国际化文件的Key
	 */
	public void addFieldError(String key) {
		addFieldError(key, getText(key));
	}

	/**
	 * 添加信息 调用addActionMessage做国际化处理
	 * @param key 国际化文件的Key
	 */
	public String addMessage(String key) {
		addActionMessage(getText(key));
		return key;
	}

	/**
	 * 获得客户端的Cookie数组
	 * @return Cookie数组
	 */
	public Cookie[] getCookies() {
		return getRequest().getCookies();
	}

	/**
	 * 添加Cookie到客户端
	 * @param name CookieName
	 * @param value CookieValue
	 */
	public void addCookie(String name, String value) {
		CookieUtil.add(getResponse(), name, Encrypts.encrypt(value));
	}

	/**
	 * 删除Cookie
	 * @param name CookieName
	 * @param value CookieValue
	 */
	public void removeCookie(String name) {
		CookieUtil.remove(getResponse(), name);
	}

	/**
	 * 根据name获得Cookie 没找到返回null
	 * @param name CookieName
	 * @return Cookie
	 */
	public Cookie getCookie(String name) {
		return CookieUtil.getCookie(getRequest(), name);
	}

	/**
	 * 根据name获得CookieValue 没找到返回""
	 * @param name CookieName
	 * @return CookieValue
	 */
	public String getCookieValue(String name) {
		return CookieUtil.getCookieValue(getRequest(), name);
	}

	/**
	 * 获得Action方法
	 * @return Action方法
	 */
	public String getActionName() {
		return context.getActionMapping().getName();
	}

	/**
	 * 截取字符串
	 * @param str 要截取的字符串
	 * @param len 截取长度
	 * @return 截取后字符串
	 */
	public String substring(String str, int len) {
		// 判断字符串为空
		if (EmptyUtil.isEmpty(str)) {
			return str;
		}
		// 判断长度大于指定截取长度
		if (str.length() > len) {
			return StringUtil.subString(str, 0, len) + "...";
		}
		// 返回原字符串
		return str;
	}

	/**
	 * 获得Action方法
	 * @return Action方法
	 */
	public List<Object> getFields(Collection<Object> list, String fieldName) {
		return BeanUtil.getFieldValues(list, fieldName);
	}

	/**
	 * 上传文件
	 * @param fileName 文件名
	 * @return 文件路径
	 */
	protected String[] uploads(File[] uploads, String[] uploadsFileName) {
		// 如果没有文件跳出
		if (EmptyUtil.isEmpty(uploads)) {
			return ArrayConstants.STRING_EMPTY;
		}
		// 获得文件个数
		int size = uploads.length;
		// 获得路径
		String[] names = new String[size];
		// 循环文件名
		for (int i = 0; i < size; i++) {
			// 获得路径
			names[i] = upload(uploads[i], uploadsFileName[i]);
		}
		// 返回路径
		return names;
	}

	/**
	 * 上传文件
	 * @return
	 * @throws Exception
	 */
	public String upload() throws Exception {
		return ajax(upload(upload, uploadFileName));
	}

	/**
	 * 上传文件
	 * @return
	 * @throws Exception
	 */
	public String uploads() throws Exception {
		return ajax(uploads(uploads, uploadsFileName));
	}

	/**
	 * 上传文件
	 * @param file 上传文件
	 * @param fileName 文件名
	 * @return 文件路径
	 */
	protected String upload(File file, String fileName) {
		// 如果没有文件跳出
		if (EmptyUtil.isEmpty(file)) {
			return null;
		}
		// 获得上次路径
		String name = getFileName(fileName);
		// 上传文件
		FileUtil.write(getRealPath(name), file);
		// 返回路径
		return getServerPath(name);
	}

	/**
	 * 输出数据到客户端方法
	 * @param json 对象
	 */
	protected String ajax(Object data) throws Exception {
		return ajax(data, CommonParams.ENCODING);
	}

	/**
	 * 输出数据到客户端方法
	 * @param json 对象
	 * @param charsetName 编码
	 */
	protected String ajax(Object data, String charsetName) throws Exception {
		// 清除缓存
		ResponseUtil.noCache(getResponse());
		// 声明返回字符串
		StringBuilder s = new StringBuilder();
		// 如果callback不为空 填补左括号
		if (!EmptyUtil.isEmpty(callback)) {
			s.append(callback).append(StringConstants.LEFT_PARENTHESIS);
		}
		// 添加json数据
		s.append(data instanceof String || data instanceof Number ? Conversion.toString(data) : JsonEngine.toJson(data));
		// 如果callback不为空 填补右括号
		if (!EmptyUtil.isEmpty(callback)) {
			s.append(StringConstants.RIGHT_PARENTHESIS);
		}
		// 写出
		StreamUtil.write(getResponse().getOutputStream(), s.toString(), charsetName);
		// 返回空
		return null;
	}

	/**
	 * 方法回调 所有直接Action回调的方法 一边统一处理
	 * @param s 字符串标识
	 * @return 返回标识
	 */
	protected String callback(Object obj) throws Exception {
		// 判断使用哪种模式
		if ("ajax".equals(mode)) {
			return ajax(obj);
		} else if ("sign".equals(mode)) {
			return ajax(obj instanceof String || obj instanceof Number ? Conversion.toString(obj) : EmptyUtil.isEmpty(obj) ? ERROR : SUCCESS);
		} else {
			if (obj == null) {
				return addMessage(ERROR);
			} else if (obj instanceof String) {
				return Conversion.toString(obj);
			} else if (obj instanceof List<?>) {
				return LIST;
			} else if (obj instanceof Boolean) {
				return Conversion.toBoolean(obj) ? SUCCESS : ERROR;
			} else {
				return addMessage(SUCCESS);
			}
		}
	}

	/**
	 * 获得文件名
	 * @param fileName 文件名
	 * @return 文件名
	 */
	private String getFileName(String fileName) {
		// 获得文件名
		String name = BaseParams.UPLOAD_PATH + fileName;
		// 文件是否存在
		if (FileUtil.exists(getRealPath(name))) {
			return getFileName((DateUtil.getTime() % 100) + StringConstants.UNDERLINE + fileName);
		}
		// 返回文件名
		return name;
	}
}
