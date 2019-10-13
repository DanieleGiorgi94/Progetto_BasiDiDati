<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@page import="model.*" 
    	import="java.util.*"
    	import="exception.*"%>
    
    <jsp:useBean id="SatBean" class="view.SatelliteBean"/>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<title>Inserire Agenzia</title>
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
						<li><a href="Frazione di stelle in formazione nei filamenti all interno di una regione.jsp">Frazione di stelle in formazione nei filamenti all’interno di una regione</a></li>
						<li><a href="Posizione di una stella rispetto alla spina dorsale-distanza.jsp">Posizione di una stella rispetto alla spina dorsale-distanza</a></li>
						<li><a href="Posizione di una stella rispetto alla spina dorsale-flusso.jsp">Posizione di una stella rispetto alla spina dorsale-flusso</a></li>
					</ul></li>
     	
     	 
     	 <li><a href="Homepage.jsp">Logout</a></li>
     	 
    	</ul>
  	</div>
  	</nav>


<%if(request.getParameter("InsAgenzia") != null){
    	try{
    		SatBean.insertAgenzia(request.getParameter("InsAgenzia"), request.getParameter("ag"));%>
    		<jsp:forward page="Inserire i dati di un satellite.jsp"/>
      <%}catch(AgenziaObbligatoriaException aoe){%>
    	  <div class="alert alert-danger alert-dismissible" role="alert">
			 <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			   <strong> Attenzione!</strong> Inserisci l'agenzia!
			   </div>
    	<%}
    }
    %>

<h1 id="white">Info Satellite</h1>
<ul class="list-group">
		  <li class="list-group-item">
			Nome:
			<%out.print(SatBean.getSatelliteDaVisualizzare(SatBean.getVisualSat()).getName());%> <br>
			Prima Osservazione:
			<%out.print(SatBean.getSatelliteDaVisualizzare(SatBean.getVisualSat()).getFirstObservation());%><br>
			Agenzie:
			<%if(SatBean.getSatelliteDaVisualizzare(SatBean.getVisualSat()).getSpacialAgencies() != null){
			ArrayList<Agenzia> vSat = new ArrayList<Agenzia>(SatBean.getSatelliteDaVisualizzare(SatBean.getVisualSat()).getSpacialAgencies());
			if(vSat.size() != 0){ 
				//Agenzia[] a = SatBean.getSatelliti().get(x).getSpacialAgencies().toArray(new Agenzia[0]);
				for (int i=0; i<vSat.size(); i++){ 
					out.print(vSat.get(i).getName()+", ");
				}
				}
				}else{
				out.print("Non ci sono agenzie");
				}%>
				<br>
			<%
			if((SatBean.getSatelliteDaVisualizzare(SatBean.getVisualSat()).getLastObservation() == null)){%>
				Fine Osservazione:
				<%out.print("");%> <br>
				Durata Missione:
				<%out.print("");%> <br>
			<%}else{%>
				Fine Osservazione:
				<%out.print(SatBean.getSatelliteDaVisualizzare(SatBean.getVisualSat()).getLastObservation());%> <br>
				Durata Missione:
				<%out.print(SatBean.getSatelliteDaVisualizzare(SatBean.getVisualSat()).getDuration());%> <br>
			<%}%>
			Strumenti:
			<%if(SatBean.getSatelliteDaVisualizzare(SatBean.getVisualSat()).getTools() != null){
				ArrayList<Strumento> vStrum = new ArrayList<Strumento>(SatBean.getSatelliteDaVisualizzare(SatBean.getVisualSat()).getTools());
				//Strumento[] a = SatBean.getSatelliti().get(x).getTools().toArray(new Strumento[0]);
				if(vStrum.size() != 0){
					for (int i=0; i<vStrum.size(); i++){ 
						out.print(vStrum.get(i).getToolId()+", ");
					}
				}
				}else{
					out.print("Non ci sono strumenti");
				}%>
			</li>
</ul>
<form action="InsAgenzia.jsp" method="post" name="ag">

		<span class="label label-default" id="white">Agenzia:</span>
		 <select class="form-control" id="ag" name="ag">
 		<% for (int i=0; i<SatBean.getAgenzie().size(); i++){ %>
  			<option value="<%=SatBean.getAgenzie().get(i).getName() %>"><%= SatBean.getAgenzie().get(i).getName() %></option>
   		<%}%>
  	</select>
		<div>
			<button class="btn btn-default btn-sm pull-right" name="InsAgenzia" value="<%=SatBean.getVisualSat()%>">Apporta Modifiche</button><br>
		</div>
</form>
</div>
</body>
<style>
#white{color:#FFFFFF;}
</style>

</html>