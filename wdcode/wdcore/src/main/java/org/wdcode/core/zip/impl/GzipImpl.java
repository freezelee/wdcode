package org.wdcode.core.zip.impl;

import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.wdcode.common.io.IOUtil;
import org.wdcode.common.lang.Bytes;
import org.wdcode.core.zip.Zip;

/**
 * GZIP压缩
 * @author WD
 * @since JDK7
 * @version 1.0 2012-09-15
 */
public final class GzipImpl implements Zip {
	/**
	 * 压缩数据
	 * @param b 字节数组
	 * @return 压缩后的字节数组
	 */
	public byte[] compress(byte[] b) {
		try (ByteArrayOutputStream baos = Bytes.getOutputStream(); GZIPOutputStream gzip = new GZIPOutputStream(baos)) {
			// 把压缩后的字节数组写到输出流
			IOUtil.write(gzip, b, false);
			// 完成压缩数据
			gzip.finish();
			// 返回字节数组
			return baos.toByteArray();
		} catch (Exception e) {
			return b;
		}
	}

	/**
	 * 解压数据
	 * @param b 压缩字节
	 * @return 解压后数据
	 */
	public byte[] extract(byte[] b) {
		try (GZIPInputStream gzip = new GZIPInputStream(Bytes.getInputStream(b))) {
			return IOUtil.read(gzip, false);
		} catch (Exception e) {
			return b;
		}
	}
}