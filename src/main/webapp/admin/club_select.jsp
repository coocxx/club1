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
function  delConfirm(){
	/*  var r=confirm("确认删除?删除之后会将这个社团所有相关信息删除掉(包括社团成员，社团申请信息)");
	if (r==true) {
		return true;
	}else{
		return false;
	} */
   
}
function stopConfirm(){
	var r=confirm("确认暂停社团运行?");
	if (r==true) {
        return true;
    }else{
        return false;
    }
	
}
function reConfirm(){
	var r=confirm("确认恢复社团运行?");
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
    <c:if test="${sessionScope.admin==null}">
        <script type="text/javascript">
           top.location="${pageContext.request.contextPath}/admin/admin_login.jsp?req=fail";
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
    <!-- 按条件查询 -->
    <div style="margin:0px auto;background-color:rgba(57, 61, 73, 0.1);height:170px;">
       <!-- <h2
        style="padding-top: 20px;padding-bottom:10px;margin-left: 420px; align: center; color: rgba(57, 61, 73, 0.7);">
        <span style="align: center; color: rgba(35, 38, 46, 0.7)">
            管理员账号信息</span>
    </h2> -->
        <form class="layui-form" method="post" name="form1" style="margin-left:70px;padding-top: 20px;"
            action="${pageContext.request.contextPath }/admin/selClubs">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">社团ID:</label>
                    <div class="layui-input-inline" style="width: 150px;">
                        <input type="text" name="id" class="layui-input" />
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">社团名:</label>
                    <div class="layui-input-inline" style="width: 150px;">
                        <input type="text" name="name" autocomplete="off" value=""
                            class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">社团类型:</label>
                    <div class="layui-input-inline" style="width: 150px;">
                        <input type="text" name="clubType.name" class="layui-input" />
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">创始人:</label>
                    <div class="layui-input-inline" style="width: 150px;">
                        <input type="text"  name="student.name" class="layui-input" />
                    </div>
                </div>

                
                <div class="layui-inline">
                    <label class="layui-form-label">活动地点:</label>
                    <div class="layui-input-inline" style="width: 150px;">
                        <input type="text" name="place" autocomplete="off" value=""
                            class="layui-input">
                    </div>
                </div>
                
                
                
                <div class="layui-inline">
                    <label class="layui-form-label">成立时间:</label>
                    <div class="layui-input-inline" style="width: 150px;">
                        <input type="text" id="time" name="time" autocomplete="off"
                            value="" class="layui-input">
                    </div>
                </div>
                
                <div class="layui-inline">
                    <label class="layui-form-label">状态:</label>
                    <div class="" style="display:inline;width: 150px;">
                        <input type="radio" name="status" value="0" title="正常">
                        <input type="radio" name="status" value="1" title="停止">
                        <input type="radio" name="status" value="3" title="全部" checked>
                    </div>
                </div>
                <div class="layui-inline ">
                   <div class="layui-input-block" style="margin-top:10px" >
                        <button class="layui-btn" lay-submit lay-filter="setId" style="width:100px;"><i class="layui-icon">&#xe615;</i>查询</button>
                        <button type="reset" class="layui-btn layui-btn-primary" style="width:100px;">重置</button>
                    </div>
                </div>
                
                
            </div>
        </form>
    </div>

    <!-- 条件查询没有结果不显示 -->
    <c:if test="${ curPage.data!=null && curPage.data.size()!=null }">
        <!-- 给表格固定大小 -->
        <div style="">
            <table class="layui-table">
                <colgroup>
                    <col width="10px">
                    <col width="80px">
                    <col width="80px">
                    <col width="10px">
                    <col width="40px">
                    <col width="80px">
                    <col>
                    <col>
                    <col width="80px">
                    <col width="10px">
                    <col>
                </colgroup>
                <thead>
                    <tr style="">
                        <!--  style="background-color:rgba(57,61,73,0.2);color:white" -->
                        <th>社团号</th>
                        <th>社团类型</th>
                        <th>名称</th>
                        <th>创始人</th>
                        <th>学号</th>
                        <th>活动地点</th>
                        <th>社团简介</th>
                        <th>社团宗旨</th>
                        <th>成立时间</th>
                        <th>状态</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${curPage.data }" var="i">
                        <tr>
                            <td>${i.id }</td>
                            <c:if test="${i.clubType.name!=null }">
                                <td>${i.clubType.name }</td>
                            </c:if>
                            <c:if test="${i.clubType.name==null }" >
                                <td>其他</td>
                            </c:if>
                            <td>${i.name }</td>
                            <td>${i.student.name}</td>
                            <td>${i.studentId}</td>
                            <td>${i.place}</td>
                            <td>${i.introduce}</td>
                            <td>${i.notice }</td>
                            <td>${i.time }</td>
                            <td><c:if test="${i.status==0 }">正常</c:if> <c:if
                                    test="${i.status==1 }">停止</c:if></td>
                            <td><div><a class="layui-btn layui-btn-radius layui-btn-normal"
                                href="${pageContext.request.contextPath }/admin/preUpdClub?id=${i.id }"><i class="layui-icon layui-icon-edit">编辑</i></a>
                                </div>
                                <%-- <c:if test="${i.status==1 }">
                                    <div><a class="layui-btn layui-btn-radius"
                                href="${pageContext.request.contextPath }/admin/delClub?id=${i.id }"
                                onclick="return delConfirm()"><i class="layui-icon">&#xe640;删除</i></a></div>
                                </c:if> --%>
                                <c:if test="${i.status==1 }">
                                    <div><a class="layui-btn layui-btn-radius"
                                href="${pageContext.request.contextPath }/admin/updStatus?id=${i.id }&status=0"
                                onclick="return reConfirm()"><i class="layui-icon">&#xe605;正常</i></a></div>
                                </c:if>
                                <c:if test="${i.status==0 }">
                                    <div><a class="layui-btn layui-btn-radius"
                                href="${pageContext.request.contextPath }/admin/updStatus?id=${i.id }&status=1"
                                onclick="return stopConfirm()"><i class="layui-icon">&#x1006;停止</i></a></div>
                                </c:if>
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
                    href="${pageContext.request.contextPath }/admin/selClubByPage?curPage=1">首页</a>
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
                    href="${pageContext.request.contextPath }/admin/selClubByPage?curPage=${curPage.curPage-1 }"><i class="layui-icon layui-icon-left"></i></a>
            </c:if>
            <!-- 下一页 -->
            <c:if test="${curPage.curPage==curPage.totalPage}">
                <a
                    style="border: solid 1px rgba(57, 61, 73, 0.3); color: rgba(57, 61, 73, 0.3);"
                    class="layui-btn layui-btn-primary" href="javascipt:void(0);"><i class="layui-icon">&#xe602;</i></a>
            </c:if>
            <c:if
                test="${(curPage.curPage>=1) && (curPage.curPage<curPage.totalPage)}">
                <a class="layui-btn"
                    href="${pageContext.request.contextPath }/admin/selClubByPage?curPage=${curPage.curPage+1 }"><i class="layui-icon">&#xe602;</i></a>
            </c:if>
            <!-- 尾页 -->
            <c:if test="${curPage.curPage==curPage.totalPage}">
                <a
                    style="border: solid 1px rgba(57, 61, 73, 0.3); color: rgba(57, 61, 73, 0.3);"
                    class="layui-btn layui-btn-primary" href="javascipt:void(0);">尾页</a>
            </c:if>
            <c:if test="${curPage.curPage!=curPage.totalPage}">
                <a class="layui-btn"
                    href="${pageContext.request.contextPath }/admin/selClubByPage?curPage=${curPage.totalPage }">尾页</a>
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
                if (document.form1.studentId.value == ''
                        || document.form1.studentId.value == 0) {
                    document.form1.studentId.value = 0;
                    
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