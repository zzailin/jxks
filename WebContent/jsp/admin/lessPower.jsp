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
<style type="text/css">
div{
	margin-top: 160px;
}
</style>
</head>
<body>
	<c:if test="${sessionScope.message!=null}">
		<div class="alert alert-danger" role="alert">${sessionScope.message}</div>
	</c:if>
	<c:remove var="message" scope="session" />
</body>
</html>