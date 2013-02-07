var _D;
var div_Top;
var div_Id;
var div_Left;
var div_Width;
var div_Height;
var doc_Height;
var doc_Width;

var _style;
var style_Top;
var style_Left;

var T = function(divId) {
	alert(divId);
	// alert("T");
	init(divId);
	style_Top = (arrayPageSize[1] - div_Height) / 2 + arrayScroll[1];
	style_Left = (arrayPageSize[0] - div_Width) / 2 + arrayScroll[0];
	_style.display = "";
};

// ��ȡ����
var init = function(divId) {
	_D = document.getElementById(divId);
	div_Top = parseInt(document.getElementById(divId).style.top, 10); // ��ȡ��
	div_Left = parseInt(document.getElementById(divId).style.left, 10); // div��y���
	div_Height = parseInt(document.getElementById(divId).offsetHeight, 10); // div�ĸ߶�
	div_Width = parseInt(document.getElementById(divId).offsetWidth, 10); // div�Ŀ��
	arrayScroll = getPageScroll();
	arrayPageSize = getPageSize();
	_style = divId.style;
	style_Top = divId.style.top;
	style_Left = divId.style.left;
};

// ���ӵķ���
var addTM = function(x, y) {
	// ��ȡֵ
	var td1 = document.getElementById("gztype").value;
	var td2 = document.getElementById("jinge").value;

	// ������
	var _T = document.getElementById("t2");
	var R_ind = _T.rows.Length;
	var objTR = objTable.insertRow(R_ind);
	var objTD_1 = objTR.insertCell(0);
	var objTD_2 = objTR.insertCell(1);
	objTD_1.innerHTML = "<td>" + td1 + "</td>";
	objTD_2.innerHTML = "<td>" + td2 + "</td>";
};

// ��ȡgetPageSize
var getPageSize = function() {
	var de = document.documentElement;
	var w = window.innerWidth || self.innerWidth || (de && de.clientWidth)
			|| document.body.clientWidth;
	var h = window.innerHeight || self.innerHeight || (de && de.clientHeight)
			|| document.body.clientHeight;
	arrayPageSize = new Array(w, h);
	return arrayPageSize;
};

// ��ȡpageScrollTop
var getPageScroll = function() {
	var yScrolltop = null;
	var xScrollleft = null;
	if (self.pageYOffset || self.pageXOffset) {
		yScrolltop = self.pageYOffset;
		xScrollleft = self.pageXOffset;
	} else if (document.documentElement && document.documentElement.scrollTop
			|| document.documentElement.scrollLeft) { // Explorer 6 Strict
		yScrolltop = document.documentElement.scrollTop;
		xScrollleft = document.documentElement.scrollLeft;
	} else if (document.body) {// all other Explorers
		yScrolltop = document.body.scrollTop;
		xScrollleft = document.body.scrollLeft;
	}
	arrayPageScroll = new Array(xScrollleft, yScrolltop);
	return arrayPageScroll;
};
