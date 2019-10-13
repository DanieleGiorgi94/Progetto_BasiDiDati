<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="Login" class="view.LoginBean" scope="session"/>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>


<%
    Login.setEmail("");
    Login.setPassword("");
    Login.setAdmin(false);
    %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<title>Homepage</title>
</head>
<body background = "sfondo2.JPG">
<nav class="navbar navbar-ligth" style="background-color: #00121F" role="navigation">

 	 <!-- Logo e pulsante per barra ridimensionata -->
 	 <div class="navbar-header">
    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
      	<span class="sr-only">Espandi barra di navigazione</span>
      	<span class="icon-bar"></span>
      	<span class="icon-bar"></span>
     	 <span class="icon-bar"></span>
   	 	</button>
  	</div>

  	<!-- Elementi della barra -->
  	<div class="collapse navbar-collapse navbar-ex1-collapse">
    	<ul class="nav navbar-nav">
     	 <li><a href="Homepage.jsp">HOMEPAGE</a></li>
     	 <li><a href="Login.jsp">Login</a></li>
    	</ul>
  	</div>
  	</nav>
<div class="container">
	
  		
  	<div id="myCarousel" class="carousel slide" data-ride="carousel">
  		<!-- Indicators -->
  		<ol class="carousel-indicators">
    	<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    	<li data-target="#myCarousel" data-slide-to="1" ></li>
    	<li data-target="#myCarousel" data-slide-to="2"></li>
 
  		</ol>
	
  <!-- Wrapper for slides -->
  	<div class="carousel-inner" role="listbox">
   		<div class="item active" align="center">
      	<img src="nascitastelle.jpg">
    	</div>
    	<div class="item" align="center">
      	<img src="protostelle.jpg">
    	</div>
    	<div class="item" align="center">
      	<img src="sfondo.jpg">
    	</div>
    	
  	</div>
  	

  	<!-- Left and right controls -->
  	<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
   	 <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
   	 <span class="sr-only">Previous</span>
  	</a>
  	<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
   	 <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
   	 <span class="sr-only">Next</span>
 	 </a>
	</div>
	</div>


</body>

<style>
	.input-group{padding-bottom:15px;}
	h1{font-family:"Serif";}
	.carousel-inner{height:300px; margin-top:10px;}
</style>
</html>