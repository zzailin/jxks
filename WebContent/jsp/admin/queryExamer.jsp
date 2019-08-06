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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<base href="<%=basePath%>"></base>
<title></title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/manage.css" />
<style type="text/css">
table {
	margin-top: 40px;
}
</style>
</head>
<body>
	<hr>
	<form id="qryForm" class="search-dialog" action="queryExamer.action"
		method="post">
		<i></i> <input name="key" type="text" value="${requestScope.result.key}" /> <input
			type="submit" data-loading-text="loading" value="查询" />
	</form>
	<table class="table table-striped table-hover">
		<tr>
			<td>账号</td>
			<td>姓名</td>
			<td>科目</td>
			<td>成绩1</td>
			<td>成绩4</td>
			<td>操作</td>
		</tr>
		<c:forEach var="info" items="${requestScope.result.rows}">
			<tr>
				<td>${info.account}</td>
				<td>${info.name}</td>
				<td>${info.userInfo.subject}</td>
				<td>${info.userInfo.score1}</td>
				<td>${info.userInfo.score4}</td>
				<td><a href="<%=basePath %>checkExamer.action?id=${info.id}">查看/编辑</a></td>
			</tr>
		</c:forEach>
		<tr style="text-align: center;">
			<td colspan="7"><span>
					共${requestScope.result.totalRows}条记录
					每页${requestScope.result.pageSize}条
					当前第${requestScope.result.pageNo}页
					共${requestScope.result.totalPages}页 </span> <span>
					 <c:if	test="${requestScope.result.pageNo==1}">
						上一页
					</c:if> 
					<c:if test="${requestScope.result.pageNo>1}">
						<a href="javascript:goPage(${requestScope.result.pageNo-1})">上一页</a>
					</c:if>
					<c:if test="${requestScope.result.pageNo==requestScope.result.totalPages}">
						下一页
					</c:if>
					<c:if test="${requestScope.result.pageNo<requestScope.result.totalPages}">
						<a href="javascript:goPage(${requestScope.result.pageNo+1})">下一页</a>
					</c:if>
			</span></td>
		</tr>
	</table>
	<form id="hiddenForm" action="queryExamer.action" method="post">
		<input name="key" type="hidden" value="${requestScope.result.key}"> <input
			name="pageNo" type="hidden" value="1"> <input name="pageSize"
			type="hidden" value="6">
	</form>
	<script>
		var goPage = function(no) {
			var qryForm = document.getElementById("qryForm");
			var hiddenForm = document.getElementById("hiddenForm");
			hiddenForm.pageNo.value = no;
			hiddenForm.key.value = qryForm.key.value;
			hiddenForm.submit();
		}
	</script>

</body>
</html>