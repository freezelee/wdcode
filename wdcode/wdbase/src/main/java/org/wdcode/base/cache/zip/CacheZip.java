package org.wdcode.base.cache.zip;

import java.io.Serializable;
import java.util.List;

import org.wdcode.base.cache.base.BaseCache;
import org.wdcode.base.entity.Entity;
import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.lang.Bytes;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.cache.Cache;
import org.wdcode.core.cache.map.CacheMap;
import org.wdcode.core.engine.ZipEngine;

/**
 * 标准的缓存Map实现 保存为json格式
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-18
 */
public final class CacheZip<E extends Entity> extends BaseCache<E> {
	// 标准缓存
	private Cache<Serializable, byte[]>	cache;

	/**
	 * 构造方法
	 */
	public CacheZip() {
		// 实例化缓存
		cache = new CacheMap<Serializable, byte[]>();
	}

	/**
	 * 获得所有缓存Value
	 * @return
	 */
	public List<E> list() {
		return getEntitys(cache.list());
	}

	/**
	 * 获得缓存数据
	 * @param key 缓存Key
	 * @return 缓存Value
	 */
	public E get(Serializable key) {
		return getEntity(cache.get(key));
	}

	/**
	 * 获得缓存数据
	 * @param keys 缓存Key
	 * @return 缓存Value
	 */
	public List<E> get(List<Serializable> keys) {
		return getEntitys(cache.get(keys));
	}

	/**
	 * 删除缓存
	 * @param key 缓存Key
	 */
	public E remove(Serializable key) {
		return getEntity(cache.remove(key));
	}

	/**
	 * 删除缓存
	 * @param keys 缓存Key
	 */
	public List<E> remove(Serializable... keys) {
		return getEntitys(cache.remove(keys));
	}

	/**
	 * 清除所有缓存
	 */
	public void clear() {
		cache.clear();
	}

	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return cache.isEmpty();
	}

	/**
	 * 获得缓存是否有效
	 * @return 是否有效
	 */
	public boolean isValid() {
		return cache.isValid();
	}

	/**
	 * 添加缓存
	 * @param key 缓存的Key
	 * @param value 缓存的Value
	 * @return 缓存的Value
	 */
	public E set(E value) {
		// 设置实体
		cache.set(value.getKey(), getZip(value));
		// 返回实体
		return value;
	}

	/**
	 * 获得缓存大小
	 * @return 缓存大小
	 */
	public int size() {
		return cache.size();
	}

	@Override
	public String toString() {
		return cache.toString();
	}

	/**
	 * 获得实体json压缩后的zip
	 * @param entity 实体
	 * @return 压缩后的字节数组
	 */
	private byte[] getZip(E entity) {
		// 如果实体为空
		if (entity == null) {
			return ArrayConstants.BYTES_EMPTY;
		} else {
			// 调用转换字符串方法 主要是为了懒加载属性
			entity.toString();
			// 转换并压缩
			return ZipEngine.compress(entity);
		}
	}

	/**
	 * 根据压缩后的字节数组 获得实体
	 * @param b 字节数组
	 * @return 实体
	 */
	private E getEntity(byte[] b) {
		return (E) Bytes.deserialize(ZipEngine.extract(b));
	}

	/**
	 * 根据压缩后的字节数组 获得实体
	 * @param bs 字节数组
	 * @return 实体
	 */
	private List<E> getEntitys(List<byte[]> bs) {
		// 字节数组列表不为空
		if (EmptyUtil.isEmpty(bs)) {
			return Lists.emptyList();
		} else {
			// 声明列表
			List<E> list = Lists.getList(bs.size());
			// 循环获得列表获得实体
			for (byte[] b : bs) {
				list.add(getEntity(b));
			}
			// 返回列表
			return list;
		}
	}
}
