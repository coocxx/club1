<%@page import="com.cxx.pojo.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>百团纳新网站</title>
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
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
	<c:if test="${sessionScope.president==null}">
		<script type="text/javascript">
			top.location = "${pageContext.request.contextPath}/admin/prsdt_login.jsp";
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


	<div style="margin-top: 50px;">
		<form class="layui-form" name="form"
			action="${pageContext.request.contextPath}/admin/updPassModelPr">
			<div class="layui-form-item">


				<c:if test="${prmodel!=null }">

					<div class="layui-input-block">
						<h3 style="color: grey">您的选择：${prmodel.content }</h3>
					</div>
				</c:if>


				<div class="layui-input-block">
					<input type="radio" name="content"
						value="您申请${sessionScope.president.club.name }成功！"
						title="您申请${sessionScope.president.club.name }成功！">
				</div>
				<div class="layui-input-block">
					<input type="radio" name="content"
						value="您申请${sessionScope.president.club.name }成功！"
						title="您申请${sessionScope.president.club.name }成功！">
				</div>
				<div class="layui-input-block">
					<input type="radio" name="content"
						value="恭喜您，申请成功，欢迎加入我们${ sessionScope.president.club.name}"
						title="恭喜您，申请成功，欢迎加入我们${ sessionScope.president.club.name}">
				</div>
				<div class="layui-input-block">
					<input type="radio" name="content"
						value="欢迎加入我们${ sessionScope.president.club.name}大家庭"
						title="欢迎加入我们${ sessionScope.president.club.name}大家庭">
				</div>
				<div class="layui-input-block">
					<input type="radio" name="content"
						value="我代表${ sessionScope.president.club.name}全体成员欢迎您加入我们"
						title="我代表${ sessionScope.president.club.name}全体成员欢迎您加入我们">
				</div>
				<div class="layui-input-block">
					<input type="radio" name="content"
						value="您申请加入${ sessionScope.president.club.name}成功！"
						title="您申请加入${ sessionScope.president.club.name}成功！">
				</div>

				<div class="layui-form-item" style="margin-top: 20px;">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="formDemo">设置</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
						<!-- <button class="layui-btn" lay-submit lay-filter="new">新建</button> -->
					</div>
				</div>
			</div>
		</form>
	</div>


	<script>
		//Demo
		layui.use('form', function() {
			var form = layui.form;

			//监听提交
			form.on('submit(formDemo)', function(data) {
				if(document.form.content.value==0){
					layer.msg("请选择再修改");
					return false;
				}
				
				
			});
			//监听提交
			/* form.on('submit(new)', function(data) {
				var i =2;
			          
			        param += "<div class='layui-input-block'><input type='radio' name='"+content+"' value='"+
                        +"' title='"+"'/></div>";
			        document.form.innerHTML = param;
			        i=i+1;
			}); */
		});
	</script>
</body>
</html>
