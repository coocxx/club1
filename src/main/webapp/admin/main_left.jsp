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

<body style="margin: 0px 0px">
	<c:if test="${sessionScope.admin==null}">
		<script type="text/javascript">
           top.location="${pageContext.request.contextPath}/admin/admin_login.jsp?";
        </script>
	</c:if>
	<c:if test="${login != null}">
		<script type="text/javascript">
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("<%=session.getAttribute("login")%>");
			});
		</script>
		<%
			session.removeAttribute("login");
		%>
	</c:if>
	<c:if test="${result != null}">
		<script type="text/javascript">
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("<%=session.getAttribute("result")%>
			");
			});
		</script>
		<%
			session.removeAttribute("result");
		%>
	</c:if>
	<c:if test="${sessionScope.admin!=null}">
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll ">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree">
					<li class="layui-nav-item layui-nav-itemed"><a
						href="${pageContext.request.contextPath }/admin/admin_center.jsp"
						target="main">个人信息</a></li>
					<li class="layui-nav-item layui-nav-itemed"><a class=""
						href="javascript:;">审核</a>
						<dl class="layui-nav-child">
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selAppClubByPage"
									target="main">社团</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selAppMemberByPage"
									target="main">社团成员</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selAppEventByPage"
									target="main">活动</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a class="" href="javascript:;">管理员</a>
						<dl class="layui-nav-child">
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selAdminByPage"
									target="main">查询</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/admin_add.jsp"
									target="main">添加</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;"
						target="main">社团</a>
						<dl class="layui-nav-child">
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selClubByPage"
									target="main">查询</a>
							</dd>
							<dd>
								<a href="${pageContext.request.contextPath }/admin/preAddClub"
									target="main">添加</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selClubApplyByPage"
									target="main">申请信息</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:void(0);"
						target="main">社团类型</a>
						<dl class="layui-nav-child">
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selClubTypeByPage"
									target="main">查询</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/clubType_add.jsp"
									target="main">添加</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;"
						target="main">社团成员</a>
						<dl class="layui-nav-child">
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selClubMemberByPage"
									target="main">查询</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/preAddClubMember"
									target="main">添加</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selMemberQuitByPage"
									target="main">退出社员信息</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selMemberJoinByPage"
									target="main">申请加入社团信息</a>
							</dd>
							<%-- <dd><a href="${pageContext.request.contextPath }/admin/.jsp" target="main">换届</a></dd> --%>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;"
						target="main">社团活动</a>
						<dl class="layui-nav-child">
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selEventByPage"
									target="main">查询</a>
							</dd>
							<dd>
								<a href="${pageContext.request.contextPath }/admin/preAddEvent"
									target="main">添加</a>
							</dd>
							<!-- <dd>
								<a href="javascript:;" target="main">换届</a>
							</dd> -->
						</dl></li>

					<li class="layui-nav-item"><a href="javascript:;"
						target="main">公告管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selNoticeByPage"
									target="main">查询公告</a>
							</dd>
							<dd>
								<a href="${pageContext.request.contextPath }/admin/preAddNotice"
									target="main">添加公告</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;"
						target="main">新闻管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selNewsByPage"
									target="main">查询新闻</a>
							</dd>
							<dd>
								<a href="${pageContext.request.contextPath }/admin/news_add.jsp"
									target="main">添加新闻</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/admin/selStuClubByPage"
						target="main">查询学生</a></li>
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/admin/selAcademyByPage"
						target="main">查询学院</a></li>
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/admin/selMajorByPage"
						target="main">查询专业</a></li>
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/admin/selClassesByPage"
						target="main">查询班级</a></li>

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
