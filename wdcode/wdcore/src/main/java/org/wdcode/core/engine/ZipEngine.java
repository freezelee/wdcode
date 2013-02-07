package org.wdcode.core.engine;

import java.io.ByteArrayOutputStream;
import java.util.zip.DeflaterInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.wdcode.common.constants.ArrayConstants;
import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.constants.ZipConstants;
import org.wdcode.common.io.StreamUtil;
import org.wdcode.common.lang.Bytes;
import org.wdcode.core.params.CoreParams;

/**
 * 压缩引擎
 * @author WD
 * @since JDK7
 * @version 1.0 2012-01-18
 */
public final class ZipEngine {
	// 压缩器
	private final static Zip	ZIP;

	/**
	 * 静态初始化
	 */
	static {
		// 判断算法
		switch (CoreParams.ZIP) {
			case ZipConstants.GZIP:
				ZIP = new GzipImpl();
				break;
			case ZipConstants.ZIP:
				ZIP = new ZipImpl();
				break;
			case ZipConstants.BZIP2:
				ZIP = new BZip2Impl();
				break;
			default:
				ZIP = new ZlibImpl();
				break;
		}
	}

	/**
	 * 压缩数据
	 * @param obj 要压缩的对象
	 * @return 压缩后的字节数组或则原对象的字节数组
	 */
	public static byte[] compress(Object obj) {
		return obj == null ? ArrayConstants.BYTES_EMPTY : ZIP.compress(Bytes.toBytes(obj));
	}

	/**
	 * 解压数据
	 * @param obj 要解压的对象
	 * @return 解压后数据
	 */
	public static byte[] extract(Object obj) {
		return obj == null ? ArrayConstants.BYTES_EMPTY : ZIP.extract(Bytes.toBytes(obj));
	}

	/**
	 * 私有构造
	 */
	private ZipEngine() {}

	/**
	 * 压缩与解压接口
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-09-15
	 */
	static interface Zip {
		/**
		 * 压缩数据
		 * @param b 字节数组
		 * @return 压缩后的字节数组
		 */
		byte[] compress(byte[] b);

		/**
		 * 解压数据
		 * @param b 压缩字节
		 * @return 解压后数据
		 */
		byte[] extract(byte[] b);
	}

	/**
	 * GZIP压缩
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-09-15
	 */
	static class GzipImpl implements Zip {
		/**
		 * 压缩数据
		 * @param b 字节数组
		 * @return 压缩后的字节数组
		 */
		public byte[] compress(byte[] b) {
			try (ByteArrayOutputStream baos = Bytes.getOutputStream(); GZIPOutputStream gzip = new GZIPOutputStream(baos)) {
				// 把压缩后的字节数组写到输出流
				StreamUtil.write(gzip, b, false);
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
				return StreamUtil.read(gzip, false);
			} catch (Exception e) {
				return b;
			}
		}
	}

	/**
	 * ZIP压缩
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-09-15
	 */
	static class ZipImpl implements Zip {
		/**
		 * 压缩数据
		 * @param b 字节数组
		 * @return 压缩后的字节数组
		 */
		public byte[] compress(byte[] b) {
			try (ByteArrayOutputStream baos = new ByteArrayOutputStream(); ZipOutputStream zip = new ZipOutputStream(baos)) {
				// 设置压缩实体
				zip.putNextEntry(new ZipEntry(StringConstants.EMPTY));
				// 把压缩后的字节数组写到输出流
				StreamUtil.write(zip, b, false);
				// 完成压缩数据
				zip.finish();
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
			try (ByteArrayOutputStream baos = Bytes.getOutputStream(); ZipInputStream zin = new ZipInputStream(Bytes.getInputStream(b))) {
				// 循环解压缩
				while (zin.getNextEntry() != null) {
					baos.write(StreamUtil.read(zin, false));
					baos.flush();
				}
				// 返回字节数组
				return baos.toByteArray();
			} catch (Exception e) {
				return b;
			}
		}
	}

	/**
	 * ZLIB压缩
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-09-15
	 */
	static class ZlibImpl implements Zip {
		/**
		 * 压缩数据
		 * @param b 字节数组
		 * @return 压缩后的字节数组
		 */
		public byte[] compress(byte[] b) {
			return StreamUtil.read(new DeflaterInputStream(Bytes.getInputStream(b)));
		}

		/**
		 * 解压数据
		 * @param b 压缩字节
		 * @return 解压后数据
		 */
		public byte[] extract(byte[] b) {
			return StreamUtil.read(new InflaterInputStream(Bytes.getInputStream(b)));
		}
	}

	/**
	 * ZLIB压缩
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-09-15
	 */
	static class BZip2Impl implements Zip {
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
}