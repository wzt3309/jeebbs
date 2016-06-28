<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统参数设置--JEEBBS安装向导</title>
<link href="img/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function formSubmit() {
	if(document.getElementById('dbPassword').value==''){
		if(!confirm("您没有填写数据库密码，您确定数据库密码为空吗？")) {
			return false;
		}
	}
	document.getElementById('beforeSubmit').style.display = "none";
	document.getElementById('afterSubmit').style.display = "";
}
</script>
</head>

<body>
<form action="install_setup.svl" method="post" onsubmit="return formSubmit();">
<table width="900" align="center" style="border:#106DBA 1px solid; margin-top:30px;">
  <tr>
    <td bgcolor="#D1E9FA"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="27%" height="60" rowspan="2" align="center"><img src="img/logo.gif" border="0"/></td>
        <td width="73%" height="30" class="f14b">2、系统参数设置<span style="color:#FF0000">（环境要求：jdk1.5或以上、tomcat5.5或以上、mysql5.0或以上）</span></td>
      </tr>
      <tr>
        <td height="20" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;请设置系统相关参数</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="400" align="center" bgcolor="#F0F8FD"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td width="30%" height="30" align="right">数据库主机：</td>
        <td width="22%" align="left"><input name="dbHost" type="text" class="input" id="dbHost" value="127.0.0.1" /></td>
        <td align="left">数据库的ip地址，如果是本机无需改动</td>
      </tr>      <tr>
        <td width="30%" height="30" align="right">数据库端口号：</td>
        <td width="22%" align="left"><input name="dbPort" type="text" class="input" id="dbPort" value="3306" /></td>
        <td align="left">数据库的端口号，一般无需改动</td>
      </tr>
      <tr>
        <td height="30" align="right">数据库名称：</td>
        <td align="left"><input name="dbName" type="text" class="input" id="dbName" value="jeebbs_3" /></td>
				<td align="left">&nbsp;</td>
      </tr>
      <tr>
        <td height="30" align="right">数据库用户：</td>
        <td align="left"><input name="dbUser" type="text" class="input" id="dbUser" value="root" /></td>
				<td align="left">&nbsp;</td>
      </tr>
      <tr>
        <td height="30" align="right">数据库密码：</td>
        <td align="left"><input name="dbPassword" type="text" class="input" id="dbPassword" /></td>
		<td align="left">安装数据库时输入的密码</td>
      </tr>
      <tr>
        <td height="30" align="right">是否创建数据库：</td>
        <td align="left">
			<input type="radio" name="isCreateDb" value="true" checked="checked"/>是
			<input type="radio" name="isCreateDb" value="false" />否
		</td>
		<td align="left">如果您自己手工创建了数据库，请选否</td>
      </tr>
      <tr>
        <td height="30" align="right">是否创建表：</td>
        <td align="left">
				<input type="radio" name="isCreateTable" value="true" checked="checked"/>是
				<input type="radio" name="isCreateTable" value="false" />否
				</td>
				<td align="left">如果您自己手工创建了表，请选否</td>
      </tr>
      <tr>
        <td height="30" align="right">是否初始化数据：</td>
        <td align="left">
				<input type="radio" name="isInitData" value="true" checked="checked"/>是
				<input type="radio" name="isInitData" value="false" />否
				</td>
				<td align="left">如果您自己手工初始化了数据，请选否</td>
      </tr>
      <tr>
        <td height="30" align="right">域名：</td>
        <td align="left"><input name="domain" type="text" class="input" value="<%=request.getServerName()%>"/></td>
				<td align="left">系统已经检测出您的域名，请勿改动</td>
      </tr>
      <tr>
        <td height="30" align="right">部署路径：</td>
        <td align="left"><input name="cxtPath" type="text" class="input" value="<%=request.getContextPath()%>"/></td>
				<td align="left">系统已经检测出您的部署路径，请勿改动</td>
      </tr>
      <tr>
        <td height="30" align="right">端口号：</td>
        <td align="left"><input name="port" type="text" class="input" value="<%=request.getServerPort()%>"/></td>
				<td align="left">系统已经检测出您的端口号，请勿改动</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="30" align="center" bgcolor="#D1E9FA">
		<span id="beforeSubmit"><input type="submit" class="btn" value=" 提 交 " />
		</span>
		<span id="afterSubmit" style="display:none;color:red;">安装需要十几秒的时间，请您耐心等待...</span>
	</td>
  </tr>
</table>
<input type="hidden" name="dbFileName" value="/install/db/jeebbs-db-3.sql"/>
<input type="hidden" name="initFileName" value="/install/db/jeebbs-init-3.sql"/>
</form>
</body>
</html>
