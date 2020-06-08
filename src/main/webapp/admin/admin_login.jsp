<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>百团纳新后台超级管理员登陆页面</title>
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
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
<body
	style="background-image: url('images/timg.jpg'); background-repeat: repeat;">
	<c:if test="${login != null && login!=''}">
		<script type="text/javascript">
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg("${login}");
			});
		</script>
		<%
			session.removeAttribute("login");
		%>
	</c:if>
	<div class="container" style="margin-top: 60px">
		<main>
		<div class="login"
			style="height: 440px; width: 600px; margin: 20px auto; background-color: white;">
			<div class="title" style="height: 70px; width: 800px;">
				<h1
					style="margin: 15px 0px 0px 230px; color: rgba(57, 61, 73, 0.7); line-height: 90px">管理员登录</h1>
				&emsp;
			</div>
			<div class="content"
				style="height: 300px; width: 600px; text-align: center; margin: 10px auto; border-radius: 20px;">
				<ul>
					<!-- rgb(153,16,16) -->
					<li style="display: inline-block; margin: 0px 30px 25px 20px"><a
						href="${pageContext.request.contextPath }/admin/admin_login.jsp"
						style="color: rgb(47, 169, 158); font-size: 17px;">超级管理员</a></li>
					<li style="display: inline-block; margin: 30px 0px 25px 10px"><a
						href="${pageContext.request.contextPath }/admin/prsdt_login.jsp"
						style="color: rgba(57, 61, 73, 0.5); font-size: 17px;">社团管理员</a></li>
				</ul>

				<!-- return verifyLogin() -->
				<form class="layui-form form1" name="form1"
					action="${pageContext.request.contextPath }/admin/adminLogin"
					method="post">
					<div class="layui-form-item" class="pwd">
						<div class="layui-input-block">
							<input type="text" id="name" placeholder="管理员名称" value="${loginAdmin.name }" style="height: 43px; width: 390px"
								class="layui-input" name="name" lay-verify="required" lay-reqText="管理员名称不能为空" />
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label"></label>
						<div class="layui-input-inline">
							<input type="password" id="password" style="height: 43px; width: 390px" placeholder="密码"
								name="password" value="" class="layui-input" lay-verify="required" lay-reqText="密码不能为空" />
						</div>
					</div>

					<div class="layui-form-item">
						<div class="layui-input-block" style="margin: 0px auto;">
							<button class="layui-btn" lay-submit style="width: 185px; height: 43px">登录</button>
							<button type="reset" style="width: 185px; height: 43px" class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>

				</form>
			</div>
		</div>

		</main>

	</div>
	<script>
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
		layui.use([ 'element', 'form', 'layer' ], function() {
			var element = layui.element;
			var form = layui.form;
			var layer = layui.layer;

			form.verify({
				name : [ /^[a-zA-Z0-9]{3,20}$/, '用户名组成为字母3-20位' ],
				sid : [ /^[0-9]{10}$/, '学号组成为10位数字' ],
				password : [ /^[a-zA-Z0-9]{6,20}$/, '密码组成为字母或数字,6-20位' ],
				numoro : [ /^[0-9]{0,}$/, '组成为数字' ],
				age : [ /^[1]?[0-9]{1,2}$/, '年龄为0-99' ]

			})
		});
	</script>
</body>
</html>