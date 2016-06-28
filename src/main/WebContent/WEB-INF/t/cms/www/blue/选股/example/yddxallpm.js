function dgmsg(msg){
    console.log(msg);
}
var islogin = false;
var lsdate = null;
var orderby =0;//排列次序id

var isdesc='';//升序或降序

var pagenum =20;//分页数目

var cpage =1;//当前页

var updatetime='';
var url ='ygetddxpm.php';
var zxurl='xg/xgzixuan.php';
var isauto = true;//自动刷新
var sit;
var delay = 60;
var ytp = 0;

var ddx_config =[];
var addcolor_config =[];

var addbfh_config = [];
var bj_conifg =[];
var ddxlen = 0;
var bkcode = '';

$(document).ready(function(){
    if (typeof qurl != "undefined")
    {
        url = qurl;
    }
    var table = [];
    ddxlen = ddx_config.length;
    table[0] = '<table class="pmtb" id="sjtable"><tr id="tth">';
    var len = ddx_dconfig.length;
    for(var i=0;i<len;i++){
        table[table.length] = '<th colspan="';
        table[table.length] = ddx_dconfig[i].colspan;
        table[table.length] = '">';
        table[table.length] = ddx_dconfig[i].name;
        table[table.length]='</th>';
    }
    table[table.length] = '</tr><tr id="mth" class="mth"><td>序号</td><td class="order"><a href="javascript:listorder(0);">股票代码</a></td><td class="aright">名称|';
    if (isgp)
    {
        table[table.length] = '自选股1|自2|自3|';
    }
    table[table.length] = '查股数据</td>';
    for(var i=1;i<ddxlen;i++){
        if (typeof ddx_config[i].color ==="number" && ddx_config[i].color)
        {
            addcolor_config[addcolor_config.length] = i;
        }
        
        if (typeof ddx_config[i].bfh ==="number" && ddx_config[i].bfh)
        {
            addbfh_config[addbfh_config.length] = i;
        }
        
        table[table.length] = '<td class="order"><a href="javascript:listorder(';
        table[table.length] = i;
        table[table.length] = ');">';
        table[table.length] = ddx_config[i].name;
        table[table.length] = '</a>';
        if (typeof ddx_config[i].option ==="object")
        {
            bj_conifg.push(ddx_config[i].bj);
            var kk = ddx_config[i].option.length;
            var tt = [];
            table[table.length] = '<select name=';
            table[table.length] = ddx_config[i].bj;
            table[table.length] ='" id="';
            table[table.length] = ddx_config[i].bj;
            table[table.length] = '">'
            for (var j=0; j<kk; j++)
            {
                table[table.length] = '<option value="';
               table[table.length] = j;
                table[table.length] = '">';
               table[table.length] = ddx_config[i].option[j];
                table[table.length] = '</option>';
            }
            table[table.length] = '</select>';

        }
        table[table.length] = '</td>';
    }
    table[table.length]='</tr><tr id="bth" class="bth"><td>序号</td><td class="aright">股票代码</td><td>名称|';
    if (isgp)
    {
        table[table.length] = '自选股1|自2|自3|';
    }
    table[table.length] = '查股数据</td>';
    for(var i=1;i<ddxlen;i++){
        table[table.length] = '<td>';
        table[table.length] = ddx_config[i].name;
        table[table.length] = '</td>';
    }
    table[table.length] = '</tr></table>';
    $('#sj').append(table.join(''));
    var len = bj_conifg.length;
    if (len)
    {
        for (var i=0; i<len ;i++ )
        {
            $('#'+bj_conifg[i]).change(showdata);
        }
        
    }

    var username = $.cookie('zhuser');
    var y_tp = $.cookie('ytp');
    if(y_tp){
        ytp =1;
        $('#tingpai').attr('checked',true);
    }

    $('#tingpai').click(function(){
        if (this.checked)
        {
            ytp =1;
            $.cookie('ytp',1);
        }else{
            ytp = 0;
            $.cookie('ytp',null);
        }
        showdata();
    });
    if(!GetD()){
        isauto = false; $('#sxauto').attr('checked',false);
    }else{
        isauto=true; $('#sxauto').attr('checked',true);
    }

    $('#sxauto').click(function(){
        if (this.checked)
        {
            if (GetD())
            {
                isauto=true;
                startautosx();
            }
        }else{
            isauto = false;
            clearautosx();
        }

    });

    $('#sxtime').change(function(){
        delay = $(this).val();
        if (isauto)
        {
            clearautosx();
            startautosx();
        }
    });
    $('#sxbutton').click(showdata);
    $('#selectdate').change(function(){
        lsdate = $(this).val();
        showdata();
    });
    if (username)
    {
        islogin =true;
        showuserinfo(username);
    }



    var tlsdate =  $.trim(getQueryValue('date'));
    if (tlsdate && checkdate(tlsdate))
    {
        lsdate = tlsdate;
    }

    bkcode = $.trim(getQueryValue('bkcode'));
    var bklen = BkCodeArray.length;
    if (bkcode)
    {
        var bkname = '';
        for(var k=0;k<bklen;k++){
             if(bkcode==BkCodeArray[k][0]){
                   bkname =BkCodeArray[k][1];
                   break;
             }
        }
        if (bkname)
        {
            document.title =bkcode+'板块对应的成分股|网页版在线level2|查股网';
            $('#bkmsg').html('&nbsp;('+bkcode+')&nbsp;'+bkname);
        }else{
            alert('参数错误！无此版块！');
            return;
        }
    }
    showlsdate();
    showdata();
    suggestStock_0 = new Suggest("no", "上海深圳股票代码/名称/拼音", astock_suggest, null, StockSuggestConfiguration, gotoLookupN_0);
    if (isauto)
    {
        startautosx();
    }

});

function startautosx(){
    sit=setInterval("autoshowdata()",delay*1000);
}
function clearautosx(){
    if(typeof sit!="undefined")clearInterval(sit);
}
function gotoLookupN_0() {
    var value = $("no").value.replace(/s[hz]/, "");
    if (value != "上海深圳股票代码/名称/拼音" && value != "") {
        var stringUrl = "";
    }
}
function showdataloading(){
    $('#loadingmsg').show();
}

function hidedataloading(){
    $('#loadingmsg').hide();
}

function showlsdate(lasttime){
    var temp =[];
    var n = lsdate_array.length;

    if (lasttime != undefined)
    {
        var str=lasttime.replace(/\s*\d{2}:\d{2}:\d{2}/img,'');
        if (!in_array(str, lsdate_array))
        {
            lsdate_array[n]=str;
            n++;
        }else{
            return;
        }
    }
    $('#selectdate >option').remove();
    for(var i=n-1;i>=0;i--){
        var temp2 =[];
        temp2[temp2.length] = '<option value="';
        temp2[temp2.length] = lsdate_array[i];
        temp2[temp2.length] = '"';

        if (lsdate && lsdate == lsdate_array[i])
        {
            temp2[temp2.length] =' selected="selected"';
        }
        temp2[temp2.length] = '>';
        temp2[temp2.length] = lsdate_array[i];
        temp2[temp2.length] = '</option>';
        $('#selectdate').append(temp2.join(''));
    }
}


function getQueryValue(queryName){
    if(location.href.indexOf("?")==-1 || location.href.indexOf(name+'=')==-1){
        return '';
    }
    var queryString = location.href.substring(location.href.indexOf("?")+1);
    var parameters = queryString.split("&");
    var posl, paraName, paraValue;
    for(var i=0; i<parameters.length; i++){
        posl = parameters[i].indexOf('=');
        if(posl == -1) continue;
        paraName = parameters[i].substring(0, posl);
        paraValue = parameters[i].substring(posl + 1);
        if(paraName == queryName){
            return unescape(paraValue.replace(/\+/g, " "));
        }
    }
    return '';
}

function checkdate(d){
    var p = /^20\d{2}-((0[1-9]{1})|(1[0-2]{1}))-((0[1-9]{1})|([1-2]{1}[0-9]{1})|(3[0-1]{1}))$/;
    if (!p.exec(d))
    {
        return false;
    }
    return true;
}

function showmustlogin(){
    alert('请登录使用！谢谢！');
}

function GetD(){
    var d=new Date();
    if(d.getDay()==0 || d.getDay()==6)return false;
    var h=d.getHours()*60+d.getMinutes();
    if((h>(9*60+25) && h<(11*60+35)) || (h>(13*60-5) && h<(15*60+10)))return true;
    return false;
}

function listorder(j){
    var obj = $('#mth > .order');
    obj.find('span').hide();
    var objt= obj.eq(j);
    orderby = j;

    if (objt.find('span').size() ==0)
    {

        objt.append('<span>▼</span>');
        isdesc=1;
    }else{
        var sp = objt.find('span').eq(0);
        var temp = sp.html();
        if (temp == '▼')
        {
            sp.html('△');
            isdesc=0;
        }else{
            sp.html('▼');
            isdesc=1;
        }
    }
    objt.find('span').eq(0).show();
    cpage=1;
    showdata();
}
function pager(total,curpage,perpage){
    var pageLabel ='页数：';
    var poffset = 5;


    if(total>perpage){
        var ppages = Math.ceil(total/perpage);
        var page = 10;
        if(page > ppages){
            var from = 1;
            var to = ppages;
        } else {
            var from = curpage - poffset;
            var to = from + page - 1;
            if(from < 1) {
                to = curpage + 1 - from;
                from = 1;
                if(to - from < page) {
                    to = page;
                }
            } else{
                if(to > ppages) {
                    from = ppages - page+1;
                    to = ppages;
                }
            }
        }

        if(curpage - poffset > 1 && ppages > curpage){
            pageLabel +='<a href="javascript:changepage(1);" class="first">1...</a>';
        }
        if(curpage > 1){
            pageLabel += '<a href="javascript:changepage('+(curpage-1)+');" class="prev" alt="上一页">&lsaquo;&lsaquo;</a>';
        }
        for(var i = from; i <= to; i++) {
            if (i == curpage)
            {
                pageLabel += '<strong>'+i+'</strong>';
            }else{
                pageLabel += '<a href="javascript:changepage('+i+');">'+i+'</a>';
            }
        }
        if (to < ppages)
        {
            pageLabel += '<a href="javascript:changepage('+ppages+');" class="last">...'+ ppages+'</a>';
        }
        if(curpage < ppages)
        {
            pageLabel += '<a href="javascript:changepage('+(curpage+1)+');" class="next" alt="下一页">&rsaquo;&rsaquo;</a>';
        }

        pageLabel += "&nbsp;&nbsp;第<input name='go' id='go' size='2' type='text' value='"+curpage+"' maxlength=2 style='margin:0;padding:0;'>页 <input onclick='tiaozhuan()' value='跳转' type='button' style='height:20px;'>";
    }
    $('#pageer').html(pageLabel);
}

function tiaozhuan(){
    var p = $.trim($('#go').val());
    if (p)
    {
        changepage(p);
    }
}
function changepage(p){
    cpage = p;
    showdata();
}
function showdataloading(){
    $('#loadingmsg').show();
}

function hidedataloading(){
    $('#loadingmsg').hide();
}

function showdata(){
    if (isauto && !GetD())
    {
        if(typeof sit!="undefined")clearInterval(sit);
        isauto =false;
    }
    showdataloading();
    
    loaddata();
}
function in_array(searchstr,arr){
    var len = arr.length;
    for(var i=0;i<len;i++){
        if (arr[i] == searchstr)
        {
            return true;
        }
    }
    return false;
}
function gscolor(kk,shuzi){
    if (in_array(kk,addcolor_config))
    {

        if (shuzi <0)
        {
            return ' style="color:green"';
        }else{
            return ' style="color:red"';
        }
    }
    return '';
}

function addbfh(kk){
    if (in_array(kk,addbfh_config))
    {
        return '%';
    }
    return '';
}
function getquerystr(){
    var getstr=[];
    var len = bj_conifg.length;
    for(var i=0;i<len;i++){
        var v = $('#'+bj_conifg[i]).val();
        if (v)
        {
            getstr.push(bj_conifg[i]+"="+v);
        }
        
    }
    if (typeof gtype === "number" )
    {

        getstr.push('gtype='+gtype);
    }
    if (bkcode )
    {

        getstr.push('bkcode='+bkcode);
    }
    if (cpage >1)
    {
        getstr.push('page='+cpage);
    }
    getstr.push('pagenum='+pagenum);
    if (orderby===0 || orderby)
    {
        getstr.push('orderby='+orderby);
    }
    if (isdesc !='')
    {
        getstr.push('isdesc='+isdesc);
    }
    if (ytp)
    {
        getstr.push('tp='+1);
    }
    if (lsdate)
    {
        getstr.push('lsdate='+lsdate);
    }
    return getstr;

}
function removedata(){
    $('#sjtable').find('.trdata').remove();
}
function loaddata(){
    var str = getquerystr();
    var surl =url+'?'+str.join('&')+'&t='+Math.random();
    $.get(surl,function(data){
        removedata();
        hidedataloading();
        if ( typeof data !== "string" || !data ) {
            return ;
        }
        data = $.trim( data );

        if (data) {
            data = new Function("return " + data)();
            var trdata = [];
            if (data.data != undefined)
            {
                var data1 = data.data;
                var num =data1.length;
                if (cpage >1)
                {
                    var startp = (cpage-1)*pagenum;
                }else{
                    var startp = 0;
                }
                var stocklen = stockCodeArray.length;
                var bklen = BkCodeArray.length;
                for(var i=0;i<num;i++){
                    
                    startp +=1;
                    gid = data1[i][0];
                    
                    
                    trdata[trdata.length] = '<tr id="dataname';
                    trdata[trdata.length] = i;
                    trdata[trdata.length] =  '" class="trdata"><td class="xh">';
                    trdata[trdata.length] = startp;
                    trdata[trdata.length] = '</td><td class="gpid"><a href="http://www.chaguwang.cn/showsp.php?no=';
                    trdata[trdata.length] = gid;
                    trdata[trdata.length] ='" target="_blank">'
                    trdata[trdata.length] =gid;
                    trdata[trdata.length] ='</a><td class="gpname szdata">';
                    if (isgp)
                    {
                        for(var k=0;k<stocklen;k++){
                            if(gid==stockCodeArray[k][0]){
                                trdata[trdata.length]=stockCodeArray[k][1];
                                break;
                            }
                        }

                    trdata[trdata.length] ='<input value="';
                    trdata[trdata.length] =gid;
                    trdata[trdata.length] ='" type="checkbox" onclick="addzixuan(this)"  ';
                    //trdata[trdata.length] =zxtypechecked1;
                    trdata[trdata.length] ='  class="zx1"><input value="';
                    trdata[trdata.length] =gid;
                    trdata[trdata.length] ='" type="checkbox" onclick="addzixuan(this)"  ';
                    //trdata[trdata.length] =zxtypechecked2;
                    trdata[trdata.length] ='  class="zx2"><input value="';
                    trdata[trdata.length] =gid;
                    trdata[trdata.length] ='" type="checkbox" onclick="addzixuan(this)" ';
                    //trdata[trdata.length] =zxtypechecked3;
                    trdata[trdata.length] =' class="zx3">&nbsp;<a href="http://www.chaguwang.cn/showsp.php?no=';
                    trdata[trdata.length] =gid;
                    trdata[trdata.length] ='"  target="_blank">超赢</a>&nbsp;<a href="ddx.html?code=';
                    trdata[trdata.length] =gid;
                    trdata[trdata.length] ='" target="_blank">分时</a>&nbsp;<a href="dde.html?code=';
                    trdata[trdata.length] =gid;
                    trdata[trdata.length] ='"  target="_blank" >历史</a></td>';
                    }else{
                        for(var k=0;k<bklen;k++){
                            if(gid==BkCodeArray[k][0]){
                                trdata[trdata.length]=BkCodeArray[k][1];
                                break;
                            }
                        }
                        trdata[trdata.length] = ' <a href="showgp.php?bkcode=';
                        trdata[trdata.length] = gid;
                        trdata[trdata.length] = '" target="_blank">成分股</a> <a href="';
                        if (gid=='000001')
                        {
                            trdata[trdata.length] = 'shdapanddx.html';
                        }else if (gid=='399001')
                        {
                             trdata[trdata.length] = 'szdapanddx.html';
                        }else{
                            trdata[trdata.length] =  'ddx.html?code=';
                            trdata[trdata.length] = gid;
                        }
                        trdata[trdata.length] = '" target="_blank">分时</a> <a href="';
                        if (gid=='000001')
                        {
                            trdata[trdata.length] = 'dapandde.html';
                        }else if (gid=='399001')
                        {
                             trdata[trdata.length] = 'dapandde.html?code=';
                             trdata[trdata.length] = gid;

                        }else{
                            trdata[trdata.length] = 'dde.html?code=';
                            trdata[trdata.length] = gid;
                        }
                        
                        trdata[trdata.length] = '" target="_blank">历史</a>';
                    }

                    for(var j=1; j<ddxlen;j++){
                        trdata[trdata.length] ='<td class="szdata"';
                        if (j==22 || j ==24 || j==26)
                        {
                            trdata[trdata.length] = ' style="color:green"';
                        }else{
                            trdata[trdata.length] =gscolor(j,data1[i][j]);
                        }
                        trdata[trdata.length] = '>';
                        trdata[trdata.length] = data1[i][j];
                        trdata[trdata.length] = addbfh(j);
                        trdata[trdata.length] = '</td>';
                    }

                    trdata[trdata.length] ='</tr>';
                }

                $('#mth').after(trdata.join(''));
                $('.trdata').hover(function(){
                    $(this).addClass('f6');
                },function(){
                    $(this).removeClass('f6');
                }).click(function(){
                    var tclass= this.className;
                    if (tclass.indexOf('b6') == -1)
                    {
                        $(this).addClass('b6');
                    }else{
                        $(this).removeClass('b6');
                    }
                });
            }
            if (data.updatetime != undefined){
                $('#updatetime').html(data.updatetime);
                showlsdate(data.updatetime);
            }
            if (data.total != undefined)
            {
                pager(data.total,cpage,pagenum);
            }else{
                pager(0,cpage,pagenum);
            }
            if (data.errmsg  != undefined)
            {
                if (data.errmsg =='nodata')
                {
                    alert('无选定日期数据！');
                }
            }
        }
    });
}

function addzixuan(obj){
     if (!islogin)
    {
        showmustlogin();
        return;
    }
    var zid =  $(obj).attr("value");
    var kt = $(obj).attr("class");
    if ( kt=='zx1')
    {
        kt =1;
    }else{
        if ( kt=='zx2'){
               kt =2;
        }else{
               kt =3;
        }
    }
    var ischecked = obj.checked;
    if (ischecked)
    {
            var surl=zxurl+'?action=add&zxtype='+kt+'&id='+zid+'&t='+Math.random();
            $.get(surl,function(data){
                if (data == 'success'){
                     //getallzixuan();
                     isgetzixuan =true;
                }else{
                    if (data)
                    {
                        alert(data+',操作失败！');
                    }else{

                        alert('操作失败！');
                    }
                    obj.checked=false;
                }
            });
    }else{

        if (confirm("确定去掉这个自选股吗？")) {

            var surl=zxurl+'?action=del&zxtype='+kt+'&id='+zid+'&t='+Math.random();
            $.get(surl,function(data){
                if (data == 'success'){
                    //getallzixuan();
                    isgetzixuan =true;
                    
                }else{
                        
                        alert('操作失败！');
                    obj.checked=true;
                }
            });

        
        }else{
            obj.checked=true;
            
        }
    }

}