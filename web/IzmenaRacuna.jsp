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
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Izmena racuna</title>
    </head>
    <body>
        <div id="container">
        <h1>Racuni</h1>        
        <div id="izmenaRacuna">
            <form method="get" action="IzmenaRacunaServlet">
                <h2>Unesite izmene u racun</h2>
                <div class="izmenaracun">
                <% 
                    HttpSession ss = request.getSession(false);
                    Racun r = (Racun) ss.getAttribute("r");
                    ArrayList<StavkaRacuna> stavke = r.getStavke();
                %> 
                Broj racuna: <input type="text" name="brRacuna" value="<% out.println(r.getBrRacuna());%>" readonly>
                Naziv firme: <input type="text" name="nazivFirme" value="<% out.println(r.getNazivFirme());%>" readonly>
                Radnik: <input type="text" name="radnik" value="<% out.println(r.getRadnik());%>">
                Datum: <input type="text" name="datum" value="<% out.println(r.getDatum());%>" readonly>
                Cena: <input type="text" name="ukCena" value="<% out.println(r.getUkCena()); %>">
                <br><br><br>
                <input type="submit" name="Akcija" value="Sacuvaj"> 
                <input type="submit" name="Akcija" value="Obrisi Racun">
                <br><br><br>
                </div>
                <div class="izmenastavka">
                <br>
                <h3>Unesite novu stavku:</h3>
                Broj stavke: <input type="text" name="rbStavke">
                Usluga: <input type="text" name="usluga">
                Cena: <input type="text" name="cena">
                <br><br><br> <input type="submit" name="Akcija" value="Unesi stavku">
                    <br><br><br><br>
                </div>
                <br>
                
                <h3>Stavke racuna</h3>
                    <table border="3">
                        <tr>
                            <td>Racun</td>
                            <td>Redni broj</td>
                            <td>Usluga</td>
                            <td>Cena</td>
                            <td>Izaberi</td>
                        </tr>
                        
                        <%
                            StavkaRacuna sr = null;
                            for(int i=0; i<stavke.size(); i++){
                                sr = stavke.get(i);
                        %>
                        
                        <tr>
                            <td><input type="text" name="brRacuna1" value="<% out.println(sr.getBrRacuna()); %>" readonly></td>
                            <td><input type="text" name="rbStavke1" value="<% out.println(sr.getRbStavke()); %>" readonly></td>
                            <td><input type="text" name="usluga1" value="<% out.println(sr.getUsluga()); %>"></td>
                            <td><input type="text" name="cena1" value="<% out.println(sr.getCena()); %>"></td>
                            <td><input type="radio" name="izbor" value="<%=sr.getRbStavke()%>"></td>
                        </tr>
                                           
                        <% } %>
                        
                        
                    </table><br><br> 
                    Unesite novu cenu: <input type="text" name="cena2" ><br>
                    Unesite novu uslugu: <input type="text" name="usluga2"><br><br>
                    <input type="submit" name="Akcija" value="Izmeni stavku">                 
                    <input type="submit" name="Akcija" value="Obrisi stavku">

                </form>
            </div>
        </div>        
    </body>
</html>
