package org.wdcode.core.zip.impl;

import java.io.ByteArrayOutputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.wdcode.common.io.StreamUtil;
import org.wdcode.common.lang.Bytes;
import org.wdcode.core.zip.Zip;

/**
 * BZip2压缩
 * @author WD
 * @since JDK7
 * @version 1.0 2012-09-15
 */
public final class BZip2Impl implements Zip {
	/**
	 * 压缩数据
	 * @param b 字节数组
	 * @return 压缩后的字节数组
	 */
	public byte[] compress(byte[] b) {
		try (ByteArrayOutputStream baos = Bytes.getOutputStream(); BZip2CompressorOutputStream bzip = new BZip2CompressorOutputStream(baos)) {
			StreamUtil.write(bzip, b);
			// 压缩
			bzip.finish();
			bzip.flush();
			// 返回结果
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
		try (BZip2CompressorInputStream gis = new BZip2CompressorInputStream(Bytes.getInputStream(b))) {
			return StreamUtil.read(gis, false);
		} catch (Exception e) {
			return b;
		}
	}
}