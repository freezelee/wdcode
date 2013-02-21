package org.wdcode.core.json;

import java.util.List;
import java.util.Map;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.json.factory.JsonFactory;
import org.wdcode.core.params.CoreParams;

/**
 * JSON处理引擎
 * @author WD
 * @since JDK7
 * @version 1.0 2012-06-24
 */
public final class JsonEngine {
	// JSON接口
	private final static Json	JSON	= JsonFactory.getJson(CoreParams.JSON_PARSE);

	/**
	 * 把一个对象转换成JSON
	 * @param obj 要转换的对象
	 * @return 转换后的字符串
	 */
	public static String toJson(Object obj) {
		return obj == null ? StringConstants.EMPTY : JSON.toJson(obj);
	}

	/**
	 * 转换JSON根据传入的Class反射生成回实体Bean
	 * @param json JSON字符串
	 * @param clazz 要转换对象的class
	 * @return 对象
	 */
	public static <E> E toBean(String json, Class<E> clazz) {
		return EmptyUtil.isEmpty(json) ? null : JSON.toBean(json, clazz);
	}

	/**
	 * 把json转换成List
	 * @param json JSON字符串
	 * @return List
	 */
	public static <E> List<E> toList(String json, Class<E> clazz) {
		return EmptyUtil.isEmpty(json) ? (List<E>) Lists.getList() : JSON.toList(json, clazz);
	}

	/**
	 * 把json转换成Map
	 * @param json JSON字符串
	 * @return Map
	 */
	public static Map<String, Object> toMap(String json) {
		return toBean(json, Map.class);
	}

	/**
	 * 把json转换成List
	 * @param json JSON字符串
	 * @return List
	 */
	public static List<Object> toList(String json) {
		return toList(json, Object.class);
	}

	/**
	 * 私有构造
	 */
	private JsonEngine() {}
}
