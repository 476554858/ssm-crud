<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>购物车列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	
	<% pageContext.setAttribute("APP_PATH",request.getContextPath()); %>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	* {
		font-size: 11pt;
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>购物车</h1>
<c:choose>
	<c:when test="${not empty cart and fn:length(cart.map) gt 0}">
<table border="1" width="100%" cellspacing="0" background="black">
	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			<%-- <a href="${APP_PATH}/CartServlet?method=clear">清空购物车</a> --%>
		</td>
	</tr>
	<tr>
		<th>图片</th>
		<th>书名</th>
		<th>作者</th>
		<th>单价</th>
		<th>数量</th>
		<th>小计</th>
		<th>操作</th>
	</tr>
	
	<%-- <c:forEach var="item" items="${map2}">   
		${item.value}<br>   
	</c:forEach>    --%>
<body>
<c:forEach items="${cart.map}" var="c">
	<tr>
		<td><div><img src="<c:url value='/${c.value.book.image }'/>"/></div></td>
		<td>${c.value.book.bname}</td>
		<td> ${c.value.book.author }</td>
		<td><fmt:formatNumber value="${c.value.book.price }" pattern="0.00"/>元</td>
		<td>${c.value.count }</td>
		<td>${c.value.subTotal }</td>
		<td> <a href="${APP_PATH}/cart/delete?bid=${c.value.book.bid}">删除</a></td>
	</tr>
	</c:forEach>

	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
		<%-- 	${cart.total} --%>
		</td>
	</tr>
	<tr>
	
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
		<a id="buy" href="<c:url value='/order/add'/>"></a>
		</td>
	</tr>
</table>
</c:when>
<c:otherwise>
	<img src="${APP_PATH }/images/cart.jpg" alt="" />
</c:otherwise>
</c:choose>
  </body>
  
</html>
