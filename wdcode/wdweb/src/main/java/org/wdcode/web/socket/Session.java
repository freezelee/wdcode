package org.wdcode.web.socket;

import org.wdcode.common.interfaces.Close;

/**
 * Socket Session
 * @author WD
 * @since JDK7
 * @version 1.0 2013-11-28
 */
public interface Session extends Close {
	/**
	 * 写入数据
	 * @param b
	 */
	public void write(byte[] b);
}
