<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'bookRegister.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		#table1{
			margin-top: 70px;
			font-size: 10px;
		}
		td{
			margin-right: 40px;
			margin-top: 30px;
		}
	</style>
	<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	
  </head>
  
  <body>
  	<form action="servlet/LibraryServlet?method=updateBook" method="post">
	  	 <table align="center" id="table1">
	    	<tr>
	    		<td >书&nbsp;号：</td>
	    		<td ><input type="text" name="bookId" readonly="readonly" value="${book.bookId }"></td>
	    		<td >书&nbsp;名：</td>
	    		<td ><input type="text" name="bookName" value="${book.bookName }"></td>
	    	</tr>
	    	<tr>
	    		<td >作&nbsp;者：</td>
	    		<td ><input type="text" name="writer" value="${book.writer }"></td>
	    		<td >类别名称：</td>
	    		<td ><input type="text" name="bookType" value="${book.bookType }"></td>
	    	</tr>
	    	<tr>
	    		<td >出&nbsp;版&nbsp;社：</td>
	    		<td ><input type="text" name="press" value="${book.press }"></td>
	    		<td >发行日期：</td>
	    		<td ><input type="text" name="pressDate" onfocus="WdatePicker()" value="${book.pressDate }"></td>
	    	</tr>
	    	<tr>
	    		<td >操&nbsp;作&nbsp;员：</td>
	    		<td ><input type="text" name="operator" value="${book.operator }"></td>
	    		<td >价&nbsp;格：</td>
	    		<td ><input type="text" name="price" value="${book.price }">(元)</td>
	    	</tr>
	    	<tr>
	    		<td >入库数量：</td>
	    		<td ><input type="text" name="count" value="${book.count }"></td>
	    		<td >入库时间：</td>
	    		<td ><input type="text" onfocus="WdatePicker()" name="regDate" value="${book.regDate }"></td>
	    	</tr>
	    	<tr>
	    		<td >图书简介：</td>
	    		<td colspan="3"><textarea rows="5" cols="60" id="remark" name="remark" >${book.remark }</textarea></td>
	    	</tr>
	    	<tr>
	    		<td colspan="4" align="center">
	    			<input type="submit" value="保存">&nbsp;
	    			<input type="button" name="Submit" value="返回" class="button" onclick="history.back()" />
	    		</td>
	    	</tr>
	    </table>
	    ${error }
    </form>
   
  </body>
</html>
