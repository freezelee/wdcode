package org.wdcode.base.entity;

import java.io.Serializable;

/**
 * 用户实体
 * @author WD
 * @since JDK7
 * @version 1.0 2012-07-18
 */
public interface EntityUser {
	/**
	 * 获得用户ID
	 * @return 用户ID
	 */
	Serializable getUserId();

	/**
	 * 设置用户ID
	 * @param userId 用户ID
	 */
	void setUserId(Serializable userId);
}
