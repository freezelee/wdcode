package org.wdcode.core.engine;

import org.wdcode.common.constants.FileConstants;
import org.wdcode.common.lang.Conversion;
import org.wdcode.core.params.CoreParams;

/**
 * Bean相关操作
 * @author WD
 * @since JDK7
 * @version 1.0 2011-11-03
 */
public final class DataEngine {
	/**
	 * 把对象转换为字符串
	 * @param obj 要转换的对象
	 * @param fields 要过滤掉的字段
	 * @return 转换后的字符串
	 */
	public static String toString(Object obj, String... fields) {
		// 判断是否字符串
		if (obj instanceof String || obj instanceof Number || obj instanceof Boolean) {
			return Conversion.toString(obj);
		}
		// 判断使用哪种格式
		if (FileConstants.SUFFIX_XML.equals(CoreParams.DATA_FORMAT)) {
			return XmlEngine.toXML(obj);
		} else {
			return JsonEngine.toJson(obj, fields);
		}
	}

	/**
	 * 私有构造
	 */
	private DataEngine() {}
}
