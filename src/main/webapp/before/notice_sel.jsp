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
										<p style="color: green;">${notice.club.name }公告</p>
									</div>
									<c:forEach items="${notices }" var="i">
										<div class="layui-card-header" style="overflow: hidden;">
											<a
												href="${pageContext.request.contextPath }/before/selNoticesOfClub?id=${i.id}&clubId=${i.club.id}">${i.title }</a>
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
									href="${pageContext.request.contextPath }/before/selNoticesById?clubId=1">社团公告</a>&nbsp;>>&nbsp;<a
									href="${pageContext.request.contextPath }/before/selNoticesById?clubId=${notice.club.id}">${notice.club.name }</a>&nbsp;>>&nbsp;<a
									href="${pageContext.request.contextPath }/before/selNoticesOfClub?id=${notice.id}&clubId=${notice.club.id}">${notice.title }</a>
							</h5>
							<hr />
						</div>
						<div class="layui-row layui-col-space5"
							style="margin-top: 20px; margin-left: 10px; background-color: snow; margin-bottom: 20px;">

							<div style="">
								<h3 style="text-align: center; margin-top: 20px;margin-bottom: 10px;color: green;">${notice.title }</h3>
								
								<div
									style="width: 820px; float: left; overflow: hidden; margin-top: 20px; margin-bottom: 5px; margin-left: 18px;">

									
									<h5
										style="margin-top: 3px; margin-bottom: 10px;"
										class="turn">
										<a
											href="${pageContext.request.contextPath }/before/selNoticesOfClub?id=${notice.id}&clubId=${notice.club.id}">${notice.title }</a>

									</h5>
									<p style="margin-top: 3px; text-indent: 2em; margin-bottom: 10px;"
										class="turn">${notice.content }</p>
									<div style="margin: 3px; margin-bottom: 25px" >
										<div style="margin: 3px;">
											<span style="color: green;">发布时间：</span>
											<p style="text-indent: 2em; color: grey; margin: 5px;">${notice.time }</p>
										</div>

									</div>
								</div>
								<!-- 第二列 -->
							</div>
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
