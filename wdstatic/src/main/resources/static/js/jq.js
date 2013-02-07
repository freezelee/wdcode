/**
 * $功能描述: 本JS是扩展jQuery功能 jQuery版本1.3.2
 * $作者: 吴敌
 * $时间: 2008-03-01
 * $版本: 1.0
 */

/**
 * 扩展jQuery对象本身
 */
jQuery.extend({
	//浏览器版本
	bowver: jQuery.browser.version,
	
	 //是否是IE
	isIE: jQuery.browser.msie,
	isIE5: jQuery.browser.msie && jQuery.browser.version == "5.0", //是否IE6
	isIE6: jQuery.browser.msie && jQuery.browser.version == "6.0", //是否IE6
	isIE7: jQuery.browser.msie && jQuery.browser.version == "7.0", //是否IE7
	isIE8: jQuery.browser.msie && jQuery.browser.version == "8.0", //是否IE8
	
	//是否是Firefox
	isFF: jQuery.browser.mozilla,
	//是否是Firefox2
	isFF2: jQuery.browser.mozilla && jQuery.browser.version.indexOf("1.8.") > -1,
	//是否是Firefox3
	isFF3: jQuery.browser.mozilla && jQuery.browser.version.indexOf("1.9.") > -1,
	
	//是否是Safari
	isSafari: jQuery.browser.safari,
	
	//是否是Opera
	isOpera: jQuery.browser.opera,
	
	//get方式提交返回XML文档
	getXML: function (url,data,callback) {
	return jQuery.get(url,data,function(xml){
				callback(getXmlDoc(xml));
			});
	},
	
	//post方式提交返回XML文档
	postXML: function (url,data,callback) {
	return jQuery.post(url,data,function(xml){
			callback(getXmlDoc(xml));
		});
	},
	
	/**
	 * ajax二级联动 url 提交路径 one 联动源框的ID two 改变数据框ID 
	 */
	ajaxLink: function (url,one,two,flag) {		
		return $("#" + one).change(function(){
			jQuery.postXML(url,"id=" + $(this).val(),function(xml){
				if(!jQuery.isArray(two)){
					var temp = two;
					two = new Array;
					two[0] = temp;
				}
				for( var i = 0; i < two.length; i++) {
					var obj = $("#" + two[i]);
					var opt = flag ? obj.find("option").eq(0) : null;
					obj.empty();
					if(flag){
						obj.append(opt);
					}
					if(jQuery(xml).text()){
						jQuery(xml).find("entity" + i).each(function(){				
							obj.append("<option value='" + $(this).find("key").text() + "'>" + $(this).find("value").text() + "</option>");
						});
					}	
				}							
			});
		});
	}
}); 

/**
 * 扩展 jQuery 元素集来提供新的方法
 */
jQuery.fn.extend({
	//日期控件
	calendar: function(isTime){
		if($.datetimepicker){
			$.datetimepicker.regional['zh-CN'] = {
					clearText: '清除',
					clearStatus: '清除已选日期',
					closeText: '关闭',
					closeStatus: '不改变当前选择',
					prevText: '&lt;上月',
					prevStatus: '显示上月',
					nextText: '下月&gt;',
					nextStatus: '显示下月',
					currentText: '今天', 
					currentStatus: '显示本月',
					monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
					monthNamesShort: ['一','二','三','四','五','六','七','八','九','十','十一','十二'],
					monthStatus: '选择月份',
					yearStatus: '选择年份',
					weekHeader: '周',
					weekStatus: '年内周次',
					dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
					dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],
					dayNamesMin: ['日','一','二','三','四','五','六'],
					dayStatus: '设置 DD 为一周起始', 
					dateStatus: '选择 m月 d日, DD',
					dateFormat: 'yy-mm-dd ',
					timeFormat: isTime ? 'hh:ii' : '', 
					firstDay: 1, 
					initStatus: '请选择日期',
					isRTL: false,
					isTime: isTime
				};
				$.datetimepicker.setDefaults($.datetimepicker.regional['zh-CN']);
				$(this).datetimepicker();
		}
	}
});

/**
 * 把XML字符串转换成XML文档
 */
function getXmlDoc(xml){
	var xmlDoc;
	try{ 
		if (window.ActiveXObject){ 
			xmlDoc= new ActiveXObject("MSXML2.DOMDocument.3.0");
			xmlDoc.async=true;//异步进行
			xmlDoc.loadXML(xml);
		}else if (document.implementation && document.implementation.createDocument){ 
			var oParser = new DOMParser();
			xmlDoc = oParser.parseFromString(xml,"text/xml");
		}else{} 
	}catch(e){}
	return xmlDoc;
}
