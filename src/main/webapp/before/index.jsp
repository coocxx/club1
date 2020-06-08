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
    .span{
    
    }
    .span:hover{
        color:rgba(100,170,58,1);
    }
    .layui-card .layui-card-header a:hover{
        color:rgba(100,170,58,1);
    }
</style>
</head>
<body>
	<!-- style="background-image:url('images/bgd.png');" -->

	<c:if test="${reg!=null }">
		<script type="text/javascript">
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("<%=session.getAttribute("reg")%>");
          }); 
        </script>
		<%
			session.removeAttribute("reg");
		%>
	</c:if>
	<c:if test="${login!=null }">
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
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg("${result}");
			});
		</script>
	</c:if>

	<div class="container">
		<header> <jsp:include flush="fasle" page="top.jsp" /> </header>

		<%-- <div class="search">
			<img
				src="${pageContext.request.contextPath }/before/images/header2.png" />
			<div class="logo">
				<img
					src="${pageContext.request.contextPath }/before/images/logo.png" />
				<input type="text" placeholder="输入要搜索的内容" id="select" /> <a
					href="#"><span>搜索</span></a>
			</div> --%>
		<div class="flexslider"
			style="margin-top: 10px; width: 1160px; height: 300px; background-color: #EEEEEE;">
			<ul class="slides" style="margin-bottom: 10px">
				<li><img src="images/one.jpg"
					style="border: solid 3px rgba(153, 16, 16, 0.1); border-radius: 40%;" /></li>
				<li><img src="images/two.jpg"
					style="border: solid 3px rgba(153, 16, 16, 0.1); border-radius: 40%;" /></li>
				<li><img src="images/three.jpg"
					style="border: solid 3px rgba(153, 16, 16, 0.1); border-radius: 40%;" /></li>
				<li><img src="images/four.jpg"
					style="border: solid 3px rgba(153, 16, 16, 0.1); border-radius: 40%;" /></li>
				<li><img src="images/five.jpg"
					style="border: solid 3px rgba(153, 16, 16, 0.1); border-radius: 40%;" /></li>
			</ul>
		</div>



		<div class="main">
			<!-- <h1 style="display: inline-block; margin: 20px 10px 30px 350px;">社团信息</h1>
		<h3 style="display: inline-block;">
			<a href="">查看更多>></a>
		</h3>
 -->
			<div class="layui-container"
				style="margin-top: 20px; background-color: #EEEEEE; background-image: url('images/bgd.png'); color: rgb(51, 61, 73);">

				<div class="layui-row layui-col-space10 ">
					<!-- 左 -->
					<div class="layui-col-md3" style="margin-top: 20px;">
						<div class="layui-row layui-col-space10 ">
							<div class="layui-col-md" style="margin: 20px 0px 30px 0px">

								<div class="layui-card">
									<!--   style="background-color:rgb(57,61,73,0.2);"-->
									<div class="layui-card-header">
										社团类型<a href="${pageContext.request.contextPath }/before/selClubOfOneType?id=1" style="color: green">&emsp;<span class="span">查看更多>></span></a>
									</div>
									<c:forEach items="${clubTypes }" var="i" end="4" begin="0">
										<div class="layui-card-header" style="overflow: hidden;">
											<a
												href="${pageContext.request.contextPath }/before/selClubOfOneType?id=${i.id}">${i.name }</a>
										</div>
									</c:forEach>
									<div class="layui-card-header">......</div>
								</div>

							</div>
							<!-- 第二行 -->
							<%-- <div class="layui-col-md" style="margin: 20px 0px 30px 0px">

								<div class="layui-card">
									<!--   style="background-color:rgb(57,61,73,0.2);"-->
									<div class="layui-card-header">
										社团类型<a href="${pageContext.request.contextPath }/before/selclubType?id=1" style="color: green">&emsp;<span class="span">查看更多>></span></a>
									</div>
									<c:forEach items="${clubTypes }" var="i" end="4" begin="0">
										<div class="layui-card-header" style="overflow: hidden;">
											<a
												href="${pageContext.request.contextPath }/before/selclubType?id=${i.id}">${i.name }</a>
										</div>
									</c:forEach>
									<div class="layui-card-header">......</div>
								</div>

							</div> --%>
                            <!-- 第三行 -->
						</div>
					</div>
					<!-- 左 -->
					<%-- <div class="layui-col-md3" style="margin-top: 20px;">
						<div class="layui-row layui-col-space10 ">
							<div class="layui-col-md" style="margin-top: 20px;">

								<div class="layui-card">
									<div class="layui-card-header">
										社团信息<a href="${pageContext.request.contextPath }/before/selClubById?id=1&clubTypeId=1" style="color: green">&emsp;<span class="span">查看更多>></span></a>
									</div>
									<c:forEach items="${clubs }" var="i" end="4" begin="0">
										<div class="layui-card-header" style="overflow: hidden;">
											<a href="${pageContext.request.contextPath }/before/selClubById?id=${i.id}&clubTypeId=${i.clubTypeId}">${i.name }</a>
										</div>
									</c:forEach>
									<div class="layui-card-header">......</div>
									<!--  <div class="layui-card-body">
                                        卡片式面板面板通常用于非白色背景色的主体内<br> 从而映衬出边框投影
                                    </div> -->
								</div>

							</div>
						</div>
					</div> --%>

                    <!-- 左 -->
                    <div class="layui-col-md3" style="margin-top: 20px;">
                        <div class="layui-row layui-col-space10 ">
                            <div class="layui-col-md" style="margin-top: 20px;">

                                <div class="layui-card">
                                    <div class="layui-card-header">
                                        社团通告<a href="${pageContext.request.contextPath }/before/selNoticesById?clubId=1" style="color: green">&emsp;<span class="span">查看更多>></span></a>
                                    </div>
                                    <c:forEach items="${notices }" var="i" end="4" begin="0">
                                        <div class="layui-card-header" style="overflow: hidden;">
                                            <a
                                                href="${pageContext.request.contextPath }/before/selNoticesById?clubId=${i.club.id}">${i.title }</a>
                                        </div>
                                    </c:forEach>
                                    <div class="layui-card-header">......</div>
                                </div>

                            </div>
                        </div>
                    </div>
					<!-- 动-->
					<!-- 左 -->
					<div class="layui-col-md3" style="margin-top: 20px;">
						<div class="layui-row layui-col-space10 ">
							<div class="layui-col-md" style="margin-top: 20px;">

								<div class="layui-card">
									<div class="layui-card-header" style="overflow: hidden;">
										近期活动<a href="${pageContext.request.contextPath }/before/selEvents?clubId=1" style="color: green">&emsp;<span class="span">查看更多>></span></a>
									</div>
									<c:forEach items="${events }" var="i" end="4" begin="0">
										<div class="layui-card-header">
											<a
												href="${pageContext.request.contextPath }/before/selEventById?id=${i.id}&clubId=${i.club.id}">${i.name }</a>
										</div>
									</c:forEach>
									<div class="layui-card-header">......</div>
								</div>

							</div>
						</div>
					</div>

					<!-- 左 -->
					<div class="layui-col-md3" style="margin-top: 20px;">
						<div class="layui-row layui-col-space10 ">
							<div class="layui-col-md" style="margin-top: 20px;">

								<div class="layui-card">
									<div class="layui-card-header">
										新闻动态<a href="${pageContext.request.contextPath }/before/selNewsById" style="color: green">&emsp;<span class="span">查看更多>></span></a>
									</div>
									<c:forEach items="${news }" var="i" end="4" begin="0">
										<div class="layui-card-header" style="overflow: hidden;">
											<a
												href="${pageContext.request.contextPath }/before/selNewsById?id=${i.id}">${i.title }</a>
										</div>
									</c:forEach>
									<div class="layui-card-header">......</div>
								</div>

							</div>
						</div>
					</div>

					






				</div>
			</div>


<%-- 			<!-- 社团风采 -->
			<!-- 社团照片 -->

			<div class="layui-container"
				style="background-color: #EEEEEE; background-image: url('images/bgd.png'); margin-top: 20px;">

				<div class="layui-row layui-col-space10 ">
					<!-- 左 -->
					<div class="layui-col-md2"
						style="margin-top: 20px; margin-bottom: 20px">
						<div class="layui-row layui-col-space10 ">
							<div class="layui-col-md">

								<div class="layui-card">
									<div class="layui-card-header">
										社团信息<a href="">&emsp;查看更多>></a>
									</div>
									<c:forEach items="${clubs }" var="i" end="4" begin="0">
										<div class="layui-card-header">
											<a href="">${i.name }</a>
										</div>
									</c:forEach>
									<div class="layui-card-header">......</div>
									<!--  <div class="layui-card-body">
                                        卡片式面板面板通常用于非白色背景色的主体内<br> 从而映衬出边框投影
                                    </div> -->
								</div>

							</div>
						</div>
					</div>

					<!-- 右 -->
					<div class="layui-col-md9">
						<div class="layui-row layui-col-space5 ">
							<!-- (1,1) -->
							<c:forEach items="${clubs }" var="i" begin="0" end="2">
								<div class="layui-col-md4" style="margin-top: 20px;">
									<div class="layui-card">
										<div class="layui-card-header">
											<a
												href="${pageContext.request.contextPath }/before/selNoticeById?id=${i.id}">${i.name }</a>
										</div>
										<div class="layui-card-body"
											style="height: 240px; overflow: hidden">
											<p style="display: block; margin-bottem: 10px;">${i.introduce}</p>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
			</div> --%>



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
