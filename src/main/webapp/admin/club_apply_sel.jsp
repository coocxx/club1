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
		var r = confirm("确认删除？");
		if (r == true) {
			return true;
		} else {
			return false;
		}
	}
	function updConfirm() {
		var r = confirm("确认审核通过？");
		if (r == true) {
			return true;
		} else {
			return false;
		}
	}
	function rejConfirm() {
		var r = confirm("确认拒绝？");
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
	<c:if test="${result != null}">
		<script type="text/javascript">
			layui.use('layer', function() {
				var layer = layui.layer;
				layer.msg("${result}");
			});
		</script>
	</c:if>
	<div
		style="margin: 30px 0px 0px 160px; align: center; color: rgba(57, 61, 73, 0.7)">
		<a href="${pageContext.request.contextPath}/admin/selClubApplyByPage"><i
			class="layui-icon"> &#xe603;返回</i></a>
	</div>
	<h2
		style="margin: 10px 0px 30px 150px; align: center; color: rgba(57, 61, 73, 0.7)">
		<span style="align: center; color: rgba(35, 38, 46, 0.7)">
			社团申请详情</span>
	</h2>



	<div style="height: 550px; width: 750px; margin: 10px auto;">
		<form class="layui-form layui-form-pane" name="form1"
			action="${pageContext.request.contextPath}/admin/updClubApplyStatus">


			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">编号</label>
				<div class="layui-input-block">
					<input type="text" id="id" name="id" class="layui-input"
						style="text-align: center" value="${clubApply.id }"
						readonly="readonly" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">学号</label>
				<div class="layui-input-block">
					<input type="text" id="id" name="id" class="layui-input"
						style="text-align: center" value="${clubApply.studentId }"
						readonly="readonly" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">学生姓名</label>
				<div class="layui-input-block">
					<input type="text" id="id" name="studentName" class="layui-input"
						style="text-align: center" value="${clubApply.student.name }"
						readonly="readonly" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">社团类型ID</label>
				<div class="layui-input-block">
					<input type="text" id="clubTypeId" name="clubTypeId"
						class="layui-input" style="text-align: center"
						value="${clubApply.clubTypeId }" readonly="readonly" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">社团类型名</label>
				<div class="layui-input-block">
					<input type="text" id="clubTypeName" name="clubTypeName"
						class="layui-input" style="text-align: center"
						value="${clubApply.clubType.name }" readonly="readonly" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">管理员ID</label>
				<div class="layui-input-block">
					<input type="text" id="adminId" name="adminId" class="layui-input"
						style="text-align: center" value="${clubApply.adminId }"
						readonly="readonly" />
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">社团名</label>
				<div class="layui-input-block">
					<input type="text" id="name" name="name" class="layui-input"
						style="text-align: center" value="${clubApply.name }"
						readonly="readonly" />
				</div>
			</div>
			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">申请理由</label>
				<div class="layui-input-block">
					<textarea name="reason" placeholder="请输入内容" class="layui-textarea">${clubApply.reason}</textarea>
				</div>
			</div>

			<div class="layui-form-item" pane>
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">状态</label>
				<div class="layui-input-block">
					<c:if test="${clubApply.status==0}">
						<input type="radio" name="status" value="0" title="拒绝" checked>
					</c:if>
					<c:if test="${clubApply.status==1}">
						<input type="radio" name="status" value="1" title="通过" checked>
					</c:if>
					<c:if test="${clubApply.status==2}">
						<input type="radio" name="status" value="2" title="待审核" checked>
					</c:if>

				</div>
			</div>
			<div class="layui-form-item" pane>
				<label class="layui-form-label"
					style="background-color: rgba(57, 61, 73, 0.1)">审核时间</label>
				<div class="layui-input-block">
					<c:if test="${clubApply.time==null }">
						<p style="disply: block; margin-top: 10px;">&emsp;&emsp;&emsp;等待审核</p>
					</c:if>
					<c:if test="${clubApply.time!=null }">
						<input type="text" id="time" name="time" class="layui-input"
							style="text-align: center" value="${clubApply.time }"
							readonly="readonly" />
					</c:if>
				</div>
			</div>

			<div class="layui-form-item">

				<div class="layui-input-block" style="">
					<c:if test="${clubApply.status==2 }">
							<a class="layui-btn layui-btn-radius"
								href="${pageContext.request.contextPath }/admin/updClubApplyStatus?id=${clubApply.id }&studentId=${clubApply.studentId }&name=${clubApply.name }&clubTypeId=${clubApply.clubTypeId }&status=1"
								onclick="return updConfirm()">通过</a>
					</c:if>

					<c:if test="${clubApply.status==2 }">
							<a class="layui-btn layui-btn-radius layui-btn-warm"
								href="${pageContext.request.contextPath }/admin/updClubApplyStatusR?id=${clubApply.id }&status=0&studentId=${clubApply.studentId }"
								onclick="return rejConfirm()">拒绝</a>
					</c:if>
					<c:if test="${clubApply.status==1 || clubApply.status==0}">
							<a class="layui-btn layui-btn-radius layui-btn-warm"
								href="${pageContext.request.contextPath }/admin/delClubApply?id=${clubApply.id }"
								onclick="return delConfirm()">删除</a>
					</c:if>

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