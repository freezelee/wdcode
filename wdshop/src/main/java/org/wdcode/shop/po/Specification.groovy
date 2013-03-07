package org.wdcode.shop.po

import java.io.Serializable
import java.util.List

import javax.persistence.Entity

import org.hibernate.annotations.Cache
import org.hibernate.annotations.CacheConcurrencyStrategy
import org.hibernate.annotations.Type
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.wdcode.base.entity.base.BaseEntityIdTime
import org.wdcode.common.constants.ArrayConstants
import org.wdcode.common.util.EmptyUtil
import org.wdcode.site.entity.EntityFiles;

/**
 * 规格
 * @author WD
 * @since JDK7
 * @version 1.0 2012-01-10
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
class Specification extends BaseEntityIdTime implements EntityFiles {
	// 备注
	String						remark
	// 规格值
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	List<SpecificationValue>	specificationValues
	// 状态
	Integer						state
	// 名称
	String						name

	/**
	 * 获得路径数组
	 */
	public String[] getPaths() {
		if (!EmptyUtil.isEmpty(specificationValues)) {
			// 获得值长度
			int size = specificationValues.size()
			// 声明数组
			String[] paths = new String[size]
			// 循环赋值
			for (int i = 0; i < size; i++) {
				paths[i] = specificationValues.get(i).getPath()
			}
			// 返回数组
			return paths
		} else {
			return ArrayConstants.STRING_EMPTY
		}
	}

	/**
	 * 设置路径数组
	 */
	public void setPaths(String[] paths) {
		// 设置路径
		for (int i = 0; i < paths.length; i++) {
			specificationValues.add(new SpecificationValue(paths[i]))
		}
	}

	/**
	 * 商品规格值
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-04-11
	 */
	static class SpecificationValue implements Serializable {
		// 图片
		String				path
		// 名称
		String				name

		public SpecificationValue(){}

		public SpecificationValue(String path){
			this.path = path
		}

		@Override
		public int hashCode() {
			final int prime = 31
			int result = 1
			result = prime * result + ((name == null) ? 0 : name.hashCode())
			return result
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true
			if (obj == null)
				return false
			if (getClass() != obj.getClass())
				return false
			SpecificationValue other = (SpecificationValue) obj
			if (name == null) {
				if (other.name != null)
					return false
			} else if (!name.equals(other.name))
				return false
			return true
		}
	}
}