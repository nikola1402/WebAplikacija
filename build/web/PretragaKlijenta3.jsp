<%-- 
    Document   : PretragaKlijenta3
    Created on : Sep 16, 2016, 7:46:07 PM
    Author     : nikol
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.klijenti.Klijent"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pretraga klijenta 3</title>
                <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    
    <body>
        <h1>Pretraga klijenta 3</h1>
        <div id="container">
            <div id="izborNaslovna">

                <form action="PretragaKlijentaServlet" method="post">
            <select name="nazivFirme" id="nazivFirme">   
                    
               <%-- start web service invocation --%><hr/>
                <%
                    
                try {
                    Klijent kl = new Klijent();
                com.klijenti.PretragaKlijentaServisService service = new com.klijenti.PretragaKlijentaServisService();
                com.klijenti.PretragaKlijentaServis port = service.getPretragaKlijentaServisPort();
                // TODO initialize WS operation arguments here
                java.lang.String arg0 = "";
                // TODO process result here
                java.util.List<com.klijenti.Klijent> result = port.vratiKlijente(arg0);
                
                
                String nazivFirme = null;
                for (int i=0; i<result.size(); i++){
                        kl = result.get(i);
                        nazivFirme = kl.getNazivFirme();
                
                %>
                    <option value="<%=nazivFirme%>"><%=nazivFirme%></option>
                <% } 
        
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
                %>
            <%-- end web service invocation --%><hr/>     
                    
            </select>
            <input type="submit" value="Trazi">
        </form>
            </div>
        </div>
    </body>
</html>
