<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询管理员</title>
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/layui/layui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.7.2.js"></script>
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
		var r = confirm("确认审核不通过？");
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
				layer.msg("${result} ");
			});
		</script>
	</c:if>

	<!-- 按条件查询 -->
	<div
		style="margin: 0px auto; background-color: rgba(57, 61, 73, 0.1); height: 210px;">

		<form class="layui-form" method="post" name="form1"
			style="margin-left: 70px; padding-top: 20px;"
			action="${pageContext.request.contextPath }/admin/selEvents?action=pr">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">活动编号:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="id" class="layui-input" />
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">负责人学号:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="student.id" id="studentId"
							class="layui-input" />
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">负责人姓名:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="student.name" class="layui-input" />
					</div>
				</div>

				<div class="layui-inline">
					<label class="layui-form-label">活动名:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="name" autocomplete="off" value=""
							class="layui-input">
					</div>
				</div>

				<div class="layui-inline">
					<label class="layui-form-label">开始时间:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" id="startTime" name="startTime"
							autocomplete="off" value="" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">结束时间:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" id="endTime" name="endTime" autocomplete="off"
							value="" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">审批时间:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" id="appTime" name="appTime" autocomplete="off"
							value="" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">提交时间:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" id="subTime" name="subTime" autocomplete="off"
							value="" class="layui-input">
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">活动地点:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" id="place" name="place" autocomplete="off"
							value="" class="layui-input">
					</div>
				</div>

				<div class="layui-inline">
					<div class="layui-input-block"
						style="width: 170px; margin-top: 5px">
						<button class="layui-btn" lay-submit lay-filter="setId">
							<i class="layui-icon">&#xe615;</i>查询
						</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">状态:</label>
					<div class="" style="display: inline; width: 150px;">
						<input type="radio" name="status" value="0" title="未通过"> <input
							type="radio" name="status" value="1" title="审核通过"> <input
							type="radio" name="status" value="2" title="待审核"> <input
							type="radio" name="status" value="3" title="全部" checked>
					</div>
				</div>
			</div>
		</form>
	</div>

	<!-- 条件查询没有结果不显示 -->
	<c:if test="${ curPage.data!=null }">
		<!-- 给表格固定大小 -->
		<div style="">
			<table class="layui-table">
				<colgroup>
					<col>
					<col width="40">
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
				</colgroup>
				<thead>
					<tr>
						<th>编号</th>
						<th>会长学号</th>
						<th>会长名称</th>
						<th>社团号</th>
						<th>社团名</th>
						<th>活动名</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>审核时间</th>
						<th>提交时间</th>
						<th>活动简介</th>
						<th>活动地点</th>
						<th>状态</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${curPage.data }" var="i">
						<tr>
							<td>${i.id }</td>
							<td>${i.studentId }</td>
							<td>${i.student.name }</td>
							<td>${i.club.id}</td>
							<td>${i.club.name}</td>
							<td>${i.name}</td>
							<td>${i.startTime}</td>
							<td>${i.endTime}</td>
							<td>${i.appTime}</td>
							<td>${i.subTime}</td>

							<td>${i.introduce }</td>
							<td>${i.place }</td>
							<td><c:if test="${i.status==0 }">未通过</c:if> <c:if
									test="${i.status==1 }">通过</c:if> <c:if test="${i.status==2 }">待审核</c:if></td>

							<td>
								<div>
									<a class="layui-btn layui-btn-radius layui-btn-normal"
										href="${pageContext.request.contextPath }/admin/preUpdEvent?action=pr&id=${i.id }">编辑</a>
								</div>
								<div>
									<a class="layui-btn layui-btn-radius layui-btn-warm"
										href="${pageContext.request.contextPath }/admin/delEvent?action=pr&id=${i.id }"
										onclick="return delConfirm()">删除</a>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!--  分页 -->
		<div style="margin: 10px 0px 10px 380px">
			<!-- 首页 -->
			<c:if test="${curPage.curPage==1}">
				<a class="layui-btn layui-btn-primary"
					style="border: solid 1px rgba(57, 61, 73, 0.3); color: rgba(57, 61, 73, 0.3);"
					href="javascipt:void(0);">首页</a>
			</c:if>
			<c:if test="${curPage.curPage!=1}">
				<a class="layui-btn"
					href="${pageContext.request.contextPath }/admin/selEventByPage?action=pr&curPage=1">首页</a>
			</c:if>
			<!-- 上一页 -->
			<c:if test="${curPage.curPage<=1}">
				<a class="layui-btn layui-btn-primary"
					style="border: solid 1px rgba(57, 61, 73, 0.3); color: rgba(57, 61, 73, 0.3);"
					href="javascipt:void(0);"><i class="layui-icon layui-icon-left"></i></a>
			</c:if>
			<c:if
				test="${(curPage.curPage>1)&&(curPage.curPage<=curPage.totalPage)}">
				<a class="layui-btn"
					href="${pageContext.request.contextPath }/admin/selEventByPage?action=pr&curPage=${curPage.curPage-1 }"><i
					class="layui-icon layui-icon-left"></i></a>
			</c:if>
			<!-- 下一页 -->
			<c:if test="${curPage.curPage==curPage.totalPage}">
				<a
					style="border: solid 1px rgba(57, 61, 73, 0.3); color: rgba(57, 61, 73, 0.3);"
					class="layui-btn layui-btn-primary" href="javascipt:void(0);"><i
					class="layui-icon">&#xe602;</i></a>
			</c:if>
			<c:if
				test="${(curPage.curPage>=1) && (curPage.curPage<curPage.totalPage)}">
				<a class="layui-btn"
					href="${pageContext.request.contextPath }/admin/selEventByPage?action=pr&curPage=${curPage.curPage+1 }"><i
					class="layui-icon">&#xe602;</i></a>
			</c:if>
			<!-- 尾页 -->
			<c:if test="${curPage.curPage==curPage.totalPage}">
				<a
					style="border: solid 1px rgba(57, 61, 73, 0.3); color: rgba(57, 61, 73, 0.3);"
					class="layui-btn layui-btn-primary" href="javascipt:void(0);">尾页</a>
			</c:if>
			<c:if test="${curPage.curPage!=curPage.totalPage}">
				<a class="layui-btn"
					href="${pageContext.request.contextPath }/admin/selEventByPage?action=pr&curPage=${curPage.totalPage }">尾页</a>
			</c:if>
		</div>
	</c:if>
	<script>
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
		layui.use([ 'element', 'form', 'laydate' ], function() {
			var element = layui.element;
			var form = layui.form;
			var laydate = layui.laydate;
			laydate.render({
				elem : '#startTime', //指定元素
			});
			laydate.render({
				elem : '#endTime', //指定元素
			});
			laydate.render({
				elem : '#appTime', //指定元素
			});
			laydate.render({
				elem : '#subTime', //指定元素
			});
			//监听提交
			form.on('submit(setId)', function(data) {
				if (document.form1.id.value == ''
						|| document.form1.id.value == 0) {
					document.form1.id.value = 0;
				}
				if (document.form1.studentId.value == ''
						|| document.form1.studentId.value == 0) {
					document.form1.studentId.value = 0;
				}

				if (document.form1.clubId.value == ''
						|| document.form1.clubId.value == 0) {
					document.form1.clubId.value = 0;
				}
				return true;
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