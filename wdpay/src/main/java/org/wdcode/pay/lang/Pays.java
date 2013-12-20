package org.wdcode.pay.lang;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.wdcode.base.context.Context;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.params.Params;
import org.wdcode.pay.interfaces.Pay;

/**
 * 支付构建器
 * @author WD
 * @since JDK6
 * @version 1.0 2012-12-04
 */
@Component
public final class Pays {
	@Resource
	private Context						context;
	// 支付Map
	private ConcurrentMap<String, Pay>	maps;

	/**
	 * 初始化
	 */
	@PostConstruct
	protected void init() {
		// 实例化支付Map
		maps = Maps.getConcurrentMap();
		// 获得支付接口实现
		for (Map.Entry<String, Pay> e : context.getBeans(Pay.class).entrySet()) {
			// 获得Key
			String key = e.getKey();
			// 判断是否开启
			if (Params.getBoolean(Params.getKey("pay", "enabled", key), false)) {
				maps.put(key, e.getValue());
			}
		}
	}

	/**
	 * 获得支付接口
	 * @param key 键
	 * @return 支付接口
	 */
	public Pay get(String key) {
		return maps.get(key);
	}

	/**
	 * 获得所有能应用的支付接口Map
	 * @return 支付接口Map
	 */
	public Map<String, Pay> map() {
		return Maps.getMap(maps);
	}

	/**
	 * 获得所有能应用的支付接口
	 * @return 支付接口列表
	 */
	public List<Pay> list() {
		return Lists.getList(maps.values());
	}

	/**
	 * 获得所有能应用的支付接口
	 * @return 支付接口列表
	 */
	public List<String> keys() {
		return Lists.sort(Lists.getList(maps.keySet()));
	}
}
