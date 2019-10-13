<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	
	
	<%@ page import="model.Contorno"%>
	<%@ page import="model.Stella"%>
	<%@ page import="model.TipiStella"%>
	<%@ page import="model.Scheletro"%>
	
	
<jsp:useBean id="scheletrobean" scope="request"
class="view.ScheletroBean" />

<jsp:useBean id="stellabean" scope="request"
	class="view.StellaBean" />
	

	
<jsp:useBean id="Login" class="view.LoginBean" scope="session"/>

<jsp:setProperty name="stellabean" property="*"/>
<jsp:setProperty name="scheletrobean" property="*"/>

<%@ page import="java.util.*" %>
<%@ page import= "java.io.*" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Posizione stella rispetto spina dorsale</title>
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

<%!
	public  ArrayList<Stella> allStars ;
%>

		<%
			if (request.getParameter("Search") != null) {
				try {

					scheletrobean.setFilamento_id(Integer.parseInt(request.getParameter("idInput")));
					stellabean.setid_filamento(Integer.parseInt(request.getParameter("idInput")));
					allStars = scheletrobean.DistanzaSpina(allStars);
				  	allStars = stellabean.DistanzaSpinaOrderedBYDistanza(allStars);
					stellabean.setnStelle(allStars.size());

				} catch (NumberFormatException e) {
		%>
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong> Errore</strong> L'id deve essere numerico!
		</div>


		<%
			} catch (NullPointerException e) {
		%>
		<div class="alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong> Errore</strong> Filamento inserito non presente o non di
			tipo Hershel!
		</div>

		<% }}
		%>
		
<div class = "container">
<div class="jumbotron">

<h2>Posizione di una stella rispetto alla spina dorsale-distanza:</h2>
	<br><br>
	<hr>
<form action="Posizione di una stella rispetto alla spina dorsale-distanza.jsp" name="myform" method="POST" class="form-inline">
<br>
<strong>Ordina in base alla distanza:</strong>
	<br><br>
	
	 <div class="input-group">
  <div class="input-group-prepend">
    <div class="input-group-text">
    
    <br>
     ID FILAMENTO:
    </div>
  </div>
 <input type="text" class="form-control" placeholder="Inserire l'ID Filamento " name="idInput" id="idInput">
</div>
<br>	
<br>
 <div>
		<button class="btn btn-default btn-lg" name="Search" id="Search">Cerca</button></div> 
		
		
</form>
				
<form action="Posizione di una stella rispetto alla spina dorsale-distanza.jsp" method="POST">			
				
				<div class="bs-example">
			 	<h1></h1>
			 	 	<h1></h1>					
				
				<% if (stellabean.getnStelle() > 0){
		 	 	
		 	 
					if(request.getParameter("avanti") != null)
						stellabean.incC();
				
	
					if(request.getParameter("indietro")!= null)
						stellabean.decC();		
				%>				
							

					    <table class="table table-striped">
		
					        <thead>
					            <tr>
					                <th>#</th>
					                <th>Id </th>
					                <th>DistanzaSpina</th>
					                <th>FLusso</th>
					            </tr>
					        </thead>
					        <tbody>
					        	<%
					        	 if (allStars.size() > 0)
					 			{
					        
					    
					        	for(int x=stellabean.getC()*20; x< Math.min(stellabean.getC()*20+20, stellabean.getnStelle()); x++){
									%>
					            <tr>
					                <td><%out.print(x+1);%></td>
					                <td><%out.print(allStars.get(x).getStellaId());%> </td>
					                <td><%out.print(allStars.get(x).getDistanza());%></td>
					                <td><%out.print(allStars.get(x).getFlusso()); %>
					            </tr>
					     			<%}

					     			%>
					        </tbody>		        		
					        		
					   	
					    </table>
								
</div>							
							<%if(stellabean.getC()==0 ){
							 	%>

								<button class = "btn btn-link" name="avanti" id="avanti" value="avanti" onclick="incC()">avanti</button>
								<%} %>
								
								 <%if(stellabean.getC()*20+20>stellabean.getnStelle() && stellabean.getC()>0 ){

							 	%>
								<button class="btn btn-link" name="indietro" id="indietro">indietro</button>
								<%}
								 if(stellabean.getC()>0 && !(stellabean.getC()*20+20>stellabean.getnStelle() && stellabean.getC()>0)){%>
								 <button class="btn btn-link" name="indietro" id="indietro" onclick="decC()">indietro</button>
			 					 <button class="btn btn-link" name="avanti" id="avanti" onclick = "incC()">avanti</button>
								<%}
				}
			
			else{%> 
			<div class="alert alert-warning alert-dismissible" role="alert">
   			<button type="button" class="close" data-dismiss="warning" aria-label="Close"><span aria-hidden="true"></span></button>
  			<strong> Attenzione!</strong> Non ci sono stelle con l'id selezionato.
   			</div>
   			<%}
   			}%>
 
</form>
</div>
</div>
</div>
</body>
</html>