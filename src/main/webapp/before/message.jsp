<%@page import="com.cxx.pojo.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>百团纳新网站</title>
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/layui/layui.js"></script>

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
	<c:if test="${sessionScope.student==null}">
		<script type="text/javascript">
			top.location = "${pageContext.request.contextPath}/before/pwd_login.jsp?";
		</script>
	</c:if>
	<c:if test="${result != null}">
		<script type="text/javascript">
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg("${result}");
			});
		</script>
	</c:if>
	<div style="margin: 30px 50px">
		未读信息
		<c:if test="${count==0||count==null }">
			<span class="layui-badge">0</span>
		</c:if>
		<c:if test="${count!=0 && count!=null }">
			<span class="layui-badge">${count }</span>
		</c:if>
	</div>
	<div style="margin: 30px 150px;">
		<c:forEach items="${nom }" var="i">
			<div class="layui-card-header">${i.content }<span>${i.time}</span><span
					class="layui-badge-dot"></span>
			</div>
		</c:forEach>
		<c:forEach items="${yesm }" var="i">
			<div class="layui-card-header">${i.content }<span>${i.time }</span>
			</div>
		</c:forEach>
	</div>

	<script>
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
		layui.use('element', function() {
			var element = layui.element;

		});
	</script>
</body>
</html>
