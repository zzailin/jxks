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
<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="panel panel-info">
		<div class="panel-heading">
			<h3 class="panel-title">添加试题</h3>
		</div>
		<div class="panel-body">
			<c:if test="${sessionScope.message!=null}">
				<div class="alert alert-danger" role="alert">${sessionScope.message}</div>
			</c:if>
			<c:remove var="message" scope="session" />
			<form action="addQuestion.action" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label>所属科目</label>
					<select id="subject" name="subject">
						<option value="1">科目一</option>
						<option value="4">科目四</option>
					</select>
				</div>
				<div class="form-group">
					<label>题目</label> <input
						type="text" class="form-control" name="question" placeholder="试题题目">
				</div>
				<div class="form-group">
					<label>选项一</label> <input id="item1"
						type="text" class="form-control" name="item1" placeholder="选项一">
				</div>
				<div class="form-group">
					<label>选项二</label> <input id="item2"
						type="text" class="form-control" name="item2" disabled="disabled" placeholder="选项二">
				</div>
				<div class="form-group">
					<label>选项三</label> <input id="item3"
						type="text" class="form-control" name="item3" disabled="disabled" placeholder="选项三">
				</div>
				<div class="form-group">
					<label>选项四</label> <input id="item4"
						type="text" class="form-control" name="item4" disabled="disabled" placeholder="选项四">
				</div>
				<div class="form-group">
					<label>正确选项</label> 
					<select id="select" class="selectpicker" name="answer">
						<option value="">请选择</option>
					</select>
				</div>
				<div class="form-group">
					<label>答案解析</label> <input
						type="text" class="form-control" name="explains" placeholder="答案解析">
				</div>
				<div class="form-group">
					<label>图片上传</label> <input type="file" name="image" accept="image/*">
					<p class="help-block">题目所需参考图片，没有则不上传。</p>
				</div>
				<button type="submit" class="btn btn-default">Submit</button>
				<button type="reset" class="btn btn-default">Reset</button>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#subject").change(function(){
				if($(this).val()==4){
					$("#select").attr("multiple","multiple");
				}
				if($(this).val()==1){
					$("#select").removeAttr("multiple");
				}
			});
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("#item1").change(function(){
				if($(this).val().length!=0){
					$("#item2").attr("disabled",false);
			        $("#select").append("<option value='1'>选项一</option>");
				}
			});
			$("#item2").change(function(){
				if($(this).val().length!=0){
					$("#item3").attr("disabled",false);
			        $("#select").append("<option value='2'>选项二</option>");
				}
			});
			$("#item3").change(function(){
				if($(this).val().length!=0){
					$("#item4").attr("disabled",false);
			        $("#select").append("<option value='3'>选项三</option>");
				}
			});
			$("#item4").change(function(){
				if($(this).val().length!=0){
			        $("#select").append("<option value='4'>选项四</option>");
				}
			});
		});
	</script>
</body>
</html>