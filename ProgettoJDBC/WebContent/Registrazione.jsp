
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@page import="exception.MinLengthException"
import="exception.*"%>
<%@ page import="java.lang.ClassNotFoundException" %>

<jsp:useBean id="RegistrazioneBean" scope="request" class="view.RegistrazioneBean" />
<jsp:setProperty property="*" name="RegistrazioneBean" />



<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>

<title>Registrazione</title>

</head>

<body background = "img2.jpg">

<div class="container">
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
						<li><a href="Frazione di stelle in formazione nei filamenti all interno di una regione.jsp">Frazione di stelle in formazione nei filamenti all'interno di una regione</a></li>
						<li><a href="Posizione di una stella rispetto alla spina dorsale-distanza.jsp">Posizione di una stella rispetto alla spina dorsale-distanza</a></li>
						<li><a href="Posizione di una stella rispetto alla spina dorsale-flusso.jsp">Posizione di una stella rispetto alla spina dorsale-flusso</a></li>
					</ul></li>
     	
     	 
     	 <li><a href="Homepage.jsp">Logout</a></li>
     	 
    	</ul>
  	</div>
  	</nav>
</div>


	<%
		if (request.getParameter("bottone") != null) {
			try{
				RegistrazioneBean.registrautente();%>
				<jsp:forward page="HomePageAdmin.jsp"/>
			<%} catch(MinLengthException m){%>
				<div class="alert alert-danger alert-dismissible" role="alert">
			   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			   	<strong> Attenzione!</strong> La lunghezza minima di userid e password � 6!
			   </div>
			<%}
			catch(DatiErratiException de){%>
			<div class="alert alert-danger alert-dismissible" role="alert">
		   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		   	<strong> Attenzione!</strong> Inserire tutti i dati!
		   </div>
		<%}catch(UtentePresenteException up){%>
		<div class="alert alert-danger alert-dismissible" role="alert">
	   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	   	<strong> Attenzione!</strong> L'utente � gia registrato nel sistema
	   </div>
	<%}


			
			

		}
	%>





	<div class="container">
	<div class="jumbotron">
		<h2>Registrazione al sito</h2><br>
		<form action="Registrazione.jsp" name="myform" method="POST">

			<div class="form-group">
				<label for=nome> Nome*</label><input type="text"
					class="form-control" id="name" name="name" placeholder="Nome" /> <label
					for=cognome> Cognome</label><input type="text" class="form-control"
					id="surname" placeholder="surname" name="surname" /> <label
					for=email> Email*</label><input type="text" class="form-control"
					id="email" placeholder="mia_email@esempio.com" name="email" /> <label
					for=password> Password*</label><input type="password"
					class="form-control" id="password" name="password"
					placeholder="scegli una password" />

				<div class="form-group ">
					<label for=userid> User-id</label> <input type="text"
						class="form-control" id="userid" name="userid"
						placeholder="UserId" />
				</div>

			</div>
			<input type="submit" class="btn btn-alarm " name="bottone"
				value="Registrati" id="bottone">
		</form>
		</div>
	</div>
</body>
</html>