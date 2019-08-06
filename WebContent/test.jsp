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
<title>驾校考试系统</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
	<div class="container">
		<form class="form-signin" action="test.action" method="post">
			<input type="checkbox" name="answer" value="1">
			<input type="checkbox" name="answer" value="2">
			<input type="checkbox" name="answer" value="3">
			<button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
		</form>
	</div>
</body>
</html>