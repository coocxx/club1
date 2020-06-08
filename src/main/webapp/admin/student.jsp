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
		var r = confirm("确认删除?");
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
	<c:if test="${sessionScope.admin==null}">
		<script type="text/javascript">
			top.location = "${pageContext.request.contextPath}/admin/admin_login.jsp?req=fail";
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
		style="margin: 0px auto; background-color: rgba(57, 61, 73, 0.1); height: 250px;">
		<form class="layui-form" method="post" name="form1"
			style="margin-left: 70px; padding-top: 20px;"
			action="${pageContext.request.contextPath }/admin/selStudents">
			<div class="layui-form-item">
				<div class="layui-inline">
					<label class="layui-form-label">学号:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="id" id="id" class="layui-input" />
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">姓名:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="name" class="layui-input" />
					</div>
				</div>

				<div class="layui-inline">
					<label class="layui-form-label">学院名:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="classes.major.academy.name"
							class="layui-input" />
					</div>
				</div>

				<div class="layui-inline">
					<label class="layui-form-label">专业名称:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="classes.major.name" class="layui-input" />
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">班级号:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="classes.id" id="classesId"
							class="layui-input" />
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">班级名:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="classes.name" class="layui-input" />
					</div>
				</div>


				<div class="layui-inline">
					<label class="layui-form-label">邮箱:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="email" class="layui-input" />

					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">电话:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="phone" class="layui-input" />

					</div>
				</div>

				<div class="layui-inline">
					<label class="layui-form-label">注册时间:</label>
					<div class="layui-input-inline" style="width: 150px;">
						<input type="text" name="time" id="time" class="layui-input" />

					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">状态:</label>
					<div class="" style="display: inline; width: 150px;">
						<input type="radio" name="status" value="0" title="不在线"> <input
							type="radio" name="status" value="1" title="在线"> <input
							type="radio" name="status" value="2" title="全部" checked>
					</div>
				</div>
				<div class="layui-inline">
					<label class="layui-form-label">性别:</label>
					<!-- <div class="layui-input-inline" style="width: 150px;"> -->
					<div class="" style="display: inline; width: 150px;">
						<input type="radio" name="sex" value="0" title="男"> <input
							type="radio" name="sex" value="1" title="女"> <input
							type="radio" name="sex" value="2" title="全部" checked>
					</div>
				</div>
				<div class="layui-inline ">
					<div class="layui-input-block" style="margin-top: 10px;">
						<button class="layui-btn" lay-submit lay-filter="setId"
							style="width: 100px;">
							<i class="layui-icon">&#xe615;</i>查询
						</button>
						<button type="reset" class="layui-btn layui-btn-primary"
							style="width: 100px;">重置</button>
					</div>
				</div>

			</div>
		</form>
	</div>

	<!-- 条件查询没有结果不显示 -->
	<c:if test="${ curPage.data!=null && curPage.data.size()!=null }">
		<!-- 给表格固定大小 -->
		<div style="font-size:10px">
			<table class="layui-table">
				<colgroup>
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
					<col>
					<col>
					<col width="500">
					<col>
				</colgroup>
				<thead>
					<tr style="">
						<th style="text-align: center;font-size:12px">学院名</th>
						<th style="text-align: center;font-size:12px">专业名</th>
						<th style="text-align: center;font-size:12px">班级号</th>
						<th style="text-align: center;font-size:12px">班级名</th>
						<th style="text-align: center;font-size:12px">学号</th>
						<th style="text-align: center;font-size:12px">姓名</th>
						<th style="text-align: center;font-size:12px">密码</th>
						<th style="text-align: center;font-size:12px">性别</th>
						<th style="text-align: center;font-size:12px">邮箱</th>
						<th style="text-align: center;font-size:12px">电话</th>
						<th style="text-align: center;font-size:12px">状态</th>
						<th style="text-align: center;font-size:12px">注册时间</th>
						<th style="text-align: center;font-size:12px">性格爱好</th>
						<th style="text-align: center;font-size:12px">加入社团数</th>
						<th style="text-align: center;font-size:12px">加入社团</th>
						<th style="text-align: center;font-size:12px"></th>
					</tr>
				</thead>
				<tbody >
					<c:forEach items="${curPage.data }" var="i">
						<tr>
							<td style="font-size:10px">${i.classes.major.academy.name }</td>
							<td style="font-size:10px">${i.classes.major.name }</td>
							<td style="font-size:10px">${i.classes.id }</td>
							<td style="font-size:10px">${i.classes.name }</td>
							<td style="font-size:10px">${i.id }</td>
							<td style="font-size:10px">${i.name }</td>
							<td style="font-size:10px">${i.password }</td>
							<td style="font-size:10px"><c:if test="${i.sex==0 }">男</c:if> <c:if
									test="${i.sex==1 }">女</c:if></td>
							<td style="font-size:10px">${i.email }</td>
							<td style="font-size:10px">${i.phone }</td>
							<td style="font-size:10px"><c:if test="${i.status==1 }">在线</c:if> <c:if
									test="${i.status==0 }">不在线</c:if></td>
							<td style="font-size:10px">${i.time }</td>
							<td style="font-size:10px">${i.hobby }</td>
							<td style="font-size:10px">
							<c:set value="0" var="sum"></c:set>
							<c:forEach items="${stuClubs }" var="s">
									<c:if test="${i.id==s.studentId }">
									${s.count }
									<c:set value="1" var="sum"></c:set>
									</c:if>
								</c:forEach>
								<c:if test="${sum!=1 }">0</c:if></td>
							<td style="font-size:10px"><c:set value="0" var="sum"></c:set><c:forEach items="${stuClubs }" var="s">
									<c:if test="${i.id==s.studentId }">
                                        <c:set value="1" var="sum"></c:set>
										<c:forEach items="${s.clubMembers }" var="c">

											<div>${c.club.name }
												&nbsp;
												<c:if test="${c.status==0 }">成员</c:if>
												<c:if test="${c.status==1 }">会长</c:if>
												<c:if test="${c.status==2 }">往届会长</c:if>
												<c:if test="${c.status==3 }">往届成员</c:if>
											</div>
										</c:forEach>
									</c:if>
								</c:forEach><c:if test="${sum!=1 }">无</c:if></td>
							<td >
								<div>
									<a class="layui-btn layui-btn-radius layui-btn-normal"
										href="${pageContext.request.contextPath }/admin/preUpdStudent?id=${i.id }"><i
										class="layui-icon" style="font-size:10px">&#xe642;编辑</i></a>
								</div>
								<div>
									<a class="layui-btn layui-btn-radius"
										href="${pageContext.request.contextPath }/admin/delStudent?id=${i.id }"
										onclick="return delConfirm()"><i class="layui-icon" style="font-size:10px">&#xe640;删除</i></a>
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
					href="${pageContext.request.contextPath }/admin/selStuClubByPage?curPage=1">首页</a>
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
					href="${pageContext.request.contextPath }/admin/selStuClubByPage?curPage=${curPage.curPage-1 }"><i
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
					href="${pageContext.request.contextPath }/admin/selStuClubByPage?curPage=${curPage.curPage+1 }"><i
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
					href="${pageContext.request.contextPath }/admin/selStuClubByPage?curPage=${curPage.totalPage }">尾页</a>
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
				elem : '#time' //指定元素
			//,value:new Date() //默认值
			});
			//监听提交
			form.on('submit(setId)', function(data) {
				//alert("sss");
				//alert(document.form1.id.value);
				if (document.form1.id.value == ''
						|| document.form1.id.value == 0) {
					document.form1.id.value = 0;
				}
				if (document.form1.classesId.value == ''
						|| document.form1.classesId.value == 0) {
					document.form1.classesId.value = 0;

				}
				if (document.form1.majorId.value == '') {
					document.form1.majorId.value = 0;

				}
				if (document.form1.academyId.value == '') {
					document.form1.academyId.value = 0;

				}
				if (document.form1.sex.value == '') {
					document.form1.sex.value = 3;

				}
				if (document.form1.status.value == '') {
					document.form1.status.value = 3;

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