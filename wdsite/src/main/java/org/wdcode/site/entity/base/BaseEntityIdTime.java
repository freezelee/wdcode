package org.wdcode.site.entity.base;

import javax.persistence.MappedSuperclass;

import org.wdcode.base.entity.Entity;
import org.wdcode.base.entity.EntityTime;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Conversion;
import org.wdcode.common.util.DateUtil;
import org.wdcode.common.util.EmptyUtil;

/**
 * 标准实体实现
 * @author WD
 * @since JDK7
 * @version 1.0 2012-03-29
 */
@MappedSuperclass
public abstract class BaseEntityIdTime extends BaseEntityId implements EntityTime {
	// 序列化ID
	private static final long	serialVersionUID	= 457244042060870415L;
	// 时间
	private Integer				time;

	/**
	 * 获得时间
	 */
	public Integer getTime() {
		return time;
	}

	/**
	 * 时间
	 */
	public void setTime(Integer time) {
		this.time = time;
	}

	/**
	 * 获得日期
	 */
	public String getDate() {
		return EmptyUtil.isEmpty(time) ? StringConstants.EMPTY : DateUtil.toString(time);
	}

	@Override
	public int compareTo(Entity o) {
		return o instanceof EntityTime && time != null ? Integer.compare(Conversion.toInt(time), Conversion.toInt(((EntityTime) o).getTime())) : super.compareTo(o);
	}
}
