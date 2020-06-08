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
	function delConfirm() {
		var r = confirm("确认删除?");
		if (r == true) {
			return true;
		} else {
			return false;
		}

	}
	function updConfirm() {
		var r = confirm("确认通过?");
		if (r == true) {
			return true;
		} else {
			return false;
		}

	}
	function rejConfirm() {
		var r = confirm("确认拒绝?");
		if (r == true) {
			return true;
		} else {
			return false;
		}

	}
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

<body>
    <c:if test="${sessionScope.president==null}">
        <script type="text/javascript">
           top.location="${pageContext.request.contextPath}/admin/prsdt_login.jsp?req=fail";
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
		style="margin: 50px 0px 30px 440px; align: center; color: rgba(57, 61, 73, 0.7)">
		<span style="align: center; color: rgba(57, 61, 73, 0.7)">
			添加社团公告</span>
	</h2>



	<div style="width: 700px; margin: 10px auto;">
		<form class="layui-form layui-form-pane" name="form1"
			action="${pageContext.request.contextPath}/admin/addNoticePr">


			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">社团名</label>
				<div class="layui-input-block">
					<select name="club.id" id="clubId">
						<option value="${president.club.id  }"
							style="color: rgba(57, 61, 73, 0.1)" selected>&emsp;${president.club.name }&emsp;</option>
					</select>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">标题</label>
				<div class="layui-input-block">
					<input type="text" id="title" name="title" class="layui-input"
						value="${notice.title }" lay-verify="required" />
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">内容</label>
				<div class="layui-input-block">
					<textarea name="content" placeholder="请输入内容" lay-verify="required"
						value="${notice.content}" class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block" style="margin-top: 30px;">
					<button class="layui-btn" style="margin-left: 80px; width: 200px;"
						lay-submit>添加</button>
					<button type="reset" style="width: 200px"
						class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
	</div>

	<script>
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
		layui.use([ 'element', 'form', 'layer', 'laydate' ], function() {
			var element = layui.element;
			var form = layui.form;
			var layer = layui.layer;
			var laydate = layui.laydate;
			laydate.render({
				elem : '#time' //指定元素
			//,value:new Date() //默认值
			});

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

