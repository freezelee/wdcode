package org.wdcode.base.action;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionSupport;

import org.wdcode.base.context.Context;
import org.wdcode.base.entity.Entity;
import org.wdcode.base.params.UploadParams;
import org.wdcode.common.codec.Hex;
import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.crypto.Digest;
import org.wdcode.common.crypto.Encrypts;
import org.wdcode.common.io.FileUtil;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.params.CommonParams;
import org.wdcode.common.util.BeanUtil;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.ImageUtil;
import org.wdcode.common.util.MathUtil;
import org.wdcode.common.util.StringUtil;
import org.wdcode.core.json.JsonEngine;
import org.wdcode.core.log.Logs;
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
	private static final long					serialVersionUID	= 3314538887531859725L;

	// LIST
	protected final static String				LIST				= "list";
	// 回调方法处理
	protected final static Map<String, Method>	METHODS				= Maps.getMap();

	// 提交的url
	protected String							url;
	// 跨域方法
	protected String							callback;

	// 上传文件
	protected File								file;
	// 上传文件类型
	protected String							fileContentType;
	// 上传文件名
	protected String							fileFileName;

	// 上传文件数组
	protected File[]							files;
	// 上传文件数组类型
	protected String[]							filesContentType;
	// 上传文件数组名
	protected String[]							filesFileName;

	// 模板名
	protected String							module;
	// 方法名
	protected String							method;
	// 返回模式名
	protected String							mode;
	// 全局Context
	@Resource
	protected Context							context;
	// 要回执消息的字段
	protected String							field;
	// HttpServletRequest
	protected HttpServletRequest				request;
	// HttpServletResponse
	protected HttpServletResponse				response;
	// HttpSession
	protected HttpSession						session;
	// 错误信息
	protected List<String>						error;

	/**
	 * 初始化方法
	 */
	@PostConstruct
	protected void init() {
		// 获得提交Action地址
		String actionName = getActionName();
		// 分解提交action
		String[] action = StringUtil.split(actionName, StringConstants.UNDERLINE);
		// 获得模板名
		module = action[0];
		// 方法名
		method = action.length > 1 ? action[1] : action[0];
		// 获得方法名
		mode = EmptyUtil.isEmpty(mode) ? action.length > 2 ? action[2] : action.length == 2 ? action[1] : action[0] : mode;
		// 获得request与response
		request = context.getRequest();
		response = context.getResponse();
		session = request.getSession();
		// 声明错误信息
		error = Lists.getList();
	}

	/**
	 * 获得验证码方法
	 * @return
	 * @throws Exception
	 */
	public String verifyCode() throws Exception {
		// 获得验证码
		VerifyCodeUtil.make(request, response);
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
	public File getFile() {
		return file;
	}

	/**
	 * 设置文件
	 * @param file 文件
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 获得文件数组
	 * @return 文件数组
	 */
	public File[] getFiles() {
		return files;
	}

	/**
	 * 设置文件数组
	 * @param files 文件数组
	 */
	public void setFiles(File[] files) {
		this.files = files;
	}

	/**
	 * 获得上传文件类型
	 * @return 上传文件类型
	 */
	public String getFileContentType() {
		return fileContentType;
	}

	/**
	 * 设置上传文件类型
	 * @param fileContentType 上传文件类型
	 */
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	/**
	 * 获得上传文件名
	 * @return 上传文件名
	 */
	public String getFileFileName() {
		return fileFileName;
	}

	/**
	 * 设置上传文件名
	 * @param fileFileName 上传文件名
	 */
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 * 获得上传文件数组类型
	 * @return 上传文件数组类型
	 */
	public String[] getFilesContentType() {
		return filesContentType;
	}

	/**
	 * 设置上传文件数组类型
	 * @param filesContentType 上传文件数组类型
	 */
	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
	}

	/**
	 * 获得上传文件数组名
	 * @return 上传文件数组名
	 */
	public String[] getFilesFileName() {
		return filesFileName;
	}

	/**
	 * 设置上传文件数组名
	 * @param filesFileName 上传文件数组名
	 */
	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
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
	 * 获得方法名
	 * @return
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * 获得模式名
	 * @return
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * 获得错误信息列表
	 * @return
	 */
	public List<String> getError() {
		return error;
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
			sb.append(super.getText(val[i], val[i]));
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
	public String getServer() {
		// 获得path
		String path = request.getServerName();
		// 返回域名路径
		return IpUtil.LOCAL_IP.equals(path) ? IpUtil.getIp() : path;
	}

	/**
	 * 获得域名路径
	 * @param name 文件名
	 * @return 域名路径
	 */
	public String getDomain() {
		// 获得域名
		String domain = HttpConstants.HTTP + getServer() + getBase();
		// 返回域名
		return domain.endsWith(StringConstants.BACKSLASH) ? domain : domain + StringConstants.BACKSLASH;
	}

	/**
	 * 获得项目路径
	 * @return 项目路径
	 */
	public String getBase() {
		return request.getContextPath();
	}

	/**
	 * 获得提交IP
	 * @return 提交IP
	 */
	public String getIp() {
		return IpUtil.getIp(request);
	}

	/**
	 * 设置属性
	 * @param key 属性键
	 * @param value 属性值
	 */
	public void set(String key, Object value) {
		AttributeUtil.set(request, response, key, value);
	}

	/**
	 * 根据属性键获得属性值
	 * @param key 属性键
	 * @return 属性值
	 */
	public Object get(String key) {
		return AttributeUtil.get(request, key);
	}

	/**
	 * 删除属性
	 * @param key 属性键
	 */
	public void remove(String key) {
		AttributeUtil.remove(request, response, key);
	}

	/**
	 * 获得ServletContext
	 * @return ServletContext
	 */
	public ServletContext getServletContext() {
		return context.getServletContext();
	}

	/**
	 * 添加错误信息 错误Field=key value=国际化value
	 * @param key 国际化文件的Key
	 */
	public void addError(String key) {
		error.add(getText(key));
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
		return request.getCookies();
	}

	/**
	 * 添加Cookie到客户端
	 * @param name CookieName
	 * @param value CookieValue
	 */
	public void addCookie(String name, String value) {
		CookieUtil.add(response, name, Encrypts.encrypt(value));
	}

	/**
	 * 删除Cookie
	 * @param name CookieName
	 * @param value CookieValue
	 */
	public void removeCookie(String name) {
		CookieUtil.remove(response, name);
	}

	/**
	 * 根据name获得Cookie 没找到返回null
	 * @param name CookieName
	 * @return Cookie
	 */
	public Cookie getCookie(String name) {
		return CookieUtil.getCookie(request, name);
	}

	/**
	 * 根据name获得CookieValue 没找到返回""
	 * @param name CookieName
	 * @return CookieValue
	 */
	public String getCookieValue(String name) {
		return CookieUtil.getCookieValue(request, name);
	}

	/**
	 * 获得Action方法名
	 * @return Action方法名
	 */
	public String getActionName() {
		return context.getActionMapping().getName();
	}

	/**
	 * 获得Action方法名 只保留x_x
	 * @return Action方法名
	 */
	public String getLink() {
		// 获得提交Action地址
		String actionName = getActionName();
		// 分解名称
		String[] name = StringUtil.split(actionName, StringConstants.UNDERLINE);
		// 返回链接名
		return name.length > 2 ? name[0] + StringConstants.UNDERLINE + name[1] : actionName;
	}

	/**
	 * 上传文件
	 * @return
	 * @throws Exception
	 */
	public String upload() throws Exception {
		return ajax(upload(file, fileFileName));
	}

	/**
	 * 上传文件
	 * @return
	 * @throws Exception
	 */
	public String uploads() throws Exception {
		return ajax(uploads(files, filesFileName));
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
	 * 获得要显示的字段
	 * @return 要显示的字段
	 */
	public String getField() {
		return field;
	}

	/**
	 * 设置要显示的字段
	 * @param field 要显示的字段
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * 写数据到前端
	 * @param str 要写的字符串
	 * @param charsetName 编码
	 * @param isClose 是否关闭流
	 * @throws IOException
	 */
	public void write(String str, String charsetName) {
		// 清除缓存
		ResponseUtil.noCache(response);
		// 设置编码
		response.setCharacterEncoding(charsetName);
		// 写入到前端
		try {
			response.getWriter().write(str);
		} catch (IOException e) {}
	}

	/**
	 * 输出数据到客户端方法
	 * @param json 对象
	 * @param charsetName 编码
	 */
	public String ajax(Object data, String charsetName) {
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
		// 写字符串
		write(s.toString(), charsetName);
		// 返回空
		return null;
	}

	/**
	 * 方法回调 所有直接Action回调的方法 一边统一处理
	 * @param s 字符串标识
	 * @return 返回标识
	 */
	public String callback(Object obj) {
		// 声明方法
		Method method = null;
		// 获得Key相对的方法是否存在
		if (METHODS.containsKey(mode)) {
			method = METHODS.get(mode);
		} else {
			// 不存在获得
			synchronized (METHODS) {
				METHODS.put(mode, method = BeanUtil.getMethod(this, mode, Object.class));
			}
		}
		// 方法不为空
		if (method != null) {
			try {
				return Conversion.toString(method.invoke(this, obj), null);
			} catch (Exception e) {
				Logs.debug(e);
			}
		}
		if (obj == null) {
			return addMessage(ERROR);
		} else if (obj instanceof String) {
			String re = Conversion.toString(obj);
			return SUCCESS.equals(re) || ERROR.equals(re) ? addMessage(re) : re;
		} else if (obj instanceof List<?> || obj instanceof Map<?, ?>) {
			return LIST;
		} else if (obj instanceof Boolean) {
			return Conversion.toBoolean(obj) ? SUCCESS : ERROR;
		} else if (obj instanceof Integer) {
			return EmptyUtil.isEmpty(obj) ? ERROR : SUCCESS;
		} else {
			return addMessage(SUCCESS);
		}
	}

	/**
	 * 以ajax模式输出数据到客户端方法
	 * @param json 对象
	 */
	protected String ajax(Object obj) {
		return ajax(obj == null ? ERROR : !EmptyUtil.isEmpty(field) && obj instanceof Entity ? BeanUtil.getFieldValue(obj, field) : obj, CommonParams.ENCODING);
	}

	/**
	 * 以sign模式输出数据到客户端方法
	 * @param json 对象
	 */
	protected String sign(Object obj) {
		return ajax(obj instanceof String || obj instanceof Number ? obj : EmptyUtil.isEmpty(obj) ? ERROR : SUCCESS);
	}

	/**
	 * 以key模式输出数据到客户端方法
	 * @param json 对象
	 */
	protected String key(Object obj) {
		return ajax(obj instanceof String || obj instanceof Number ? obj : obj instanceof Entity ? ((Entity) obj).getKey() : ERROR);
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
		String name = getFileName(file, fileName);
		// 上传文件
		String fn = UploadParams.UPLOAD_RESOURCE ? UploadParams.UPLOAD_PATH + name : getRealPath(name);
		// 文件是否存在
		if (FileUtil.exists(fn)) {
			// 获得上传文件MD5
			String md5 = Hex.encode(Digest.md5(FileUtil.read(file)));
			// 文件存在始终循环
			while (FileUtil.exists(fn)) {
				// 验证MD5
				if (Hex.encode(Digest.md5(FileUtil.read(fn))).equals(md5)) {
					// 相同文件不处理跳出循环
					break;
				} else {
					// 文件不同 获得新文件名
					name = getFileName(file, (DateUtil.getTime() % 100) + StringConstants.UNDERLINE + fileName);
					fn = UploadParams.UPLOAD_RESOURCE ? UploadParams.UPLOAD_PATH + name : getRealPath(name);
				}
			}
		}
		// 文件不存在写文件
		if (!FileUtil.exists(fn)) {
			FileUtil.write(fn, file);
			// 是否开启图片压缩 并且是图片
			if (UploadParams.UPLOAD_IMAGE_COMPRESS_POWER && ImageUtil.isImage(file)) {
				// 循环压缩图片
				for (String compress : UploadParams.UPLOAD_IMAGE_COMPRESS_NAMES) {
					// 获取压缩文件保存文件名
					String f = StringUtil.subStringLastEnd(fn, StringConstants.BACKSLASH) + StringConstants.BACKSLASH + compress + StringConstants.BACKSLASH + StringUtil.subStringLast(fn, StringConstants.BACKSLASH);
					// 写入压缩图片
					ImageUtil.compress(file, FileUtil.getOutputStream(f), UploadParams.getWidth(compress), UploadParams.getHeight(compress), true);
				}
			}
		}
		// 返回路径
		return UploadParams.UPLOAD_SERVER ? getDomain() + name : name;
	}

	/**
	 * 获得文件名
	 * @param fileName 文件名
	 * @return 文件名
	 */
	private String getFileName(File file, String fileName) {
		// 上传路径
		StringBuilder name = new StringBuilder(UploadParams.UPLOAD_RESOURCE ? StringConstants.EMPTY : UploadParams.UPLOAD_PATH);
		name.append(EmptyUtil.isEmpty(module) ? StringConstants.EMPTY : module + StringConstants.BACKSLASH);
		name.append(Digest.absolute(fileName, 20));
		// 是否处理后缀
		if (UploadParams.UPLOAD_SUFFIX) {
			// 获得后缀
			String suffix = StringUtil.subStringLast(fileName, StringConstants.POINT);
			// 判断后缀不在处理列表中
			if (!UploadParams.UPLOAD_POSTFIX.contains(suffix)) {
				name.append(StringConstants.POINT);
				name.append(suffix);
			}
		}
		// 返回文件名
		return name.toString();
	}
}
