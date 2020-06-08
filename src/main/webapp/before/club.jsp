<%@page import="com.cxx.pojo.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>百团纳新网站</title>
<link href="${pageContext.request.contextPath }/before/css/slider.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<%-- <link href="${pageContext.request.contextPath }/before/css/index.css"
    rel="stylesheet" type="text/css" /> --%>
<!-- 引入slider -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<!-- 引入slider -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/slider.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/layui/layui.js"></script>


<script>
	/* jQuery 初始化轮播插件 */
	$(function() {
		$('.flexslider').flexslider({
			animation : "slide",
			direction : "horizontal",
			easing : "swing"
		});
	});

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
<style type="text/css">
.turn a {
	
}

.turn a:hover {
	color: rgba(100, 170, 58, 1);
}
</style>

</head>
<body>
	<c:if test="${result != null}">
		<script type="text/javascript">
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg("${result}");
			});
		</script>
	</c:if>

	<div class="container">
		<header> <jsp:include flush="fasle" page="top.jsp" /> </header>


		<div class="main">
			<div class="layui-container turn"
				style="margin-top: 20px; background-color: #EEEEEE; color: rgb(51, 61, 73);">

				<div class="layui-row layui-col-space10 ">
					<!-- 左 -->
					<div class="layui-col-md3" style="margin-top: 5px;">
						<div class="layui-row layui-col-space10 ">
							<div class="layui-col-md" style="margin: 20px 0px 30px 0px">

								<div class="layui-card" class="turn">
									<!--   style="background-color:rgb(57,61,73,0.2);"-->
									<div class="layui-card-header">
										<p style="color: green;">社团类型</p>
									</div>
									<c:forEach items="${clubTypes }" var="i">
										<div class="layui-card-header" style="overflow: hidden;">
											<a
												href="${pageContext.request.contextPath }/before/selClubOfOneType?id=${i.id}">${i.name }</a>
										</div>
									</c:forEach>
								</div>

							</div>
						</div>
					</div>
					<!-- 右-->
					<div class="layui-col-md9">
						<div style="margin-left: 30px">
							<hr />
							<h5>
								当前位置&nbsp;:&nbsp;<a
									href="${pageContext.request.contextPath }/index.jsp">首页</a>&nbsp;>>&nbsp;<a
									href="${pageContext.request.contextPath }/before/selClubOfOneType?id=1">社团</a>&nbsp;>>&nbsp;<a
									href="${pageContext.request.contextPath }/before/selClubOfOneType?id=${clubType.id}">${clubType.name }</a>
							</h5>
							<hr />
						</div>
						<div class="layui-row layui-col-space5"
							style="margin-top: 20px; margin-left: 10px;">

							<h3 style="text-align: center; color: green;">${clubType.name }</h3>
							<c:forEach items="${clubs }" var="i">
								<div
									style="float: left; overflow: hidden; width: 250px; height: 250px; margin-top: 20px; margin-bottom: 20px; margin-left: 18px; background-color: snow">
									<h5
										style="margin-top: 3px; margin-bottom: 10px; text-align: center;"
										class="layui-card-header turn">
										<i><a
											href="${pageContext.request.contextPath }/before/selClubById?id=${i.id}&clubTypeId=${i.clubTypeId}">${i.name }</a><a
											href="${pageContext.request.contextPath }/before/addMemberJoin?id=${i.id}&clubTypeId=${i.clubTypeId}"><i
												class="layui-icon" style="text-size: 13px;">&emsp;&#xe61f;</i></a></i>
									</h5>

									<div
										style="margin: 3px; margin-left: 10px; margin-bottom: 5px; font-size: 12px">
										<div style="margin: 3px; margin-left: 20px">
											<i class="layui-icon" style="color: rgb(57, 160, 107)">&#xe637;&nbsp;<span
												style="font-size: 13px; color: grey">建立时间：</span></i> <span
												style="color: grey;">${i.time }</span>
										</div>

										<div
											style="margin-top: 3px; margin-bottom: 3px; margin-left: 20px">
											<i class="layui-icon" style="color: rgb(57, 160, 107)">&#xe612;&nbsp;<span
												style="font-size: 13px; color: grey;">&nbsp;创&nbsp;始&nbsp;人：</span></i>
											<span style="color: grey;">${i.student.name }</span>
										</div>
										<div style="margin: 3px; margin-left: 20px">
											<i class="layui-icon" style="color: rgb(57, 160, 107)">&#xe678;&nbsp;<span
												style="font-size: 13px; color: grey">联系方式：</span></i> <span
												style="color: grey;">${i.student.phone }</span>
										</div>
										<div style="margin-top: 13px;">
											<p style="color: grey;">社团宗旨:</p>
											<p style="font-size: 12px; color: grey; text-indent: 2em;">${i.notice }</p>
										</div>

									</div>
									<%-- <h5
                                    style="margin-top: 3px; margin-bottom: 10px; text-align: center;">
                                    <a
                                        href="${pageContext.request.contextPath }/before/?id=${i.id}"><i
                                        class="layui-icon" style="text-size: 13px;">&#xe61f;加入社团</i></a>
                                </h5> --%>
								</div>


							</c:forEach>
							<!-- 第二列 -->
						</div>
					</div>








				</div>
			</div>



		</div>

		<footer
			style="height:90px; text-align: center; border: rgba(153,16,16,0.1) solid thin;margin-top: 40px;background-image:url('images/bgd1.png');background-color:rgb(57,61,73,0.1)">
		<p
			style="color: white; font-size: 12px; text-align: center; padding-top: 40px;">青岛科技大学(©版权所有)</p>
		</footer>

	</div>
	<script>
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
		layui.use('element', function() {
			var element = layui.element;

		});
	</script>
</body>
</html>
