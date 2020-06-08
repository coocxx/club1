<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<meta charset="utf-8" />
<title>百团纳新网站首页</title>
<link href="${pageContext.request.contextPath }/before/css/first.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/before/js/jquery-1.8.3.min.js"></script>
</head>
<body>
	<div id="container">
		<header>
			<div class="content">
				<ul class="left">
					<li><a href="${pageContext.request.contextPath }/before/index.jsp">首页</a></li>
					<li><a href="#">通告</a></li>
					<li><a href="#">最近活动</a></li>
					<li><a href="#">新闻动态</a></li>
				</ul>
				<ul class="right">
					<li><a href="${pageContext.request.contextPath }/before/reg.jsp">注册</a></li>
					<li><a href="${pageContext.request.contextPath }/before/pwd_login.jsp">登录</a></li>
				</ul>
			</div>
		</header>
		<div class="main">
			<p>百团纳新·青岛科技大学</p>
			<div class="entry">
				<a href="${pageContext.request.contextPath }/before/index.jsp">进入</a>
			</div>
		</div>



		<footer>
			<p>青岛科技大学（©版权所有)</p>
		</footer>
	</div>
</body>
</html>
