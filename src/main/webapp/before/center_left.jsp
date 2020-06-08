<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/layui/layui.js"></script>
	<style type="text/css">

	</style>
</head>

<body style="margin: 0px 0px;" id="body">
    <c:if test="${sessionScope.student==null}">
        <script type="text/javascript">
            top.location = "${pageContext.request.contextPath}/before/pwd_login.jsp?";
        </script>
    </c:if>
	<!--  -->
	<c:if
		test="${sessionScope.student!=null}">
		<div class="layui-side">
			<div class="layui-side-scroll layui-bg-black">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree">
					<li class="layui-nav-item " ><a
						href="${pageContext.request.contextPath }/before/selMessage"  target="main">查看消息</a>
					</li>
					<li class="layui-nav-item " ><a
						href="${pageContext.request.contextPath }/before/student.jsp"  target="main">修改个人信息</a>
					</li>
					<li class="layui-nav-item " ><a
						href="${pageContext.request.contextPath }/before/student_updpwd.jsp"  target="main">修改密码</a>
					</li>
					<li class="layui-nav-item " ><a
						href="${pageContext.request.contextPath }/before/joinClubHistory" target="main">申请加入社团历史</a>
					</li>
					<li class="layui-nav-item " ><a
						href="${pageContext.request.contextPath }/before/preStuClubApply" target="main">申请新建社团</a>
					</li>
					<li class="layui-nav-item " ><a
						href="${pageContext.request.contextPath }/before/selStuClubApp" target="main">申请新建社团历史</a>
					</li>
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
