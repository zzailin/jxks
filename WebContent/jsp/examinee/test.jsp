<%@ page pageEncoding="utf-8"%>
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
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}
</style>
</head>
<body>
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">考生信息确认</h4>
				</div>
				<div class="modal-body">
					<label>身份证号:</label><p class="text-info text-center">${sessionScope.userEntity.account}</p>
					<label>姓名:</label><p class="text-info text-center">${sessionScope.userEntity.name}</p>
					<label>考试科目:</label><p class="text-info text-center">${sessionScope.userEntity.userInfo.subject}</p>
					<div class="alert alert-warning">
					    <a href="#" class="close" data-dismiss="alert">
					        &times;
					    </a>
					    <strong>警告！</strong>个人信息如有错误请与考场工作人员联系。
					</div>
				</div>
				<div class="modal-footer">
					<a href="<%=basePath %>doExam.action" type="button" class="btn btn-primary">同意</a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$("#myModal").modal({backdrop:false,keyboard:false});
	</script>
</body>
</html>