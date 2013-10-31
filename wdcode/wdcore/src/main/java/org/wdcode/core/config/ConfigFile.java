package org.wdcode.core.config;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.wdcode.common.constants.FileConstants;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.interfaces.Config;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.StringUtil;

/**
 * Apache Commons-Configuration 实现
 * @author WD
 * @since JDK7
 * @version 1.0 2012-9-16
 */
public final class ConfigFile implements Config {
	// Apache commons Configuration
	private FileConfiguration	file;

	/**
	 * 构造方法
	 * @param fileName 文件名
	 */
	public ConfigFile(String fileName) {
		try {
			// 判断文件类型
			if (fileName.indexOf(FileConstants.SUFFIX_XML) > -1) {
				// xml文件
				file = new XMLConfiguration(fileName);
			} else {
				// properties文件
				file = new PropertiesConfiguration(fileName);
			}
		} catch (Exception e) {}
	}

	/**
	 * 获得指定标识下第一级.和.之间的Key列表
	 * @param prefix 标识
	 * @return key列表
	 */
	public String[] getKeys(String prefix) {
		// 声明列表
		List<String> list = Lists.getList();
		// 循环解析出标识
		for (Iterator<String> it = file.getKeys(prefix); it.hasNext();) {
			list.add(StringUtil.subString(it.next(), StringConstants.POINT, StringConstants.POINT));
		}
		// 返回列表
		return list.toArray(new String[list.size()]);
	}

	@Override
	public void write() {
		try {
			file.save();
		} catch (Exception e) {}
	}

	@Override
	public void setProperty(String key, Object value) {
		file.setProperty(key, value);
	}

	@Override
	public Object getProperty(String key) {
		return file.getProperty(key);
	}

	@Override
	public String[] getStringArray(String key) {
		return file.getStringArray(key);
	}

	@Override
	public boolean isEmpty() {
		return EmptyUtil.isEmpty(file);
	}
}
