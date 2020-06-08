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
	<div
		style="margin: 30px 0px 0px 220px; align: center; color: rgba(57, 61, 73, 0.7)">
		<a href="${pageContext.request.contextPath}/admin/selAcademyByPage"><i
			class="layui-icon"> &#xe603;返回</i></a>
	</div>
	<h2
		style="margin: 10px 0px 30px 420px; align: center; color: rgba(57, 61, 73, 0.7)">
		<span style="align: center; color: rgba(35, 38, 46, 0.7)">
			学院信息详情</span>
	</h2>



	<div style="height: 550px; width: 750px; margin: 10px auto;">
		<form class="layui-form layui-form-pane" name="form1"
			action="javascript:void(0);">


			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">学院号</label>
				<div class="layui-input-block">
					<input type="text" id="id" name="id" class="layui-input"
						style="text-align: center" value="${academy.id }"
						readonly="readonly" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">学院名</label>
				<div class="layui-input-block">
					<input type="text" id="name" name="name" class="layui-input"
						style="text-align: center" value="${academy.name }"
						readonly="readonly" />
				</div>
			</div>

			<!-- <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" style="margin-left: 57px; width: 200px;"
                        lay-submit>返回</button>
                    
                    <button type="reset" style="width: 200px"
                        class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div> -->
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