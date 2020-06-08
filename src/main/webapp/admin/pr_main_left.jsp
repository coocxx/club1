<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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

<body style="margin: 0px 0px">
	<c:if test="${sessionScope.president==null}">
        <script type="text/javascript">
           top.location="${pageContext.request.contextPath}/admin/prsdt_login.jsp?";
        </script>
    </c:if>
	<!--社团管理员登录  -->
	<c:if
		test="${ sessionScope.president!=null}">
		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll ">
				<!-- 左侧导航区域（可配合layui已有的垂直导航） -->
				<ul class="layui-nav layui-nav-tree">
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/admin/selMessage" target="main">查看消息<c:if test="${count!=0&&count!=null }"><span class="layui-badge layui-bg-green">${count }</span></c:if></a></li>
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/admin/pr_student.jsp" target="main">个人信息管理</a></li>
					<li class="layui-nav-item " ><a
                        href="${pageContext.request.contextPath }/admin/joinClubHistory" target="main">申请社团历史</a>
                    </li>
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/admin/selClubImage" target="main">社团图片</a></li>
                    
					<li class="layui-nav-item"><a href="javascript:;"
						target="main">设置发送模板</a>
						<dl class="layui-nav-child">
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selModelRejectPr"
									target="main">拒绝模板</a>
							</dd>
							
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selModelPassPr"
									target="main">通过模板</a>
							</dd>
							
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;"
						target="main">社团成员</a>
						<dl class="layui-nav-child">
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selClubMemberByPage?active=pr"
									target="main">查询</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/pr_member_add.jsp"
									target="main">添加</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selMemberQuitByPage?active=pr"
									target="main">退出社员信息</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selMemberJoinByPage?active=pr"
									target="main">申请加入社团信息</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a href="javascript:;"
						target="main">社团活动</a>
						<dl class="layui-nav-child">
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selEventByPage?action=pr"
									target="main">查询</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/pr_event_add.jsp"
									target="main">添加</a>
							</dd>
						</dl></li>

					<li class="layui-nav-item"><a href="javascript:;"
						target="main">公告管理</a>
						<dl class="layui-nav-child">
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/selNoticeByPage?action=pr"
									target="main">查询公告</a>
							</dd>
							<dd>
								<a
									href="${pageContext.request.contextPath }/admin/pr_notice_add.jsp?action=pr"
									target="main">添加公告</a>
							</dd>
						</dl></li>
					<li class="layui-nav-item"><a
						href="${pageContext.request.contextPath }/admin/selNewsByPage?action=pr"
						target="main">查询新闻</a></li>
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
