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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>"></base>
<title></title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css" href="css/manage.css" />
<style type="text/css">
table {
	margin-top: 40px;
	table-layout:fixed;
}

table tr td{
	width:100px;
	word-break:keep-all;
	white-space:nowrap;
	overflow:hidden;
	text-overflow:ellipsis;
}

img {
	position: absolute;
	top: 22px;
	left: 10px;
	width: 100px;
	display: none;
}
</style>
</head>
<body>
	<hr>
	<span id="message" class="hide">${sessionScope.message}</span>
	<script type="text/javascript">
		if($("#message").text().length!=0){
			alert($("#message").text());
		}
	</script>
	<c:remove var="message" scope="session"/>
	<form id="qryForm" class="search-dialog" action="queryQuestion.action"
		method="post">
		<i></i> <input name="key" type="text"
			value="${requestScope.result.key}" /> <input type="submit"
			data-loading-text="loading" value="查询" />
	</form>
	<table class="table table-striped table-hover">
		<tr>
			<td>题目</td>
			<td>选项一</td>
			<td>选项二</td>
			<td>选项三</td>
			<td>选项四</td>
			<td>正确选项</td>
			<td>科目</td>
			<td>操作</td>
		</tr>
		<c:forEach var="info" items="${requestScope.result.rows}">
			<tr onclick="modify(${info.id})">
				<td class="hidden">${info.url}</td>
				<td class="showImg">${info.question}</td>
				<td>${info.item1}</td>
				<td>${info.item2}</td>
				<td>${info.item3}</td>
				<td>${info.item4}</td>
				<td>${info.answer}</td>
				<td>${info.subject}</td>
				<td><a style="height: 25px;" class="btn btn-info" href="<%=basePath%>deleteQuestion.action?id=${info.id}">删除</a></td>
			</tr>
		</c:forEach>
		<tr style="text-align: center;">
			<td colspan="8"><span>
					共${requestScope.result.totalRows}条记录
					每页${requestScope.result.pageSize}条
					当前第${requestScope.result.pageNo}页
					共${requestScope.result.totalPages}页 </span> <span> <c:if
						test="${requestScope.result.pageNo==1}">
						上一页
					</c:if> <c:if test="${requestScope.result.pageNo>1}">
						<a href="javascript:goPage(${requestScope.result.pageNo-1})">上一页</a>
					</c:if> <c:if
						test="${requestScope.result.pageNo==requestScope.result.totalPages}">
						下一页
					</c:if> <c:if
						test="${requestScope.result.pageNo<requestScope.result.totalPages}">
						<a href="javascript:goPage(${requestScope.result.pageNo+1})">下一页</a>
					</c:if>
			</span></td>
		</tr>
	</table>
	<img alt="" src="img/photo.jpg">
	<form id="hiddenForm" action="queryQuestion.action" method="post">
		<input name="key" type="hidden" value="${requestScope.result.key}">
		<input name="pageNo" type="hidden" value="1"> <input
			name="pageSize" type="hidden" value="5">
	</form>
	<script>
	var modify = function(ids){
		$.get("checkQuestion.action",{id:ids},function(){
			window.location.href="<%=basePath%>jsp/admin/modifyQuestion.jsp";
		});
	};
	
		var goPage = function(no) {
			var qryForm = document.getElementById("qryForm");
			var hiddenForm = document.getElementById("hiddenForm");
			hiddenForm.pageNo.value = no;
			hiddenForm.key.value = qryForm.key.value;
			hiddenForm.submit();
		};
		$(document).ready(function(){
			$(".showImg").mouseover(function(){
				var url = $(this).parent().children(".hidden").text();
				if(url.length!=0){
					$("img").attr("src",url);
				}else{
					$("img").attr("src","img/photo.jpg");
				}
				$("img").show();
			});
			$(".showImg").mouseout(function(){
				$("img").hide();
			});
		});
	</script>

</body>
</html>