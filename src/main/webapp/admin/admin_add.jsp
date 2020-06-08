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
           top.location="${pageContext.request.contextPath}/admin/admin_login.jsp?req=fail";
        </script>
    </c:if>
    <c:if test="${result != null}">
        <script type="text/javascript">
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("${result}");
          }); 
        </script>
    </c:if>

    <h2
        style="margin: 30px 0px 20px 80px; text-align: center; color: rgba(57, 61, 73, 0.7)">
        <span style="align: center; color: rgba(35, 38, 46, 0.7)">
            添加管理员</span>
    </h2>
 
 <div style="height:550px;width:650px;margin:10px auto;">
    <form class="layui-form form1" name="form1" 
        action="${pageContext.request.contextPath}/admin/addAdmin" >
        <table class="layui-table" lay-size="lg" style="text-align:center">
            <colgroup>
                <col width="150">
                <col width="150">
            </colgroup>
            <tbody>
                
                <tr >
                    <td style="background-color: rgba(57, 61, 73, 0.1)">姓名</td>
                    <td><input type="text" id="name"
                        style="height: 50px; width: 520px;margin-left:30px;padding-left:100px;"
                        name="name" class="layui-input" value="${addAdmin.name }"
                        lay-verify="name" /></td>

                </tr>
                <tr >
                    <td style="background-color: rgba(57, 61, 73, 0.1)">密码</td>
                    <td><input type="password" id="password"
                        style="height: 50px; width: 520px;margin-left:30px;padding-left:100px" name="password"
                        value="${addAdmin.password }" class="layui-input" 
                        lay-verify="password|required"/></td>

                </tr>
                <tr>
                    <td style="background-color: rgba(57, 61, 73, 0.1)">电话号码</td>
                    <td><input type="text" id="phone"
                        style="height: 50px; width: 520px;margin-left:30px;padding-left:100px" class="layui-input"
                        name="phone" value="${addAdmin.phone }" 
                        lay-verify="required|phone"/></td>

                </tr>
                <tr >
                    <td style="background-color: rgba(57, 61, 73, 0.1)">邮箱</td>
                    <td><input type="text" id="email"
                        style="height: 50px; width: 520px;margin-left:30px;padding-left:100px" class="layui-input"
                        name="email" value="${addAdmin.email }"
                        lay-verify="email" /></td>
                </tr>
                <tr height="60">
                    <td style="background-color: rgba(57, 61, 73, 0.1)">性别</td>
                    <td >
                           <c:if test="${addAdmin.sex==0 ||(addAdmin.sex!=1&&addAmin.sex!=0)}">
                              <input
                                type="radio" name="sex" value="0" title="男" checked>
                                <input
                                type="radio" name="sex" value="1" title="女">
                            </c:if>
                            <c:if test="${addAdmin.sex==1 }">
                              <input type="radio" name="sex" value="0" title="男">
                              <input
                                type="radio" name="sex" value="1" title="女" checked>
                            </c:if>
                     </td>   
                </tr>
            </tbody>
        </table>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-top:30px;">
                <button class="layui-btn" style="margin-left:80px;width: 200px;" lay-submit>添加</button>
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
            form.on('submit(form1)', function() {
                verifyInfo();
                return false;
            });
            form.verify({
                name : [ /^[a-zA-Z0-9]{3,20}$/, '用户名组成为字母3-20位' ],
                sid : [ /^[0-9]{10}$/, '学号组成为10位数字' ] ,
                password : [ /^[a-zA-Z0-9]{6,20}$/, '密码组成为字母或数字,6-20位' ]
            })
        });
    </script>
</body>
</html>