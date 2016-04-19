<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>东方嘉禾-媒资系统（MRS）</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/javascript/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	document.onkeydown=function(e) {
	    var theEvent = e || window.event;  
	    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;  
	    if (code == 13) {  
	    	submitForm();
	    }  
	}
		
	function submitForm() {
		var result = $('#loginform').form('validate');
		if(result){
			$('#loginform').submit();
		}
	}
	function clearForm() {
		$('#loginform').form('clear');
	}
</script>
<style type="text/css">
.login-panel{
	position: fixed;
	top: 150px;
	width: 400px;
	left: 50%;
	margin-left: -200px;
}
</style>
</head>
<body>
	<div class="login-panel">
		<div class="easyui-panel" title="登录" style="width: 400px;">
			<div style="padding: 10px 0 10px 60px">
				<form id="loginform" method="post" target="_self" action="/index">
					<c:if test="${ not empty errMsg }">
						<span style="line-height:20px; color: red; font-size: 13px; font-family:verdana,lucida,arial,helvetica,sans-serif;">错误：${errMsg.errorMsg}</span>	
					</c:if>
					<table>
						<tr>
							<td>登录用户:</td>
							<td><input class="easyui-validatebox" type="text"
								name="n" data-options="required:true"></input></td>
						</tr>
						<tr>
							<td>登录密码:</td>
							<td><input class="easyui-validatebox" type="password"
								name="p" data-options="required:true,validType:'password'"></input></td>
						</tr>
					</table>
				</form>
			</div>

			<div style="text-align: center; padding: 5px">
				<a  href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm()">登录</a> 
			</div>

		</div>
	</div>
</body>
</html>