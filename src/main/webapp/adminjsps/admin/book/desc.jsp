<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'bookdesc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<%pageContext.setAttribute("APP_PATH",request.getContextPath()); %>
	<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
		background: rgb(254,238,189);
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
</style>
  </head>
  
  <body>
  <div>
    <img src="${APP_PATH }/${book.image }" border="0"/>
  </div>
  <form id="form" style="margin:20px;" id="form"  method="post">
  	图书名称：<input type="text" name="bname" value="${book.bname }"/><br/>
  	图书单价：<input type="text" name="price" value="${book.price }"/><br/>
  	图书作者：<input type="text" name="author" value="${book.author }"/><br/>
  	图书分类：<select style="width: 150px; height: 20px;" name="cid">
  	<c:forEach items="${categoryList }" var="clist">
  	
  	<option value="" <c:if test="${clist.cid eq book.cid}">selected="selected"</c:if>>${clist.cname }</option>
  	
			
	</c:forEach>
<!-- 	<option value="" selected='selected'>Spring</option> -->
    	</select><br/>
    	
    	<input type="hidden" name="bid" value="${book.bid}"/>
  	<input type="button" id="del" value="del" onclick="return confirm('是否真要删除该图书？');"/>
  	<input type="button" id="mod" value="mod"/>
  </form>
  		<script type="text/javascript">
  			$("#del").click(function(){
  				$("#form").attr("action","${APP_PATH }/book/del");
  				$("#form").submit();
  			})
  			
  			$("#mod").click(function(){
  				$("#form").attr("action","${APP_PATH }/book/mod");
  				$("#form").submit();
  			})
  		
  		
  		</script>
  
  </body>
</html>
