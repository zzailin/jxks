<%@ page pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

.jumbotron {
	width: 70%;
	margin: 0 auto;
}

.left {
	float: left;
	width: 50%;
}

.right {
	float: left;
	width: 50%;
}

label {
	font-weight: normal;
}
</style>
</head>
<body>
	<div class="jumbotron">
		<div class="container">
			<c:if test="${sessionScope.score/sessionScope.examNum>=0.9}">
				<div class="alert alert-success">恭喜你，通过了，成绩为${sessionScope.score}。</div>
			</c:if>
			<c:if test="${sessionScope.score/sessionScope.examNum<0.9}">
				<div class="alert alert-danger">你的成绩为${sessionScope.score}，别灰心，下次再来。</div>
			</c:if>
			<a class="btn btn-info" href="<%=basePath%>loginOut.action">安全退出</a>
			<c:forEach var="exam" items="${sessionScope.queryExam}"
				varStatus="em">
				<div class="panel panel-default">
					<div class="panel-heading">题目${em.index+1}</div>
					<div class="panel-body">
						<div class="left">
							<p class="text-left">${exam.question}</p>
							<p class="text-left">1.${exam.item1}</p>
							<p class="text-left">2.${exam.item2}</p>
							<p class="text-left">3.${exam.item3}</p>
							<p class="text-left">4.${exam.item4}</p>
						</div>
						<div class="right">
							<img src="${exam.url}" alt="..."
								class="img-rounded img-responsive" width="100%">
						</div>
					</div>
					<p class="text-left">
						<label>正确答案:</label><label class="label label-success">${exam.answer}</label>
						<label>您的答案:</label>
						<c:forEach var="result" items="${sessionScope.result}">
							<c:if test="${result.key==exam.id}">
								<c:if test="${result.value!=exam.answer}">
									<label class="label label-danger"> ${result.value} </label>
								</c:if>
								<c:if test="${result.value==exam.answer}">
									<label class="label label-success"> ${result.value} </label>
								</c:if>
							</c:if>
						</c:forEach>
					</p>
					<h4>解释:</h4>
					<p>
						<p class="lead text-success">${exam.explains}</p>
					</p>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>