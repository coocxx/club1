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
	<c:if test="${sessionScope.admin==null}">
		<script type="text/javascript">
			top.location = "${pageContext.request.contextPath}/admin/admin_login.jsp";
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
			action="${pageContext.request.contextPath}/admin/updRejectModelPr">
			<div class="layui-form-item">


				<c:if test="${prmodel!=null }">

					<div class="layui-input-block">
						<h3 style="color: grey">您的选择：${prmodel.content }</h3>
					</div>
				</c:if>


				<div class="layui-input-block">
					<input type="radio" name="content"
						value="您申请${sessionScope.president.club.name }失败！"
						title="您申请${sessionScope.president.club.name }失败！">
				</div>
				<div class="layui-input-block">
					<input type="radio" name="content"
						value="抱歉，您申请${sessionScope.president.club.name }失败！"
						title="抱歉，您申请${sessionScope.president.club.name }失败！">
				</div>
				<div class="layui-input-block">
					<input type="radio" name="content"
						value="很抱歉，由于您的信息不太符合我们${ sessionScope.president.club.name}所以申请失败"
						title="很抱歉，由于您的信息不太符合我们${ sessionScope.president.club.name}所以申请失败">
				</div>
				<div class="layui-input-block">
					<input type="radio" name="content"
						value="很抱歉，感谢您的申请，我们${ sessionScope.president.club.name}社团人数已满，所以您申请失败"
						title="由于我们${ sessionScope.president.club.name}社团人数已经足够了，所以很抱歉，您申请失败">
				</div>
				<div class="layui-input-block">
					<input type="radio" name="content"
						value="很抱歉，由于您的信息不太符合我们${ sessionScope.president.club.name}所以申请失败"
						title="很抱歉，由于您的信息不太符合我们${ sessionScope.president.club.name}所以申请失败">
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
