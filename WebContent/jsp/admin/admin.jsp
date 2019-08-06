<%@ page pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>"></base>
<title>基于B/S的驾校考试管理系统后台管理</title>
<link href="css/StudentStyle.css" rel="stylesheet" type="text/css" />
<link href="css/ks.css" rel="stylesheet" type="text/css" />
<script src="js/jquery-3.1.1.min.js" type="text/javascript"></script>
</head>
<body>



	<div class="banner">
		<div class="bgh">
			<div class="page">
				<div id="logo">
					<a href="<%=basePath %>jsp/admin/admin.jsp"><img src="img/head.jpg" alt=""
						width="165" height="48" />
					</a>
				</div>
				<div class="topxx">
					${sessionScope.userEntity.name}，欢迎您！ <a href="<%=basePath %>jsp/admin/personInfo.jsp" target="mainIframe">我的信息</a>
					<a href="<%=basePath %>jsp/admin/modifyPassword.jsp" target="mainIframe">密码修改</a> 
					<a href="loginOut.action">安全退出</a>
				</div>
				<div class="blog_nav">
					<ul>
						<li><a href="<%=basePath %>jsp/admin/personInfo.jsp" target="mainIframe">我的信息</a></li>
						<li><a href="queryExamer.action" target="mainIframe">考生信息</a></li>
						<li><a href="queryQuestion.action" target="mainIframe">试题信息</a></li>
						<li><a href="<%=basePath %>jsp/admin/about.jsp" target="mainIframe">关于</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="page">
		<div class="box mtop">
			<div class="leftbox">
				<div class="l_nav2">
					<div class="ta1">
						<strong>个人中心</strong>
						<div class="leftbgbt"></div>
					</div>
					<div class="cdlist">
						<div>
							<a href="<%=basePath %>jsp/admin/personInfo.jsp" target="mainIframe">我的信息</a>
						</div>
						<div>
							<a href="<%=basePath %>jsp/admin/personInfo.jsp" target="mainIframe">个人信息修改</a>
						</div>
						<div>
							<a href="<%=basePath %>jsp/admin/modifyPassword.jsp" target="mainIframe">密码修改</a>
						</div>
					</div>
					<div class="ta1">
						<strong>考生管理</strong>
						<div class="leftbgbt2"></div>
					</div>
					<div class="cdlist">
						<div>
							<a href="queryExamer.action" target="mainIframe">考生查询</a>
						</div>
						<div>
							<a href="<%=basePath %>jsp/admin/addExamer.jsp" target="mainIframe">添加考生</a>
						</div>
					</div>
					<div class="ta1">
						<strong>试题管理</strong>
						<div class="leftbgbt2"></div>
					</div>
					<div class="cdlist">
						<div>
							<a href="queryQuestion.action" target="mainIframe">试题查询</a>
						</div>
						<div>
							<a href="<%=basePath %>jsp/admin/addQuestion.jsp" target="mainIframe">添加试题</a>
						</div>
					</div>

					<div class="ta1">
						<strong>管理中心</strong>
						<div class="leftbgbt2"></div>
					</div>
					<div class="cdlist">
						<div>
							<a href="<%=basePath %>check.action?action=add" target="mainIframe">添加管理员</a>
						</div>
						<div>
							<a href="<%=basePath %>check.action?action=query" target="mainIframe">管理员查询</a>
						</div>
					</div>
				</div>
			</div>
			<div class="rightbox">
				<iframe name="mainIframe" src="<%=basePath %>jsp/admin/about.jsp" width="100%" height="450px" scrolling="atuo" frameborder="0" >
					本系统使用了框架技术，但是您的浏览器不支持框架，请升级您的浏览器以便正常访问。
				</iframe>
			</div>
		</div>
		<div class="footer">
			<p>&copy;copyright 2017  张在林 zz_zlin@foxmail.com 版权所有</p>
		</div>
	</div>
</body>
</html>
