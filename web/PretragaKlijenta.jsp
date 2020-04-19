<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Klijent"%>
<%@page import="util.DBBroker"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title>Pretraga klijenta</title>
    </head>
    <body>
        <div id="container">
        <h1>KLIJENTI</h1>
            <% String poruka = (String) request.getAttribute("poruka");
               if(poruka != null) { %>
               <h3><font color="red"> <%=poruka%> </font></h3>
               <br>
            <% } %>
            <div id="izborNaslovna">
            <form action="PretragaKlijentaServlet" method="post">
                <select name="nazivFirme" id="nazivFirme">   
                <% 
                    Klijent kl = new Klijent();
                    ArrayList<Klijent> klijenti= (ArrayList) request.getAttribute("klijenti");
                    String nazivFirme = null;
                    for (int i=0; i<klijenti.size(); i++){
                        kl = klijenti.get(i);
                        nazivFirme = kl.getNazivFirme();
                    %>
                    
                    <option value="<%=nazivFirme%>"><%=nazivFirme%></option>
                        <% } %>
            </select>
            <input type="submit" value="Trazi">
             </form>
        </div>
    </body>
</html>
