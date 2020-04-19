<!DOCTYPE html>
<%@page import="model.StavkaRacuna"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Racun"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Izmena stavke</title>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>
        <div id="container">
        <h1>Stavke</h1>
            <div id="izmenaStavke">
            <form method="get" action="IzmenaRacunaServlet">
                <h2>Unesite izmene u stavku</h2>
                <% 
                    Racun r = (Racun) request.getAttribute("r");
                    Integer rbStavke = (Integer) request.getAttribute("rbStavke");
                    ArrayList<StavkaRacuna> stavke = r.getStavke();
                    StavkaRacuna sr = stavke.get(rbStavke-1);
                    /*
                    
                    for (int i=0; i<stavke.size(); i++) {
                        StavkaRacuna sr = stavke.get(i);
                    }*/
                %> 
                Broj racuna: <input type="text" name="brRacuna" value="<% out.println(sr.getBrRacuna());%>" readonly>
                
                Radnik: <input type="text" name="radnik" value="<% out.println(r.getRadnik());%>" readonly>br><br>
                Broj stavke: <input type="text" name="izbor" value="<% out.println(sr.getRbStavke());%>">
                Usluga <input type="text" name="usluga2" value="<% out.println(sr.getUsluga());%>">
                Cena <input type="text" name="cena2" value="<% out.println(sr.getCena());%>">
                <br><br>
                <input type="submit" name="Akcija" value="Izmeni stavku"> 
                <input type="submit" name="Akcija" value="Obrisi stavku">
            </form>
            </div>    
        </div>
    </body>
</html>
