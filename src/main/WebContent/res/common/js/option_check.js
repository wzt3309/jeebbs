/***************************************** 
调用方法为： 
Jselect($("#inputid"),{ 
bindid:'bindid', 
hoverclass:'hoverclass', 
optionsbind:function(){return hqhtml();} 
}); 
inputid为下拉框要绑定的文本框id 
bindid为点击弹出/收回下拉框的控件id 
hoverclass为鼠标移到选项时的样式 
hqhtml为绑定的数据 
******************************************/ 
(function($){ 
$.showselect = { 
init : function(o,options){ 
var defaults = { 
bindid : null, //事件绑定在bindid上 
hoverclass:null, //鼠标移到选项时样式名称 
optionsbind:function(){} //下拉框绑定函数 
} 
var options = $.extend(defaults,options); 
if(options.optionsbind!=null){//如果绑定函数不为空 
this._setbind(o,options); 
} 
if(options.bindid!=null){ 
this._showcontrol(o,options); 
} 
}, 
_showcontrol:function(o,options){//控制下拉框显示 
$("#"+options.bindid).toggle(function(){ 
$(o).next().slideDown(); 
},function(){ 
$(o).next().slideUp(); 
}) 
}, 
_setbind:function(o,options){//绑定数据 
var optionshtml="<div style=\"z-index: 999; position: absolute;\">" 
+options.optionsbind()+"</div>"; 
$(o).after(optionshtml); 
var offset= $(o).offset(); 
var w=$(o).width(); 
$(o).next().css({top:offset.top+$(o).height()+7,left:offset.left,width:w}); 
if(options.hoverclass!=null){ 
$(o).next().find("tr").hover(function(){$(this).addClass(options.hoverclass);}, 
function(){$(this).removeClass(options.hoverclass);}); 
} 
$(o).next().find("input[type=checkbox]").filter("[lang=checked]").each(function(){$(this).attr("checked","checked");}); 
$(o).next().find("input[type=checkbox]").click(function(){ 
var $ckoption=$(this).attr("checked"); 
if($ckoption){ 
$(this).attr("checked",""); 
}else{ 
$(this).attr("checked","checked"); 
} 
}); 
$(o).next().find("tr").click(function(){ 
var $ckflag=$(this).find("input[type=checkbox]"); 
if($ckflag.attr("checked")){ 
$ckflag.attr("checked",""); 
$ckflag.attr("lang",""); 
} 
else{ 
$ckflag.attr("checked","checked"); 
$ckflag.attr("lang","checked"); 
} 
var selarray=new Array(); 
$(o).next().find("input[type=checkbox]").each(function(){ 
if($(this).attr("checked")) 
selarray.push($(this).parent().next().text()); 
}); 
$(o).val(selarray.join(',')); 
}); 
$(o).next().hide(); 
} 
} 
Jselect = function(o,json){ 
$.showselect.init(o,json); 
}; 
})(jQuery); 