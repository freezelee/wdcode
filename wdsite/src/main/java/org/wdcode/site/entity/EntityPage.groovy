package org.wdcode.site.entity

import java.io.Serializable

import org.wdcode.core.engine.DataEngine

/**
 * 带页面关键字的实体
 * @author WD
 * @since JDK7
 * @version 1.0 2012-05-09
 */
final class EntityPage implements Serializable {
	// 页面标题
	String				title
	// 页面关键字
	String				keyWords
	// 页面描述
	String				description

	/**
	 * 重写toString 使用json输出属性
	 */
	public String toString() {
		return DataEngine.toString(this)
	}
}
