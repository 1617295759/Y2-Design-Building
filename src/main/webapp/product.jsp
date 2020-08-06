
<%@page import="beans.Product"%>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'product.jsp' starting page</title>
    <style>p{font-size: 20px;}
    	a:link {color: #FF0000}
        a:visited {color: #00FF00} 
        a:hover {color: #FF00FF} 
        a:active {color: #0000FF} 
        body {
			  margin: 0;
		      padding: 0;
		  }
		  header{
			  height: 30px;
			  width: 100%;
			  background-color: #bbbbbb;
			  text-align: center;
		  }
    </style>
  </head>
  
  <body>
  	<header>HearthStone Organ.</header>
  	<body style="background:url(image/background.png)">
    This is product page. <br>
    <p> <a href=./index.jsp target=“_self”>Back to index</a> 
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href=./login target=“_self”>Your information</a> </p>
    
    <form method="post" action="./product">
		<table>
			<tr><td><input type=radio name="searchchoice" value="searchbykeywords"/>search by key words</td>
			<td><input type=text name="keywords"/></td></tr>
		</table>
		<table><tr><td><input type=radio name="searchchoice" value="searchbykinds"/>search by kinds</td>
			<td><input type=radio name=kind value="wooden"/>wooden
			<input type=radio name=kind value="plastics"/>plastics
			<input type=radio name=kind value="metal"/>metal
			<input type=radio name=kind value="mixed"/>mixed material</td></tr>
		</table>
		<table><tr><td><input type=radio name="searchchoice" value="sortbytime"/>sort by time</td>
			<td><input type=radio name=howtosort value="ASC"/>ASC
			<input type=radio name=howtosort value="DESC"/>DESC
			</td></tr>
			<tr><td><input type=radio name="searchchoice" value="sortbyprice"/>sort by price</td>
			<td><input type=radio name=howtosortbyprice value="ASC"/>ASC
			<input type=radio name=howtosortbyprice value="DESC"/>DESC</td>
			</tr>
		</table>
		<input type="image" name="Search" value="search" src="image/submit.png" width="90">  
	</form>
    
    <table > 
 		<c:forEach var="product" items = "${products}">
        <tr>  
           <th>name</th>  
           <th>appearance</th>
           <th>superCatagory</th>
           <th>added time</th>  
           <th>functionality</th>
           <th>price</th>
           <th>image</th>
           <th>add it to the cart</th>     
        </tr>  
        <tr>
          <td>${product.getName()}</td>
          <td>${product.getAppearance()}</td>
          <td>${product.getSuperCategory()}</td>
          <td>${product.getAddedTime()}</td> 
          <td>${product.getFunctionality()}</td>
          <td>${product.getPrice()}</td>
          <td><img alt="index" src=${product.getImgsrc()} width="100"></td>
          <td><a href="./addintocart?addintocartproduct=${product.getName()}"
          		onclick="if(confirm('add this into your carts'+ '?')===false) return false;">add into cart</a></td>        
          </tr>  
		</c:forEach>
	</table>
  </body>
</html>