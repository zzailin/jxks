<%@ page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<base href="<%=basePath%>"></base>
<title>基于B/S的驾校考试管理系统</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="text"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
h2{
	text-align: center;
}
</style>
</head>

<body>
	<div class="container">
		<form class="form-signin" action="userLogin.action" method="post">
			<h2 class="form-signin-heading">基于B/S的驾校</h2>
			<h2 class="form-signin-heading">理论考试管理系统</h2>
			<c:if test="${sessionScope.message!=null}">
			<div class="alert alert-danger" role="alert">${sessionScope.message}</div>
			</c:if>
			<c:remove var="message" scope="session"/>
			<label class="sr-only">ID</label> <input class="form-control" placeholder="身份证号码" required=""
				autofocus="" type="text" name="account"> <label class="sr-only">Password</label> <input class="form-control" placeholder="密码为身份证号码后六位" type="password" name="password">
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>
	</div>
	<canvas height="106" width="1366" style="position: fixed; top: 0px; left: 0px; z-index: -1; opacity: 0.5;" id="c_n16"></canvas>
<script type="text/javascript" color="255,0,0" opacity="0.5" count="99" src="js/canvas.js"></script>
</body>
</html>