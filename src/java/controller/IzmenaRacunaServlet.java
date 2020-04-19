package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Klijent;
import model.Racun;
import model.StavkaRacuna;
import util.DBBroker;

public class IzmenaRacunaServlet extends HttpServlet {
    DBBroker dbb = new DBBroker();
    ArrayList<StavkaRacuna> stavke = new ArrayList();
    Racun r = new Racun();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet IzmenaRacunaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IzmenaRacunaServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer brRacuna = Integer.valueOf(request.getParameter("brRacuna"));
        String nazivFirme = request.getParameter("nazivFirme");
        String radnik = request.getParameter("radnik");
        java.sql.Date datum = new java.sql.Date(new java.util.Date().getTime());    
        
        String Akcija = request.getParameter("Akcija");
        String status = "";

        HttpSession ss = request.getSession();

        if (Akcija.equals("Sacuvaj")) {
   
            status = "update";
 
            r.setRadnik(radnik);
            r.setBrRacuna(brRacuna);
            r.setStatus(status);
            boolean ret = dbb.sacuvajRacun(r);
        
            if (ret) {
                dbb.potvrdiDBTransakciju();
            } else {
                dbb.ponistiDBTransakciju();
            }
    
            request.setAttribute("poruka", "Uspesno ste se izmenili racun!");
            getServletContext().getRequestDispatcher("/index.html").forward(request, response);
            
        } else if (Akcija.equals("Obrisi Racun")) {

            status = "delete";
    
            r = r.obrisiRacun(brRacuna);
            boolean ret = dbb.sacuvajRacun(r);
        
                    if (ret) {
                        dbb.potvrdiDBTransakciju();
                    } else {
                        dbb.ponistiDBTransakciju();
                    }

            request.setAttribute("poruka", "Uspesno ste obrisali racun!");
            getServletContext().getRequestDispatcher("/index.html").forward(request, response);
            
        } else if (Akcija.equals("Unesi stavku")) {
            
            Integer rbStavke = Integer.valueOf(request.getParameter("rbStavke"));
            String usluga = request.getParameter("usluga");
            Integer cena = Integer.valueOf(request.getParameter("cena"));  
            status = "insert";
          
            r = r.izmeniRacun(brRacuna, rbStavke, usluga, cena, nazivFirme, datum, radnik, status);

            request.setAttribute("poruka", "Uspesno ste uneli stavku!");
            RequestDispatcher rd = request.getRequestDispatcher("/IzmenaRacuna.jsp");
            rd.forward(request, response);
            
        } else if (Akcija.equals("Izmeni stavku")) {
            
            status = "update";
                
            Integer rbStavke = Integer.valueOf(request.getParameter("izbor"));
            String usluga = request.getParameter("usluga2");
            Integer cena = Integer.valueOf(request.getParameter("cena2"));  

            r = r.izmeniRacun(brRacuna, rbStavke, usluga, cena, nazivFirme, datum, radnik, status);
            request.setAttribute("poruka", "Uspesno ste izmenili stavku!");
            RequestDispatcher rd = request.getRequestDispatcher("/IzmenaRacuna.jsp");
            rd.forward(request, response);
            
        } else if (Akcija.equals("Obrisi stavku")){
                
            status = "delete";

            Integer rbStavke = Integer.valueOf(request.getParameter("izbor"));
            String usluga = request.getParameter("usluga1");
            Integer cena = Integer.valueOf(request.getParameter("cena1"));  
                
            r = r.izmeniRacun(brRacuna, rbStavke, usluga, cena, nazivFirme, datum, radnik, status);
            request.setAttribute("poruka", "Uspesno ste izmenili stavku!");
            RequestDispatcher rd = request.getRequestDispatcher("/IzmenaRacuna.jsp");
            rd.forward(request, response);
        }  
        
    }
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer brRacuna = Integer.valueOf(request.getParameter("brRacuna"));
            String nazivFirme = request.getParameter("nazivFirme");
            String radnik = request.getParameter("radnik");
            Integer rbStavke = Integer.valueOf(request.getParameter("rbStavke"));
            String usluga = request.getParameter("usluga");
            Integer cena = Integer.valueOf(request.getParameter("cena"));  
                
            java.sql.Date datum = new java.sql.Date(new java.util.Date().getTime());
            
        String Akcija = request.getParameter("Akcija");
        String status = "";
        
        if (Akcija.equals("Izmeni stavku")){
            
            status = "update";

            r = r.izmeniRacun(brRacuna, rbStavke, usluga, cena, nazivFirme, datum, radnik, status);
            
            request.setAttribute("poruka", "Uspesno ste izmenili stavku!");
            getServletContext().getRequestDispatcher("/index.html").forward(request, response);
            
        } else if (Akcija.equals("Obrisi stavku")){
                
            status = "delete";

            r = r.izmeniRacun(brRacuna, rbStavke, usluga, cena, nazivFirme, datum, radnik, status);
            request.setAttribute("poruka", "Uspesno ste obrisali stavku!");
            getServletContext().getRequestDispatcher("/index.html").forward(request, response);
        }  
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
