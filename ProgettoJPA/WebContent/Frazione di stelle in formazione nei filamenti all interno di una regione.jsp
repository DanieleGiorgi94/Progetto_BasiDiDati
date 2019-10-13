<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="filamentobean" class="view.FilamentoBean" scope="request"/>
<jsp:useBean id="contornobean" class="view.ContornoBean" scope="request"/>
<jsp:useBean id="stellabean" class="view.StellaBean" scope="request"/>
<jsp:useBean id="Login" class="view.LoginBean" scope="request"/>
<jsp:setProperty name="filamentobean" property="*"/>
<jsp:setProperty name="contornobean" property="*"/>
<jsp:setProperty name="stellabean" property="*"/>

<%@ page import="java.util.*" %>
<%@ page import= "java.io.*" %>
<%@ page import="model.Stella"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informazioni Stella</title>
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
						<li><a href="Frazione di stelle in formazione nei filamenti all interno di una regione.jsp">Frazione di stelle in formazione nei filamenti all’interno di una regione</a></li>
						<li><a href="Posizione di una stella rispetto alla spina dorsale-distanza.jsp">Posizione di una stella rispetto alla spina dorsale-distanza</a></li>
						<li><a href="Posizione di una stella rispetto alla spina dorsale-flusso.jsp">Posizione di una stella rispetto alla spina dorsale-flusso</a></li>
					</ul></li>
     	
     	 
     	 <li><a href="Homepage.jsp">Logout</a></li>
     	 
    	</ul>
  	</div>
  	</nav>
<%}%>
	<div class="jumbotron">

	<form action name="Frazione di stelle in formazione nei filamenti all interno di una regione" method="POST">
	<!-- <div>
		<label class="radio-inline"><input type="radio" name="optradio" id="optradio" value="F">Filamento</label></div> -->
		
		<!-- <label class="radio-inline"><input type="radio" name="optradio2" id="optradio2" value="Rectangle"  >Rettangolo</label></div>	 -->
    
	<div class="input-group input-group-sm" >
				<span class="input-group-addon"  >Base</span> 
				<input type="text" id="val1" name="val1" class="form-control" placeholder="Base">
	</div>
	<div class="input-group input-group-sm" >
				<span class="input-group-addon"  >Altezza</span> 
				<input type="text" id="val2" name="val2" class="form-control" placeholder="Altezza">
	</div>
	<div class="input-group input-group-sm">
			<span class="input-group-addon"  >Latitudine</span> 
			<input type="text" id="latitude" name="latitude" class="form-control" placeholder="Latititudine">
	</div>
	
	<div class="input-group input-group-sm">
			<span class="input-group-addon"  >Longitudine</span> 
			<input type="text" id="longitude" name="longitude" class="form-control" placeholder="Longitudine">
	</div>
	
	
	<div align="center">
		<button class="btn btn-default btn-lg pull-right" name="Search" id="Search">Cerca</button><br><br></div> 
</form>

<%!
	public ArrayList<Stella> StarsRectFil;
	public ArrayList<Double> Percentuali;
	public List<Stella> StarsRect;

%>

<% if(request.getParameter("Search")!=null)
   {
			try{

								if ((Float.parseFloat(request.getParameter("val1"))) < 0 || (Float.parseFloat(request.getParameter("val2"))) <0)
								{%>
									<div class="alert alert-danger alert-dismissible" role="alert">
								   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								   	<strong> Attenzione!</strong> base e altezza  devono essere positivi!
								   </div>
								<% }else{
										if (stellabean.getAllStarsRectangle(Float.parseFloat(request.getParameter("val1")),Float.parseFloat(request.getParameter("val2")),Float.parseFloat(request.getParameter("latitude")),Float.parseFloat(request.getParameter("longitude")))  != null &&
											(stellabean.getObjRectangle(Float.parseFloat(request.getParameter("val1")),Float.parseFloat(request.getParameter("val2")),Float.parseFloat(request.getParameter("latitude")),Float.parseFloat(request.getParameter("longitude")))!= null))
											{
											StarsRectFil = stellabean.getStelleFilRect();
											StarsRect = stellabean.getListRectangle();
											Percentuali = stellabean.PercentualeStelleRectangleFilamento(StarsRect, StarsRectFil);
											
											if(StarsRectFil.size() == 0 && StarsRect.size() == 0)
											{
												
												
												%>
												<div class="alert alert-danger alert-dismissible" role="alert">
											   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
											   	<strong> Attenzione!</strong> La ricerca non ha prodotto risultati
											   </div>
											<%}
											
											%>	
											<strong>STELLE TOTALI ALL'INTERNO DI UN FILAMENTO: <% out.println(StarsRectFil.size());%></strong><br>		
											<strong>PERCENTUALE STELLE NEL FILAMENTO: <% out.println(Percentuali.get(0)+"%");%></strong><br>		
											<strong>PROTOSTELLAR: <% out.println(Percentuali.get(1)+"%");%></strong><br>						
											<strong>PRESTELLAR: <% out.println(Percentuali.get(2)+"%");%></strong><br>		
											<strong>UNBOUND : <% out.println(Percentuali.get(3)+"%");%></strong><br>		
											<strong>PERCENTUALE STELLE NON NEL FILAMENTO: <% out.println(Percentuali.get(4)+"%");%></strong><br>		
											
											
											
											<%-- <strong>PERCENTUALE STELLE NON NEL FILAMENTO: <% out.println(stellabean.PercentualeStelleRectangleNotFilamento(Float.parseFloat(request.getParameter("val1")),Float.parseFloat(request.getParameter("val2")),Float.parseFloat(request.getParameter("latitude")),Float.parseFloat(request.getParameter("longitude"))));%></strong></div>
											<strong>% UNBOUND: <% out.println(stellabean.PercentualeNotRectangleUNBOUND(Float.parseFloat(request.getParameter("val1")),Float.parseFloat(request.getParameter("val2")),Float.parseFloat(request.getParameter("latitude")),Float.parseFloat(request.getParameter("longitude"))));%></strong><br>
											<strong>% PRESTELLAR: <% out.println(stellabean.PercentualeNotRectanglePRESTELLAR(Float.parseFloat(request.getParameter("val1")),Float.parseFloat(request.getParameter("val2")),Float.parseFloat(request.getParameter("latitude")),Float.parseFloat(request.getParameter("longitude"))));%></strong><br>
											<strong>% PROTOSTELLAR: <% out.println(stellabean.PercentualeNotRectanglePROTOSTELLAR(Float.parseFloat(request.getParameter("val1")),Float.parseFloat(request.getParameter("val2")),Float.parseFloat(request.getParameter("latitude")),Float.parseFloat(request.getParameter("longitude"))));%></strong><br>
									 --%>
								
											
								        	<%-- <table class="table table-striped">
								       
								        	
									
								
								        <thead>
								            <tr>
								                <th>#</th>
								                <th>Id </th>
								                <th>Nome</th>
								            </tr>
								        </thead>
								        <tbody>
								        	<%
											
											for(int x=0;x<StelleRect.size();x++){
												%>
								            <tr>
								                <td><%out.print(x+1);%></td>
								                <td><%out.print(StelleRect.get(x).getStellaId());%> </td>
								                <td><%out.print(StelleRect.get(x).getNome());%></td>
								                <td><%out.print(stellabean.getVecRectangle().get(x).getStellaId());%> </td>
								                <td><%out.print(stellabean.getVecRectangle().get(x).getNome());%></td>
								            </tr>
								     			<%}%>
								        </tbody>
								        		
								        	
								    </table>
									</div> --%>
										<% }
										else
										{
											%>
											<div class="alert alert-danger alert-dismissible" role="alert">
										   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										   	<strong> Attenzione!</strong> Non ci sono stelle  nella regione selezionata!
										   </div>
										<%
										}
								}
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
						<strong> Errore</strong> Non ci sono stelle all'interno del filamento!
					</div>

			<% }}
				%>
			
</div>
</body>
</html>


