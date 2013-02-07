/**
 * 给左边菜单使用JS
 */

/**
 * 文档加载事件
 */
$(function() {
	// 元素动画效果时间
	var speed = 500;
	// 给菜单TD 添加单击事件
	$(".head").click(function() {
		$(".head").each(function() {
			$(this).next().hide();
		});
		$(this).next().slideDown(speed);
	});
});