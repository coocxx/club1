<%@page import="com.cxx.pojo.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>百团纳新网站</title>
<link href="${pageContext.request.contextPath }/before/css/slider.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<%-- <link href="${pageContext.request.contextPath }/before/css/index.css"
    rel="stylesheet" type="text/css" /> --%>
<!-- 引入slider -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<!-- 引入slider -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/slider.js"></script>
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
</script>
<script>
	/* jQuery 初始化轮播插件 */
	$(function() {
		$('.flexslider').flexslider({
			animation : "slide",
			direction : "horizontal",
			easing : "swing"
		});
	});
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
<script>
	function upload(){
		var name=document.form1.name.value;
	    var url = document.form1.url.value;
	    alert("name:"+name);
	    alert("url:"+url);
	    $.ajax({
	        url:"${pageContext.request.contextPath}/admin/loadImage",
	        contentType:'application/json;charset=utf-8',
	        data:{"name":name,"url":url},
	        type:post,
	        success:function(data){
	            alert(data);
	        },
	        error:function(data){
	            alert(data);
	        }
	    })
	}
   
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

	<div class="main">
		<div class="layui-container turn"
			style="margin-top: 20px; background-color: #EEEEEE; color: rgb(51, 61, 73);">
			<div style="">
				<div class="layui-container">

					<div class="layui-row">
						<div
							style="font-size: 12px; float: left; overflow: hidden; width: 300px; height: 370px; margin-top: 20px; margin-bottom: 20px; background-color: snow">
							<div style="margin-top: 5px">
								<form class="layui-form" name="form1"
									action="${pageContext.request.contextPath }/admin/addImage"
									style="float: left; margin: 70px 10px; width: 270px; height: 370px;">
									<div class="layui-form-item">

										描述：<input type="text" name="name" class="layui-input"
											style="display: inline-block; width: 200px"
											value="${image.name }" lay-verify="required"
											lay-reqText="描述不能为空" />

									</div>
									<div class="layui-form-item" style="text-align: center">
										上传：<input type="file" name="url" id="url"
											value="${image.url }" id="url" class="layui-input"
											style="display: inline-block; width: 200px"
											lay-verify="required" lay-reqText="请上传图片" />
									</div>
									<div class="layui-form-item">
										<div>
											<button class="layui-btn" lay-submit >提交</button>
											<button type="reset" class="layui-btn layui-btn-primary">重置</button>
										</div>
									</div>
								</form>
							</div>
						</div>


						<c:if test="${images!=null }">
							<c:forEach items="${images }" var="i">

								<div
									style="font-size: 12px; float: left; overflow: hidden; width: 280px; height: 370px; margin-top: 20px; margin-bottom: 20px; margin-left: 20px; background-color: snow">
									<div style="float: left">
										<a href="${pageContext.request.contextPath }/${i.url}"
											target="bank"><img
											style="width: 260px; height: 260px; margin: 10px 10px"
											alt="${i.url}"
											src="${pageContext.request.contextPath }/${i.url}"> </a> <span
											style="margin-left: 20px; color: green;">编号:&emsp;</span><span>${i.id }</span><br />
										<span style="margin-left: 20px; color: green;">描述:&emsp;</span><span>${i.name }</span><br />
										<span style="margin-left: 20px; color: green;">地址:&emsp;</span><span>${i.url }</span><br />
									</div>
									<div style="float: right">
										<a class="layui-btn"
											style="font-size: 12px; margin-left: 30px; background-color: grey"
											href="${pageContext.request.contextPath }/admin/delImage?id=${i.id}"
											onclick="return delConfirm()">删除</a>
									</div>
								</div>


							</c:forEach>
						</c:if>
					</div>

				</div>
			</div>



		</div>

	</div>

	<script>
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
		layui.use([ 'form', 'element', 'upload' ], function() {
			var element = layui.element;
			var upload = layui.upload;
			var form = layui.form;
			//执行实例
			/* var name=Document.form1.name.value;
			var url = document.form1.url.value;
			alert("name:"+name);
			alert("url:"+url);
			 var uploadInst = upload.render({
				elem : '#url' //绑定元素
				 ,
				url : "${pageContext.request.contextPath }/admin/upload/" //上传接口
				,
				done : function(res) {
					//上传完毕回调
				},
				error : function() {
					//请求异常回调
				} 
			}); */

		});
	</script>
</body>
</html>
