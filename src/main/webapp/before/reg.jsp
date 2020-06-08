<%@page import="com.cxx.pojo.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户注册</title>
<link href="${pageContext.request.contextPath }/js/layui/css/layui.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/layui/layui.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/verify.js"></script>
<%-- <script type="text/javascript"
	src="${pageContext.request.contextPath }/before/js/reg.js">   </script> --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.7.2.js">   </script>
</head>
<script type="text/javascript">
//验证用户名和密码格式是否正确
function verifyInfo() {
    //名字
    if(!verifyName(document.form1.name.value)) {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("请按要求填写名(字母或汉字)!");
          });  
        return false;
    }
   
    //密码
    if(!verifyPwd(document.form1.password.value)) {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("请按照要求填写密码(字母或数字，6-20位)");
          });  
        return false;
    }
    
    //学号
    if (!verifySid(document.form1.id.value)) {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("请按要求填写学号(10位数字)");
          });  
        return false;
    }
    //电话号码
    if(!verifyTel(document.form1.phone.value)) {
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("请按要求输入电话号码(11位数字)!");
          });  
        return false;
    }
    //电子邮箱
    if(!verifyEmail(document.form1.email.value)) {
         layui.use('layer', function(){
             var layer = layui.layer;
             layer.msg("电子邮箱格式不对");
           });  
        return false;
    }
    //验证码
    if (!verifyNotNull(document.form1.code.value)) {
         layui.use('layer', function(){
             var layer = layui.layer;
             layer.msg("验证码不能为空!");
           });  
        return false;
    }
    //班级号
   /*  if (!verifyNotNull(document.form1.classId.value)) {
         layui.use('layer', function(){
             var layer = layui.layer;
             layer.msg("班级不能为空!");
           });  
        return false;
    } */
}

//内容不为空
function verifyNullCode() {
	
    if(!verifyNotNull(document.form1.id.value)) {
    	layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("请输入学号!");
          });  
        return false;
    }
    
    if(!verifyNotNull(document.form1.name.value)) {
    	layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("请输入姓名!");
          });  
        return false;
    }
    if(!verifyNotNull(document.form1.password.value)) {
    	layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("请输入密码!");
          });  
        return false;
    }
    if(!verifyNotNull(document.form1.phone.value)) {
    	layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("请输入电话号码!");
          });  
        return false;
    }
return true;
}
 /*请求控制器发送验证码到邮箱*/
function redirect(){
	
	if(!verifyNotNull(document.form1.email.value)){
		layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("请输入邮箱!");
          }); 
	} else if(!verifyEmail(document.form1.email.value)) {
     layui.use('layer', function(){
         var layer = layui.layer;
         layer.msg("电子邮箱格式不对");
       });  
     
    }else{
        if(verifyNullCode()){
        	var email=document.form1.email.value;
            var id=document.form1.id.value;
            var name=document.form1.name.value;
            var password=document.form1.password.value;
            var phone=document.form1.phone.value;
            var sex=document.form1.sex.value;
            var classesId=document.form1.classesId.value;
            var hobby=document.getElementById("hobby").value;
           
            window.location.href='${pageContext.request.contextPath}/before/preparedReg?email='+email+'&id='+id+"&name="+name+"&password="+password+"&phone="+phone+"&hobby="+hobby+"&classesId="+classesId+"&sex="+sex;
		}
    }	   
}


/* 获取验证码倒计时 */
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

<body>
	<%
		Student regStudent = (Student) session.getAttribute("regStudent");
		String email1 = null;
		if (regStudent != null) {
			email1 = regStudent.getEmail();
		}
		System.out.println("email:" + email1);
		System.out.println("result:" + (String) session.getAttribute("result"));
	%>

	<c:if test="${reg!=null }">
		<script type="text/javascript">
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("<%=session.getAttribute("reg")%>");
          }); 
        </script>
		<%
			session.removeAttribute("reg");
		%>
	</c:if>

	<c:if test="${result=='success'}">
		<script type="text/javascript">
            $(function(){
            	var e="<%=email1%>";
				document.form1.email.value = e;
				if (e == '' || e == null) {
					layui.use('layer', function() {
						var layer = layui.layer;
						layer.msg("请输入邮箱!");
					});
				} else {
					setemail();
				}
			})
		</script>
		<%
			session.removeAttribute("result");
		%>
	</c:if>
	<div class="container">
		<header> <jsp:include flush="fasle" page="top.jsp" /> </header>
		<main style="background-image: url(''); background-repeat: repeat;">
		<div class="login"
			style="height: 800px; width: 700px; margin: 0px auto; background-color: white">
			<div class="title"
				style="height: 80px; width: 800px; margin: 0px auto;">
				<h1
					style="margin: 15px 0px 0px 300px; color:green; line-height: 90px">注&emsp;册</h1>
			</div>
			<div class="content"
				style="margin: 0px auto; height: 550px; width: 600px;">

				<form class="layui-form form1" name="form1"
					action="${pageContext.request.contextPath}/before/reg"
					onsubmit="return verifyInfo()">

					<div class="layui-form-item">
						<label for="id" class="layui-form-label">学&emsp;&emsp;号：</label>
						<div class="layui-input-block">
							<c:if test="${regStudent.id!=0}">
								<input type="text" id="id" style="height: 40px; width: 400px"
									name="id" class="layui-input" value="${regStudent.id }"
									lay-verify="sid" />
							</c:if>
							<c:if test="${regStudent.id==0 }">
								<input type="text" id="id" style="height: 40px; width: 400px"
									name="id" class="layui-input" lay-verify="sid" />
							</c:if>

						</div>

					</div>

					<div class="layui-form-item">
						<label class="layui-form-label">班&emsp;&emsp;级：</label>
						<div class="layui-input-block"
							style="display: block; height: 40px; width: 400px">
							<c:if test="${regStudent.classes==null }">
							     <select name="classesId" id="classesId">
	                                <c:forEach items="${classes }" var="i">
	                                    <option value="${i.id  }">&emsp;${i.major.academy.name }&emsp;${i.major.name }&emsp;${i.name }</option>
	                                </c:forEach>
                                </select>
							</c:if>
							
							<c:if test="${regStudent.classes!=null }">
                                 <select name="classesId" id="classesId">
                                    <c:forEach items="${classes }" var="i">
                                        <c:if test="${regStudent.classesId==i.id}">
			                               <option value="${i.id  }" selected>&emsp;${i.major.academy.name }&emsp;${i.major.name }&emsp;${i.name }</option>
                                        </c:if>
                                        <c:if test="${regStudent.classesId!=i.id}">
			                               <%-- <option value="${regStudent.classesId }">&emsp;${regStudent.classes.major.academy.name }&emsp;${regStudent.classes.major.name }&emsp;${regStudent.classes.name }</option> --%>
                                            <option value="${i.id  }">&emsp;${i.major.academy.name }&emsp;${i.major.name }&emsp;${i.name }</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </c:if>
						</div>
					</div>
					<div class="layui-form-item">
						<label for="name" class="layui-form-label">姓&emsp;&emsp;名：</label>
						<div class="layui-input-block">
							<input type="text" id="name" style="height: 40px; width: 400px"
								name="name" class="layui-input" value="${regStudent.name }"
								lay-verify="required" lay-reqText="姓名不能为空" />
						</div>

					</div>

					<div class="layui-form-item">
						<label for="password" class="layui-form-label">密&emsp;&emsp;码：</label>
						<div class="layui-input-inline">
							<input type="password" id="password"
								style="height: 40px; width: 400px" name="password"
								value="${regStudent.password }" class="layui-input"
								lay-verify="password" />
						</div>
						<!-- <div class="layui-form-mid layui-word-aux">辅助文字</div> -->
					</div>


					<div class="layui-form-item">
						<label for="phone" class="layui-form-label">电话号码：</label>
						<div class="layui-input-block">
							<input type="text" id="phone" style="height: 40px; width: 400px"
								class="layui-input" name="phone" value="${regStudent.phone }"
								required lay-verify="phone"/>
						</div>
					</div>

					<div class="layui-form-item">
						<label class="layui-form-label" for="email">邮&emsp;&emsp;箱：</label>
						<div class="layui-input-block">
							<input type="text" id="email" style="height: 40px; width: 400px"
								class="layui-input" name="email" value="${regStudent.email }"
								lay-verify="email" />
						</div>
					</div>

					<!-- 验证码 -->
					<div class="layui-form-item">
						<label for="code" class="layui-form-label">验&ensp;证&ensp;码：</label>
						<!-- border:solid thin 2px rgba(255,255,255,0.2); -->
						<div class="layui-input-block">
							<input type="text" id="code" class="layui-input"
								style="display: inline-block; width: 240px" name="code"
								lay-verify="required" lay-reqText="验证码不能为空"/> <input type="button"
								class="layui-btn" id="btn" style="width: 150px" value="获取验证码"
								onclick="redirect()" />
							<!-- <button class="layui-btn" id="btn" style="width: 150px"lay-filter="getCode">获取验证码</button> -->
						</div>
					</div>
					<div class="layui-form-item">
						<div class="layui-input-block">
						    
						    <c:if test="${regStudent.sex==0 ||(regStudent.sex!=1&&regStudent.sex!=0)}">
						      <input
                                type="radio" name="sex" value="0" title="男" checked>
                                <input
                                type="radio" name="sex" value="1" title="女">
						    </c:if>
						    <c:if test="${regStudent.sex==1 }">
						      <input type="radio" name="sex" value="0" title="男">
                              <input
                                type="radio" name="sex" value="1" title="女" checked>
                            </c:if>
						</div>
					</div>

					<div class="layui-form-item layui-form-text">
						<label class="layui-form-label">性格爱好:</label>
						<div class="layui-input-block">
							<textarea id="hobby" name="hobby" style="width: 400px"
								class="layui-textarea" value="${regStudent.hobby }"></textarea>
						</div>
					</div>
					<a style="display: inline-block; margin: 0px 0px 15px 280px"
						href="${pageContext.request.contextPath }/before/pwd_login.jsp">已有账号？</a>
					<div class="layui-form-item">
						<div class="layui-input-block">
							<button class="layui-btn" style="width: 200px;" lay-submit>注册</button>
							<button type="reset" style="width: 200px"
								class="layui-btn layui-btn-primary">重置</button>
						</div>
					</div>
				</form>





			</div>
		</div>
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
	              age : [ /^[1]?[0-9]{1,2}$/, '年龄为0-99' ]

	          })
		});
	</script>
</body>
</html>
