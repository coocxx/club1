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
           top.location="${pageContext.request.contextPath}/admin/admin_login.jsp?req=fail";
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
		style="margin: 30px 420px; align: center; color: rgba(57, 61, 73, 0.7)">
		<span style="align: center; color: rgba(35, 38, 46, 0.7)"> 添加学生</span>
	</h2>



	<div style="height: 550px; width: 700px; margin: 10px auto;">
		<form class="layui-form layui-form-pane" name="form1"
			action="${pageContext.request.contextPath}/admin/addStudent">


			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">学号</label>
				<div class="layui-input-block">
					<input type="text" id="id" name="id" class="layui-input"
						value="${student.id }" lay-verify="required" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">名称</label>
				<div class="layui-input-block">
					<input type="text" id="name" name="name" class="layui-input"
						value="${student.name }" lay-verify="required" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">密码</label>
				<div class="layui-input-block">
					<input type="password" id="password" name="password"
						class="layui-input" value="${student.password }"
						lay-verify="password" />
				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">性别</label>
				<div class="layui-input-block">
					<c:if test="${student.sex==0 ||(student.sex!=1&&student.sex!=0)}">
						<input type="radio" name="sex" value="0" title="男" checked>
						<input type="radio" name="sex" value="1" title="女">
					</c:if>
					<c:if test="${student.sex==1 }">
						<input type="radio" name="sex" value="0" title="男">
						<input type="radio" name="sex" value="1" title="女" checked>
					</c:if>
				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">班&emsp;&emsp;级</label>
				<div class="layui-input-block">
					<c:if test="${student==null }">
						<select name="classesId" id="classesId">
							<c:forEach items="${classes }" var="i">
								<option value="${i.id  }">&emsp;${i.major.academy.name }&emsp;${i.major.name }&emsp;${i.name }</option>
							</c:forEach>
						</select>
					</c:if>

					<c:if test="${student!=null }">
						<select name="classesId" id="classesId">
							<c:forEach items="${classes }" var="i">
								<c:if test="${student.classes.id==i.id}">
									<option value="${i.id  }" selected>&emsp;${i.major.academy.name }&emsp;${i.major.name }&emsp;${i.name }</option>
								</c:if>
								<c:if test="${student.classes.id!=i.id}">
									<option value="${i.id  }">&emsp;${i.major.academy.name }&emsp;${i.major.name }&emsp;${i.name }</option>
								</c:if>
							</c:forEach>
						</select>
					</c:if>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">邮箱</label>
				<div class="layui-input-block">
					<input type="text" id="email" name="email" class="layui-input"
						value="${student.email}" lay-verify="email" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">电话</label>
				<div class="layui-input-block">
					<input type="text" id="phone" name="phone" class="layui-input"
						value="${student.phone }" lay-verify="phone" />
				</div>
			</div>
			<div class="layui-form-item " pane>
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">状态</label>
				<div class="layui-input-block">
					<c:if
						test="${student.status==0 ||(student.status!=1&&student.status!=0)}">
						<input type="radio" name="status" value="0" title="正常" checked>
						<input type="radio" name="status" value="1" title="停止">
					</c:if>
					<c:if test="${student.status==1 }">
						<input type="radio" name="status" value="0" title="正常">
						<input type="radio" name="status" value="1" title="停止" checked>
					</c:if>
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">兴趣爱好</label>
				<div class="layui-input-block">
					<textarea name="hobby" placeholder="请输入内容" value="${student.hobby}"
						class="layui-textarea"></textarea>
				</div>
			</div>

			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" style="margin-left: 57px; width: 200px;"
						lay-submit>添加</button>
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