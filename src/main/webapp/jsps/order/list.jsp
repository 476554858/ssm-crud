<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单列表</title>
    
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
	* {
		font-size: 11pt;
	}
	div {
		border: solid 2px gray;
		width: 75px;
		height: 75px;
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
<h1>我的订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">
<c:forEach items="${orderList}" var="orders" >
	<tr bgcolor="gray" bordercolor="gray">
		<td colspan="6">
			订单编号：${orders.oid }
			成交时间：<fmt:formatDate value="${orders.ordertime}"
			 pattern="yy-MM-dd HH-mm-ss"/>
			金额：<font color="red"><b>
			<fmt:formatNumber value="${orders.total}" pattern="0.00"/>
			</b></font>　
	<c:choose>
		<c:when test="${orders.state eq 1 }">
			<a href="<c:url value='/order/load/${orders.oid}'/>">付款</a>
		</c:when>
		<c:when test="${orders.state eq 2 }">
			等待发货
		</c:when>
		<c:when test="${orders.state eq 3 }">
			<a href="${APP_PATH }/order/confirm/${orders.oid}">确认收货</a>
		</c:when>
		<c:otherwise>
			订单结束
		</c:otherwise>
	</c:choose>
		</td>
	</tr>

	<c:forEach items="${orders.list}" var="oi">
	<tr bordercolor="gray" align="center">
		<td width="15%">
			<div><img src="<c:url value='/${oi.book.image}'/>" height="75"/></div>
		</td>
		<td>书名：${oi.book.bname}</td>
		<td>单价：<fmt:formatNumber value="${oi.book.price}" pattern="0.00"/>元</td>
		<td>作者：${oi.book.author}</td>
		<td>数量：${oi.count}</td>
		<td>小计：<fmt:formatNumber value="${oi.subtotal}" pattern="0.00"/>元</td>
	</tr>
	</c:forEach>
</c:forEach>
</table>
  </body>
</html>
