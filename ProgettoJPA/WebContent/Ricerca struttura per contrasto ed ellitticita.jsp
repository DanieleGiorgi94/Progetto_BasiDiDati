<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="filamentobean" scope="request"
	class="view.FilamentoBean" />
	<jsp:useBean id="contornobean" scope="request"
	class="view.ContornoBean" />
	<jsp:useBean id="scheletrobean" scope="request"
	class="view.ScheletroBean" />
	<%@ page import="model.Contorno"%>
	<%@ page import="model.Punto_contorno" %>
	<%@ page import="model.Punto_scheletro" %>
	<%@ page import="model.Scheletro" %>
	<%@ page import="model.Filamento"%>
	
<jsp:useBean id="Login" class="view.LoginBean" scope="session"/>

<jsp:setProperty name="filamentobean" property="*"/>

<%@ page import="java.util.*" %>
<%@ page import= "java.io.*" %>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Informazioni Filamento</title>
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
						<li><a href="Ricerca struttura per contrasto ed ellitticita.jsp">Ricerca struttura per contrasto ed ellitticità</a></li>
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
	public List<Filamento> fil ;
	public Long CountAllFilamenti;
%>

<%if(request.getParameter("Search")!=null){
	try{
			filamentobean.setIndex1(Float.parseFloat(request.getParameter("index1Input"))); 
			filamentobean.setIndex2(Float.parseFloat(request.getParameter("index2Input"))); 
			filamentobean.setBrillanza(Float.parseFloat(request.getParameter("pInput")));
			fil = filamentobean.ricercaperellitticitaebrillanza();
			CountAllFilamenti = filamentobean.getCountAllFilamenti();
			filamentobean.setnFilamenti(fil.size());
			
			if ((Float.parseFloat(request.getParameter("pInput")))<0 || (Float.parseFloat(request.getParameter("pInput"))) > 100)
			{%>
			<div class="alert alert-danger alert-dismissible" role="alert">
		   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		   	<strong> Attenzione!</strong> Il valore percentuale deve essere maggiore di 0 e minore di 100!
		   </div>
			<%}
				
	}catch(NumberFormatException e)
	  {%>
		<div class="alert alert-danger alert-dismissible" role="alert">
	   	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	   	<strong> Attenzione!</strong> i valori di ellitticita e brillanza devono essere numerici
	   </div>
			<%}catch(NullPointerException e){
				%>
				   <div class="alert alert-danger alert-dismissible" role="alert">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<strong> Errore</strong> inserire tutti i campi!
				</div>

		<% }}
			%>

<div class="jumbotron">

<h2> Ricerca per ellitticità e brillanza </h2>
<hr>
<form action="Ricerca struttura per contrasto ed ellitticita.jsp" name="myform" method="POST" class="form-inline">

<br>
<strong>Selezionare valori di ellitticita :</strong>
<br><br>
		
<div data-role="main" class="ui-content">
    <form method="post" action="/action_page_post.php">
    
      <div data-role="rangeslider">
        <label for="index1Input">Min:</label>
        <input type="range"   name="index1Input" id="index1Input" value="1" min="1.1" step ="0.1" max="9.9" oninput="myFunction()">
       
       <p id="script1"></p>
		
        <label for="index2Input">Max:</label>
        <input type="range"  name="index2Input" id="index2Input" value="1" min="1.1" step ="0.1" max="9.9" oninput="myFunction()">
        
        
     <p id="script2"></p>
     
   	<script>
		function myFunction() {
    		var x = document.getElementById("index1Input").value;
    		var y = document.getElementById("index2Input").value;
    		document.getElementById("script1").innerHTML = x;
    		document.getElementById("script2").innerHTML = y;
}
	</script> 	
     
        
 	 </div> 
    </form>

  
  <div class="input-group">
  <div class="input-group-prepend">
    <div class="input-group-text">
   
    <br>
    <strong> Brillanza: </strong> <br>
    </div>
  </div>
 <input type="text" class="form-control" placeholder="Inserire percentuale " name="pInput" id="pInput">
</div>
	
  <div>
		<button class="btn btn-default btn-lg" name="Search" id="Search">Cerca</button></div> 
		</div> 
</form>



<form action="Ricerca struttura per contrasto ed ellitticita.jsp" method="POST">

<div class="bs-example">
 	<h1></h1>
 	 	<h1></h1>

        	<%
			 if(filamentobean.getnFilamenti() > 0){ ;
        	
       	
				if(request.getParameter("avanti") != null)
					filamentobean.incC();
					
			
					if(request.getParameter("indietro")!= null)
						filamentobean.decC();

					
					%>	
				       <table class="table table-striped">
						       
						        <thead>
						            <tr>
						                <th>#</th>
						                <th>Id </th>
						                <th>Nome</th>
						            </tr>
						        </thead>
						        <tbody>
						        	<%
						        
				if (fil !=null){
				%>
					        
					       <strong>FRAZIONE FILAMENTI RISPETTO INTERO CATALOGO: <% out.println(filamentobean.getCatalogoFilamenti(fil, CountAllFilamenti) + "%");%></strong><br>		
					          			  
					           <%for (int x=filamentobean.getC()*20; x< Math.min(filamentobean.getC()*20+20,fil.size()); x++){
					        	   %>
						            <tr>
						                <td><%out.print(x+1);%></td>
						                <td><%out.print(fil.get(x).getId());%> </td>
						                <td><%out.print(fil.get(x).getNome());%></td> 
						            </tr>
						     			<%}
						     			%>
						        </tbody>
						        		
						        <%} %>	
						    </table>
					</div>
							 	<%if(filamentobean.getC()==0 ){
						 	%>

							<button class = "btn-link" name="avanti" id="avanti" value="avanti"onclick="incC()" >avanti</button>
							<%} %>
							
							 <%if(filamentobean.getC()*20+20>filamentobean.getnFilamenti() && filamentobean.getC()>0 ){

						 	%>
							<button class="btn btn-link" name="indietro" id="indietro">indietro</button>
							<%}
							 if(filamentobean.getC()>0 && !(filamentobean.getC()*20+20>filamentobean.getnFilamenti() && filamentobean.getC()>0)){%>
							 <button class="btn btn-link" name="indietro" id="indietro" onclick="decC()">indietro</button>
							 <button class="btn btn-link" name="avanti" id="avanti" onclick = "incC()">avanti</button>
							<%}
							 }
							
							%>
				</form>		
						
				</div>
				</div>
				</div>
				</body>
				</html>