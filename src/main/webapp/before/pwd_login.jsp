<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/layui/layui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/verify.js"></script>

</head>
<body>

	<%--  <c:if test="${req == fail}">
        <script type="text/javascript">
	    layui.use('layer', function(){
	        var layer = layui.layer;
	        layer.msg("请先登录");
	      }); 
	    </script>
    </c:if> --%>
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
		<main style="background-image: url(''); background-repeat: repeat;">
		<div class="login"
			style="height: 450px; width: 600px; margin: 0px auto; background-color: white;">
			<div class="title" style="height: 60px; width: 800px;">
				<h1
					style="margin: 15px 0px 0px 250px; color: rgba(57, 61, 73, 0.7); line-height: 90px">登&emsp;录</h1>
			</div>
			<div class="content"
				style="height: 300px; width: 600px; text-align: center; margin: 10px auto; border-radius: 20px;">
				<ul>
					<!-- rgb(153,16,16) -->
					<li style="display: inline-block; margin: 30px 0px 15px 10px"><a
						href="${pageContext.request.contextPath }/before/pwd_login.jsp"
						id="pwd_login" style="color: rgb(47, 169, 158); font-size: 17px;">密码登录</a></li>
					<li style="display: inline-block; margin: 0px 0px 15px 50px"><a
						href="${pageContext.request.contextPath }/before/sms_login.jsp"
						id="sms_login"
						style="color: rgba(57, 61, 73, 0.5); font-size: 17px;">验证码登录</a></li>
				</ul>


				<form class="form1 layui-form" name="form1"
					action="${pageContext.request.contextPath }/before/studentLogin"
					method="post">
					<div class="layui-form-item" class="pwd">
						<div class="layui-input-block">
							<input type="text" id="id" placeholder="学号" value="${student.id }" style="height: 43px; width: 390px"
								class="layui-input" name="id" lay-verify="sid" />
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label"></label>
						<div class="layui-input-inline">
							<input type="password" id="password" style="height: 43px; width: 390px" placeholder="密码"
								name="password" class="layui-input" lay-verify="required"
								lay-reqText="密码不能为空" />
						</div>
					</div>
					<a style="display: inline-block; margin: 0px 0px 15px 0px"
						href="${pageContext.request.contextPath }/before/sms_login.jsp">忘记密码？
					</a>
					<div class="layui-form-item">
						<div class="layui-input-block" style="margin: 0px auto;">
							<button class="layui-btn" lay-submit
								style="width: 185px; height: 43px">登录</button>
							<button type="reset" style="width: 185px; height: 43px"
								class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
			</div>
		</div>

		</main>
		<footer
			style="height:90px; text-align: center;background-image:url('images/bgd1.png'); border: rgba(153,16,16,0.1) solid thin;background-color: rgb(57,61,73,0.1)">
		<p
			style="color: white; font-size: 12px; text-align: center; padding-top: 40px;">青岛科技大学(©版权所有)</p>
		</footer>
	</div>
	<script>
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
		layui.use([ 'element', 'form' ], function() {
			var element = layui.element;
			var form = layui.form;
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