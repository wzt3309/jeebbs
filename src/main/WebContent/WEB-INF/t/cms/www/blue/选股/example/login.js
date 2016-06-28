var islogining =0;

function login() {
    if(islogining){
        return;
    }
    var username = $('#username').val();
    username = $.trim(username);
    var password = $.trim($('#password').val());
    
    
    if (username && password) {
       //$("#loginbutton").disabled =true;
        showajaxlogin();
        islogining = 1;
        var cookielife = $('#cookielife').val();
        $.post('xg/login.php',{username:username,password:password,cookielife:cookielife},function(data){
               if(data=='success'){
                    islogin =true;islogining=0;
                    hideajaxlogin();
                    showuserinfo(username);
                    isgetfangan = true;
        isgetzixuan = true;
        showdata();
               }else{
                    alert('登陆失败，请再次登陆');
                    hideajaxlogin();
               }
        });
    }else{
        alert('用户名或密码为空！');
        hideajaxlogin();
    }
}

function showajaxlogin(){
    if($('#ajaxlogin') != 'undefined'){
        $('#logininfo > span').eq(0).before('<span id="ajaxlogin"><img src="images/ajaxloader1.gif" />登陆中，请稍候。。。。</span>');
    }
    $('#ajaxlogin').show();
}
function hideajaxlogin(){
    $('#ajaxlogin').hide();
}

function showuserinfo(user){
    if(user!= 'undefined'){
        var str = user+'欢迎您使用查股网数据!&nbsp;[<a href="zixuan.php">自选股系统</a>]&nbsp; [<a href="../xg/ddx.html">DDX选股系统</a>] &nbsp;[<a href="../xg/xuangu.html">DDX综合选股</a>]&nbsp; [<a href="../xg/tongji.html">DDX统计系统</a>]&nbsp; [<a href="../xg/chaoying.html">超赢持仓选股</a>]&nbsp; [<a href="../xg/jiben.html">基本面选股</a>]&nbsp; [<a href="ths/ddexuangu.html">同花顺DDE选股</a>] [<a href="ths/ccxuangu.html">拓赢选股系统</a>]&nbsp; [<a href="logout.php">退出</a>]';
        $('#logininfo').html(str);
    }
}

function showmustlogin(){
    alert('请登录使用！谢谢！');
}
