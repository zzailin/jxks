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
<style type="text/css">
#show {
	position: absolute;
	bottom: -200px;
	right: 100px;
	width: 200px;
}
</style>
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
			<script type="text/javascript">
				if($("#message").text().length!=0){
					alert($("#message").text());
				}
			</script>
			<c:remove var="message" scope="session" />
			<form action="modifyQuestion.action" method="post"
				enctype="multipart/form-data">
				<div class="form-group">
					<label>所属科目</label> <span id="subjectValue" class="hide">${sessionScope.questionEntity.subject}</span>
					<select id="subject" name="subject">
						<option value="1">科目一</option>
						<option value="4">科目四</option>
					</select>
				</div>
				<input type="hidden" name="id"
					value="${sessionScope.questionEntity.id}">
				<div class="form-group">
					<label>题目</label> <input type="text" class="form-control"
						name="question" value="${sessionScope.questionEntity.question}">
				</div>
				<div class="form-group">
					<label>选项一</label> <input id="item1" type="text"
						class="form-control" name="item1"
						value="${sessionScope.questionEntity.item1}">
				</div>
				<div class="form-group">
					<label>选项二</label> <input id="item2" type="text"
						class="form-control" name="item2"
						value="${sessionScope.questionEntity.item2}">
				</div>
				<div class="form-group">
					<label>选项三</label> <input id="item3" type="text"
						class="form-control" name="item3"
						value="${sessionScope.questionEntity.item3}">
				</div>
				<div class="form-group">
					<label>选项四</label> <input id="item4" type="text"
						class="form-control" name="item4"
						value="${sessionScope.questionEntity.item4}">
				</div>
				<div class="form-group">
					<label>正确选项</label> <span id="answerValue" class="hide">${sessionScope.questionEntity.answer}</span>
					<select id="select" class="selectpicker" name="answer">
						<option value="">请选择</option>
					</select>
				</div>
				<div class="form-group">
					<label>答案解析</label> <input type="text" class="form-control"
						name="explains" value="${sessionScope.questionEntity.explains}">
				</div>
				<div class="form-group">
					<label>图片上传</label> <span id="picShow" class="btn btn-default">图片预览</span><input
						id="image" type="file" name="image" accept="image/*">
					<p class="help-block">题目所需参考图片，没有则不上传。</p>
				</div>
				<button type="submit" class="btn btn-default">修改</button>
				<button type="reset" class="btn btn-default">取消</button>
			</form>
		</div>
	</div>
	<img id="show" alt="此题没有图片或网络繁忙" src="${sessionScope.questionEntity.url}">
	<script type="text/javascript">
		$("#show").hide();
		$("#image").change(function() {
			var imgurl = document.getElementById("image");
			var img = document.getElementById("show");
			var src = window.URL.createObjectURL(imgurl.files[0]);
			img.src = src;
		});
		$("#show").click(function() {
			$(this).hide();
		});
		$("#picShow").click(function() {
			$("#show").show();
		});
		var subjectChange = function(){
			if ($("#subject").val() == 4) {
				$("#select").attr("multiple", "multiple");
			}
			if ($("#subject").val() == 1) {
				$("#select").removeAttr("multiple");
			}
		};
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			if ($("#item1").val().length != 0) {
				$("#select").append("<option value='1'>选项一</option>");
			}
			if ($("#item2").val().length != 0) {
				$("#select").append("<option value='2'>选项二</option>");
			}
			if ($("#item3").val().length != 0) {
				$("#select").append("<option value='3'>选项三</option>");
			}
			if ($("#item4").val().length != 0) {
				$("#select").append("<option value='4'>选项四</option>");
			}
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			if ($("#subjectValue").text() == 1) {
				$("#subject option[value=1]").attr('selected', true);
			}
			if ($("#subjectValue").text() == 4) {
				$("#subject option[value=4]").attr('selected', true);
			}
			subjectChange();
			$("#subject").change(function(){
				subjectChange();
			});
			var arr = $("#answerValue").text().split(', ');
			for(var i in arr){
				$("#select option[value="+arr[i]+"]").attr('selected',true);
			}
		});
	</script>
	
</body>
</html>