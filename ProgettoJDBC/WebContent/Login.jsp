<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

 <%@ page import="exception.DatiErratiException" %>
 <%@ page import="java.lang.NullPointerException" %>
  <%@ page 
 		import = "java.lang.*"
 		import = "javax.persistence.*" %>
 		
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body background = "sfondo.jpg">

<jsp:useBean id="LoginBean" scope="request" class="view.LoginBean" />
<jsp:setProperty name="LoginBean" property="*" />
    
<%
if(request.getParameter("btn") != null) {
	try{
		if (LoginBean.validate()) {
			if(LoginBean.isAdmin()){%>
				<jsp:forward page="HomePageAdmin.jsp"/><%
			}else{
				%>
				<jsp:forward page="HomePageUser.jsp"/><%
			}
		}
		}catch (DatiErratiException e){
		%>
 	   <div class="alert alert-danger alert-dismissible" role="alert">
	   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	   	<strong> Errore</strong> Utente non esistente, username o password errati!
	   </div>
   <%}catch (NullPointerException e){
		%>
	   <div class="alert alert-warning alert-dismissible"role="alert">
   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
   	<strong>Attenzione!</strong> Inserire tutti i campi!
   </div><%}}%>
	<div class="container">
	<div class="jumbotron">	
   <div class="container">
   
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        <h2><strong> National Astrophysic Institute Observatory </strong></h2>
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>Login to our site</h3>
                            		Enter your e-mail and password to log on:
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-email">Username</label>
			                        	<input type="text" name="userid" placeholder="Username..." class="form-email form-control" id="email">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">Password</label>
			                        	<input type="password" name="password" placeholder="Password..." class="form-password form-control" id="password">
			                        </div>
			                        <button type="submit" class="btn-success" name="btn">Sign in</button>
			                    </form>
		                    </div>
                        </div>
                    </div>
                    </div>
                    </div>
</body>
</html>