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
</head>
<body>
<script type="text/javascript">
layui.use('table', function(){
	  var table = layui.table;
	  
	  //第一个实例
	  table.render({
	    elem: '#demo'
	    ,height: 312
	    ,url: '${pageContext.request.contextPath }/admin/select/' //数据接口
	    ,page: true //开启分页
	    ,cols: [[ //表头
	      {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
	      ,{field: 'username', title: '用户名', width:80}
	      ,{field: 'sex', title: '性别', width:80, sort: true}
	      ,{field: 'city', title: '城市', width:80} 
	      ,{field: 'sign', title: '签名', width: 177}
	      ,{field: 'experience', title: '积分', width: 80, sort: true}
	      ,{field: 'score', title: '评分', width: 80, sort: true}
	      ,{field: 'classify', title: '职业', width: 80}
	      ,{field: 'wealth', title: '财富', width: 135, sort: true}
	    ]]
	  });
	  
	});
</script>
</body>
</html>