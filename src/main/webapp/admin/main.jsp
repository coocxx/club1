<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>百团纳新-后台管理</title>
<meta http-equiv=Content-Type content=text/html;charset=gb2312>
<style>
html {
	margin: 0px 0px;
	background-color: rgb(35, 38, 46);
}
</style>
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
<frameset rows="64,*" frameborder="no" border="0" framespacing="0">
	<frame src="${pageContext.request.contextPath }/admin/main_top.jsp"
		noresize="noresize" frameborder="0" name="topFrame" scrolling="no"
		marginwidth="0" marginheight="0" target="main" />
	<frameset cols="200,*" rows="560,*" id="frame">
		<!--  <frameset cols="200,*" rows="560,*" id="frame1"> -->
		<frame src="${pageContext.request.contextPath }/admin/main_left.jsp"
			name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0"
			frameborder="0" scrolling="no" target="main" />
		<!--  </frameset> -->
		<frame src="${pageContext.request.contextPath }/admin/inline"
			name="main" marginwidth="0" marginheight="0" frameborder="0"
			scrolling="auto" target="main" style="background-color: white" />
		<!-- rgba(228,228,228) -->
	</frameset>
	<noframes>
		<body class="layui-layout-body">

			
		</body>
	</noframes>
</frameset>
</html>
