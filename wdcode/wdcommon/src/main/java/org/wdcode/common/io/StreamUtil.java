package org.wdcode.common.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.Channels;

import org.wdcode.common.constants.StringConstants;
import org.wdcode.common.lang.Bytes;
import org.wdcode.common.params.CommonParams;
import org.wdcode.common.util.CloseUtil;
import org.wdcode.common.util.EmptyUtil;
import org.wdcode.common.util.StringUtil;

/**
 * IO流操作
 * @author WD
 * @since JDK7
 * @version 1.0 2012-02-17
 */
public final class StreamUtil {
	// IO接口
	private final static IO	IO;

	/**
	 * 静态初始化
	 */
	static {
		IO = "io".equalsIgnoreCase(CommonParams.IO_MODE) ? new OIO() : new NIO();
	}

	/**
	 * 读取InputStream内容成为字符串 默认使用UTF-8
	 * @param in 输入流
	 * @return 读取的字符串
	 */
	public static String readString(InputStream in) {
		return IO.readString(in);
	}

	/**
	 * 读取InputStream内容成为字符串
	 * @param in 输入流
	 * @param charsetName 编码格式
	 * @return 读取的字符串
	 */
	public static String readString(InputStream in, String charsetName) {
		return IO.readString(in, charsetName);
	}

	/**
	 * 读取InputStream内容成为字符串
	 * @param in 输入流
	 * @param charsetName 编码格式
	 * @param isClose 是否关闭流
	 * @return 读取的字符串
	 */
	public static String readString(InputStream in, String charsetName, boolean isClose) {
		return IO.readString(in, charsetName, isClose);
	}

	/**
	 * 读取出输入流的所有字节
	 * @param in 输入流
	 * @return 字节数组
	 */
	public static byte[] read(InputStream in) {
		return IO.read(in);
	}

	/**
	 * 读取出输入流的所有字节
	 * @param in 输入流
	 * @param isClose 是否关闭流
	 * @return 字节数组
	 */
	public static byte[] read(InputStream in, boolean isClose) {
		return IO.read(in, isClose);
	}

	/**
	 * 把text写入到os中 默认使用UTF-8编码
	 * @param os 输出流
	 * @param text 输入的字符串
	 */
	public static boolean write(OutputStream out, String text) {
		return IO.write(out, text);
	}

	/**
	 * 把text写入到os中
	 * @param out 输出流
	 * @param text 输入的字符串
	 * @param charsetName 编码格式
	 * @return true false
	 */
	public static boolean write(OutputStream out, String text, String charsetName) {
		return IO.write(out, text, charsetName);
	}

	/**
	 * 把text写入到os中
	 * @param out 输出流
	 * @param text 输入的字符串
	 * @param charsetName 编码格式
	 * @param isClose 是否关闭流
	 * @return true false
	 */
	public static boolean write(OutputStream out, String text, String charsetName, boolean isClose) {
		return IO.write(out, text, charsetName, isClose);
	}

	/**
	 * 把字节数组写入到流中
	 * @param out 输出流
	 * @param b 字节数组
	 * @return 是否成功
	 */
	public static boolean write(OutputStream out, byte[] b) {
		return IO.write(out, b);
	}

	/**
	 * 把字节数组写入到流中
	 * @param out 输出流
	 * @param b 字节数组
	 * @param isClose 是否关闭流
	 * @return 是否成功
	 */
	public static boolean write(OutputStream out, byte[] b, boolean isClose) {
		return IO.write(out, b, isClose);
	}

	/**
	 * 把text写入到out中
	 * @param out 输出流
	 * @param in 输入流
	 * @return true false
	 */
	public static boolean write(OutputStream out, InputStream in) {
		return IO.write(out, in);
	}

	/**
	 * 把text写入到out中
	 * @param out 输出流
	 * @param in 输入流
	 * @param isClose 是否关闭流
	 * @return true false
	 */
	public static boolean write(OutputStream out, InputStream in, boolean isClose) {
		return IO.write(out, in, isClose);
	}

	/**
	 * 私有构造
	 */
	private StreamUtil() {}

	/**
	 * IO方法接口 内部使用
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-02-17
	 */
	static interface IO {
		/**
		 * 读取InputStream内容成为字符串
		 * @param in 输入流
		 * @return 读取的字符串 失败返回""
		 */
		String readString(InputStream in);

		/**
		 * 读取InputStream内容成为字符串
		 * @param in 输入流
		 * @param charsetName 编码格式
		 * @return 读取的字符串 失败返回""
		 */
		String readString(InputStream in, String charsetName);

		/**
		 * 读取出输入流的所有字节
		 * @param in 输入流
		 * @return 字节数组
		 */
		byte[] read(InputStream in);

		/**
		 * 读取出输入流的所有字节
		 * @param in 输入流
		 * @param isClose 是否关闭流
		 * @return 字节数组
		 */
		byte[] read(InputStream in, boolean isClose);

		/**
		 * 把text写入到os中
		 * @param out 输出流
		 * @param text 输入的字符串
		 * @return true false
		 */
		boolean write(OutputStream out, String text);

		/**
		 * 把text写入到os中
		 * @param out 输出流
		 * @param text 输入的字符串
		 * @param charsetName 编码格式
		 * @return true false
		 */
		boolean write(OutputStream out, String text, String charsetName);

		/**
		 * 把字节数组写入到out中
		 * @param out 输出流
		 * @param b 字节数组
		 * @return true false
		 */
		boolean write(OutputStream out, byte[] b);

		/**
		 * 把字节数组写入到os中
		 * @param out 输出流
		 * @param in 输入流
		 * @param isClose 是否关闭流
		 * @return true false
		 */
		boolean write(OutputStream out, byte[] b, boolean isClose);

		/**
		 * 把输入流写入到os中
		 * @param out 输出流
		 * @param in 输入流
		 * @return true false
		 */
		boolean write(OutputStream out, InputStream in);

		/**
		 * 把text写入到os中
		 * @param out 输出流
		 * @param in 输入流
		 * @param isClose 是否关闭流
		 * @return true false
		 */
		boolean write(OutputStream out, InputStream in, boolean isClose);

		/**
		 * 读取InputStream内容成为字符串
		 * @param in 输入流
		 * @param charsetName 编码格式
		 * @param isClose 是否关闭流
		 * @return 读取的字符串
		 */
		String readString(InputStream in, String charsetName, boolean isClose);

		/**
		 * 把text写入到os中
		 * @param out 输出流
		 * @param text 输入的字符串
		 * @param charsetName 编码格式
		 * @param isClose 是否关闭流
		 * @return true false
		 */
		boolean write(OutputStream out, String text, String charsetName, boolean isClose);
	}

	/**
	 * 新IO操作
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-02-17
	 */
	static final class NIO implements IO {
		/**
		 * 读取InputStream内容成为字符串
		 * @param in 输入流
		 * @return 读取的字符串 失败返回""
		 */
		public String readString(InputStream in) {
			return readString(in, CommonParams.ENCODING);
		}

		/**
		 * 读取InputStream内容成为字符串
		 * @param in 输入流
		 * @param charsetName 编码格式
		 * @return 读取的字符串 失败返回""
		 */
		public String readString(InputStream in, String charsetName) {
			return readString(in, charsetName, true);
		}

		/**
		 * 读取出输入流的所有字节
		 * @param in 输入流
		 * @return 字节数组
		 */
		public byte[] read(InputStream in) {
			return read(in, true);
		}

		/**
		 * 读取出输入流的所有字节
		 * @param in 输入流
		 * @param isClose 是否关闭流
		 * @return 字节数组
		 */
		public byte[] read(InputStream in, boolean isClose) {
			return ChannelUtil.read(Channels.newChannel(in), isClose);
		}

		/**
		 * 把text写入到os中
		 * @param out 输出流
		 * @param text 输入的字符串
		 * @return true false
		 */
		public boolean write(OutputStream out, String text) {
			return write(out, StringUtil.toBytes(text));
		}

		/**
		 * 把text写入到os中
		 * @param out 输出流
		 * @param text 输入的字符串
		 * @param charsetName 编码格式
		 * @return true false
		 */
		public boolean write(OutputStream out, String text, String charsetName) {
			return write(out, text, charsetName, true);
		}

		/**
		 * 把字节数组写入到out中
		 * @param out 输出流
		 * @param b 字节数组
		 * @return true false
		 */
		public boolean write(OutputStream out, byte[] b) {
			return ChannelUtil.write(Channels.newChannel(out), b, true);
		}

		/**
		 * 把字节数组写入到os中
		 * @param out 输出流
		 * @param in 输入流
		 * @param isClose 是否关闭流
		 * @return true false
		 */
		public boolean write(OutputStream out, byte[] b, boolean isClose) {
			return ChannelUtil.write(Channels.newChannel(out), b, isClose);
		}

		/**
		 * 把输入流写入到os中
		 * @param out 输出流
		 * @param in 输入流
		 * @return true false
		 */
		public boolean write(OutputStream out, InputStream in) {
			return write(out, in, true);
		}

		/**
		 * 把text写入到os中
		 * @param out 输出流
		 * @param in 输入流
		 * @param isClose 是否关闭流
		 * @return true false
		 */
		public boolean write(OutputStream out, InputStream in, boolean isClose) {
			return ChannelUtil.write(Channels.newChannel(out), in, isClose);
		}

		/**
		 * 读取InputStream内容成为字符串
		 * @param in 输入流
		 * @param charsetName 编码格式
		 * @param isClose 是否关闭流
		 * @return 读取的字符串
		 */
		public String readString(InputStream in, String charsetName, boolean isClose) {
			return StringUtil.toString(read(in, isClose), charsetName);
		}

		/**
		 * 把text写入到os中
		 * @param out 输出流
		 * @param text 输入的字符串
		 * @param charsetName 编码格式
		 * @param isClose 是否关闭流
		 * @return true false
		 */
		public boolean write(OutputStream out, String text, String charsetName, boolean isClose) {
			return write(out, StringUtil.toBytes(text, charsetName), isClose);
		}
	}

	/**
	 * 旧IO操作
	 * @author WD
	 * @since JDK7
	 * @version 1.0 2012-02-17
	 */
	static final class OIO implements IO {
		/**
		 * 读取InputStream内容成为字符串 默认使用UTF-8
		 * @param in 输入流
		 * @return 读取的字符串
		 */
		public String readString(InputStream in) {
			return readString(in, CommonParams.ENCODING);
		}

		/**
		 * 读取InputStream内容成为字符串
		 * @param in 输入流
		 * @param charsetName 编码格式
		 * @return 读取的字符串
		 */
		public String readString(InputStream in, String charsetName) {
			return readString(in, charsetName, true);
		}

		/**
		 * 读取InputStream内容成为字符串
		 * @param in 输入流
		 * @param charsetName 编码格式
		 * @param isClose 是否关闭流
		 * @return 读取的字符串
		 */
		public String readString(InputStream in, String charsetName, boolean isClose) {
			// 创建可变字符序列
			StringBuilder buffer = new StringBuilder();
			// 声明BufferedReader
			BufferedReader br = null;
			try {
				// 创建BufferedReader流对象
				br = new BufferedReader(new InputStreamReader(in, charsetName), CommonParams.IO_BUFFERSIZE);
				// 流的缓冲
				String line;
				// 读取内容
				while ((line = br.readLine()) != null) {
					// 把流转义的字符加入到字符序列中
					buffer.append(line);
					buffer.append(StringConstants.NEWLINE);
				}
			} catch (IOException e) {} finally {
				// 关闭资源
				if (isClose) {
					CloseUtil.close(br, in);
				}
			}
			// 返回流的字符串形式
			return buffer.toString();
		}

		/**
		 * 读取出输入流的所有字节
		 * @param in 输入流
		 * @return 字节数组
		 */
		public byte[] read(InputStream in) {
			return read(in, true);
		}

		/**
		 * 读取出输入流的所有字节
		 * @param in 输入流
		 * @param isClose 是否关闭流
		 * @return 字节数组
		 */
		public byte[] read(InputStream in, boolean isClose) {
			// 创建结果字节缓存
			ByteArrayOutputStream out = new ByteArrayOutputStream(CommonParams.IO_BUFFERSIZE * 10);
			try {
				// 声明用于缓存的字节数组
				byte[] buffer = new byte[CommonParams.IO_BUFFERSIZE];
				// 每次读取字节数组的长度
				int length = 0;
				// 循环读取流 如果读取长度大于0 继续循环
				while ((length = in.read(buffer)) > 0) {
					// 把字节数组添加到缓存里
					out.write(buffer, 0, length);
				}
			} catch (IOException e) {} finally {
				// 关闭资源
				if (isClose) {
					CloseUtil.close(in);
				}
			}
			// 返回字节数组
			return out.toByteArray();
		}

		/**
		 * 把text写入到os中 默认使用UTF-8编码
		 * @param os 输出流
		 * @param text 输入的字符串
		 */
		public boolean write(OutputStream os, String text) {
			return write(os, text, CommonParams.ENCODING, true);
		}

		/**
		 * 把text写入到os中
		 * @param out 输出流
		 * @param text 输入的字符串
		 * @param charsetName 编码格式
		 * @return true false
		 */
		public boolean write(OutputStream out, String text, String charsetName) {
			return write(out, text, charsetName, true);
		}

		/**
		 * 把text写入到os中
		 * @param out 输出流
		 * @param text 输入的字符串
		 * @param charsetName 编码格式
		 * @param isClose 是否关闭流
		 * @return true false
		 */
		public boolean write(OutputStream out, String text, String charsetName, boolean isClose) {
			// 声明BufferedWriter
			BufferedWriter bw = null;
			try {
				// 获得BufferedWriter
				bw = new BufferedWriter(new OutputStreamWriter(out, charsetName), CommonParams.IO_BUFFERSIZE);
				// 写入字符串
				bw.write(text);
				// 刷新文件流 把流内所有内容更新到文件上
				bw.flush();
				// 返回成功
				return true;
			} catch (IOException e) {} finally {
				// 关闭资源
				if (isClose) {
					CloseUtil.close(bw, out);
				}
			}
			// 返回失败
			return false;
		}

		/**
		 * 把字节数组写入到流中
		 * @param out 输出流
		 * @param b 字节数组
		 * @return 是否成功
		 */
		public boolean write(OutputStream out, byte[] b) {
			return write(out, Bytes.getInputStream(b), true);
		}

		/**
		 * 把字节数组写入到流中
		 * @param out 输出流
		 * @param b 字节数组
		 * @param isClose 是否关闭流
		 * @return 是否成功
		 */
		public boolean write(OutputStream out, byte[] b, boolean isClose) {
			return write(out, Bytes.getInputStream(b), isClose);
		}

		/**
		 * 把text写入到out中
		 * @param out 输出流
		 * @param in 输入流
		 * @return true false
		 */
		public boolean write(OutputStream out, InputStream in) {
			return write(out, in, true);
		}

		/**
		 * 把text写入到out中
		 * @param out 输出流
		 * @param in 输入流
		 * @param isClose 是否关闭流
		 * @return true false
		 */
		public boolean write(OutputStream out, InputStream in, boolean isClose) {
			// 判断如果流为空 直接返回
			if (EmptyUtil.isEmpty(out) || EmptyUtil.isEmpty(in)) {
				return false;
			}
			try {
				// 声明字节数组 当缓存用
				byte[] buffer = new byte[CommonParams.IO_BUFFERSIZE];
				// 声明保存读取字符数量
				int num = 0;
				// 循环读取
				while ((num = in.read(buffer)) > 0) {
					// 输出到文件流
					out.write(buffer, 0, num);
				}
				// 刷新文件流 把流内所有内容更新到文件上
				out.flush();
				// 返回成功
				return true;
			} catch (IOException e) {} finally {
				// 关闭资源
				if (isClose) {
					CloseUtil.close(out, in);
				}
			}
			// 返回失败
			return false;
		}
	}
}
