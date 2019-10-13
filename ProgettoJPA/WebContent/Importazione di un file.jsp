<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="Login" class="view.LoginBean" scope="session"/>


<%if(request.getAttribute("fileup") != null){
	if(request.getAttribute("fileup").equals("TRUE")){%>
		<div class="alert alert-success alert-dismissible" role="alert">
	   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	   	<strong> OK!</strong> File caricato correttamente!
	   </div>
	<%}else{%>
		<div class="alert alert-danger alert-dismissible" role="alert">
	   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	   	<strong> Attenzione!</strong> Il caricamento del file non è andato a buon fine!
	   </div>
	<%}
	}%>



<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<title>Importazione di un file</title>
</head>
<body background = "img2.jpg">

	<div class="container">
	
	<%if(Login.isAdmin()){ %>
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

<div class="container">
<div class="jumbotron">	
<form method="post" action="UploadServlet" enctype="multipart/form-data">




		<h2>Scegli il tipo di file:</h2>
	
	
	<label class="radio-inline"><input type="radio" name="optradio" id="optradio" value="1">contorni_filamenti_Herschel.csv</label>
		<label class="radio-inline"><input type="radio" name="optradio" id="optradio" value="2">contorni_filamenti_Spitzer.csv</label>
		<label class="radio-inline"><input type="radio" name="optradio" id="optradio" value="3">filamenti_Herschel.csv</label>
		<label class="radio-inline"><input type="radio" name="optradio" id="optradio" value="4">filamenti_Spitzer.csv</label>
		<label class="radio-inline"><input type="radio" name="optradio" id="optradio" value="5">scheletro_filamenti_Herschel.csv</label>
		<label class="radio-inline"><input type="radio" name="optradio" id="optradio" value="6">scheletro_filamenti_Spitzer.csv</label>
		<label class="radio-inline"><input type="radio" name="optradio" id="optradio" value="7">stelle_Herschel.csv</label>
		<hr>
		
		<input type="file" name="dataFile" id="dataFile"/><br/><br/>
		<!-- <input type="text" name="description" /> -->
		<input type="submit"  value="Upload" />
</form>
</div>

</div>

</div>
</body>
</html>