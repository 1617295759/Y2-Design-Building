<%@ page language="java" pageEncoding="GB18030"%>
 
<html> 
	<head>
		<title>JSP for login form</title>
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
		  p{font-size: 50px;}
		</style>
	</head>
	<body>
	<body style="background:url(image/background.png)">
	<header>HearthStone Organ.</header>
	<p> <a href=./index.jsp target=¡°_self¡±>go to index.jsp</a> </p>
		
		<form method="post" action="./login"> 
			<p>username : <input type="text" name="username"/><br/>
			password : <input type="password" name="password"/><br/>
			<input type="image" name="submit" value="Submit" src="image/submit.png" width="90"> 
		</form>
		
	</body>
</html>

