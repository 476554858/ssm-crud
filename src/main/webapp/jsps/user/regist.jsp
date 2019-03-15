<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<% pageContext.setAttribute("APP_PATH",request.getContextPath()); %>
	<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <h1>注册</h1>
<%-- <p style="color: red; font-weight: 900">${msg }</p> --%>


<!-- 
		${pageContext.request.contextPath }/UserServlet

 -->
<form action="${APP_PATH}/user/regist" method="post">
	
	用户名：<input id="username" type="text" name="username" /><scan id="checkResult">${msg.username}</scan><br/>
	密　码：<input type="password" name="password"/>${msg.password}<br/>
	邮　箱：<input type="text" name="email" />${msg.email}<br/>
	<input type="submit" value="注册"/>
</form>


<script type="text/javascript">

	//校验用户名是否可用
	 $("#username").change(function(){
		var username = $("#username").val();

		 $.ajax({
				url:"${APP_PATH}/user/checkUserName",
				data:{username:username},
				type:"POST",
				success:function(result){
					var msg = result.username;
					$("#checkResult").html(msg);
				}
			});
	}); 
	
	

</script>





  </body>
</html>
