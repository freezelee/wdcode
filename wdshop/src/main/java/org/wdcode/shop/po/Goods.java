package org.wdcode.shop.po;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field; 
import org.hibernate.search.annotations.Indexed;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.shop.po.base.BaseGoods;
import org.wdcode.base.entity.EntityFiles;
import org.wdcode.base.entity.simple.EntityPage;

/**
 * 物品
 * @author WD
 * @since JDK7
 * @version 1.0 2010-12-13
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Indexed
public final class Goods extends BaseGoods implements EntityFiles {
	// 序列化ID
	private static final long		serialVersionUID	= -1228214818201975972L;
	// 名称
	@Field
	private String					name;
	// 类别
	private Integer					sortId;
	// 品牌ID
	private Integer					brandId;
	// 类型
	private Integer					typeId;
	// 简介
	private String					brief;
	// 描述
	private String					detail;
	// 精品
	private Boolean					best;
	// 新品
	private Boolean					newIn;
	// 热销
	private Boolean					hot;
	// 是否启用规格
	private Boolean					specificationEnabled;
	// 页面信息
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	private EntityPage				page;
	// 商品图片
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	private List<GoodsImage>		images;
	// 商品属性
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")  
	private List<GoodsAttribute>	attributes;
	// 商品参数
	@Type(type = "org.wdcode.base.dao.hibernate.type.JsonType")
	private List<GoodsParam>		params;
	// 商品规格
	@ManyToMany
	@JoinTable(name = "goods_specification", joinColumns = @JoinColumn(name = "goods_id"), inverseJoinColumns = @JoinColumn(name = "sid"))
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private List<Specification>		specifications;

	/**
	 * 获得页面信息
	 * @return 页面信息PageInfoBean
	 */
	public EntityPage getPage() {
		return page;
	}

	/**
	 * 设置页面信息
	 * @param page 页面信息
	 */
	public void setPage(EntityPage page) {
		this.page = page;
	}

	/**
	 * 获得商品规格
	 * @return 商品规格
	 */
	public List<Specification> getSpecifications() {
		return specifications;
	}

	/**
	 * 设置商品规格
	 * @param specifications 商品规格
	 */
	public void setSpecifications(List<Specification> specifications) {
		this.specifications = specifications;
	}

	/**
	 * 获得商品图片
	 * @return 商品图片
	 */
	public List<GoodsImage> getImages() {
		return images;
	}

	/**
	 * 设置商品图片
	 * @param images 商品图片
	 */
	public void setImages(List<GoodsImage> images) {
		this.images = images;
	}

	/**
	 * 获得商品属性
	 * @return 商品属性
	 */
	public List<GoodsAttribute> getAttributes() {
		return attributes;
	}

	/**
	 * 设置商品属性
	 * @param attributes 商品属性
	 */
	public void setAttributes(List<GoodsAttribute> attributes) {
		this.attributes = attributes;
	}

	/**
	 * 获得商品参数
	 * @return 商品参数
	 */
	public List<GoodsParam> getParams() {
		return params;
	}

	/**
	 * 设置商品参数
	 * @param params 商品参数
	 */
	public void setParams(List<GoodsParam> params) {
		this.params = params;
	}

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
	 * 获得类型
	 * @return 类型
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * 设置类型
	 * @param type 类型
	 */
	public void setTypeId(Integer type) {
		this.typeId = type;
	}

	/**
	 * 获得品牌ID
	 * @return 品牌ID
	 */
	public Integer getBrandId() {
		return brandId;
	}

	/**
	 * 设置品牌ID
	 * @param brandId 品牌ID
	 */
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	/**
	 * 获得简介
	 * @return 简介
	 */
	public String getBrief() {
		return brief;
	}

	/**
	 * 设置简介
	 * @param brief 简介
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	/**
	 * 获得描述
	 * @return 描述
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置描述
	 * @param detail 描述
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * 获得是否精品
	 * @return 精品
	 */
	public Boolean getBest() {
		return best;
	}

	/**
	 * 设置是否精品
	 * @param best 精品
	 */
	public void setBest(Boolean best) {
		this.best = best;
	}

	/**
	 * 获得是否新品
	 * @return 新品
	 */
	public Boolean getNewIn() {
		return newIn;
	}

	/**
	 * 设置是否新品
	 * @param newIn 是否新品
	 */
	public void setNewIn(Boolean newIn) {
		this.newIn = newIn;
	}

	/**
	 * 获得是否启用规格
	 * @return 是否启用规格
	 */
	public Boolean getSpecificationEnabled() {
		return specificationEnabled;
	}

	/**
	 * 设置是否启用规格
	 * @param specificationEnabled 是否启用规格
	 */
	public void setSpecificationEnabled(Boolean specificationEnabled) {
		this.specificationEnabled = specificationEnabled;
	}

	/**
	 * 获得是否热销
	 * @return 热销
	 */
	public Boolean getHot() {
		return hot;
	}

	/**
	 * 设置是否热销
	 * @param hot 热销
	 */
	public void setHot(Boolean hot) {
		this.hot = hot;
	}

	/**
	 * 获得类别
	 * @return 类别
	 */
	public Integer getSortId() {
		return sortId;
	}

	/**
	 * 设置类别
	 * @param sortId 类别
	 */
	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	/**
	 * 获得路径数组
	 */
	public String[] getPaths() {
		if (!EmptyUtil.isEmpty(images)) {
			// 获得所有图片列表
			List<GoodsImage> list = Lists.getList(images);
			// 获得值长度
			int size = list.size();
			// 声明数组
			String[] paths = new String[size];
			// 循环赋值
			for (int i = 0; i < size; i++) {
				paths[i] = list.get(i).getPath();
			}
			// 返回数组
			return paths;
		} else {
			return ArrayConstants.STRING_EMPTY;
		}
	}

	/**
	 * 设置路径数组
	 */
	public void setPaths(String[] paths) {
		if (!EmptyUtil.isEmpty(images)) {
			// 设置路径
			for (int i = 0; i < paths.length; i++) {
				images.get(i).setPath(paths[i]);
			}
		}
	}

	/**
	 * 商品参数
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-03-28
	 */
	public static final class GoodsParam implements Serializable {
		// 序列化ID
		private static final long	serialVersionUID	= 6209055221629089227L;
		// 值
		private String				value;
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
		 * 获得值
		 * @return 值
		 */
		public String getValue() {
			return value;
		}

		/**
		 * 设置值
		 * @param value 值
		 */
		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * 商品属性
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-03-28
	 */
	public static final class GoodsAttribute implements Serializable {
		// 序列化ID
		private static final long	serialVersionUID	= 5847953307704146032L;
		// 值
		private String				value;
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
		 * 获得值
		 * @return 值
		 */
		public String getValue() {
			return value;
		}

		/**
		 * 设置值
		 * @param value 值
		 */
		public void setValue(String value) {
			this.value = value;
		}
	}

	/**
	 * 商品图片
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-04-19
	 */
	public static final class GoodsImage implements Serializable {
		// 序列化ID
		private static final long	serialVersionUID	= 7065313373475313961L;
		// 图片路径
		private String				path;
		// 描述
		private String				description;

		/**
		 * 获得图片路径
		 * @return 图片路径
		 */
		public String getPath() {
			return path;
		}

		/**
		 * 设置图片路径
		 * @param path 图片路径
		 */
		public void setPath(String path) {
			this.path = path;
		}

		/**
		 * 获得描述
		 * @return 描述
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * 设置描述
		 * @param description 描述
		 */
		public void setDescription(String description) {
			this.description = description;
		}
	}
}
