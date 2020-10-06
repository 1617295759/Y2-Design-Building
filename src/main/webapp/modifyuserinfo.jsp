<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'modifyuserinfo.jsp' starting page</title>
	<style>p{font-size: 30px;}
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
	<body style="background:url(image/background.png)" >
    <p> <a href=./userinfo.jsp target=“_self”>go to userinfo.jsp</a> </p>
    This is user's information modify page. <br>
    <script>
    	function check(){
    		var email = document.forms["modify"]["email"].value;
    		var regemail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    		if(email.length!=0 && !(regemail.test(email))){alert("please type email in a right pattern");return false;}
    		
			var phone = document.forms["modify"]["phone"].value;
			var regphone=/(^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$)|(^(([0\+]\d{2,3})?(0\d{2,3}))(\d{7,8})((\d{3,}))?$)|(^0{0,1}1[3|4|5|6|7|8|9][0-9]{9}$)/;
			if(phone.length!=0 && regphone.test(phone)==false){alert("please type phone in a right pattern");return false;}
    		
    		var age = document.forms["modify"]["age"].value;
    		var regage = /^(([0-9]|[1-9][1-9]|1[0-7][0-9])(\\.[0-9]+)?|180)$/;
    		if(age.length!=0 && regage.test(age)==false){alert("please type in a right age");return false;}
    		return true;
    	}
    </script>
    
    <form name="modify" id="modify" method="post" action="./modifyuserinfo" onSubmit="return check()">
		<ul>	
			<li>password : <input type="password" name="password"/></li>
			<li>Sex : <input type="radio" name="sex" value="man"/>man
				<input type="radio" name="sex" value="woman"/>woman
			</li>
			<li>E-mail :<input type="text" id="email" name="email"/></li>
			<li>phone :<input type="text" id="phone" name="phone"/></li>
			<li>address :<input type="text" id="address" name="address"/></li>
			<li><input type="image" src="image/submit.png" width="90"></li>
		</ul>	
	</form>
	

	
  </body>
</html>
