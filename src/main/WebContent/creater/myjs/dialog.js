// JavaScript Document
var Dialog = function(){
	var options = arguments[0] || {};
    this.title = options.title || "新窗口",
    this.width = options.width || 400,
    this.height = options.height || 300,
    this.container = document.createElement("div"),
    this.id = "id" + Math.abs(new Date() * Math.random()).toFixed(0);
    this.init();
};
Dialog.prototype = {
    constructor: Dialog,
    init: function() {
		var me = this, container = me.container, width = me.width, height = me.height,
      	id = me.id,builder = [],t = "getElementsByTagName",
      	bg = function(pic){
        	var bgcolor = arguments[1] || 'transparent',
        	left = arguments[2] || 'left',
        	s =  'background:'+bgcolor + ' url(images/' +  pic + ') no-repeat '+left+' center;';
        	return s;
     	};
		if(typeof Dialog.z === "undefined"){
        	Dialog.zIndex = 999;
		}
		document.body.insertBefore(container,null);
		container.id = id;
		container.className = "popups";
		builder.push('<div class="caption">'+me.title+'</div>');
		builder.push('<form>');
		builder.push('<div class="replaceable"></div>');
		//builder.push('<div class="submitable">');
		//builder.push('<a class="negative" href="javascript:void(0)">取消</a>');
		//builder.push('<a class="positive" href="javascript:void(0)">确认</a>');
		//builder.push('</div>');
		builder.push('</form>');
		builder.push('<a class="closebtn" href="javascript:void(0)"></a>');
		container.innerHTML = builder.join('');
		var size  = me.getBrowserWindowSize();
		me.left = ((size.width - width)/2) >> 0;
		me.top = ((size.height - height)/2) >> 0;
		me.ie6 = /msie|MSIE 6/.test(navigator.userAgent);
	
		var divs = container[t]("div"),k = divs.length;
		while (--k >= 0) {
			if(divs[k].className == "replaceable"){
				me.content = divs[k];
				break;
			}
		}

      	//设置样式
    	me.css(".popups","position:absolute;width:"+width+"px;height:"+
        	height+"px;left:"+me.left+"px;top:"+me.top+"px;");//background:#4C7DCF/#68DFFB
    	container.style.zIndex = Dialog.zIndex++;
    	me.css(".popups .caption",'position:absolute;top:10px;left:10px;width:'+(width-50)+'px;height:20px;'+
        	'padding-left:30px;font:700 14px/20px "SimSun","Times New Roman";color: #fff;'+
       	 	bg("o_icon.gif","#4C7DCF","5px"));
   		//me.css(".popups .closebtn",'position:absolute;top:0;right:10px;display:block;width:28px; '+
        //	'height:17px;text-decoration:none;'+ bg("o_dialog_closebtn.gif"));
		me.css(".popups .closebtn",'position:absolute;top:0;right:10px;display:block;width:28px; '+
        	'height:35px;text-decoration:none;'+ bg("o_dialog_closebtn.gif"));
		me.css(".popups a.closebtn:hover",bg("o_dialog_closebtn_over.gif"));
		me.css(".popups form","position:absolute;top:30px;left:10px;border:3px solid #4C7DCF;width:"+(width-26)+"px;height:"+(height-51)+"px;background:#fff;");
		me.css(".popups .submitable","position:absolute;bottom:0;border-top:1px solid #c0c0c0;width:100%;height:40px;background:#f9f9f9;");
		var buttoncss = 'display:block;float:right;margin: 0.7em 0.5em;padding:2px 7px;border:1px solid #dedede;'
        + 'background:#f5f5f5;color:#a9ea00;font:700 12px/130%  "SimSun","Times New Roman";text-decoration:none;';
		me.css("a.positive",buttoncss);//IE6有bug，不能动态创建联合选择器
		me.css("a.negative",buttoncss);
		me.css("a.negative","color:#ff5e00;");
		me.css("a.positive:hover","border:1px solid #E6EFC2;background:#E6EFC2;color:#529214;");
		me.css("a.negative:hover","border:1px solid #fbe3e4;background:#fbe3e4;color:#d12f19;");
		me.css("a.positive:active","border:1px solid #529214;background:#529214;color:#fff;");
		me.css("a.negative:active","border:1px solid #d12f19;background:#d12f19;color:#fff;");
		me.css("a","outline: 0;");
    	//按钮的圆角
		var ff = /a/[-1]=='a';
		if(ff){
			me.css("a.positive","-moz-border-radius:4px;");
        	me.css("a.negative","-moz-border-radius:4px;");
		}else{
        	me.css("a.positive","-webkit-border-radius:4px;");
        	me.css("a.negative","-webkit-border-radius:4px;");
		}
    	//***************************

    	if (!+"\v1" ){
			if(!document.namespaces.vml){
				document.namespaces.add('vml', 'urn:schemas-microsoft-com:vml');
         		var vmlobj = document.createElement("<object classid=CLSID:10072CEC-8CC1-11D1-986E-00A0C955B42E id=VMLRender>"),
          		head = document.getElementsByTagName("head")[0];
          		head.appendChild(vmlobj);
          		document.createStyleSheet().addRule(".vml", "behavior: url(#VMLRender); display:inline-block;");
        	}
        	var rect = document.createElement('<vml:roundrect class="vml">');
        	container.insertBefore(rect,container.firstChild);
        	rect.style.cssText = "position:absolute;top:0px;left:0px;width:"+width+"px;height:"+height+"px;";
        	me.attr(rect,{arcsize:5 /Math.min(width, height),stroked:"f"});
        	rect.innerHTML = '<vml:fill class="vml" opacity="0.8" color="#4C7DCF" />' +
          		'<vml:shadow class="vml" on="t" color="#333" opacity="0.2"  offset="10px,10px" />';
		}
		else{
        	var svg = me.createSVG("svg");
        	container.insertBefore(svg,container.firstChild);
        	me.attr(svg,{width:me.width+10+"px",height:me.height+10+"px"});

        	var defs = me.createSVG("defs");
        	svg.appendChild(defs);

        	var filter = me.createSVG("filter");
        	defs.appendChild(filter);
        	me.attr(filter,{id:"filter"+id});

        	var feGaussianBlur = me.createSVG("feGaussianBlur");
        	filter.appendChild(feGaussianBlur)
        	me.attr(feGaussianBlur,{"in":"SourceAlpha",result:"blur-out",stdDeviation:1.5});

        	var feOffset = me.createSVG("feOffset");
        	filter.appendChild(feOffset)
        	me.attr(feOffset,{"in":"blur-out",result:"the-shadow",dx:0,dy:2});

        	var feBlend = me.createSVG("feBlend");
        	filter.appendChild(feBlend)
        	me.attr(feBlend,{"in":"SourceGraphic","in2":"the-shadow",mode:"normal"});

        	var shadow = me.createSVG("rect");
        	svg.appendChild(shadow);
        	me.attr(shadow,{x:"10px",y:"10px",width:me.width+"px",height:me.height+"px",rx:10,
          		fill:"#333",style:"opacity:0.2",filter:"url(#filter"+id+")"});

        	var rect = me.createSVG("rect");
        	svg.appendChild(rect);
        	me.attr(rect,{width:me.width+"px",height:me.height+"px",rx:5,fill:"#4C7DCF",style:"opacity:0.8"});
		}
    	//***************************IE6 弹出窗口中遮不住select******************************
		if(me.ie6){
        	me.iframe = document.createElement("<iframe style='position:absolute;left:"+
          		me.left+"px;top:"+me.top+"px;width:"+(me.width+10)+"px;height:"+
          		(me.height+10)+"px;z-index:"+(Dialog.zIndex-2)+";filter:mask();display:none;' ></iframe>");
        	container.insertAdjacentElement('beforeBegin',me.iframe);
		}

    	//*****************************监听点击**************************
    	container.onclick = function(){
			var ee = me.getEvent(), node = ee[1],tag = ee[2];
        	if(tag == "a" ){
				switch(node.className){
            	case "closebtn" :
             	 	me.hide();
              		break;
            	case "positive" :
              		me.hide();
              		//form.submit();
              		break;
            	case "negative" :
              		alert("你点击了取消按钮!");
              		break;
				}
			}
		}
		container.onmousedown = function(e){
			e = e || window.event;
        	container.offset_x = e.clientX - container.offsetLeft;
        	container.offset_y = e.clientY - container.offsetTop;
        	document.onmousemove = function(e){
          		me.drag(e,me)
        	}
        	document.onmouseup = function(){
          		me.dragend(container)
        	}
		}
	},
    drag:function(e,me){
		e = e || window.event;//获得事件对象
      	var el = me.container;
      	var l = e.clientX - el.offset_x  + "px",
      	t = e.clientY - el.offset_y  + "px";
      	with(el.style){
        	left=l;
        	top=t;
        	cursor="move"
      	}
      	if(me.ie6){
        	with(me.iframe.style){
          		left=l;
          		top=t;
        	}
		}
        !+"\v1"? document.selection.empty() : window.getSelection().removeAllRanges();
    },
    dragend:function(el){
		el.style.cursor = "";
      	document.onmouseup = document.onmousemove = null;
    },
    hide : function(){
      	this.container.style.display = "none" ;
      	if(this.ie6){
		  	this.iframe.style.display = "none";
      	}
      	this.mode(0,0);
      	//下面两行目的是生成 html,body{width:auto;height:auto;overflow:auto;}
      	this.incss(document.body, {width:"auto",height:"auto",overflow:"auto"});
      	this.incss(document.documentElement, {width:"auto",height:"auto",overflow:"auto"});
    },
    show : function(){
		this.container.style.display = "block" ;
      	if(this.ie6){
		  	this.iframe.style.display = "block";
      	}
      	var size = this.getBrowserWindowSize();
      	this.mode(size.width, size.height);
    },
	getBrowserWindowSize :function(){
      	var de = document.documentElement;
      	return {
        	width: (de.clientWidth || document.body.clientWidth),
        	height:(de.clientHeight || document.body.clientHeight)
      	}
    },
    createSVG : function(tag){
      	return document.createElementNS("http://www.w3.org/2000/svg",tag);
    },
    attr: function(node,bag){
      	for(var i in bag){
        if(bag.hasOwnProperty(i))
          	node.setAttribute(i,bag[i])
      	}
    },
    getEvent : function(e) {
      	e = e || window.event;
      	if (!e) {
        	var c = this.getEvent.caller;
        	while (c) {
          		e = c.arguments[0];
          		if (e && (Event == e.constructor || MouseEvent  == e.constructor)) {
            		break;
          		}
          		c = c.caller;
        	}
      	}
      	var target = e.srcElement ? e.srcElement : e.target,
      	currentN = target.nodeName.toLowerCase(),
      	parentN  = target.parentNode.nodeName.toLowerCase(),
      	grandN = target.parentNode.parentNode.nodeName.toLowerCase();
      	return [e,target,currentN,parentN,grandN];
    },
    mode:function(w,h){
      	var mask = Dialog.mask,me = this;
      	this.incss(document.body, {width:"100%",height:"100%",overflow:"hidden"});
      	this.incss(document.documentElement, {width:"100%",height:"100%",overflow:"hidden"});
      	this.incss(mask,{position:"absolute",background:"#fff",top:0,left:0,
        	width:w +"px",height:h +"px","-moz-user-select":"none"});
        	!+"\v1"? (mask.style.filter = "alpha(opacity=0)") : (mask.style.opacity = "0");
      	mask.onselectstart = function(e){
        	me.stopEvent(e);
      	}
      	mask.oncontextmenu = function(e){
        	me.stopEvent(e);
      	}
    },
    stopEvent:function(e){
      	e = e || window.event;
      	if(e.preventDefault) {
        	e.preventDefault();
        	e.stopPropagation();
      	}else{
        	e.returnValue = false;
        	e.cancelBubble = true;
      	}
    },
    incss:function(node,bag){
      	var str = ";"
      	for(var i in bag){
        	if(bag.hasOwnProperty(i))
          		str += i+":"+bag[i]+";"
      	}
      	node.style.cssText = str;
    },
    css:function(selector,declaration){
      	if(typeof document.createStyleSheet === 'undefined') {
        	document.createStyleSheet = (function() {
          		function createStyleSheet() {
            		var element = document.createElement('style');
            		element.type = 'text/css';
            		document.getElementsByTagName('head')[0].appendChild(element);
            		var sheet = document.styleSheets[document.styleSheets.length - 1];
            		if(typeof sheet.addRule === 'undefined')
              			sheet.addRule = function(selectorText, cssText, index) {
                			if(typeof index === 'undefined')
                  			index = this.cssRules.length;
                			this.insertRule(selectorText + ' {' + cssText + '}', index);
              			};
            		return sheet;
          		}
          		return createStyleSheet;
        	})();
      	}
      	if(!!Dialog.sheet){
        	if(!Dialog.memory.exists(selector,declaration)){
          		Dialog.memory.set(selector,declaration);
          		Dialog.sheet.addRule(selector,declaration);
        	}
      	}
		else{
        	Dialog.sheet = document.createStyleSheet();
        	var memory = function(){
          		var keys = [],values = [],size = 0;
          		return {
            		get : function(k){
              			var results = [];
              			for(var i=0,l=keys.length;i<l;i++){
                			if(keys[i] == k){
                  				results.push(values[i])
                			}
              			}
              			return results;
            		},
            		exists:function(k,v){
              			var vs = this.get(k);
              			for(var i=0,l=vs.length;i<l;i++){
                			if(vs[i] == v)
                  				return true;
              			}
              			return false;
            		},
            		set : function(k,v){
              			keys.push(k);
              			values.push(v);
              			size++;
            		},
            		length :function(){
              			return size;
            		}
          		}
        	}
        	Dialog.memory = memory();
			Dialog.memory.set(selector,declaration);
        	Dialog.sheet.addRule(selector,declaration);
        	Dialog.mask = document.createElement("div");
        	document.body.insertBefore(Dialog.mask,this.container);
      	}
	}
};