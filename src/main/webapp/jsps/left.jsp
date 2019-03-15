<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	
	<% pageContext.setAttribute("APP_PATH",request.getContextPath()); %>

   <script type="text/javascript" src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{
			font-size:10pt;
			text-align: center;
		}
		div {
			background: #87CEFA; 
			margin: 3px; 
			padding: 3px;
		}
		a {
			text-decoration: none;
		}
	</style>
  </head>
  
  <body>
<div>
	<a href="<c:url value='/book/findAll'/>">查看所有书籍</a>
</div>
<c:forEach items="${categoryList }" var="list">
<!-- ${pageContext.request.contextPath}/BookServlet?method=findByCategory&cid=${list.cid } -->
<div>
	<a href="${APP_PATH}/book/findByCategory?cid=${list.cid }">${list.cname }</a>
</div>

</c:forEach>



  </body>
</html>
