// JavaScript Document
var resMsg = {};
resMsg = {
	'001': '请给出记录结构表字符串！',
    '002': '给定字符串初始化失败！',
	'003': '界面初始化失败！',
	'004': '请输入取值！',
	'005': '添加失败！',
	'006': '请查看页面加载是否完整正确或刷新页面重试！',
	'007': '请查看各输入是否完整正确或刷新页面重试！',
	'008': '删除失败！',
	'009': '请选择欲删除项！',
	'010': '删除成功！',
	'011': '已存在相同条件项！',
	'012': '无条件项！',
	'013': '记录结构表非法！',
	'014': '取值表非法！',
	'015': '内部错误！',
	'016': '字段不存在相应类型信息！',
	'017': '字段不存在相应枚举值！',
	'018': '字段枚举值超出字段长度！',
	'019': '字段为字符类型！',
	'020': '上下界值非法！',
	'021': '字段枚举值非法！',
	'022': '输入非法！'
}
function nocontextmenu() {  //IE5+
     event.cancelBubble = true 
     event.returnValue = false; 
     return false; 
} 
function norightclick(e) {  //Others
     if (window.Event) { 
	 	if (document.layers||document.getElementById && !document.all){
     		if (e.which == 2 || e.which == 3) 
      		return false;
		}
     } 
     else if (event.button == 2 || event.button == 3) { 
     	event.cancelBubble = true 
       	event.returnValue = false; 
       	return false; 
     } 
}
function DisableFKey() {
	if ((window.event.keyCode==8) || 
		(window.event.keyCode == 116) || 
		(window.event.keyCode == 122) ||
		(window.event.keyCode == 123) || 
		(window.event.ctrlKey) || 
		(window.event.keyCode==78 && window.event.ctrlKey) || 
		(window.event.altKey) || 
		//(window.event.altKey && window.event.keyCode == 115) || 
		(window.event.ctrlKey && window.event.keyCode==82) || 
		(window.event.shiftKey && window.event.keyCode == 121)) { //屏蔽退格删除键 F5  F11 F12 Ctrl+R Shift+F10
		window.event.keyCode = 0;
		//event.cancelBubble = true; 
        //return false; 
		window.event.returnValue = false;
	}
	/*
	if ((window.event.altKey)&&(window.event.keyCode==115)){ //屏蔽Alt+F4
		//window.showModelessDialog("about:blank","","dialogWidth:1px;dialogheight:1px");
		window.event.keyCode = 37; //or 39
		return false;
	}
	*/
}
String.prototype.replaceAll = function(s1,s2){    
	return this.replace(new RegExp(s1,"gm"),s2);    
}

String.prototype.trim = function() {
	return (this.replace(/^[\s\xA0]+/, "").replace(/[\s\xA0]+$/, ""));
}

String.prototype.startsWith = function(str) {
	if(str == null || str == "" || this.length == 0 || str.length > this.length)
  		return false;
  	if(this.substr(0, str.length) == str)
     	return true;
	else if(this.match("^" + str) == str) 
		return true;
  	else
    	return false;
}

String.prototype.endsWith=function(str) {
  	if(str == null || str == "" || this.length == 0 || str.length > this.length)
     	return false;
	var lastIndex = this.lastIndexOf(str); 
	if(lastIndex != -1) {
		if(lastIndex + str.length == this.length) {
			return true;
		}
		else if(this.substring(this.length - str.length) == str)
     		return true;
		else if (this.match(str + "$") == str)
			return true;
  		else
     		return false;
	}
  	else
     	return false;
}
function addLoadEvent(func) { 
	var oldonload = window.onload; 
	if (typeof window.onload != 'function') { 
		window.onload = func; 
	}
	else { 
		window.onload = function() { 
			oldonload(); 
			func(); 
		} 
	} 
}
//addLoadEvent(init);
function isJson(obj){
	var isjson = typeof(obj) == "object" && (Object.prototype.toString.call(obj).toLowerCase() == "[object object]" || Object.prototype.toString.call(obj).toLowerCase() == "[object array]") && obj.length;    
	return isjson;
}
/*
function str2json(str){  
	return eval(str);  
}
function str2json(str){  
	return eval('(' + str + ')');  
}
*/
function str2json(stringValue) {
   eval("var theJsonValue = " + stringValue);
   return theJsonValue;
}

function json2str(o) {
	var arr = [];
	var fmt = function(s) {
		if (typeof s == 'object' && s != null) {
			return json2str(s);
		}
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
	}
	for (var i in o) {
		arr.push("'" + i + "':" + fmt(o[i]));
	}
	return '{' + arr.join(',') + '}';
}

function jsonArray2String(jsonArray) {

   var JsonArrayString = "[";
   for(var i = 0;i < jsonArray.length;i++){
	   JsonArrayString = JsonArrayString + json2str(jsonArray[i])+",";
   }
   JsonArrayString = JsonArrayString.substring(0, JsonArrayString.length - 1)+"]";
   return JsonArrayString;
}

function toThePowerOf(base, index) {
	if(index == 0) {
		return 1;
	}
	else if(index == 1) {
		return base;
	}
	else {
		var res = base;
		for(var i = 1; i < index; i++) {
			res = res * base; 
		}
		return res;
	}
}

function checkInt(num)
{
     var re = /^[1-9]+[0-9]*]*$/;
     if (!re.test(num))
    {
        return false;
     }
	 return true;
}

function checkFloat(num)
{
     var re = /^[0-9]+.?[0-9]*$/;
     if (!re.test(num))
    {
        //return false;
		return (checkInt(num));
     }
	 return true;
}