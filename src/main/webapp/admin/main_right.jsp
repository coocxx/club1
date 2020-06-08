<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询管理员</title>
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/layui/layui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
	
<style type="text/css">
    .way{
        width:60px;
        height:60px;
        margin:5px 4px;
        background-color:rgb(242,242,242);
        border-radius: 20%;
    }
    .way>a{
        display:inline-block;
        margin-top:12px;
        margin-left:2px; 
        text-align:center;
    }
    .way>a:hover{
        color:black;
    }
    .way1{
        width:86px;
        height:90px;
        margin:7px 2px;
        background-color:rgb(242,242,242);
        border-radius: 50%;
    }
    .way1>a{
        display:inline-block;
        margin-top:12px;
        margin-left:9px; 
        text-align:center;
    }
    .way1>a:hover{
        color:black;
    }
    .way2{
        width:250px;
        height:200px;
        text-align:center;
        margin:7px 2px;
        background-color:rgb(242,242,242);
        border-radius: 50%;
    }
    .way2>a>span{
        display:block;
        margin:60px auto;
        font-size:60px;
    }
    .way2>a{
        display:inline-block;
        margin-top:12px;
        margin-left:9px; 
        text-align:center;
        font-size:30px
    }
    .way2>hover{
        color:black;
    }
    
</style>
<script type="text/javascript">
$(function() {
	
	$(".way").on('mouseover',function () {
		$(this).css({
			 "background-color":"#eeeeee",
		 });
	})
	$(".way").on('mouseout',function () {
		$(this).css({
			 "background-color":"rgb(242,242,242)",
		 });
	})
	$(".way1").on('mouseover',function () {
		$(this).css({
			 "background-color":"#eeeeee",
		 });
	})
	$(".way1").on('mouseout',function () {
		$(this).css({
			 "background-color":"rgb(242,242,242)",
		 });
	})
})
</script>
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
<body style="background-color: rgb(235, 235, 236)">
	<div>
		<div class="layui-container" style="margin-top:60px">
			<div class="layui-row layui-col-space15">
			<!-- 一个面板：快捷方式 -->
				<div class="layui-col-md4">
					<div class="layui-row"
						style="margin-top: 15px;">
						<div class="layui-card">
							<div class="layui-card-header">系统功能</div>
							<div class="layui-card-body">
							     <!-- 一个div选择  -->
								<div class="layui-row grid-demo">
								    <div class="layui-col-md3 way"><a href="" >待审核信息管理</a></div>
									<div class="layui-col-md3 way"><a href="${pageContext.request.contextPath }/admin/selAdminByPage" target="main">管理员</a></div>
									<div class="layui-col-md3 way"><a href="" >社团</a></div>
									<div class="layui-col-md3 way"><a href="" >社团类型</a></div>
									
									
								</div>
								<div class="layui-row grid-demo">
                                    <div class="layui-col-md3 way"><a href="" >社团成员</a></div>
                                    <div class="layui-col-md3 way"><a href="" >社团活动</a></div>
                                    <div class="layui-col-md3 way"><a href="" >公告管理</a></div>
                                    <div class="layui-col-md3 way"><a href="" >新闻管理</a></div>
								</div>
								<div class="layui-row grid-demo ">
								    <div class="layui-col-md3 way"><a href="" >学生管理</a></div>
									<div class="layui-col-md3 way"><a href="" >学院管理</a></div>
                                    <div class="layui-col-md3 way"><a href="" >专业管理</a></div>
                                    <div class="layui-col-md3 way"><a href="" >班级管理</a></div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
				<!-- 待办事项 -->
				<div class="layui-col-md4">
					<div class="layui-row"
						style="margin-top: 15px;">
						<div class="layui-card">
							<div class="layui-card-header">快捷方式</div>
							<div class="layui-card-body">
							     <!-- 一个div选择  -->
								<div class="layui-row grid-demo">
									<div class="layui-col-md4 way1"><a href="">申请社团</a></div>
									<div class="layui-col-md4 way1"><a href="">审核活动</a></div>
									<div class="layui-col-md4 way1"><a href="">审核社团成员</a></div>
								</div>
								<div class="layui-row grid-demo">
									<div class="layui-col-md4 way1"><a href="">社团管理</a></div>
									<div class="layui-col-md4 way1"><a href="">社团管理</a></div>
									<div class="layui-col-md4 way1"><a href="">社团管理</a></div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
				<!-- 上线人数 -->
				<div class="layui-col-md4" >
					<div class="layui-row"
						style="margin-top: 15px;">
						<div class="layui-card" style="height:270px">
							<div class="layui-card-header">在线人数</div>
							<div class="layui-card-body">
							     <!-- 一个div选择  -->
							     <div class="layui-col-md4 way2"><a href="">
							     <span style="color:#5FB878;text-align:center;">${inline }</span>
							     </a></div>
								<!-- <div class="layui-row grid-demo">
									
								</div> -->
								<!-- <div class="layui-row grid-demo">
									<div class="layui-col-md4 way1"><a href="">社团管理</a></div>
									<div class="layui-col-md4 way1"><a href="">社团管理</a></div>
									<div class="layui-col-md4 way1"><a href="">社团管理</a></div>
								</div>
								 -->
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		layui.use([ 'layer', 'form' ], function() {
			var laypage = layui.laypage;
			var form = layui.form;

		});
	</script>
</body>