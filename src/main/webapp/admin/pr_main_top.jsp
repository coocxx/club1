
<%@page import="java.util.*"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>网上订餐后台- 管理页面</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/layui/layui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
    //滚动时保存滚动位置
    $(window).scroll(function() {
        if ($(document).scrollTop() != 0) {
            localStorage.setItem("offsetTop", $(window).scrollTop());
        }
    });
    //onload时，取出并滚动到上次保存位置
    window.onload = function() {
        var offset = localStorage.getItem("offsetTop");
        $(document).scrollTop(offset);
    };
</script>
</head>

<body>

	<c:if
		test="${sessionScope.president!=null}">
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header">
				<div class="layui-logo">百团纳新后台</div>
				<!-- 头部区域（可配合layui已有的水平导航） -->
				<ul class="layui-nav layui-layout-left">
					<li class="layui-nav-item">
					
					
					   <%-- <a
                        href="${pageContext.request.contextPath }/index.jsp"
                        target="parent"><i class="layui-icon">&#xe68e;&nbsp;返回主页</i></a> --%>
					
					   
					</li>
				</ul>
				<ul class="layui-nav layui-layout-right">
					<li class="layui-nav-item"><a href="javascript:;"> <img
							src="http://t.cn/RCzsdCq" class="layui-nav-img">
								
                        ${president.student.name }
					</a> <!-- <dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl>  --></li>
					<li class="layui-nav-item">
							<a href="${pageContext.request.contextPath }/admin/prLogout"
								target="parent">退了</a>
				</li>
				</ul>
			</div>
		</div>
	</c:if>
	<c:if
		test="${sessionScope.president==null}">
		<div class="layui-layout layui-layout-admin">
			<div class="layui-header">
				<div class="layui-logo">百团纳新后台</div>
				
				<ul class="layui-nav layui-layout-left">
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/admin/admin_login.jsp"
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