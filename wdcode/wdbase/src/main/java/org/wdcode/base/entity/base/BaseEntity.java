package org.wdcode.base.entity.base;

import java.io.Serializable;

import org.wdcode.base.entity.Entity;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.core.engine.DataEngine;

/**
 * Entity接口基础实现
 * @author WD
 * @since JDK7
 * @version 1.0 2010-12-28
 */
public abstract class BaseEntity implements Entity {
	// 序列化ID
	private static final long	serialVersionUID	= -6162883824224379291L;

	/**
	 * 判断是否为空
	 */
	public boolean isEmpty() {
		return EmptyUtil.isEmpty(getKey());
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
		result = prime * result + ((getKey() == null) ? 0 : getKey().hashCode());
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
		Entity other = (Entity) obj;
		if (getKey() == null) {
			if (other.getKey() != null)
				return false;
		} else if (!getKey().equals(other.getKey()))
			return false;
		return true;
	}

	@Override
	public int compareTo(Entity o) {
		// 获得主键
		Serializable key = o.getKey();
		// 判断类型
		if (key instanceof Integer) {
			return Integer.compare(Conversion.toInt(getKey()), Conversion.toInt(key));
		} else {
			return Conversion.toString(getKey()).compareTo(Conversion.toString(key));
		}
	}
}
