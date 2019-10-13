<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
	
	<%@ page import="model.Contorno"%>
	<%@ page import="model.Stella"%>
	<%@ page import="model.TipiStella"%>
	
<jsp:useBean id="contornobean" scope="request"
class="view.ContornoBean" />

<jsp:useBean id="stellabean" scope="request"
	class="view.StellaBean" />
	

	
<jsp:useBean id="Login" class="view.LoginBean" scope="session"/>

<jsp:setProperty name="stellabean" property="*"/>

<%@ page import="java.util.*" %>
<%@ page import= "java.io.*" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca in regione</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
</head>
<body background = "sfondo2.JPG">
<div class="container">

<%
if(Login.isAdmin()){ %>
<nav class="navbar navbar-light" style="background-color: #00121F" role="navigation">
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
     	 <li><a href="HomePageAdmin.jsp">HOMEPAGE</a></li>
     	 <li><a href="Registrazione.jsp">Registra Utente</a></li>
     	 <li><a href="Importazione di un file.jsp">Importa un file</a></li>
     	 <li class="dropdown-inverse"><a class="dropdown-toggle"
					data-toggle="dropdown"> Satellite <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="Inserire i dati di un satellite.jsp">Inserisci Satellite</a></li>
						<li><a href="Inserire i dati degli strumenti con le relative bande.jsp">Inserisci strumenti per un satellite</a></li>
					</ul></li>
		<li class="dropdown-inverse"><a class="dropdown-toggle"
					data-toggle="dropdown"> Ricerca un oggetto <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="Ricerca di un oggetto all interno di una regione.jsp">Ricerca un oggetto in una regione</a></li>
						<li><a href="Ricerca di un oggetto di tutte le stelle all'interno di una struttura estesa.jsp">Ricerca di un oggetto di tutte le stelle all'interno di una struttura estesa</a></li>
					</ul></li>
		<li class="dropdown-inverse"><a class="dropdown-toggle"
					data-toggle="dropdown"> Filamento <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="Recupero informazioni derivate di un filamento.jsp">Recupero informazioni derivate di un filamento</a></li>
						<li><a href="Ricerca struttura per contrasto ed ellitticita.jsp">Ricerca struttura per contrasto ed ellitticita</a></li>
						<li><a href="Ricerca struttura per numero segmenti.jsp">Ricerca struttura per numero di segmenti</a></li>
						<li><a href="Distanza di un vertice di un segmento dal contorno.jsp">Distanza di un vertice di un segmento dal contorno</a></li>
					</ul></li>
		<li class="dropdown-inverse"><a class="dropdown-toggle"
					data-toggle="dropdown"> Stella <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="Frazione di stelle in formazione nei filamenti all interno di una regione.jsp">Frazione di stelle in formazione nei filamenti all’interno di una regione</a></li>
						<li><a href="Posizione di una stella rispetto alla spina dorsale-distanza.jsp">Posizione di una stella rispetto alla spina dorsale-distanza</a></li>
						<li><a href="Posizione di una stella rispetto alla spina dorsale-flusso.jsp">Posizione di una stella rispetto alla spina dorsale-flusso</a></li>
					</ul></li>
     	
     	 
     	 <li><a href="Homepage.jsp">Logout</a></li>
     	 
    	</ul>
  	</div>
  	</nav>
<%}else{ %>
<nav class="navbar navbar-light" style="background-color: #00121F" role="navigation">

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
     	 <li><a href="HomePageUser.jsp">HOMEPAGE</a></li>
     	 
		<li class="dropdown-inverse"><a class="dropdown-toggle"
					data-toggle="dropdown"> Ricerca un oggetto <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="Ricerca di un oggetto all interno di una regione.jsp">Ricerca un oggetto in una regione</a></li>
						<li><a href="Ricerca di un oggetto di tutte le stelle all'interno di una struttura estesa.jsp">Ricerca di un oggetto di tutte le stelle all'interno di una struttura estesa</a></li>
					</ul></li>
		<li class="dropdown-inverse"><a class="dropdown-toggle"
					data-toggle="dropdown"> Filamento <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="Recupero informazioni derivate di un filamento.jsp">Recupero informazioni derivate di un filamento</a></li>
						<li><a href="Ricerca struttura per contrasto ed ellitticità.jsp">Ricerca struttura per contrasto ed ellitticità</a></li>
						<li><a href="Ricerca struttura per numero segmenti.jsp">Ricerca struttura per numero di segmenti</a></li>
						<li><a href="Distanza di un vertice di un segmento dal contorno.jsp">Distanza di un vertice di un segmento dal contorno</a></li>
					</ul></li>
		<li class="dropdown-inverse"><a class="dropdown-toggle"
					data-toggle="dropdown"> Stella <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="Frazione di stelle in formazione nei filamenti all’interno di una regione.jsp">Frazione di stelle in formazione nei filamenti all’interno di una regione</a></li>
						<li><a href="Posizione di una stella rispetto alla spina dorsale-distanza.jsp">Posizione di una stella rispetto alla spina dorsale-distanza</a></li>
						<li><a href="Posizione di una stella rispetto alla spina dorsale-flusso.jsp">Posizione di una stella rispetto alla spina dorsale-flusso</a></li>
					</ul></li>
     	
     	 
     	 <li><a href="Homepage.jsp">Logout</a></li>
     	 
    	</ul>
  	</div>
  	</nav>
<%}%>

<div class="jumbotron">
<form action="Ricerca di un oggetto di tutte le stelle all'interno di una struttura estesa.jsp" method="POST">

<h2>Ricerca di un oggetto di tutte le stelle all'interno di una struttura estesa:</h2>
	<br><br>


	<div class="input-group mb-3">
  	 <div class="input-group-prepend">
   	 <span class="input-group-text" id="inputGroup-sizing-default">ID</span>
  	</div>
  <input type="text" class="form-control" placeholder="Inserire l'ID " name="idInput" id="idInput">
</div>
<br>

<div>
		<button class="btn btn-default btn-lg" name="Search" id="Search">Cerca</button></div> 

</form>

<%!
	public ArrayList<Stella> allStars;
	public ArrayList<Double> Percentuali;

%>

<%if(request.getParameter("Search")!=null)
{	
	try{		
				//stellabean.getAllStars2();
				stellabean.setid_filamento(Integer.parseInt(request.getParameter("idInput")));	
				Percentuali = stellabean.PercentualeStelleFilamento(allStars);
				if(Percentuali.get(3) != null){%> 
				
				
				<br>
				<table class="table table-striped">
		
					        <thead>
					            <tr>
					                <th>#</th>
					                <th>Numero Stelle</th>
					                <th> PROTOSTELLAR </th>
					                <th> PRESTELLAR</th>
					                <th> UNBOUND </th>
					                
					            </tr>
					        </thead>
					        
					        
					        <tbody>
					        					   	
					          
					           <% 
												%>
								            <tr>
								                <td><%out.print(1);%></td>
								                <td><%out.print(Percentuali.get(3) );%> </td>
								                <td> <%out.print(Percentuali.get(0) + "%"); %></td>
								                <td> <%out.print(Percentuali.get(1) + "%");  %></td>
								                <td> <%out.print(Percentuali.get(2) + "%"); %>
								            </tr>
								   
					     		<%%>
					        </tbody>
					        
					        </table>
				
</div>				
				
			
				
			<%}else{%> 
				<div class="alert alert-warning alert-dismissible" role="alert">
	   			<button type="button" class="close" data-dismiss="warning" aria-label="Close"><span aria-hidden="true"></span></button>
	  			<strong> Attenzione!</strong> Non ci sono stelle all'interno del filamento con l'id inserito! 
	   			</div>
	   		<%}
	
	  
		} catch(NumberFormatException e){
		%>
		   <div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			<strong> Errore</strong> L'id deve essere numerico!
		</div>
		
		
		<%}catch(NullPointerException e){
			%>
			   <div class="alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<strong> Errore</strong> Filamento inserito non presente nel database!
			</div>

	<% }}
		%>
	

</div>
</div>
</body>
</html>