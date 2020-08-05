<%@page import="vo.Orders.*"%>
<%@page import="vo.Cart.*"%>
<%@page import="vo.User.*"%>

<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>  
    <title>My JSP 'userinfo.jsp' starting page</title>
    <style>
    	a:link {color: #FF0000}
        a:visited {color: #00FF00} 
        a:hover {color: #FF00FF} 
        a:active {color: #0000FF} 
        <style type="text/css">
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
		  p{font-size: 30px;}
		</style>
  </head>
  
  <body>
  	<header>HearthStone Organ</header>
  	<body style="background:url(image/background.png)">
  	<p> <a href=./index.jsp target="_self">Back to index</a>
  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  	<a href=./product target="_self">Watch for Product</a> </p>
    <p>Welcome ${user.getUsername()}</p>
    <ul>
    	<li>Your age : ${user.getAge()}</li>
    	<li>Your E-mail : ${user.getEmail()}</li>
    	<li>Your sex : ${user.getSex()}</li>
    	<li>Your phoneNo : ${user.getPhone()}</li>
    </ul>
    
    <p> <a href=./modifyuserinfo.jsp target=“_self”>
    	<td style="text-align:center">modify your information</td></a> </p>
    
	<br>
	<p>Your carts<br></p>
	<table>
    	<c:forEach var="cart" items = "${carts}">
        <tr>  
           <th>product</th>  
           <th>added time</th>
           <th>delete operation</th>
           <th>order operation</th>       
        </tr>  
        <tr>
          <td>${cart.getProduct()}</td>
          <td>${cart.getTime()}</td>
          <td><a href="./deletecart?deletedproduct=${cart.getProduct()}&deletedtime=${cart.getTime()}"
          		onclick="if(confirm('delete it'+ '?')===false) return false;">delete this</a></td>
          <td><a href="./ordercart?orderedproduct=${cart.getProduct()}&orderedtime=${cart.getTime()}"
          		onclick="if(confirm('order it'+ '?')===false) return false;">order this</a></td>        
          </tr>  
		</c:forEach>
	</table>
	<p><td style="text-align:center">Your orders</td><br></p>
	<table > 
    	<c:forEach var="order" items = "${orders}"> 
        <tr>  
           <th>product</th>  
           <th>ordered time</th>
           <th>delete operation</th>       
        </tr>  
        <tr>
          <td>${order.getProduct()}</td>
          <td>${order.getOrderedtime()}</td>
          <td>${order.getPayedtime()}</td>
          <td><a href="./deleteorder?deletedorder=${order.getProduct()}&deletedtime=${order.getOrderedtime()}"
          		onclick="if(confirm('delete this order'+ '?')===false) return false;">delete this</a></td>   
          </tr>  
		</c:forEach>
	</table>
  </body>
</html>
