<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Unos racuna</title>
    </head>
    <body>
        <%
            HttpSession ss = request.getSession(false);
            String nazivFirme = (String) ss.getAttribute("nazivFirme");
            Integer brRacuna = (Integer) ss.getAttribute("brRacuna");
            String radnik = (String) ss.getAttribute("radnik");  
            String poruka = (String) request.getAttribute("poruka");
        if(poruka != null) { %>
        <%=poruka%>
        <% } %>
        <div id="container">
        <h1>Unesite podatke o racunu:</h1>
            
 
        <div id="unosRacuna">
        <form method="post" action="UnosRacunaServlet"> 
            <div class="racun">
            Naziv firme: <input type="text" name="nazivFirme" value="<% if(nazivFirme!=null){out.println(nazivFirme);} else{out.println("");};%>"><br><br>
            Broj racuna: <input type="text" name="brRacuna" value="<% if(brRacuna!=null){out.println(brRacuna);} else{out.println("");};%>"><br><br>
            Radnik: <input type="text" name="radnik" value="<% if(radnik!=null){out.println(radnik);} else{out.println("");};%>"><br><br><br><br>
            <input type="submit" name="Akcija" value="SacuvajRacun"><br><br><br><br>
            </div>
            <div class="stavka">
            Stavka racuna: <input type="text" name="stavkaR"><br><br>
            Usluga: <input type="text" name="usluga"><br><br>
            Cena: <input type="text" name="cena"><br><br>
            <input type="submit" name="Akcija" value="SacuvajStavku">
            </div>
        </form>
        </div>
        </div>
    </body>
</html>
