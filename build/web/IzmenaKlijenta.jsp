<!DOCTYPE html>
<%@page import="java.sql.Array"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Racun"%>
<%@page import="model.Klijent"%>
<%@page import="util.DBBroker"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Izmena klijenta</title>
    </head>
    <body>
        <div id="container">
        <h1>
        KLIJENTI
        </h1>
                
           <div id="izmenaKlijenta">
<div class="izmenaKlijenta">
            <form method="get" action="IzmenaKlijentaServlet">
                
                <% 
                    Klijent kl = (Klijent) request.getAttribute("kl");
                    ArrayList<Racun> rl = (ArrayList) request.getAttribute("racuni");
                %>
                Klijent ID:<input type="text" name="klijentID" value="<% out.println(kl.getKlijentID());%>" readonly>
                Naziv firme: <input type="text" name="nazivFirme" value="<% out.println(kl.getNazivFirme());%>">
                PIB: <input type="text" name="PIB" value="<% out.println(kl.getPIB());%>">
                Telefon <input type="text" name="telefon" value="<% out.println(kl.getTelefon());%>"><br><br>
                
                <input type="submit" name="Akcija" value="Izmeni klijenta">
                <input type="submit" name="Akcija" value="Obrisi klijenta"> 
                <input type="submit" name="Akcija" value="Racuni">
            </form>
                <br><br>
</div>
            </div>            
        </div>               
    </body>
</html>
