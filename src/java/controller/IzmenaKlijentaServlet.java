package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Klijent;
import model.Racun;
import model.StavkaRacuna;
import util.DBBroker;

public class IzmenaKlijentaServlet extends HttpServlet {

    DBBroker dbb = new DBBroker();
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
        processRequest(request, response);
               

        String Akcija = request.getParameter("Akcija");
        String status = "";
        
        if (Akcija.equals("Izmeni klijenta")) {
            
            Integer klijentID = Integer.valueOf(request.getParameter("klijentID"));
            Integer PIB = Integer.valueOf(request.getParameter("PIB"));
            String nazivFirme = request.getParameter("nazivFirme");
            Integer telefon = Integer.valueOf(request.getParameter("telefon"));  
            status = "update";
            
            if (klijentID == null || PIB == null || nazivFirme.isEmpty() || telefon == null) {
                request.setAttribute("poruka", "Unesi sve podatke!");
                getServletContext().getRequestDispatcher("/IzmenaKlijenta.jsp").forward(request, response);
      
            }   else  {
                
                Klijent kl = new Klijent();
            
                kl = kl.izmeniKlijenta(klijentID, PIB, nazivFirme, telefon, status);
                
                boolean ret = dbb.sacuvajKlijenta(kl);
        
                if (ret) {
                    dbb.potvrdiDBTransakciju();
                } else {
                    dbb.ponistiDBTransakciju();
                }
     
                request.setAttribute("poruka", "Uspesno ste se izmenili klijenta!");
                getServletContext().getRequestDispatcher("/index.html").forward(request, response);
            }
            
        } else if (Akcija.equals("Obrisi klijenta")) {
            
            Klijent kl = new Klijent();
            
            Integer klijentID = Integer.valueOf(request.getParameter("klijentID"));

            kl = kl.obrisiKlijenta(klijentID);
            
            boolean ret = dbb.sacuvajKlijenta(kl);
        
            if (ret) {
                dbb.potvrdiDBTransakciju();
            } else {
                dbb.ponistiDBTransakciju();
            }
            
            request.setAttribute("poruka", "Uspesno ste obrisali klijenta!");
            getServletContext().getRequestDispatcher("/index.html").forward(request, response);
            
        } else if(Akcija.equals("Racuni")){
                ArrayList<Racun> racuni = new ArrayList();
                String nazivFirme = request.getParameter("nazivFirme"); 
                
                try {
                racuni = dbb.nadjiRacuneKlijent(nazivFirme);
                if (racuni != null) {
                    request.setAttribute("racuni", racuni);
                    RequestDispatcher rd = request.getRequestDispatcher("/PretragaRacuna.jsp");
                    rd.forward(request, response);
                    racuni.clear();
                }
            } catch (SQLException e) {
                System.out.println("Klijent nema racune"+e);
            }
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
        processRequest(request, response);

    
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
