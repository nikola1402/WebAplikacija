package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.StavkaRacuna;
import java.util.ArrayList;
import model.Racun;

public final class IzmenaRacuna_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\n");
      out.write("        <title>Izmena racuna</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"container\">\n");
      out.write("      \n");
      out.write("        <div id=\"izmenaRacuna\">\n");
      out.write("            <form method=\"get\" action=\"IzmenaRacunaServlet\">\n");
      out.write("                <h2>Unesite izmene u racun</h2>\n");
      out.write("                <div class=\"izmenaracun\">\n");
      out.write("                ");
 
                    HttpSession ss = request.getSession(false);
                    Racun r = (Racun) ss.getAttribute("r");
                    ArrayList<StavkaRacuna> stavke = r.getStavke();
                
      out.write(" \n");
      out.write("                Broj racuna: <input type=\"text\" name=\"brRacuna\" value=\"");
 out.println(r.getBrRacuna());
      out.write("\" readonly>\n");
      out.write("                Naziv firme: <input type=\"text\" name=\"nazivFirme\" value=\"");
 out.println(r.getNazivFirme());
      out.write("\" readonly>\n");
      out.write("                Radnik: <input type=\"text\" name=\"radnik\" value=\"");
 out.println(r.getRadnik());
      out.write("\">\n");
      out.write("                Datum: <input type=\"text\" name=\"datum\" value=\"");
 out.println(r.getDatum());
      out.write("\" readonly>\n");
      out.write("                Cena: <input type=\"text\" name=\"ukCena\" value=\"");
 out.println(r.getUkCena()); 
      out.write("\">\n");
      out.write("                <br><br><br>\n");
      out.write("                <input type=\"submit\" name=\"Akcija\" value=\"Sacuvaj\"> \n");
      out.write("                <input type=\"submit\" name=\"Akcija\" value=\"Obrisi Racun\">\n");
      out.write("                <br><br>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"izmenastavka\">\n");
      out.write("                <br>\n");
      out.write("                <h3>Unesite novu stavku:</h3>\n");
      out.write("                Broj stavke: <input type=\"text\" name=\"rbStavke\">\n");
      out.write("                Usluga: <input type=\"text\" name=\"usluga\">\n");
      out.write("                Cena: <input type=\"text\" name=\"cena\">\n");
      out.write("                <br><br><br> <input type=\"submit\" name=\"Akcija\" value=\"Unesi stavku\">\n");
      out.write("                    <br><br><br>\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div class=\"izmenastavkatabela\">\n");
      out.write("                <h3>Stavke racuna</h3>\n");
      out.write("                    <table border=\"3\">\n");
      out.write("                        <tr>\n");
      out.write("                            <td>Racun</td>\n");
      out.write("                            <td>Redni broj</td>\n");
      out.write("                            <td>Usluga</td>\n");
      out.write("                            <td>Cena</td>\n");
      out.write("                            <td>Izaberi</td>\n");
      out.write("                        </tr>\n");
      out.write("                        \n");
      out.write("                        ");

                            StavkaRacuna sr = null;
                            for(int i=0; i<stavke.size(); i++){
                                sr = stavke.get(i);
                        
      out.write("\n");
      out.write("                        \n");
      out.write("                        <tr>\n");
      out.write("                            <td><input type=\"text\" name=\"brRacuna1\" value=\"");
 out.println(sr.getBrRacuna()); 
      out.write("\" readonly></td>\n");
      out.write("                            <td><input type=\"text\" name=\"rbStavke1\" value=\"");
 out.println(sr.getRbStavke()); 
      out.write("\" readonly></td>\n");
      out.write("                            <td><input type=\"text\" name=\"usluga1\" value=\"");
 out.println(sr.getUsluga()); 
      out.write("\"></td>\n");
      out.write("                            <td><input type=\"text\" name=\"cena1\" value=\"");
 out.println(sr.getCena()); 
      out.write("\"></td>\n");
      out.write("                            <td><input type=\"radio\" name=\"izbor\" value=\"");
      out.print(sr.getRbStavke());
      out.write("\"></td>\n");
      out.write("                        </tr>\n");
      out.write("                                           \n");
      out.write("                        ");
 } 
      out.write("\n");
      out.write("                       \n");
      out.write("                    </table>  </div>\n");
      out.write("                        <br><br> \n");
      out.write("                    <div class=\"izmenastavkaunos\">\n");
      out.write("                    Unesite novu cenu: <input type=\"text\" name=\"cena2\" ><br>\n");
      out.write("                    Unesite novu uslugu: <input type=\"text\" name=\"usluga2\"><br><br>\n");
      out.write("                    <input type=\"submit\" name=\"Akcija\" value=\"Izmeni stavku\">                 \n");
      out.write("                    <input type=\"submit\" name=\"Akcija\" value=\"Obrisi stavku\">\n");
      out.write("                    <input type=\"submit\" name=\"Akcija\" value=\"Stavka\">\n");
      out.write("                        \n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
