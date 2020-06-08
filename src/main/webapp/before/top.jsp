<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body >

	<div class="content">
		<ul class="layui-nav" style="background-color:rgb(57,61,73,0.1);background-image:url('images/top1.png');">
			<li class="" style="display:inline-block;">
			     <img src="${pageContext.request.contextPath }/before/images/2.png" style="width:100px;height:50px"/></li>
			<li class="layui-nav-item" style="margin-left: 100px"><a
				href="${pageContext.request.contextPath }/index.jsp">首页</a></li>
			<li class="layui-nav-item"><a
				href="${pageContext.request.contextPath }/before/selClubOfOneType?id=1">社团</a></li>
			<li class="layui-nav-item"><a
				href="${pageContext.request.contextPath }/before/selNoticesById?clubId=1">社团公告</a></li>
			<li class="layui-nav-item"><a href="${pageContext.request.contextPath }/before/selEvents?clubId=1">近期活动</a></li>
			<li class="layui-nav-item"><a href="${pageContext.request.contextPath }/before/selNewsById">新闻动态</a></li>


			<c:if test="${sessionScope.student != null}">

				<li class="layui-nav-item" style="float: right; margin-right: 100px">
					<a href=""><img src="//t.cn/RCzsdCq" class="layui-nav-img">
					<c:if test="${sessionScope.student.name!=null }">${sessionScope.student.name }</c:if>
					<c:if test="${sessionScope.student.name==null }">我</c:if>
					</a>
					<dl class="layui-nav-child">
						<!-- <dd>
							<a href="javascript:;">修改信息</a>
						</dd> -->
						<dd>
							<a href="${pageContext.request.contextPath }/before/logout">注销</a>
						</dd>
					</dl>
				</li>
				<li class="layui-nav-item" style="float: right"><a href="${pageContext.request.contextPath }/before/center.jsp">个人中心<span
						class="layui-badge-dot"></span></a></li>
			</c:if>

			<c:if test="${sessionScope.student == null}">
				<%-- <li class="layui-nav-item" style="float: right; margin-right: 100px"><a
					href="${pageContext.request.contextPath }/before/regClass">注册</a></li> --%>
				<li class="layui-nav-item" style="float: right;margin-right:100px"><a
					href="javascript:;">
						登录</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="${pageContext.request.contextPath }/before/pwd_login.jsp">学生登录</a>
						</dd>
						<dd>
							<a href="${pageContext.request.contextPath }/admin/prsdt_login.jsp">社长管理员登录</a>
						</dd>
						<dd>
							<a href="${pageContext.request.contextPath }/admin/admin_login.jsp">超级管理员登录</a>
						</dd>
					</dl>
					</li>
			</c:if>

		</ul>


	</div>
</body>
</html>