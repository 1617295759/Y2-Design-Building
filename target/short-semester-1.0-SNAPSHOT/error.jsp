<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'error.jsp' starting page</title>
    <style>p{font-size: 40px;}
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
    Your user name or password is wrong.<br>
    <p> <a href=./index.jsp target=“_self”>go to index.jsp</a> </p>
    <p> <a href=./login.jsp target=“_self”>go to login.jsp</a> </p>
</html>
