function GetXMLHttp(){
    var xmlhttp=false;
    try{
        xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    }catch (e){
        try{
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }catch(E){
            xmlhttp = false;
        }
    }
    if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
       xmlhttp = new XMLHttpRequest();
    }
    
    return xmlhttp;
}
function Do(v){ 
	var xmlhttp = GetXMLHttp(),SiteUrl=null,url="",linfo;
	if(v==0){
		      url="../chklogin.php";
		     SiteUrl="t=0&user="+$("user").value+"&password="+$("password").value+"&d="+$("timeout").value;
			 linfo=$("logininfo").innerHTML;
			 $("logininfo").innerHTML="正在载入登陆信息，请稍后。。。";
	}else if(v==1){
		     url="../chklogin.php";SiteUrl="t=1";
			 linfo=$("logininfo").innerHTML;
			 $("logininfo").innerHTML="正在载入登陆信息，请稍后。。。";
	}else if(v==2){
		if(arguments.length==1){
		     var e = document.getElementsByName("selectzx"),listid="",t2=document.getElementsByName("zxtype");
		     for (var i = 0; i < e.length; i++) {
		          if (e[i].checked ==true){
			               listid+=","+e[i].value;
		          }
	         }
			 
			 for (var ii=0; ii<t2.length;ii++) {
			 	if (t2[ii].checked == true)
				t1 = t2[ii].value;
			 }
			 
		     if(listid==""){alert("您还没选择要删除的自选股！");return false;}
		     else{listid=listid.substr(1);
			 }
			 SiteUrl="t=0&code="+listid+"&t1="+t1;
			 var doArguments = true;
		}else if(arguments.length==3)
		{
			SiteUrl="t=0&code="+arguments[2]+"&t1="+arguments[1];
			if(!confirm("确定要删除所选的股票？")){return;}
			var doArguments = false;
		}
		url="../del.php";
	}else if(v==3){
		var t1,t2 = document.getElementsByName("zxtype");
		for (var i=0;i<t2.length;i++)
		{
			if (t2[i].checked)
				t1 = t2[i].value;
		}
		var doArguments = true;
		url="../del.php";SiteUrl="t=1&t1="+t1;
		if(!confirm("确定要删除该自选股中的所有股票?")){return;}
	}else if(v==4){
		
		url="../import.php";
		if(arguments.length==1)
		{
			var boxArray = document.getElementsByName('zxtype');
			var tv;
			for (var i=0; i<boxArray.length;i++)
			{
				if (boxArray[i].checked)
				{
					tv = boxArray[i].value;
				}
			}
			tv = Number(tv)+1;
			if (!tv){alert('请选择自选股！');return false;}
			SiteUrl="t="+tv+"&code="+$("stockcode").value;
			var doArguments = true;
		}
		if(arguments.length==3){SiteUrl="t="+arguments[1]+"&code="+arguments[2]; var doArguments = false;}
	}else if(v==5){
		url="../chkuser.php";SiteUrl="t=0&user="+document.getElementById("username").value;
	}else if(v==6){
		url="../chkuser.php";SiteUrl="t=1&user="+document.getElementById("username").value+"&password="+document.getElementById("pwd").value;
	}
	xmlhttp.open("POST", url, true);
    xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xmlhttp.send(SiteUrl);
	xmlhttp.onreadystatechange=function(){
        if (xmlhttp.readyState==4){
		  //$("key").alt=xmlhttp.responseText;
		     if(v==1){
				   eval("var uinfo="+xmlhttp.responseText+";");
				   if(uinfo.status==1){
					        $("logininfo").innerHTML=uinfo.user+",欢迎您使用查股网数据: [<a href='../zixuan.php'>自选股系统</a>] [<a href='../xg/ddx.html'>DDX选股系统</a>] [<a href='../xg/xuangu.html'>DDX综合选股</a>] [<a href='../xg/tongji.html'>DDX统计系统</a>] [<a href='../xg/chaoying.html'>超赢持仓选股</a>] [<a href='../xg/jiben.html'>基本面选股</a>] [<a href='../ths/ddexuangu.html'>同花顺DDE选股</a>] [<a href='../ths/ccxuangu.html'>拓赢选股系统</a>] [<a href='../logout.php'>退出</a>]";
				   }else{
					        $("logininfo").innerHTML=linfo;
				   }
			 }else if(v==0){
				   eval("var uinfo="+xmlhttp.responseText+";");
				   if(uinfo.status!=1){
					        if(uinfo.err==0)alert("您输入的用户名或密码有误，请重新输入");
							else if(uinfo.err==2)alert("您输入的验证码有误，请重新输入");
							$("logininfo").innerHTML=linfo;
				   }else{		        
					        $("logininfo").innerHTML=uinfo.user+",欢迎您使用查股网数据: [<a href='../zixuan.php'>自选股系统</a>] [<a href='../xg/ddx.html'>DDX选股系统</a>] [<a href='../xg/xuangu.html'>DDX综合选股</a>] [<a href='../xg/tongji.html'>DDX统计系统</a>] [<a href='../xg/chaoying.html'>超赢持仓选股</a>] [<a href='../xg/jiben.html'>基本面选股</a>] [<a href='../ths/ddexuangu.html'>同花顺DDE选股</a>] [<a href='../ths/ccxuangu.html'>拓赢选股系统</a>] [<a href='../logout.php'>退出</a>]";
							location.reload();
				   }
			 }else if(v==2 || v==3){
				 eval("var uinfo="+xmlhttp.responseText+";");
				 //alert('../getzxdata.php?t='+t1+'&page=1&m=0&n=-1&order=-1');
				 //alert(uinfo.status);
				   
				   var tbox=0,arrbox = document.getElementsByName("zxtype");
	for (var i =0; i<arrbox.length;i++ )
	{
		if (arrbox[i].checked)
		tbox=Number(arrbox[i].value)+1;
	}
				   
				   if(uinfo.status==1){
					   		if(doArguments)
							include_js('../getzxdata.php?t='+tbox+'&page=1&m=0&n=-1&order=-1');
				   }else{
					   		//alert(xmlhttp.responseText);
					        if(uinfo.err==0){
								alert("无法删除，您还没登陆！");
							}else if(uinfo.err==1){
								alert("无法删除，请查看相关设置！");
							}
				   }
			 }else if(v==4){
				   eval("var uinfo="+xmlhttp.responseText+";");
				   if(uinfo.status==1){
					   if(doArguments)
					   include_js('../getzxdata.php?t='+tv+'&page=1&m=0&n=-1&order=-1');
				   }else{
					        if(uinfo.err==0){
								alert("您还没登陆或登陆已经过期，请重新登陆！");
							}else if(uinfo.err==1){
								alert("导入的股票格式有误或股票代码有误！");
							}else if(uinfo.err==2){
								alert("导入的股票已经存在！");
							}else if(uinfo.err==3){
								alert("温馨提示:该组自选股已经达到300个!请删除部分自选股并重新导入!");
							}
				   }
			 }else if(v==5){
				   eval("var uinfo="+xmlhttp.responseText+";");
				   if(uinfo.status==1){
					        document.getElementById("check1").innerHTML="<font color='green'>该用户名可以使用</font>";
				   }else{
					        document.getElementById("check1").innerHTML="<font color='red'>该用户名已经存在</font>";
				   }
			 }else if(v==6){
				   eval("var uinfo="+xmlhttp.responseText+";");
				   if(uinfo.status==1){
					     document.getElementById("showlogin").style.display="block";
						 document.getElementById("reginfo").style.display="none";
						 document.getElementById("uname").innerHTML=uinfo.user;
			       }else{
					     if(uinfo.err==0){
							 alert("用户名不符合！！");
						 }else if(uinfo.err==1){
							 alert("验证码错误！！");
						 }else if(uinfo.err==2){
							 alert("密码长度不符合！！");
						 }else if(uinfo.err==3){
							 alert("该用户名已经存在，请换个用户名！！");
						 }
				   }
			 }
        }
    }
}
String.prototype.trim = function(){
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
function Login(){
	if($("user").value.trim()==""){alert("您输入的用户名为空，请重新输入");return;}
	else if($("password").value.trim()==""){alert("您输入的密码为空，请重新输入");return;}
	Do(0);
}
function Chklogin(){Do(1);}
function Chkimg(){
	var img="../img.php?"+Math.random();
	$("chkimg").width=60;
	$("chkimg").height=16;
	$("chkimg").src=img;
}
function select_all(t) {
	var e = document.getElementsByName("selectzx");
	for (var i = 0; i < e.length; i++) {
		if (t==0){
			e[i].checked =true;
		}else if(t==1){
		    e[i].checked =false;
		}
	}
}
function export1(_this){
	if(_this.value=="导出自选股1"){
        window.open("../export.php?t=1","_blank");
	}else if(_this.value=="导出自选股2"){
        window.open("../export.php?t=2","_blank");
	}else if(_this.value=="导出自选股3"){
        window.open("../export.php?t=3","_blank");
	}else if(_this.value=="导出自选股4") {
        window.open("../export.php?t=4","_blank");
    }else if(_this.value.indexOf('集')>1){

        if (_this.value.indexOf('交')>1)
        {
            zxjbac = 'j';
        }
        if (_this.value.indexOf('并')>1)
        {
            zxjbac = 'b';
        }
        if (zxjbac == 'j' || zxjbac == 'b')
        {
            var zxjbtype = getzxjbtype();
            zxjbtype = zxjbtype.join("-");
            window.open("../exportjb.php?zxjbtype="+zxjbtype+'&ac='+zxjbac,"_blank");
        }
    }
}

function export2(){
	var e = document.getElementsByName("selectzx"),listid="";
		     for (var i = 0; i < e.length; i++) {
		          if (e[i].checked ==true){
			               listid+=","+e[i].value;
		          }
	         }
			 
		     if(listid==""){alert("您还没选择要导出的自选股！");return false;}
		     else{listid=listid.substr(1);
			 document.getElementById("listid").value=listid;
			 document.forms.exportList.submit();
			 }
			 return true;
			 
}


function Addzx(_this,t,code){
	if(_this.checked==true){Do(4,t+1,code);}
	else{Do(2,t,code);}
}
function Cstype1(obj){
	
	obj.checked = true;
	var t,v;
	var boxArray = document.getElementsByName('zxtype');
	for (var i=0; i<boxArray.length;i++)
	{
		if 	(boxArray[i]==obj && boxArray[i].checked)
		{
			v = boxArray[i].value;
		}else
		   boxArray[i].checked = false;
	}
	
	
	if(v==0){
		 $("export").value="导出自选股1";
		 $("zxname").innerHTML="我的自选股1";
         t=1;
	}else if(v==1){
		 $("export").value="导出自选股2";
		 $("zxname").innerHTML="我的自选股2";
		 t=2;
	}else if(v==2){
		 $("export").value="导出自选股3";
		 $("zxname").innerHTML="我的自选股3";
		 t=3;		 
	}else if(v==3){
		 $("export").value="导出自选股4";
		 $("zxname").innerHTML="我的自选股4";
		 t=4;
	}
	$("gotoPageList").innerHTML = "";
	include_js('../getzxdata.php?t='+t+'&page=1&m=0&n=-1&order=-1');
}
function CheckUserName(_this){
	var errmsg="";
	if(_this.value==""){
		errmsg="用户名不能为空";
	}else{
			 if(_this.value.length<6 || _this.value.length>20){
				   errmsg="用户名必须在6~20个字符之间";
			 }else{
				   if(_this.value.match(/^([a-z0-9\_]{1,})$/i)){
					      Do(5);
				   }else{
					      errmsg="用户名必须由字母、数字和下划线组成。";
				   }
			 }
	}
	if(errmsg!=""){
		     document.getElementById("check1").innerHTML="<font color='red'>"+errmsg+"</font>";
			 return false;
	}
	return true;
}
function CheckPassWord(_this,t){
	var errmsg="";
	if(t==1){
		if(_this.value==""){
			  errmsg="此项不能为空";
		}else{
			 if(_this.value.length<6 || _this.value.length>32){
			       errmsg="密码必须在6~32个字符之间";
			 }else{
				   var psd=document.getElementById("pwd").value;
				   if(psd!=_this.value || psd.length!=_this.value.length)errmsg="两次密码不一样！"; 
			 }
		}
		if(errmsg!=""){
			 document.getElementById("password3").innerHTML="<font color='red'>"+errmsg+"</font>";
			 return false;
		}else{
			 document.getElementById("password3").innerHTML="<font color='green'>密码正确</font>";
			 return true;
		}
	}else{
		if(_this.value==""){
			       errmsg="此项不能为空";
		}else if(_this.value.length<6 || _this.value.length>32){
			       errmsg="密码必须在6~20个字符之间";
		}
		if(errmsg!=""){
			 document.getElementById("password2").innerHTML="<font color='red'>"+errmsg+"</font>";
			 return false;
		}else{
			 document.getElementById("password2").innerHTML="<font color='green'>密码正确</font>";
			 return true;
		}
	}
}
function Reg(){
	var user,password,password1,img;
	     user=CheckUserName(document.getElementById("username"));
	     password=CheckPassWord(document.getElementById("pwd"),0);
	     password1=CheckPassWord(document.getElementById("pwd1"),1);
	     if(user && password && password1)Do(6);
	     else{alert("帐号或密码不正确！");}
}