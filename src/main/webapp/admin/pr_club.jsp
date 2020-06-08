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
        style="margin: 10px 0px 30px 420px; align: center; color: rgba(57, 61, 73, 0.7)">
        <span style="align: center; color: rgba(35, 38, 46, 0.7)">
           <i style="color:green;">${president.club.name }</i>&nbsp;信息</span>
    </h2>



    <div style="height: 550px; width: 750px; margin: 10px auto;">
        <form class="layui-form layui-form-pane" name="form1"
            action="${pageContext.request.contextPath}/admin/updClubPr">


            <div class="layui-form-item">
                <label class="layui-form-label"style="background-color: rgba(57, 61, 73, 0.1)">社团账号</label>
                <div class="layui-input-block">
                    <input type="text" id="id" name="id" class="layui-input"
                        value="${president.club.id }" readonly="readonly"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"style="background-color: rgba(57, 61, 73, 0.1)">名称</label>
                <div class="layui-input-block">
                    <input type="text" id="name" name="name" class="layui-input"
                        value="${president.club.name }"  readonly="readonly"/>
                </div>
            </div>
            <div class="layui-form-item" pane>
                <label class="layui-form-label" style="background-color: rgba(57, 61, 73, 0.1)">社团类型</label>
                <div class="layui-input-block">
                        <select name="clubTypeId" id="clubTypeId">
                            <option value="${president.club.clubTypeId  }">&emsp;${president.club.clubType.name }&emsp;</option>
                        </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="background-color: rgba(57, 61, 73, 0.1)"> 创始人学号</label>
                <div class="layui-input-block">
                    <input type="text" id="studentId" name="studentId"
                        class="layui-input" value="${president.student.id }" readonly="readonly"/>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="background-color: rgba(57, 61, 73, 0.1)">社团地点</label>
                <div class="layui-input-block">
                    <input type="text" id="place" name="place" class="layui-input"
                        value="${president.club.place }" lay-verify="required" />
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="background-color: rgba(57, 61, 73, 0.1)">状态</label>
                <div class="layui-input-block">
                    <c:if
                        test="${president.club.status==0 ||(president.club.status!=1 && president.club.status!=0)}">
                        <input type="radio" name="status" value="0" title="正常" checked>
                        <input type="radio" name="status" value="1" title="停止">
                    </c:if>
                    <c:if test="${president.club.status==1 }">
                        <input type="radio" name="status" value="0" title="正常">
                        <input type="radio" name="status" value="1" title="停止" checked>
                    </c:if>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label" style="background-color: rgba(57, 61, 73, 0.1)">公告</label>
                <div class="layui-input-block">
                    <textarea name="introduce" placeholder="请输入内容"
                        class="layui-textarea">${president.club.introduce}</textarea>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label" style="background-color: rgba(57, 61, 73, 0.1)">社团简介</label>
                <div class="layui-input-block">
                    <textarea name="notice" placeholder="请输入内容"
                         class="layui-textarea">${president.club.notice}</textarea>
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