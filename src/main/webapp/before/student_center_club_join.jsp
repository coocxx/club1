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

function stopConfirm(){
    var r=confirm("确认撤销申请?");
    if (r==true) {
        return true;
    }else{
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
    <c:if test="${sessionScope.student==null}">
        <script type="text/javascript">
           top.location="${pageContext.request.contextPath}/before/pwd_login.jsp?";
        </script>
    </c:if>
    <c:if test="${result != null}">
        <script type="text/javascript">
        layui.use('layer', function(){
            var layer = layui.layer;
            layer.msg("${result} ");
            });
        </script>
    </c:if>
    <!-- 条件查询没有结果不显示 -->
    
   
    <c:if test="${ clubs!=null}">
        <!-- 给表格固定大小 -->
        <div style="">
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
                </colgroup>
                
                <thead>
                
                    <tr style="">
                        <th>社团类型</th>
                        <th>社团名称</th>
                        <th>申请时间</th>
                        <th>状态</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${clubs }" var="i">
                        <tr>
                            <c:if test="${i.club.clubType.name!=null }">
                                <td>${i.club.clubType.name }</td>
                            </c:if>
                            <c:if test="${i.club.clubType.name==null }" >
                                <td>其他</td>
                            </c:if>
                            
                            <td>${i.club.name}</td>
                            <td>${i.time }</td>
                            <td><c:if test="${i.status==0 }">拒绝</c:if> <c:if
                                    test="${i.status==1 }">通过</c:if><c:if
                                    test="${i.status==2 }">待审核</c:if></td>
                            <c:if test="${i.status==2 }">
                                <td><a class="layui-btn layui-btn-normal" href="${pageContext.request.contextPath}/before/cancelJoin?id=${i.id}" onclick="return stopConfirm();">撤销加入该社团</a>
                            </td></c:if>
                        </tr>
                    </c:forEach>
                </tbody>
                
            </table>
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