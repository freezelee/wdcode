package org.wdcode.core.excel.builder;

import java.io.File;

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
		return JXL ? new ExcelJXL(file) : new ExcelPOI(file);
	}

	/**
	 * 私有构造
	 */
	private ExcelBuilder() {}
}
