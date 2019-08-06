<%@ page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<base href="<%=basePath%>"></base>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
</head>
<body>
<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">关于</h3>
		</div>
		<div class="panel-body">
			<h1>基于B/S的驾校考试管理系统</h1>
			<div class="alert alert-success">张在林 201306084216</div>
			<div class="alert alert-success">杨    黎 201306084233</div>
		</div>
	</div>
</body>
</html>