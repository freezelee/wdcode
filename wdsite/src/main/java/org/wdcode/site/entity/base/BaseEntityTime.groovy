package org.wdcode.site.entity.base

import javax.persistence.MappedSuperclass

import org.wdcode.base.entity.Entity
import org.wdcode.base.entity.EntityTime
import org.wdcode.common.constants.StringConstants
import org.wdcode.common.lang.Conversion
import org.wdcode.common.util.DateUtil
import org.wdcode.common.util.EmptyUtil

/**
 * 标准实体实现
 * @author WD
 * @since JDK7
 * @version 1.0 2012-03-29
 */
@MappedSuperclass
abstract class BaseEntityTime extends BaseEntity implements EntityTime {
	// 时间
	Integer			time

	/**
	 * 获得日期
	 */
	public String getDate() {
		return EmptyUtil.isEmpty(time) ? StringConstants.EMPTY : DateUtil.toString(time)
	}

	@Override
	public int compareTo(Entity o) {
		return o instanceof EntityTime && time != null ? Integer.compare(Conversion.toInt(time), Conversion.toInt(((EntityTime) o).getTime())) : super.compareTo(o)
	}
}
