<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加图书</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%pageContext.setAttribute("APP_PATH",request.getContextPath()); %>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {background: rgb(254,238,189);}
</style>
  </head>
  
  <body>
    <h1>添加图书</h1>
    <p style="font-weight: 900; color: red">${msg }</p>
    <form action="${APP_PATH}/book/add" method="post" enctype="multipart/form-data">
    	图书名称：<input style="width: 150px; height: 20px;" type="text" name="bname"/><br/>
    	图书图片：<input style="width: 223px; height: 20px;" type="file" name="file"/><br/>
    	图书单价：<input style="width: 150px; height: 20px;" type="text" name="price"/><br/>
    	图书作者：<input style="width: 150px; height: 20px;" type="text" name="author"/><br/>
    	图书分类：<select style="width: 150px; height: 20px;" name="cid">
    		<option value="1">JavaSE</option>
    		<option value="2">JavaEE</option>
			<option value="3">JavaScript</option>
			<option value="4">Hibernate</option>
			<option value="5">Struts</option>
			<option value="6">Spring</option>
    	</select>
    	<br/>
    	<input type="submit" value="添加图书"/>
    </form>
  </body>
</html>
