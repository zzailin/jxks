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
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<style type="text/css">
body {
	padding-top: 16px;
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
			<h3>
				考试剩余时间:<span id="testTime" class="label label-danger"></span>
			</h3>
			<div class="panel panel-default">
				<div class="panel-heading">题目${sessionScope.page}</div>
				<div class="panel-body">
					<div class="left">
						<p class="text-left">${sessionScope.examEntity.question}</p>
						<p class="text-left">
							<label> <input type="radio" name="test" value="1">
								${sessionScope.examEntity.item1}
							</label>
						</p>
						<p class="text-left">
							<label> <input type="radio" name="test" value="2">
								${sessionScope.examEntity.item2}
							</label>
						</p>
						<c:if test="${sessionScope.examEntity.item3!=null && sessionScope.examEntity.item3.length()!=0}">
						<p class="text-left">
							<label> <input type="radio" name="test" value="3"> ${sessionScope.examEntity.item3}
							</label>
						</p>
						<p class="text-left">
							<label> <input type="radio" name="test" value="4"> ${sessionScope.examEntity.item4}
							</label>
						</p>
						</c:if>
						<c:if test="${sessionScope.page!=1}">
						<a class="btn btn-primary"  href="<%=basePath%>pageChange.action?status=pageUp" role="button">上一题</a> 
						</c:if>
						<c:if test="${sessionScope.page<sessionScope.examNum}">
						<a class="btn btn-primary" href="<%=basePath%>pageChange.action?status=pageDown" role="button">下一题</a>
						</c:if>
						<a class="btn btn-primary pull-right" href="#" role="button">交卷</a>
					</div>
					<div class="right">
						<img src="${sessionScope.examEntity.url}"
							alt="..." class="img-rounded img-responsive" width="70%">
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		var interval = 1000;
		var timer = window.setInterval(function() {
			ShowCountDown();
		}, interval);
		var leftsecond = 1 * 60;//考试时间
		function ShowCountDown() {
			leftsecond--;
			if (leftsecond === 0) {
				alert("test over!!!");
				clearInterval(timer);
			}
			if (leftsecond >= 0) {
				var minute = Math.floor(leftsecond / 60);
				if(minute<10){
					minute = "0"+minute;
				}
				var second = Math.floor(leftsecond - minute * 60);
				if(second<10)
					second = "0"+second;
				var cc = document.getElementById("testTime");
				cc.innerHTML = minute + ":" + second;
			}
		}
	</script>
</body>
</html>