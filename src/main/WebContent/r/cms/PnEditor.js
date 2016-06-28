Pn = {
	version : '1.0'
};

Pn.Editor = function() {
	this.init();
}

Pn.Editor.prototype.init = function() {
	//菜单名称
	Pn.Editor.menuName=['_fontname','_fontsize','_forecolor','_link','_email','_image'];
	Pn.Editor.menu=[];
	Pn.Editor.popup=[];
	Pn.Editor.editor = $('#_editor_textarea');
	Pn.Editor.txtarea = Pn.Editor.editor[0];
	Pn.Editor.file = $('#_editor_file');
	Pn.Editor.fileContainer = $('#_file_container');
	Pn.Editor.fileSelector = $('#_file_selecter');
	//控制栏（按钮）
	$('#_editor_controls a').each(function(){
		$(this).addClass('_controls_normal');
		$(this).mouseover(function(){
			$(this).toggleClass('_controls_hover',true);
			$(this).toggleClass('_controls_normal',false);
		});
		$(this).mouseout(function(){
			$(this).toggleClass('_controls_hover',false)
			$(this).toggleClass('_controls_normal',true);
		});
	});
	//弹出菜单
	for(var i=0;i<Pn.Editor.menuName.length;i++) {
		var n = Pn.Editor.menuName[i];
		var pname= '#'+n+'_pop';
		var m = $('#'+n+'_menu');
		var p = $(pname);
		Pn.Editor.menu[i] = m;
		Pn.Editor.popup[i] = p;
		var offset = m.offset();
		var top = offset.top+m.outerHeight()+'px';
		var left = offset.left+'px';
		p.css({top:top,left:left});
		m.attr('popup',pname);
		m.click(function(){
			var popName = $(this).attr('popup');
			$(popName).toggle();
			Pn.Editor.closeMenu($(popName));
		});		
		$(pname+' ._input_cancel').each(function(){
			$(this).attr('popup',pname).click(function() {
				var popName = $(this).attr('popup');
				$(popName).hide();	
			})
		});
	}
	//字体
	$('#_fontname_pop li').each(function(){
		var j = $(this);
		var font = $.trim(j.text());
		j.css('font-family',font);
		j.click(function(){
			Pn.Editor.bbstyle('[font='+font+']','[/font]');
			$('#_fontname_pop').hide();
		});
	});
	//字体大小
	$('#_fontsize_pop li').each(function(){
		var j = $(this);
		var size = j.attr('fontsize');
		j.css('font-size',size);
		j.click(function(){
			Pn.Editor.bbstyle('[size='+size+']','[/size]');
			$('#_fontsize_pop').hide();
		});
	});
	//字体颜色
	$('#_forecolor_pop div').each(function(){
		var j = $(this);
		var p = j.parent();
		var col = p.attr('col');
		j.css('background-color',col);
		j.addClass('_forecolor_normal');
		j.mouseover(function(){
			j.toggleClass('_forecolor_hover',true);
			j.toggleClass('_forecolor_normal',false);
		});
		j.mouseout(function(){
			j.toggleClass('_forecolor_hover',false);
			j.toggleClass('_forecolor_normal',true);
		});		
		j.click(function(){
			Pn.Editor.bbstyle('[color='+col+']','[/color]');
			$('#_forecolor_pop').hide();
		});
	});
	//链接
	//var linkPop = $('#_link_pop ');
}
Pn.Editor.closeMenu = function(p) {
	for(var i=0;i<Pn.Editor.popup.length;i++) {
		if(p.attr('id')!=Pn.Editor.popup[i].attr('id')) {
			Pn.Editor.popup[i].hide();
		}
	}
}
Pn.Editor.emoticon = function(text) {
   text = '' + text;
   Pn.Editor.bbstyle(text,'');
}
Pn.Editor.insertUrl = function(url,label,type) {
	if(!url || url=='') {
		return;
	}
	label = label || url;
	Pn.Editor.bbstyle('['+type+'='+url+']','[/'+type+']',label);
}
Pn.Editor.bbstyle = function(bbopen,bbclose,m) {
	var txtarea = Pn.Editor.txtarea;
	if (document.selection){
		//IE
		txtarea.focus();
		sel = document.selection.createRange();
		var txt = sel.text;
		if(m) {
			txt = m+txt;
			txt = txt.replace(/\n/g,'\n'+m);
		}
		sel.text = bbopen+txt+bbclose;
		txtarea.focus();
//		sel.moveStart('character', -txt.length-bbclose.length);
//		sel.moveEnd('character', -bbclose.length);
//		sel.select();
	}else if (txtarea.selectionStart || txtarea.selectionStart == '0') {
		//Mozilla-Netscape
		var startPos = txtarea.selectionStart;
		var endPos = txtarea.selectionEnd;
		var cursorPos = endPos;
		var scrollTop = txtarea.scrollTop;
		if (startPos != endPos) {
			var txt = txtarea.value.substring(startPos, endPos);
			if(m) {
				txt = m+txt;
				txt = txt.replace(/\n/g,'\n'+m);
			}
			txtarea.value = txtarea.value.substring(0, startPos)
				+ bbopen
				+ txt
				+ bbclose
				+ txtarea.value.substring(endPos, txtarea.value.length);
			cursorPos += bbopen.length + bbclose.length;
		}else {
			txtarea.value = txtarea.value.substring(0, startPos)
				+ bbopen+''+bbclose
				+ txtarea.value.substring(endPos, txtarea.value.length);
			cursorPos = startPos + bbopen.length+bbclose.length+1;
		}
		txtarea.focus();
		txtarea.selectionStart = cursorPos;
		txtarea.selectionEnd = cursorPos;
		txtarea.scrollTop = scrollTop;
	}else {
		txtarea.value += bbopen+''+bbclose;
		txtarea.focus();
	}
}
Pn.Editor.localIndex = 1;
Pn.Editor.selectFile = function() {
	var f = Pn.Editor.file;
	//检查是否选择了文件
	var name = f.val();
	if(name=='') {
		return;
	}
	var container = Pn.Editor.fileContainer;
	container.append(f);
	//复制一个file放至原处
	Pn.Editor.file=f.clone();
	Pn.Editor.fileSelector.append(Pn.Editor.file);
	//修改属性
	var index = Pn.Editor.localIndex++;
	f.attr('id','');
	f.attr('name','attachment');
	f.css('display','none');
	name = name.replace(/\\/g,'/');
	var filename = name.substring(name.lastIndexOf('/')+1,name.length);
	var url = "<div>";
	url += "[<a href='javascript:void(0)' onclick='$(this).parent().remove();'>删除</a>]";
	url += "[<a id='insert' href='javascript:void(0)' onclick='Pn.Editor.emoticon(\"[localimg]"+index+"[/localimg]\")'>插入</a>]";
	url += "["+index+"]";
	url += "<a href='javascript:void(0)' onclick='Pn.Editor.emoticon(\"[localimg]"+index+"[/localimg]\")' onmouseover='Pn.Editor.showImage(this);' onmouseout='$(this).next(\"div\").hide()'>"+filename+"</a>";
	url += "<div style='display:none;position:absolute;' class='_editor_local_img'><img src='file:///"+name+"'/><input name='code' type='hidden' value='"+index+"'/></div>";
	url += "</div>";
	container.append(url);
}
Pn.Editor.imageSize=300;
Pn.Editor.showImage = function(o) {
	var j = $(o);
	var imgc = j.next().show();
	if(!imgc.attr('position')) {
		var img = imgc.children('img');
		var h = img.height();
		var w = img.width();
		var size = Pn.Editor.imageSize;
		var max = h > w ? h : w;
		if(max>size) {
			var zoom = size/max;
			var hmin = h*zoom;
			var wmin = w*zoom;
			img.attr('width',wmin);
			img.attr('height',hmin);
		}
		var offset = j.offset();
		var top = offset.top - imgc.outerHeight() - 2;
		imgc.css('top',top);
		imgc.css('left',offset.left);
		imgc.attr('position','ok');
	}
}