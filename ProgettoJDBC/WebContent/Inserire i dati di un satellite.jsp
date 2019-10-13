<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page 
    	import="model.*" 
    	import="exception.*"
    	import="java.util.*" 
    	%>
    
    <jsp:useBean id="Login" class="view.LoginBean" scope="session"/>
    <jsp:useBean id="SatelliteBean" class="view.SatelliteBean" scope="request"/>
    <jsp:setProperty property="*" name="SatelliteBean"/>
    
    <%
    	if(request.getParameter("FineObs") != null){
    		SatelliteBean.setVisualSat(request.getParameter("FineObs"));%>
   			<jsp:forward page="FineObs.jsp"/>
    	<%}
    	if(request.getParameter("Agency") != null){
    		SatelliteBean.setVisualSat(request.getParameter("Agency"));%>
   			<jsp:forward page="InsAgenzia.jsp"/>
    	<%}
    	%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/bootstrap-theme.min.css" rel="stylesheet" media="screen">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.js"></script>
<title>Inserisci dati Satellite</title>
</head>
<body background = "img2.jpg">
<div class="container" class="input-group">

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
<%} %>

<%
    	if(request.getParameter("Inserisci") != null){
    		try{
    			SatelliteBean.insertSatellite();
    		} catch(InfoSatelliteException ise){%>
    			<div class="alert alert-danger alert-dismissible" role="alert">
			   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			   	<strong> Attenzione!</strong> Devi prima inserire il nome e la data di prima operazione del satellite!
			   </div>
    		<%}catch(SatelliteEsistenteException spe){%>
    			<div class="alert alert-danger alert-dismissible" role="alert">
			   		<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			   		<strong> Attenzione!</strong> Il satellite che stai tentando di inserire è già presente!
			   </div>
    		<%}
    		
    	}%>

<form action="Inserire i dati di un satellite.jsp" name="form" method="post">
<%if(SatelliteBean.getSatelliti() != null){ %>
<ul class="list-group">
	<%for (int x= 0;x < SatelliteBean.getSatelliti().size(); x++){ %>
		  <li class="list-group-item">
			Nome:
			<%out.print(SatelliteBean.getSatelliti().get(x).getName());%> <br>
			Prima Osservazione:
			<%out.print(SatelliteBean.getSatelliti().get(x).getFirstObservation());%><br>
			Agenzie:
			<%
			if(SatelliteBean.getSatelliti().get(x).getSpacialAgencies() != null){
			ArrayList<Agenzia> vSat = new ArrayList<Agenzia>(SatelliteBean.getSatelliti().get(x).getSpacialAgencies());
			if(vSat.size() != 0){ 
				//Agenzia[] a = SatBean.getSatelliti().get(x).getSpacialAgencies().toArray(new Agenzia[0]);
				for (int i=0; i<vSat.size(); i++){ 
					out.print(vSat.get(i).getName());
				}
				}
			}else{
				out.print("Non ci sono agenzie");%>
				<%}%>
			<%
			if(!(SatelliteBean.getSatelliti().get(x).getLastObservation() == null)){%>
				<br>
				Fine Osservazione:
				<%out.print(SatelliteBean.getSatelliti().get(x).getLastObservation());%> <br>
				Durata Missione:
				<%out.print(SatelliteBean.getSatelliti().get(x).getDuration()+" giorni");%>
			<%}else{%>
				<button class="btn btn-default btn-sm pull-right" name="FineObs" value="<%=SatelliteBean.getSatelliti().get(x).getName()%>">Aggiungi fine osservazione</button>
			<%}%>
				<button class="btn btn-default btn-sm pull-right" name="Agency" value="<%=SatelliteBean.getSatelliti().get(x).getName()%>">Aggiungi agenzia</button><br>
			Strumenti:
			<%if(SatelliteBean.getSatelliti().get(x).getTools() != null){
				ArrayList<Strumento> vStrum = new ArrayList<Strumento>(SatelliteBean.getSatelliti().get(x).getTools());
				//Strumento[] a = SatBean.getSatelliti().get(x).getTools().toArray(new Strumento[0]);
				if(vStrum.size() != 0){
					for (int i=0; i<vStrum.size(); i++){
						if(i != vStrum.size()-1)
							out.print(vStrum.get(i).getToolId()+",");
						else
							out.print(vStrum.get(i).getToolId());
					}
				}
				}else{
					out.print("Non ci sono strumenti");
				}%>
			</li>
	<%}
	}%>	
</ul>
		<br>
			<div>
				<span class="label label-default" id="label">Nome Satellite</span>
  				<input type="text" class="form-control" placeholder="Inserisci qui il nome" name="nomeSat" id="nomeSat">
			</div>
			<br>
			<div>
  				<span class="label label-default" id="label">Prima Operazione</span>
    			<input class="form-control" id="primaOp" name="primaOp" type="date">
			</div>
			<br>
			<div>
				<span class="label label-default" id="label">Agenzia</span>
  				<input type="text" class="form-control" placeholder="Inserisci qui l'agenzia" name="agenzia" id="agenzia">
			</div>
			<br>
			<div>
				<button name="Inserisci" class="btn btn-default btn-md pull-right">Inserisci Satellite</button>
			</div>
		</form>
</div>



</body>

</html>