package org.wdcode.core.chart.builder;

import org.wdcode.core.chart.BarChart;
import org.wdcode.core.chart.PieChart;
import org.wdcode.core.chart.TimeSeriesChart;
import org.wdcode.core.chart.impl.BarChartImpl;
import org.wdcode.core.chart.impl.PieChartImpl;
import org.wdcode.core.chart.impl.TimeSeriesChartImpl;

/**
 * JFreeChart工厂
 * @author WD
 * @since JDK7
 * @version 1.0 2011-07-25
 */
public final class ChartBuilder {
	/**
	 * 实例化一个新柱图
	 * @return 柱图
	 */
	public static BarChart buildBarChart() {
		return new BarChartImpl();
	}

	/**
	 * 实例化一个新饼图
	 * @return 饼图
	 */
	public static PieChart buildPieChart() {
		return new PieChartImpl();
	}

	/**
	 * 实例化一个新时序图
	 * @return 时序图
	 */
	public static TimeSeriesChart buildTimeSeriesChart() {
		return new TimeSeriesChartImpl();
	}

	/**
	 * 私有构造
	 */
	private ChartBuilder() {}
}
