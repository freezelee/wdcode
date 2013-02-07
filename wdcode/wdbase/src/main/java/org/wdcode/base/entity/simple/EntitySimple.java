package org.wdcode.base.entity.simple;

import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.engine.DataEngine;

/**
 * Entity 的简单实现
 * @author WD
 * @since JDK7
 * @version 1.0 2011-1-12
 */
public class EntitySimple<K, V> {
	// Key
	protected K	key;
	// Value
	protected V	value;

	/**
	 * 获得Key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * 设置Key
	 * @param key Key
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * 获得Value
	 */
	public V getValue() {
		return value;
	}

	/**
	 * 设置Value
	 * @param value Value
	 */
	public void setValue(V value) {
		this.value = value;
	}

	/**
	 * 清除属性
	 */
	public void clear() {
		this.key = null;
		this.value = null;
	}

	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return EmptyUtil.isEmpty(key);
	}

	/**
	 * 重写toString 使用json输出属性
	 */
	public String toString() {
		return DataEngine.toString(this);
	}

	/**
	 * hashCode方法
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/**
	 * 判断对象是否相同
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntitySimple<K, V> other = (EntitySimple<K, V>) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
