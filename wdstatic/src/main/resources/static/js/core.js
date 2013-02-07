var WD = {};

WD.String = {
	function isUndefined(object) {
		return typeof object == "undefined";
	}
	function isString(object) {
	    return typeof object == "string";
	}
	function isElement(object) {
		return object && object.nodeType == 1;
	}
	function isFunction(object) {
		return typeof object == "function";
	}
	function isObject(object) {
		return typeof object == "object";
	}
	function isArray(object) {
		return object !== null && typeof object == "object" &&'splice' in object && 'join' in object;
	}
	function isNumber(object){
		return typeof object == 'number';
	}
	function isImportNum(event){
		event = event == "undefined" ? window.event:event;
		if((event.keyCode < 45 || event.keyCode>57) && event.keyCode!=46) {
			event.returnValue = false;
			return false;
		}
	}
}

/**
 * 字符串的操作和判断方法 nl2br:返回字符串中的回车符为<br>
 * len：返回字符串真实长度 trim:去掉字符串头尾空格 ltrim:去掉字符串左侧空格 rtrim:去掉字符串右侧空格
 * 
 */
WD.String = {
	nl2br:function(str){
		return str.replace(/([^>])\n/g, '$1<br />');
	},
	len : function (str) {
		return str.replace(/[^\x00-\xff]/g,"**").length;
	},
	trim : function(str) {
		return str.replace(/^\s+|\s+$/g,"");
	},
	ltrim :function(str) {
		return str.replace(/^\s+/,"");
	},
	rtrim :function(str) {
		return str.replace(/\s+$/,"");
	},
	stripTags: function(str) {
		return str.replace(/<\/?[^>]+>/gi, '');
	},
	escapeHTML: function(str) {
		return str.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;');
	},
	unescapeHTML: function(str) {
		return str.replace(/&amp;/g,'&').replace(/&lt;/g,'<').replace(/&gt;/g,'>').replace(/&nbsp;/g,' ').replace(/&quot;/g,'"');
	},
	include:function(str,key){
		return str.indexOf(key) > -1;
	},
	startsWith:function(str,key){
		return str.indexOf(key) === 0;
	},
	endsWith:function(str,key){
	    var d = str.length - key.length;
	    return d >= 0 && str.lastIndexOf(key) === d;	
	},
	isBlank:function(str){
		return WD.STRING.trim(str) == '';
	},
	isEmail:function(str){
		return /^[A-Z_a-z0-9-\.]+@([A-Z_a-z0-9-]+\.)+[a-z0-9A-Z]{2,4}$/.test(str);
	},
	isPhone:function(str){
		return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/.test(str);
	},
	isMobile:function(str){
		return /^((\(\d{2,3}\))|(\d{3}\-))?((13\d{9})|(15[3869]\d{8}))$/.test(str);
	},
	isUrl:function(str){
		return /^(http:|ftp:)\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"])*$/.test(str);
	},
	isIp:function(str){
		return /^(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5]).(0|[1-9]\d?|[0-1]\d{2}|2[0-4]\d|25[0-5])$/.test(str);
	},
	isNum:function(str){
		return /^\d+$/.test(str);
	},
	isZip:function(str){
		return /^[1-9]\d{5}$/.test(str);
	},
	isEN:function(str){
		return /^[A-Za-z]+$/.test(str);
	},
	isNickName:function(str){
		return  /^[a-zA-Z\u4e00-\u9fa5\s.]+$/.test(str);
	},
	isPicture:function(str){
		var exc = /\.[^\.]+$/.exec(str);
		return /[.]{1}(jpg|gif|bmp|png)+$/i.test(exc);
	},
	isFlash:function(str){
		var exc = /\.[^\.]+$/.exec(str);
		return /[.]{1}(swf)+$/i.test(exc);
	},
	isStringRule:function(str){
	   return /^([a-zA-Z0-9\u4e00-\u9fa5]|[_]|[-]|[ ])*$/.test(str);
    },  
	subStringAddPoint:function(str,num){
		if(WD.String.len(str) > num){
			str = WD.String.subString(str,num) + "...";
		}
		return str;
	},
	subString:function(str,num){
		if(WD.String.len(str) <= num){
			return str;
		}
		var iNum = 0;
		var i = 0;
		for(;iNum < num && i < str.length;i++){
			if(WD.String.isEN(str.charAt(i)) || WD.String.isNum(str.charAt(i))){
				iNum ++;
			}else {
				iNum ++;
				iNum ++;
			}
			if(iNum > num){
				i--;
			}
		}
		return str.substring(0,i);

	}
};
WD.Debug = WD.DEBUG = {
	win	:null,
	log	:null,
	On	:function() {
		WD.DEBUG_MODE = true;
		if(typeof console == 'undefined' || typeof console.log == 'undefined'){
			if (WD.DEBUG.win == null || WD.DEBUG.win.closed)
			{
				WD.DEBUG.win = window.open('debug.html',"fmDebug","width = 500,height = 400,location = no,menubar = no,toolbar = no,scrollbars = yes");
				WD.DEBUG.win.focus();
			}
			WD.log = WD.DEBUG.log = function(s){
				var _d,_p;
				_d = WD.DEBUG.win.document;
				_p = _d.createElement("p");
				_p.appendChild(_d.createTextNode(s));
				_d.body.appendChild(_p);
				_d.body.scrollTop = _d.body.scrollHeight;
				// WD.DEBUG.win.scrollTo(0,999999);
			};
			window.onerror = function(){
				WD.log('error:' + arguments[0] + '\n' + 'error file:' + arguments[1] + '\n' + 'row number:' + arguments[2] + '\n');
			};
		}else{
			WD.log = WD.DEBUG.log = function(s){
				console.log(s);
			}
		}
	},
	Off	:function() {
		WD.DEBUG_MODE = false;
		window.onerror = null;
		WD.log = WD.DEBUG.log = function() {};
	},
	init	:function(){
		WD.DEBUG[(WD.DEBUG_MODE?"On":"Off")]();
	}
};
WD.DEBUG.init();

WD.COOKIE = WD.Cookie = {
	get:function(name){
		var nameEQ = name + "=";
		var ca = document.cookie.split(';');
		for(var i=0;i < ca.length;i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1,c.length);
			if (c.indexOf(nameEQ) == 0){
				var str = decodeURIComponent(c.substring(nameEQ.length,c.length));
				str = str == "\"\""?"":str;
				return str;
			}
		}
		return null;
	},
	set:function(name,value,domain,days,secure,path){
		var expires;
		if(isNumber(days)){
			var date = new Date();
			date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
			expires = date.toGMTString();
		}else if(isString(days)){
			expires = days;
		}else{
			expires = false;
		}
		document.cookie = name + '=' + encodeURIComponent(value) +
				(expires ? ';expires=' + expires  : '') +
				(path ? ';path=' + path : '') +
				(domain ? ';domain=' + domain : '') +
				(secure ? ';secure' : '');		
	},
	del:function(name){
		WD.COOKIE.set(name,'','Thu, 01-Jan-70 00:00:01 GMT');
	}
};
