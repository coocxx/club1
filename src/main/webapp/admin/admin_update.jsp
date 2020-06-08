<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>百团纳新后台管理登陆页面</title>
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
<body>
	<c:if test="${update != null}">
		<script type="text/javascript">
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("<%=session.getAttribute("update")%>");
          }); 
        </script>
		<%
			session.removeAttribute("update");
		%>
	</c:if>
	<c:if test="${update != null}">
		<script type="text/javascript">
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("<%=session.getAttribute("update")%> ");
			});
		</script>
		<%
			session.removeAttribute("update");
		%>
	</c:if>
	
    <div style="margin: 30px 0px 0px 220px; align: center; color: rgba(57, 61, 73, 0.7)">
         <a href="${pageContext.request.contextPath}/admin/selAdminByPage"><i class="layui-icon"> &#xe603;返回</i></a>
     </div>
	<h2
		style="margin: 10px 0px 30px 420px; align: center; color: rgba(57, 61, 73, 0.7)">
		<span style="align: center; color: rgba(35, 38, 46, 0.7)">
			修改管理员账号信息</span>
	</h2>



	<div style="height: 650px; width: 800px; margin: 10px auto;">
		<form class="layui-form layui-form-pane" name="form1"
			action="${pageContext.request.contextPath}/admin/updAdmin">

			<div class="layui-form-item">
				<label class="layui-form-label"
					style="height: 60px; width: 160px; padding-top: 20px; background-color: rgba(57, 61, 73, 0.1)">
					账号</label>
				<div class="layui-input-block">
					<input
						style="height: 60px; width: 640px; padding-left: 100px; color: rgba(57, 61, 73, 0.3);"
						type="text" id="id" name="id" class="layui-input"
						value="${admin.id }" readonly="readonly" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="height: 60px; width: 160px; padding-top: 20px; background-color: rgba(57, 61, 73, 0.1)">名称</label>
				<div class="layui-input-block">
					<input
						style="height: 60px; width: 640px; padding-left: 100px; color: rgba(57, 61, 73, 0.3);"
						type="text" id="name" name="name" class="layui-input"
						value="${admin.name }" readonly="readonly" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="height: 60px; width: 160px; padding-top: 20px; background-color: rgba(57, 61, 73, 0.1)">密码</label>
				<div class="layui-input-block">
					<input style="height: 60px; width: 640px; padding-left: 100px;"
						type="password" id="password" name="password" class="layui-input"
						value="${admin.password }" lay-verify="password" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="height: 60px; width: 160px; padding-top: 20px; background-color: rgba(57, 61, 73, 0.1)">邮箱</label>
				<div class="layui-input-block">
					<input style="height: 60px; width: 640px; padding-left: 100px;"
						type="text" id="email" lay-verify="email" name="email"
						class="layui-input" value="${admin.email }" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="height: 60px; width: 160px; padding-top: 20px; background-color: rgba(57, 61, 73, 0.1)">电话</label>
				<div class="layui-input-block">
					<input style="height: 60px; width: 640px; padding-left: 100px;"
						type="text" id="phone" lay-verify="required|phone" name="phone"
						class="layui-input" value="${admin.phone}" />
				</div>
			</div>


			<div class="layui-form-item" pane>
				<label class="layui-form-label"
					style="height: 60px; width: 160px; padding-top: 20px; background-color: rgba(57, 61, 73, 0.1)">性别</label>
				<div class="layui-input-block"
					style="text-align: center; height: 60px; width: 640px;">
					<c:if test="${admin.sex==0 ||(admin.sex!=1&&admin.sex!=0)}">
                              <input
                                type="radio" name="sex" value="0" title="男" checked>
                                <input
                                type="radio" name="sex" value="1" title="女">
                            </c:if>
                            <c:if test="${admin.sex==1 }">
                              <input type="radio" name="sex" value="0" title="男">
                              <input
                                type="radio" name="sex" value="1" title="女" checked>
                            </c:if>
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
			/* form.on('submit(form1)', function() {
				verifyInfo();
				return false;
			}); */
				form.verify({
	                name : [ /^[a-zA-Z0-9]{3,20}$/, '用户名组成为字母3-20位' ],
	                sid : [ /^[0-9]{10}$/, '学号组成为10位数字' ] ,
	                password : [ /^[a-zA-Z0-9]{3,20}$/, '密码组成为字母或数字,3-20位' ]
	            })
				
		});
	</script>
</body>
</html>