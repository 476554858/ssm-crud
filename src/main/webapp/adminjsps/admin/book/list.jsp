<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<%pageContext.setAttribute("APP_PATH",request.getContextPath()); %>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
		background: rgb(254,238,189);
	}
	.icon {
		margin:10px;
		border: solid 2px gray;
		width: 160px;
		height: 190px;
		text-align: center;
		float: left;
	}
</style>
  </head>
  
  <body>
  <c:forEach items="${booklist}" var="b">
   <div class="icon">
    <a href="<c:url value='/book/adminFindBybid/${b.bid}'/>"><img src="${APP_PATH }/${b.image }" border="0"/></a>
      <br/>
   	<a href="<c:url value='/book/adminFindBybid/${b.bid}'/>">${b.bname }</a>
  </div>
   </c:forEach>
  
  </body>
 
</html>

