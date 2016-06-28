function $(id){return document.getElementById(id);}
var SelectedId="";
var ordered=1;
var ordernum=0;
var titleNumber=new Array(0,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27);
var titleText  =new Array("股票代码","最新价","涨幅","换手率","量比","DDX","DDY","DDZ","10日","连续","成交量(万元)","BBD(万元)","单数比","买入","卖出","特大差","大单差","中单差","小单差","买入","卖出","买入","卖出","买入","卖出","5日","连增");
var sn=new Array(1,3,5,10,60);
var sl=new Array(4,5,5);
var sid=new Array("myddx0","myddx1","myddx2");
var DataHtmlHead='<table>';
var DataTableLeft='<table width="320" border="1" style="border:1px solid #CBD6E8;"><tr bgcolor="#F7FCFD"><td>序号</td><td id="SortDDE0" align="center">股票代码</td><td align="center">名称|自选股|查股网数据</td></tr>';
var DataTableRight='<table width="1300" border="1" style="border:1px solid #CBD6E8;margin-left:-1px;margin-top:-3px;"><tr bgcolor="#F7FCFD"><td align="center" id="SortDDE2">最新价</td><td align="center" id="SortDDE3">涨幅</td><td align="center" id="SortDDE4">换手率</td><td align="center" id="SortDDE5">量比</td><td align="center" id="SortDDE6">DDX</td><td align="center" id="SortDDE7">DDY</td><td align="center" id="SortDDE8">DDZ</td><td align="center" id="SortDDE26">5日</td><td align="center" id="SortDDE9">10日</td><td align="center" id="SortDDE10">连续</td><td align="center" id="SortDDE27">连增</td><td align="center" id="SortDDE11">成交量(万元)</td><td align="center" id="SortDDE12">BBD(万元)</td><td align="center" id="SortDDE13">单数比</td><td align="center" id="SortDDE14">买入</td><td align="center" id="SortDDE15">卖出</td><td align="center" id="SortDDE16">特大差</td><td align="center" id="SortDDE17">大单差</td><td align="center" id="SortDDE18">中单差</td><td align="center" id="SortDDE19">小单差</td><td align="center" id="SortDDE20">买入</td><td align="center" id="SortDDE21">卖出</td><td align="center" id="SortDDE22">买入</td><td align="center" id="SortDDE23">卖出</td><td align="center" id="SortDDE24">买入</td><td align="center" id="SortDDE25">卖出</td></tr>';
function GetSelectInfo(n,arrnum){
    var str="",nn,day;
	if(n==3){nn=0;}
	else if(n==6){nn=1;}
	else {nn=2;}
	str="<a href='javascript:getsort("+ordered+","+n+","+arrnum+");'>"+titleText[n-1]+"</a><select id='myddx"+nn+"' onchange='Schange(this,"+arrnum+")' style='height:17px;width:43px;'>";
	for(var i=0;i<sl[nn];i++){
		     day=sn[i]>=10?"":"日";
			 if(i==arrnum[nn])str+="<option value='"+i+"' selected>"+sn[i]+day+"</option>";
			 else  str+="<option value='"+i+"'>"+sn[i]+day+"</option>";
	}
	str+="</select>";
	return str;
}
function getsort(s,id,arrnum){
	ordered=s==1?-1:1;
	arrnum=new Array(arrnum,arguments[3],arguments[4]);
	if(id==3)SelectedId=sid[0];
	else if(id==6)SelectedId=sid[1];
	else SelectedId=sid[2];
	ShowPageContent(id,ordered,1,arrnum);
}
function Schange(_this,arrnum){
	 SelectedId=_this.id;
	 arrnum=new Array(arrnum,arguments[2],arguments[3]);
	 var n=0;
	 for(var i=0;i<sid.length;i++){
		 if(SelectedId==sid[i]){
			  if(i==0){n=3;arrnum[0]=_this.value;}
	          else if(i==1){n=6;arrnum[1]=_this.value;}
	          else{n=7;arrnum[2]=_this.value;}
			  break;
		 }
	 }
	 ShowPageContent(n,ordered,1,arrnum);
}
function GetSvalue(){
	 var myddx0,myddx1,myddx2;
	 if($("myddx0")==null){return Array(0,0,0);  }
	 myddx0=$("myddx0").value;
	 myddx1=$("myddx1").value;
	 myddx2=$("myddx2").value;
	 return Array(myddx0,myddx1,myddx2);
}
function changeBKColor(nid) {
	var s_l=$("sid_l_"+nid);
	var s_r=$("sid_r_"+nid);
	s_l.bgColor==""?s_l.bgColor="#EFEFEF":s_l.bgColor="";
	s_r.bgColor==""?s_r.bgColor="#EFEFEF":s_r.bgColor="";
}
function ForNum(srcStr,nAfterDot){
    var srcStr,nAfterDot,i,j,dotPos,strLen;
    var resultStr,nTen;
    srcStr = ""+srcStr+"";
    strLen = srcStr.length;
    dotPos = srcStr.indexOf(".",0);
    if (dotPos == -1){
        resultStr = srcStr+".";
        for (i=0;i<nAfterDot;i++){
            resultStr = resultStr+"0";
        }
        return resultStr;
    } else{
        if ((strLen - dotPos - 1) >= nAfterDot){
            nAfter = dotPos + nAfterDot + 1;
            nTen =1;
            for(j=0;j<nAfterDot;j++){
            nTen = nTen*10;
        }
        resultStr = Math.round(parseFloat(srcStr)*nTen)/nTen;
        return resultStr;
        } else{
            resultStr = srcStr;
            for (i=0;i<(nAfterDot - strLen + dotPos + 1);i++){
                resultStr = resultStr+"0";
            }
            return resultStr;
        }
    }
}
function include_js(file) {
	$("loding").innerHTML = "<font color = red>读取中,请稍侯...</font>";
	if($("reloaddata")==null){
	}else{
		document.getElementsByTagName('head')[0].removeChild($("reloaddata"));
	}
	var _doc = document.getElementsByTagName('head')[0];
    var js = document.createElement('script');
    js.setAttribute('type', 'text/javascript');
    js.setAttribute('src', file);
	js.setAttribute('id',"reloaddata");
    _doc.appendChild(js);
	var hasarg;
    if(typeof arguments[1]!="undefined"){hasarg=true;var args=new Array(arguments[1],arguments[2],arguments[3]);}
	else hasarg=false;
    if (!/*@cc_on!@*/0) { //if not IE
        //Firefox2、Firefox3、Safari3.1+、Opera9.6+ support js.onload
        js.onload = function () {
            //alert('Firefox2、Firefox3、Safari3.1+、Opera9.6+ support js.onload');
			    if(!hasarg)LoadData();
				else LoadData(args[0],args[1],args[2]);
        }
    } else {
        //IE6、IE7 support js.onreadystatechange
        js.onreadystatechange = function () {
            if (js.readyState == 'loaded' || js.readyState == 'complete') {
			    if(!hasarg)LoadData();
				else LoadData(args[0],args[1],args[2]);
            }
        }
    }
	
    return false;
}
function LoadData(){$("ddx_update").innerHTML = ddx_update;if(typeof arguments[0]=="undefined"){sortByNumber(0,-1,1);}else{sortByNumber(arguments[0],arguments[1],arguments[2]);}	$("loding").innerHTML = "";}
function sortByNumber(n,s,p)
{
	var n1=0,n2=new Array(0,0,0);
	if(n==3 || n==6 || n==7){
		n2=GetSvalue();
		if(n==3)n1=n2[0];
		else if(n==6)n1=n2[1];
		else if(n==7)n1=n2[2];
	}else{
		SelectedId="";
		n2=GetSvalue();
	}
	myArray.sort(function sortFun(x, y){
			if(n!=3 && n!=6 && n!=7)return s*(parseFloat(x[n])-parseFloat(y[n]));
			else{
				return s*(parseFloat(x[n][n1])-parseFloat(y[n][n1]));
			}
		}
	);
	var gotoPageList=$("gotoPageList");
	var DataContent=$("DataContent");
	var content="",pageLabel="",i,j,count=0,psize=30,pagecount=0,dleft="",dright="";
	var arrLength=myArray.length;
	var startPageNum=(p-1)*psize;
	if(arrLength==0){DataContent.innerHTML="<font color=red>没有数据</font>";return;}
	if(arrLength%psize==0)pagecount=arrLength/psize;else pagecount=Math.round((arrLength/psize)+0.5);
	if(typeof allpage=="undefined")pagecount=1;
	else pagecount=allpage;
	if(p<0)p=0;if(p>pagecount)p=pagecount;
	for(j=1;j<=pagecount;j++){
		p==j?pageclass=" class=current ":pageclass="";
		if(pagecount>15){
				if(p>pagecount/2){
					  if(j>(p-1)/6 && j<(p-1)*5/6){
						     if(pageLabel.indexOf("......")!=-1)continue;
						     pageLabel+="<a"+pageclass+" href=\"javascript:ShowPageContent("+n+","+s+","+j+","+n2+");\">......</a>";
					  }else pageLabel+="<a"+pageclass+" href=\"javascript:ShowPageContent("+n+","+s+","+j+","+n2+");\">"+j+"</a>";
				}else{
					  if(j>(p+(pagecount-p)/6) && j<(p+(pagecount-p)*5/6)){
						     if(pageLabel.indexOf("......")!=-1)continue;
						     pageLabel+="<a"+pageclass+" href=\"javascript:ShowPageContent("+n+","+s+","+j+","+n2+");\">......</a>";
					  }else pageLabel+="<a"+pageclass+" href=\"javascript:ShowPageContent("+n+","+s+","+j+","+n2+");\">"+j+"</a>";
				}
		}
		else pageLabel+="<a"+pageclass+" href=\"javascript:ShowPageContent("+n+","+s+","+j+","+n2+");\">"+j+"</a>";
	}
		gotoPageList.innerHTML=pageLabel;
	for(i=0;i<arrLength;i++ ) {
	content=getArrayStr(myArray[i],startPageNum+i,n2);
	    dleft+=content[0];
		dright+=content[1];
		count++;if(count==psize)break;
	}
	content=DataHtmlHead+'<tr><td>'+DataTableLeft+dleft+'<tr><td colspan=3></td></tr></table></td><td><div style="margin-top:0;padding-top:0;overflow-x:scroll;width:660px;border:0;">'+DataTableRight+dright+'</table></div></td></tr></table>';
	DataContent.innerHTML=content;//alert(DataTableLeft);return;
	for(i=0;i<titleNumber.length;i++){
		if(titleNumber[i]==3 || titleNumber[i]==6 || titleNumber[i]==7)eval('document.getElementById("SortDDE'+titleNumber[i]+'").innerHTML="'+GetSelectInfo(titleNumber[i],n2)+'";');
		else $("SortDDE"+titleNumber[i]).innerHTML="<a href=\"javascript:ShowPageContent("+titleNumber[i]+","+(-s)+",1,"+n2+");\">"+titleText[i]+"</a>";
		
	}
	var orgText=$("SortDDE"+n).innerHTML;
		 s==1?orgText+="△":orgText+="▼";
		 $("SortDDE"+n).innerHTML=orgText;
}
function ShowPageContent(n,s,p,arr){
	var url,m1,n1;
	if(n==3 || n==6 || n==7){
		if(typeof arr=="number")arr=new Array(arr,arguments[4],arguments[5]);
		m1=n;
		if(n==3){n1=arr[0];}
		else if(n==6)n1=arr[1];
		else if(n==7)n1=arr[2];
		
	}else{n1=-1,m1=n;}
	var t,zxArrBox = document.getElementsByName('zxtype');
	for (var i = 0 ; i<zxArrBox.length; i++)
	{
		if (zxArrBox[i].checked)
		{
			t = zxArrBox[i].value;	
		}
	}
	
	url="./getzxdata.php?t="+(parseInt(t)+1)+"&m="+m1+"&n="+n1+"&order="+s+"&page="+p;
	include_js(url,n,s,p);
}
function getArrayStr(a,n,n2) {
	var classstr="",n1=n+1,stockname="";
	var line="<tr onclick=\"changeBKColor("+n1+")\" id='sid_l_"+n1+"'><td>"+n1+"</td>";
		line+=("<td align='center' class='td'><a href='http://www.chaguwang.cn/showsp.php?no="+a[0]+"' target='_blank'>"+a[0]+"</a></td>");
		for(var k=0;k<stockCodeArray.length;k++){
			      if(a[0]==stockCodeArray[k][0]){stockname=stockCodeArray[k][1];break;}
		}
		line+=("<td align='right'>"+stockname+"<input type='checkbox' name='selectzx' value='"+a[0]+"'><a href='http://www.chaguwang.cn/showsp.php?no="+a[0]+"' target='_blank'>超赢</a> <a href='http://www.chaguwang.cn/showtp.php?no="+a[0]+"' target='_blank'>全赢</a> <a href='ddx.html?code="+a[0]+"' target='_blank'>分时</a> <a href='dde.html?code="+a[0]+"' target='_blank'>历史</a> <a href='ths/dde.php?code="+a[0]+"' target='_blank'>拓赢</a></td>");
		line+="</tr>";
	var line1="<tr onclick=\"changeBKColor("+n1+")\" id='sid_r_"+n1+"'>";
		line1+=("<td class='textRight'>"+ForNum(a[2],2)+"</td>");
		a[3][n2[0]]==0?classstr=" class='textRight'":(a[3][n2[0]]>0?classstr=" class='colorRED textRight'":classstr=" class='colorGREEN textRight'");
		line1+=("<td "+classstr+">"+ForNum(a[3][n2[0]],2)+"%</td>");
		line1+=("<td class='textRight'>"+ForNum(a[4],2)+"%</td>");
		line1+=("<td class='textRight'>"+ForNum(a[5],2)+"</td>");
		a[6][n2[1]]==0?classstr=" class='textRight'":(a[6][n2[1]]>0?classstr=" class='colorRED textRight'":classstr=" class='colorGREEN textRight'");
		line1+=("<td "+classstr+">"+ForNum(a[6][n2[1]],3)+"</td>");
		a[7][n2[2]]==0?classstr=" class='textRight'":(a[7][n2[2]]>0?classstr=" class='colorRED textRight'":classstr=" class='colorGREEN textRight'");
		line1+=("<td "+classstr+">"+ForNum(a[7][n2[2]],3)+"</td>");
		a[8]==0?classstr=" class='textRight'":(a[8]>0?classstr=" class='colorRED textRight'":classstr=" class='colorGREEN textRight'");
		line1+=("<td "+classstr+">"+ForNum(a[8],3)+"</td>");
		line1+=("<td class='textRight'>"+a[26]+"</td>");
		for(var j=9;j<=10;j++)	{
		line1+=("<td class='textRight'>"+a[j]+"</td>");
		}
		line1+=("<td class='textRight'>"+a[27]+"</td>");
		line1+=("<td class='textRight'>"+ForNum(a[11],2)+"</td>");
        a[12]==0?classstr=" class='textRight'":(a[12]>0?classstr=" class='colorRED textRight'":classstr=" class='colorGREEN textRight'");
		line1+=("<td "+classstr+">"+ForNum(a[12],2)+"</td>");
		line1+=("<td class='textRight'>"+ForNum(a[13],3)+"</td>");
		line1+=("<td class='colorRED textRight'>"+a[14]+"</td>");
		line1+=("<td class='colorGREEN textRight'>"+a[15]+"</td>");
			a[16]==0?classstr=" class='textRight'":(a[16]>0?classstr=" class='colorRED textRight'":classstr=" class='colorGREEN textRight'");
		line1+=("<td "+classstr+">"+ForNum(a[16],1)+"</td>");
		a[17]==0?classstr=" class='textRight'":(a[17]>0?classstr=" class='colorRED textRight'":classstr=" class='colorGREEN textRight'");
		line1+=("<td "+classstr+">"+ForNum(a[17],1)+"</td>");
		a[18]==0?classstr=" class='textRight'":(a[18]>0?classstr=" class='colorRED textRight'":classstr=" class='colorGREEN textRight'");
		line1+=("<td "+classstr+">"+ForNum(a[18],1)+"</td>");
		a[19]==0?classstr=" class='textRight'":(a[19]>0?classstr=" class='colorRED textRight'":classstr=" class='colorGREEN textRight'");
		line1+=("<td "+classstr+">"+ForNum(a[19],1)+"</td>");
		for(var j=20;j<=25;j++) {
		j%2==0?classstr=" class='colorRED textRight'":classstr=" class='colorGREEN textRight'";
		line1+=("<td "+classstr+">"+ForNum(a[j],1)+"%</td>");
		}

	line1+="</tr>";
	return new Array(line,line1);
}
