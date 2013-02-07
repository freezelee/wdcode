package org.wdcode.core.engine;
 
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import org.wdcode.common.lang.Bytes;
import org.wdcode.common.lang.Lists;
import org.wdcode.common.lang.Maps;
import org.wdcode.common.log.Logs;
import org.wdcode.common.util.BeanUtil;
import org.wdcode.common.util.EmptyUtil;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * CSV格式处理器
 * @author WD
 * @since JDK7
 * @version 1.0 2012-8-9
 */
public final class CsvEngine {
	/**
	 * 读取出所有元素到列表中
	 * @param in 输入流
	 * @return List 所有集合
	 */
	public static List<String[]> read(byte[] b) {
		return read(Bytes.getInputStream(b));
	}

	/**
	 * 读取出所有元素到列表中
	 * @param in 输入流
	 * @return List 所有集合
	 */
	public static List<String[]> read(InputStream in) {
		return read(new InputStreamReader(in));
	}

	/**
	 * 读取出所有元素到列表中
	 * @param reader 读取器
	 * @return List 所有集合
	 */
	public static List<String[]> read(Reader reader) {
		// 声明csv读取器
		CSVReader csv = null;
		try {
			// 实例化csv读取器
			csv = new CSVReader(reader);
			// 返回全部数据列表
			return csv.readAll();
		} catch (Exception e) {
			Logs.warn(e);
		} finally {
			if (csv != null) {
				try {
					csv.close();
				} catch (IOException e) {
					Logs.warn(e);
				}
			}
		}
		// 返回空列表
		return Lists.emptyList();
	}

	/**
	 * 读取出所有元素到列表中
	 * @param in 输入流
	 * @param entityClass 实体类
	 * @return List 所有集合
	 */
	public static <E> List<E> read(byte[] b, Class<E> entityClass) {
		return read(Bytes.getInputStream(b), entityClass);
	}

	/**
	 * 读取出所有元素到列表中
	 * @param in 输入流
	 * @param entityClass 实体类
	 * @return List 所有集合
	 */
	public static <E> List<E> read(InputStream in, Class<E> entityClass) {
		return read(new InputStreamReader(in), entityClass);
	}

	/**
	 * 读取出所有元素到列表中
	 * @param reader 读取器
	 * @param entityClass 实体类
	 * @return List 所有集合
	 */
	public static <E> List<E> read(Reader reader, Class<E> entityClass) {
		// 获得所有数据
		List<String[]> list = read(reader);
		// 如果数据列表为空 返回空列表
		if (EmptyUtil.isEmpty(list)) {
			return Lists.emptyList();
		}
		// 获得列表大小
		int size = list.size();
		// 获得列数组
		String[] cols = list.get(0);
		// 声明实体列表
		List<E> entitys = Lists.getList(list.size());
		// 循环获得实体列表
		for (int i = 1; i < size; i++) {
			entitys.add(BeanUtil.copyProperties(entityClass, Maps.getMap(cols, list.get(i))));
		}
		// 返回实体列表
		return entitys;
	}

	/**
	 * 写入全部数据到CSV
	 * @param out 输出流
	 * @param list
	 */
	public static void write(OutputStream out, List<String[]> list) {
		write(new OutputStreamWriter(out), list);
	}

	/**
	 * 写入全部数据到CSV
	 * @param writer 写入器
	 * @param list
	 */
	public static void write(Writer writer, List<String[]> list) {
		// 实例化csv写入器
		CSVWriter csv = new CSVWriter(writer);
		// 写入csv数据
		csv.writeAll(list);
		try {
			// 关闭csv写入器
			csv.close();
		} catch (Exception e) {
			Logs.warn(e);
		}
	}

	/**
	 * 私有构造
	 */
	private CsvEngine() {}
}
