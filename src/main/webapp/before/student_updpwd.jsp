<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码</title>
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/layui/layui.js"></script>
</head>
<body>
	<c:if test="${sessionScope.student==null}">
		<script type="text/javascript">
			top.location = "${pageContext.request.contextPath}/before/pwd_login.jsp?req=fail";
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
		<span style="align: center; color: rgba(35, 38, 46, 0.7)"> <i
			style="color: green">${student.name }</i>&nbsp;修改密码
		</span>
	</h2>



	<div style="height: 550px; width: 750px; margin: 10px auto;">
		<form class="layui-form layui-form-pane" name="form1"
			action="${pageContext.request.contextPath}/before/updOneStudentPwd">

			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">学号</label>
				<div class="layui-input-block">
					<input type="text" name="id" class="layui-input"
						style="color: rgba(57, 61, 73, 0.5)" value="${sessionScope.student.id }"
						readonly="readonly" disabled="disabled" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">旧密码</label>
				<div class="layui-input-block">
					<input type="password" id="password" name="password"
						class="layui-input" value="${studentPwd.password }"
						lay-verify="required" lay-reqText="请输入密码" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">新密码</label>
				<div class="layui-input-block">
					<input type="password" id="newPassword" name="newPassword"
						class="layui-input" value="" lay-verify="password" />
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">确认密码</label>
				<div class="layui-input-block">
					<input type="password" id="newPasswordO" name="newPasswordO"
						class="layui-input" value="" lay-verify="password" />
				</div>
			</div>


			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" style="margin-left: 57px; width: 200px;"
						lay-filter="*" lay-submit>保存</button>
					<button type="reset" style="width: 200px"
						class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>

		</form>
	</div>

	<script>
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
		layui
				.use(
						[ 'element', 'form', 'layer' ],
						function() {
							var element = layui.element;
							var form = layui.form;
							var layer = layui.layer;

							form
									.on(
											'submit(*)',
											function() {
												if (document.form1.newPassword.value != document.form1.newPasswordO.value) {
													layui
															.use(
																	'layer',
																	function() {
																		var layer = layui.layer;
																		layer
																				.msg("密码不一致");
																	});
													return false;
												}
												return true;
											});
							form
									.verify({
										name : [ /^[a-zA-Z0-9]{3,20}$/,
												'用户名组成为字母3-20位' ],
										sid : [ /^[0-9]{10}$/, '学号组成为10位数字' ],
										password : [ /^[a-zA-Z0-9]{6,20}$/,
												'密码组成为字母或数字,6-20位' ],
										numoro : [ /^[0-9]{0,}$/, '组成为数字' ],
										age : [ /^[1]?[0-9]{1,2}$/, '年龄为0-99' ]

									})
						});
	</script>
</body>
</html>