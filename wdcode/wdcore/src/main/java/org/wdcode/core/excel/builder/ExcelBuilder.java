package org.wdcode.core.excel.builder;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

import org.wdcode.common.io.FileUtil;
import org.wdcode.core.excel.Excel;
import org.wdcode.core.excel.impl.ExcelJXL;
import org.wdcode.core.excel.impl.ExcelPOI;
import org.wdcode.core.params.CoreParams;

/**
 * 生成 Excel 接口工厂
 * @see org.wdcode.core.excel.Excel
 * @author WD
 * @since JDK7
 * @version 1.0 2009-7-17
 */
public final class ExcelBuilder {
	// 使用使用jxl
	private final static boolean	JXL;

	/**
	 * 静态初始化
	 */
	static {
		JXL = "jxl".equalsIgnoreCase(CoreParams.EXCEL_PARSE);
	}

	/**
	 * 创建Excel对象
	 * @param fileName 文件路径
	 * @return Excel对象
	 */
	public static Excel getExcel(String fileName) {
		return getExcel(FileUtil.getFile(fileName));
	}

	/**
	 * 创建Excel对象
	 * @param file 文件
	 * @return Excel对象
	 */
	public static Excel getExcel(File file) {
		return getExcel(FileUtil.getInputStream(file));
	}

	/**
	 * 创建Excel对象
	 * @param in 流
	 * @param parse 使用解析Excel的包
	 * @return Excel对象
	 */
	private static Excel getExcel(InputStream in) {
		return JXL ? new ExcelJXL(in) : new ExcelPOI(in);
	}

	/**
	 * 创建Excel文档 并返回着个Excel的Excel对象
	 * @param fileName 文件路径
	 * @return Excel对象
	 */
	public static Excel createExcel(String fileName) {
		return createExcel(FileUtil.getFile(fileName));
	}

	/**
	 * 创建Excel文档 并返回着个Excel的Excel对象
	 * @param file 文件
	 * @return Excel对象
	 */
	public static Excel createExcel(File file) {
		return createExcel(FileUtil.getOutputStream(file));
	}

	/**
	 * 创建Excel文档 并返回着个Excel的Excel对象
	 * @param out 文件流
	 * @param parse 使用解析Excel的包
	 * @return Excel对象
	 */
	public static Excel createExcel(OutputStream out) {
		return JXL ? new ExcelJXL(out) : new ExcelPOI(out);
	}

	/**
	 * 私有构造
	 */
	private ExcelBuilder() {}
}
