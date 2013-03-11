package org.wdcode.core.json.factory;

import org.wdcode.core.factory.FactoryKey;
import org.wdcode.core.json.Json;
import org.wdcode.core.json.impl.JsonFast;
import org.wdcode.core.json.impl.JsonGson;
import org.wdcode.core.json.impl.JsonLib;
import org.wdcode.core.json.impl.JsonSmart;

/**
 * JSON 解析构建器
 * @author WD
 * @since JDK6
 * @version 1.0 2013-02-21
 */
public final class JsonFactory extends FactoryKey<String, Json> {
	// 工厂
	private final static JsonFactory	FACTORY	= new JsonFactory();

	/**
	 * 获得json解析器
	 * @param key 使用包
	 * @return Json
	 */
	public static Json getJson(String key) {
		return FACTORY.getInstance(key);
	}

	@Override
	public Json newInstance(String key) {
		switch (key) {
			case "fast":
				return new JsonFast();
			case "lib":
				return new JsonLib();
			case "smart":
				return new JsonSmart();
			default:
				return new JsonGson();
		}
	}

	private JsonFactory() {}
}