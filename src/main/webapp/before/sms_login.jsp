<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/layui/layui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/verify.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
    //验证邮箱和验证码是否为空 
    function verifyLogin1() {
        if(!verifyNotNull(document.form2.email.value)) {
        	 layui.use('layer', function(){
                 var layer = layui.layer;
                 layer.msg("邮箱不能为空");
               }); 
            return false;
        }
        
        if(!verifyNotNull(document.form2.code.value)) {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg("验证码不能为空");
              }); 
            return false;
        }
        
    }
     

 
    
    /*请求控制器发送验证码到邮箱*/
    function redirect(){
        var email=document.form2.email.value;
        if(email==''){
        	layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg("请输入邮箱");
              }); 
        }else if(!verifyEmail(email)) {
            layui.use('layer', function(){
                var layer = layui.layer;
                layer.msg("电子邮箱格式不对");
              });  
           return false;
       }else {
           window.location.href='/club1/before/preparedEmailLogin?email='+email;
        }    
    }
    
    
    /* 获取验证码倒计时 */
    var countdown = 60;

    function setemail(){
            var obj = $("#btn");
            settime(obj);
        }
         
            
    function settime(obj){
        if(countdown == 0){
            obj.attr("disabled","false");
            obj.val("获取验证码");
            countdown = 60;
            return;
        }else{
            obj.attr("disabled", true);
            obj.val("重新发送(" + countdown + ")");
            countdown--;
        }
        setTimeout(function(){
            settime(obj)}
            ,1000);
        
    }         
            
</script>
</head>
<body>
	<c:if test="${login!=null }">
		<script type="text/javascript">
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("<%=session.getAttribute("login")%>");
          }); 
        </script>
		<%
			session.removeAttribute("login");
		%>
	</c:if>

	<c:if test="${result=='success'&& email!='' && email!=null}">
		<script type="text/javascript">
            $(function(){
            	
            	var e="<%=session.getAttribute("email")%>
			";
				document.form2.email.value = e;
				if (e == '' || e == null) {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg("请输入邮箱!");
					});
				} else {
					setemail();
				}

			});
		</script>
		<%
			session.removeAttribute("result");
		%>
	</c:if>
	<div class="container">
		<header> <jsp:include flush="fasle" page="top.jsp" /> </header>

		<main style="background-image: url(''); background-repeat: repeat;">
		<div class="login"
			style="height: 450px; width: 600px; margin: 0px auto; background-color: white;">
			<div class="title" style="height: 60px; width: 800px;">
				<h1
					style="margin: 15px 0px 0px 250px; color: rgba(57, 61, 73, 0.7); line-height: 90px">登&emsp;录</h1>
			</div>
			<div class="content"
				style="height: 300px; width: 600px; text-align: center; margin: 10px auto; border-radius: 20px;">
				<ul>
					<!-- rgb(153,16,16) -->
					<li style="display: inline-block; margin: 30px 0px 15px 10px"><a
						href="${pageContext.request.contextPath }/before/pwd_login.jsp"
						id="pwd_login"
						style="color: rgba(57, 61, 73, 0.5); font-size: 17px;">密码登录</a></li>
					<li style="display: inline-block; margin: 0px 0px 15px 50px"><a
						href="${pageContext.request.contextPath }/before/sms_login.jsp"
						id="sms_login" style="color: rgb(47, 169, 158); font-size: 17px;">验证码登录</a></li>
				</ul>

				<form name="form2"
					action="${pageContext.request.contextPath }/before/emailLogin"
					class="form2" method="post" onsubmit="return verifyLogin1()">
					<div class="layui-form-item" class="sms">
						<div class="layui-input-block">
							<input type="text" id="email" value="${email }" name="email"
								placeholder="请输入邮箱" style="height: 43px; width: 390px"
								class="layui-input" lay-verify="email" />
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">

							<input type="text" id="code" name="code" placeholder="请输入邮箱验证码"
								style="display: inline-block; height: 43px; width: 240px; float: left;"
								class="layui-input" lay-verify="required" /> <input
								type="button" class="layui-btn" id="btn"
								style="width: 150px; height: 43px; margin-left: 3px; float: left"
								value="获取验证码" onclick="redirect()" />
						</div>
					</div>
					<a style="display: inline-block; margin: 0px 0px 15px 0px"
						href="${pageContext.request.contextPath }/before/sms_login.jsp">忘记密码？
					</a>
					<div class="layui-form-item">
						<div class="layui-input-block" style="margin: 0px auto;">
							<button class="layui-btn" style="width: 185px; height: 43px">登录</button>
							<button type="reset" style="width: 185px; height: 43px"
								class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>
		</main>

		<footer
			style="height:90px; text-align: center; background-image:url('images/bgd1.png');border: rgba(153,16,16,0.1) solid thin;background-color: rgb(57,61,73,0.1)">
		<p
			style="color: white; font-size: 12px; text-align: center; padding-top: 40px;">青岛科技大学(©版权所有)</p>
		</footer>
	</div>
	<script>
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
		layui.use([ 'element', 'form' ], function() {
			var element = layui.element;
			var form = layui.form;
			form.verify({
				name : [ /^[a-zA-Z0-9]{3,20}$/, '用户名组成为字母3-20位' ],
				sid : [ /^[0-9]{10}$/, '学号组成为10位数字' ],
				password : [ /^[a-zA-Z0-9]{6,20}$/, '密码组成为字母或数字,6-20位' ],
				numoro : [ /^[0-9]{0,}$/, '组成为数字' ],
				age : [ /^[1]?[0-9]{1,2}$/, '年龄为0-99' ],
				code : [ /^[0-9]{6}$/, '验证码组成为6位数字' ]
			})
		});
	</script>
</body>
</html>