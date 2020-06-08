
<%@page import="java.util.*"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>百团纳新</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/layui/layui.js"></script>

</head>

<body>

	<c:if test="${sessionScope.student !=null}">
		<div class="layui-layout layui-layout-admin layui-bg-black">
			<div class="layui-header">
				<div class="layui-logo">百团纳新</div>
				<!-- 头部区域（可配合layui已有的水平导航） -->
				<ul class="layui-nav layui-layout-left">
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/index.jsp"
						target="parent"><i class="layui-icon">&#xe68e;&nbsp;返回首页</i></a></li>

					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/before/selClubOfOneType?id=1" target="parent">社团</a></li>
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/before/selNoticesById?clubId=1" target="parent">社团公告</a></li>
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/before/selEvents?clubId=1" target="parent">近期活动</a></li>
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/before/selNewsById?id=1" target="parent">新闻动态</a></li>

				</ul>



				<ul class="layui-nav layui-layout-right">
					<li class="layui-nav-item"><img src="http://t.cn/RCzsdCq"
						class="layui-nav-img"> <c:if
							test="${sessionScope.student!=null}">
                        ${student.name }
                    </c:if> <c:if test="${sessionScope.student==null}">
                                                                          我
                    </c:if></li>
					<li class="layui-nav-item"><c:if
							test="${sessionScope.student!=null}">
							<a href="${pageContext.request.contextPath }/before/logout"
								target="parent">注销</a>
						</c:if></li>
				</ul>
			</div>
		</div>
	</c:if>
	<c:if test="${sessionScope.student==null}">
		<div class="layui-layout layui-layout-before">
			<div class="layui-header">
				<div class="layui-logo">百团纳新</div>

				<ul class="layui-nav layui-layout-left">
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/before/pwd_login.jsp"
						target="parent">登录</a></li>
				</ul>
			</div>
		</div>

	</c:if>
	<script>
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
		layui.use([ 'element', 'form' ], function() {
			var element = layui.element;
			var form = layui.form;

		});
	</script>
</body>
</html>