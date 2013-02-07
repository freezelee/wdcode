package org.wdcode.core.engine;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.PropertyFilter;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.params.CoreParams;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * JSON处理引擎
 * @author WD
 * @since JDK7
 * @version 1.0 2012-06-24
 */
public final class JsonEngine {
	// JSON接口
	private final static Json	J;

	/**
	 * 私有构造
	 */
	static {
		switch (CoreParams.JSON_PARSE) {
			case "fast":
				J = new JsonFast();
				break;
			case "gson":
				J = new JsonGson();
				break;
			default:
				J = new JsonLib();
				break;
		}
	}

	/**
	 * 把一个对象转换成JSON
	 * @param obj 要转换的对象
	 * @param fields 要过滤掉的字段
	 * @return 转换后的字符串
	 */
	public static String toJson(Object obj, String... fields) {
		return obj == null ? StringConstants.EMPTY : J.toJson(obj, fields);
	}

	/**
	 * 转换JSON根据传入的Class反射生成回实体Bean
	 * @param json JSON字符串
	 * @param clazz 要转换对象的class
	 * @return 对象
	 */
	public static <E> E toBean(String json, Class<E> clazz) {
		return EmptyUtil.isEmpty(json) ? null : J.toBean(json, clazz);
	}

	/**
	 * 把json转换成List
	 * @param json JSON字符串
	 * @return List
	 */
	public static <E> List<E> toList(String json, Class<E> clazz) {
		return EmptyUtil.isEmpty(json) ? (List<E>) Lists.getList() : J.toList(json, clazz);
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

	/**
	 * Json格式读写器 接口
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-09-15
	 */
	static interface Json {
		/**
		 * 把一个对象转换成JSON
		 * @param obj 要转换的对象
		 * @param fields 要过滤掉的字段
		 * @return 转换后的字符串
		 */
		String toJson(Object obj, String... fields);

		/**
		 * 转换JSON根据传入的Class反射生成回实体Bean
		 * @param json JSON字符串
		 * @param clazz 要转换对象的class
		 * @return 对象
		 */
		<E> E toBean(String json, Class<E> clazz);

		/**
		 * 把json转换成List
		 * @param json JSON字符串
		 * @return List
		 */
		<E> List<E> toList(String json, Class<E> clazz);
	}

	/**
	 * Json格式读写器 json-lib 包实现
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-09-15
	 */
	static class JsonLib implements Json {
		@Override
		public String toJson(Object obj, String... fields) {
			return JSONUtils.isArray(obj) ? JSONArray.fromObject(obj, getConfig(fields)).toString() : JSONObject.fromObject(obj, getConfig(fields)).toString();
		}

		@Override
		public <E> E toBean(String json, Class<E> clazz) {
			return (E) JSONObject.toBean(JSONObject.fromObject(json), clazz);
		}

		@Override
		public <E> List<E> toList(String json, Class<E> clazz) {
			return Lists.getList(JSONArray.toCollection(JSONArray.fromObject(json), clazz));
		}

		/**
		 * 获得JsonConfig
		 * @param fields 要过滤的字段
		 * @return JsonConfig
		 */
		private JsonConfig getConfig(String... fields) {
			// 声明JsonConfig
			JsonConfig config = new JsonConfig();
			// 如果过滤字段不为空
			// 判断不为空
			if (!EmptyUtil.isEmpty(fields)) {
				// 设置过滤列表
				final List<String> list = Lists.getList(fields);
				// 设置过滤属性
				config.setJsonPropertyFilter(new PropertyFilter() {
					public boolean apply(Object source, String name, Object value) {
						return list.contains(name);

					}
				});
			}
			// 返回JsonConfig
			return config;
		}
	}

	/**
	 * fastjson的JSON实现
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-09-15
	 */
	static class JsonFast implements Json {
		@Override
		public String toJson(Object obj, String... fields) {
			return JSON.toJSONString(obj);
		}

		@Override
		public <E> E toBean(String json, Class<E> clazz) {
			return JSON.parseObject(json, clazz);
		}

		@Override
		public <E> List<E> toList(String json, Class<E> clazz) {
			return JSON.parseArray(json, clazz);
		}
	}

	/**
	 * Json格式读写器 gson 包实现
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-09-15
	 */
	static class JsonGson implements Json {
		// Gson
		private final static Gson	GSON;
		static {
			GSON = new GsonBuilder().create();
		}

		@Override
		public String toJson(Object obj, String... fields) {
			return GSON.toJson(obj);
		}

		@Override
		public <E> E toBean(String json, Class<E> clazz) {
			return GSON.fromJson(json, clazz);
		}

		@Override
		public <E> List<E> toList(String json, Class<E> clazz) {
			return GSON.fromJson(json, new TypeToken<List<E>>() {}.getType());
		}
	}
}
