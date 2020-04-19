<!DOCTYPE html>
<%@page import="model.Racun"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Klijent"%>
<%@page import="util.DBBroker"%>
<%@ page import = "java.sql.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Pretraga racuna</title>
    </head>
    <body>
        <div id="containers">
        <h1>Racuni</h1>   
        <div id="listaRacuna">
            <div class="listaRacuna">
            <form method="post" action="PretragaRacunaServlet">
                    <table border="3">
                        <tr>
                            <td>Broj racuna</td>
                            <td>Firma</td>
                            <td>Datum</td>
                            <td>Radnik</td>
                            <td>Cena</td>
                            <td>Izaberi</td>
                        </tr>
                        <%
                            ArrayList<Racun> rl = (ArrayList) request.getAttribute("racuni");
                            
                            Racun rac = null;
                 
                            for (int i=0; i<rl.size(); i++){
                                rac = rl.get(i);
                            
                            %>
                        <tr>
                            
                            <td><input type="text" name="brRacuna" value="<% out.println(rac.getBrRacuna()); %>" readonly></td>
                            <td><input type="text" name="nazivFirme" value="<% out.println(rac.getNazivFirme()); %>" readonly></td>
                            <td><input type="text" name="datum" value="<% out.println(rac.getDatum()); %>" readonly></td>
                            <td><input type="text" name="radnik" value="<% out.println(rac.getRadnik()); %>" readonly></td>
                            <td><input type="text" name="cena" value="<% out.println(rac.getUkCena()); %>" readonly></td>
                            <td><input type="radio" align="center" name="izbor" value="<%=rac.getBrRacuna()%>"></td>

                            
                        </tr>
                        <%}%>
                        
                    </table><br><br> 
                        <input type="submit" name="Akcija" value="Izmeni racun">
                </form>  
            </div>
        </div>            
        </div>
    </body>
</html>
