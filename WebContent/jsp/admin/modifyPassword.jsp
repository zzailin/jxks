<%@ page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<h3 class="panel-title">密码修改</h3>
		</div>
		<div class="panel-body">
			<c:if test="${sessionScope.message!=null}">
			<div class="alert alert-danger" role="alert">${sessionScope.message}</div>
			</c:if>
			<c:remove var="message" scope="session"/>
			<form class="form-horizontal" role="form" action="modifyPassword.action" method="post">
				<input type="hidden" name="id" value="${sessionScope.userEntity.id}">
				<div class="form-group">
					<label class="col-sm-2 control-label">账号:</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" disabled="disabled"
							name="account" value="${sessionScope.userEntity.account}">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">原密码:</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="password">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">新密码:</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="newPassword">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">确认密码:</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="newPassword1">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-default">修改</button>
						<button id="cancel" type="button" class="btn btn-default">取消</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$("#cancel").click(function() {
								window.location.href = "www.baidu.com";
							});
						});
	</script>
</body>
</html>