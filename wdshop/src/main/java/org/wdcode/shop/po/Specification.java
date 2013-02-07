package org.wdcode.shop.po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.base.entity.EntityFiles;
import org.wdcode.base.entity.base.BaseEntityIdTime;

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
public final class Specification extends BaseEntityIdTime implements EntityFiles {
	// 序列化ID
	private static final long			serialVersionUID	= -815131340089362472L;
	// 备注
	private String						remark;
	// 规格值
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	private List<SpecificationValue>	specificationValues;
	// 状态
	private Integer						state;
	// 名称
	private String						name;

	/**
	 * 获得名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得状态
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 设置状态
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 获得规格值
	 * @return 规格值
	 */
	public List<SpecificationValue> getSpecificationValues() {
		return specificationValues;
	}

	/**
	 * 设置规格值
	 * @param specificationValues 规格值
	 */
	public void setSpecificationValues(List<SpecificationValue> specificationValues) {
		this.specificationValues = specificationValues;
	}

	/**
	 * 获得备注
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 获得路径数组
	 */
	public String[] getPaths() {
		// 获得值长度
		int size = specificationValues.size();
		// 声明数组
		String[] paths = new String[size];
		// 循环赋值
		for (int i = 0; i < size; i++) {
			paths[i] = specificationValues.get(i).getPath();
		}
		// 返回数组
		return paths;
	}

	/**
	 * 设置路径数组
	 */
	public void setPaths(String[] paths) {
		// 设置路径
		for (int i = 0; i < paths.length; i++) {
			specificationValues.get(i).setPath(paths[i]);
		}
	}

	/**
	 * 商品规格值
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-04-11
	 */
	public static final class SpecificationValue implements Serializable {
		// 序列化ID
		private static final long	serialVersionUID	= -4287023802825609831L;
		// 图片
		private String				path;
		// 名称
		private String				name;

		/**
		 * 获得名称
		 */
		public String getName() {
			return name;
		}

		/**
		 * 设置名称
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * 获得规格值
		 * @return 规格值
		 */
		public String getPath() {
			return path;
		}

		/**
		 * 设置规格值
		 * @param path 规格值
		 */
		public void setPath(String path) {
			this.path = path;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SpecificationValue other = (SpecificationValue) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}
	}
}