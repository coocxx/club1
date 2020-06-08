<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加管理员</title>
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
<body>
	<c:if test="${sessionScope.admin==null}">
		<script type="text/javascript">
			top.location = "${pageContext.request.contextPath}/admin/admin_login.jsp?req=fail";
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
	<h2
		style="margin: 10px 0px 30px 420px; align: center; color: rgba(57, 61, 73, 0.7)">
		<span style="align: center; color: green"><i>${sessionScope.admin.name }&nbsp;</i></span>管理员信息
	</h2>



	<div style="height: 550px; width: 750px; margin: 10px auto;">
		<form class="layui-form layui-form-pane" name="form1"
			action="${pageContext.request.contextPath}/admin/updOneAdmin">


			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">管理员ID</label>
				<div class="layui-input-block">
					<input type="text" id="id" name="id" class="layui-input"
						style="color: rgba(57, 61, 73, 0.5)" value="${admin.id }"
						readonly="readonly" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">管理员姓名</label>
				<div class="layui-input-block">
					<input type="text" id="name" name="name" class="layui-input"
						lay-verify="required" value="${admin.name }" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">密码</label>
				<div class="layui-input-block">
					<input type="text" id="password" name="password"
						class="layui-input" value="${admin.password }"
						lay-verify="password" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">性别</label>
				<div class="layui-input-block">
					<c:if test="${admin.sex==0}">
						<input type="radio" name="sex" value="0" title="男" checked>
						<input type="radio" name="sex" value="1" title="女">
					</c:if>
					<c:if test="${admin.sex==1 }">
						<input type="radio" name="sex" value="0" title="男">
						<input type="radio" name="sex" value="1" title="女" checked>
					</c:if>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">邮箱</label>
				<div class="layui-input-block">
					<input type="text" id="email" name="email" class="layui-input"
						value="${admin.email }" lay-verify="email" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">电话</label>
				<div class="layui-input-block">
					<input type="text" id="phone" name="phone" class="layui-input"
						value="${admin.phone }" lay-verify="phone" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">注册时间</label>
				<div class="layui-input-block">
					<input type="text" id="time" name="time" class="layui-input"
						style="color: rgba(57, 61, 73, 0.5)" value="${admin.time }"
						readonly="readonly" />
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" style="margin-left: 57px; width: 200px;"
						lay-submit>保存</button>
					<button type="reset" style="width: 200px"
						class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>

		</form>
	</div>

	<script>
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
		layui.use([ 'element', 'form', 'layer' ], function() {
			var element = layui.element;
			var form = layui.form;
			var layer = layui.layer;

			form.on('submit(form1)', function() {
				verifyInfo();
				return false;
			});
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