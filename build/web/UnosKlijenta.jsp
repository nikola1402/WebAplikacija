<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Unos klijenta</title>
    </head>
    <body>
        <div id="container">
        <% String poruka = (String) request.getAttribute("poruka");
        if(poruka != null) { %>
        <%=poruka%>
        <% } %>
        
        <h1>Unesite podatke za novog klijenta:</h1>
        <div id="unosKlijenta">
            <div class="unosKlijenta">
        <form method="post" action="KlijentUnosServlet">
            Naziv firme:<input type="text" name="nazivFirme"><br><br>
            PIB:<input type="text" name="PIB"><br><br>
            Telefon:<input type="text" name="telefon"><br><br>
            <input type="submit" value="Sacuvaj">
        </form>
        </div>
        </div>
        </div>
    </body>
</html>
