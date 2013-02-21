package org.wdcode.core.json.impl;

import java.util.List;

import org.wdcode.core.json.Json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Json格式读写器 gson 包实现
 * @author WD
 * @since JDK7
 * @version 1.0 2012-09-15
 */
public final class JsonGson implements Json {
	// Gson
	private final static Gson	GSON	= new GsonBuilder().create();

	@Override
	public String toJson(Object obj) {
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
