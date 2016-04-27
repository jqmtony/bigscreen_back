<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>东方嘉禾-媒资系统（MRS）</title>
<link rel="stylesheet" type="text/css" href="/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
<script type="text/javascript" src="/javascript/jquery.min.js"></script>
<script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="/javascript/jquery.fullcalendar.js"></script>
<script type="text/javascript" src="/javascript/datagrid-detailview.js"></script>
<script type="text/javascript" src="/javascript/ajaxfileupload.js"></script>
<script>
	if(window.parent!=window){
		window.parent.location.reload();
	}
	function newTab(obj) {
		if(obj.url == null || obj.url == ''){
			return;
		}
		if ($('#main-tabs').tabs('exists', obj.name)) {
			$('#main-tabs').tabs('select', obj.name);
		} else {
			$("#main-tabs").tabs("add", {
				title : obj.name,
				content : '<iframe src="'+obj.url+'" frameborder="0" marginheight="0" marginwidth="0" id="mainiframe-$!{timp}" name="mainiframe" height="100%" width="100%"></iframe>',
				closable : true
			});
		}
	}

	function reloadTab(){
		var tab = $('#main-tabs').tabs('getSelected');
		var title = tab.panel('options').title;
		$('#main-tabs').tabs('update', {
			tab:tab, 
		    options:{}
		});
	}
	
	function jsonDecode(josnStr){
		return (new Function("return " + josnStr))();
	}
	
</script>
<style type="text/css">
.accordioniterm {
	padding: 10px;
	overflow: auto;
}

.accordioniterm ul {
	list-style: none;
	margin: 0px;
	padding: 0px;
}

.accordioniterm ul li {
	list-style: none;
	margin-top: 2px;
	margin-bottom: 2px;
	padding: 2px;
}

.accordioniterm a {
	text-decoration: none;
	font-size: 13px;
	color: #15428B;
	padding: 2px;
	cursor: pointer
}

.datagrid-body table{
	width: 100%;
	table-layout: auto;
}
</style>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height: 60px; background-image: url('/images/header_bg.gif'); background-repeat: repeat-x; background-color: black;">
		<div
			style="float: left; display: block; margin: 0; padding: 0; visibility: visible;">
			<img src="/images/header.png">
		</div>
		<div
			style="font-size: 13px; display: block; text-align: right; color: white; padding: 5px; margin: 0; visibility: visible; font-family: verdana,lucida,arial,helvetica,sans-serif;">
			<div>
				<span id="head-link-r"
					style="cursor: pointer; text-align: right; padding-right: 5px; color: #FC0;">
					<c:choose>
				       <c:when test="${not empty user}">
				           	 欢迎您， ${user.fullName},	<a href="/logout"><font color="white">登出</font></a>
				       </c:when>
				       <c:otherwise>
							游客, 您好! 请 <a href="/login">登录</a>
				       </c:otherwise>
					</c:choose>
				</span>
			</div>
			<!-- <div id="logo">
				<span><a href=""><font color="#FC0">下载系统帮助文档</font></a></span>
			</div> -->
			<div id="logo">
				<span>媒资系统 V1.0</span>
			</div>
		</div>
	</div>

	<div region="west" split="false" title="操作菜单"
		style="width: 240px; padding1: 1px; overflow: hidden;">
		<div class="easyui-accordion" fit="true" border="false">
			<div title="系统" class="accordioniterm">
			<c:choose>
		       <c:when test="${not empty user}">
		            <ul id="myMenuTree" class="easyui-tree" data-options='data:[{"id":0,"text":"GoChinaTV MRS","children":${menu}}],animate:true,onClick:function(node){newTab({name:node.text, url:node.attributes.url});}'></ul>
		       </c:when>
		       <c:otherwise>
					<span style="font-weight:bold">您还没有登录！</span><br><span style="color:#888; font-style:italic">登录后将显示系统菜单</span>
		       </c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
	<div region="center" style="overflow: hidden;">
		<div id="main-tabs" class="easyui-tabs" fit="true" border="false">
			
		</div>
	</div>
</body>
</html>