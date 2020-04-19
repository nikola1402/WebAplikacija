package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import model.Klijent;
import util.DBBroker;

@WebServlet(name = "KontrolorKlijenta", urlPatterns = {"/KontrolorKlijenta"})
public class KlijentUnosServlet extends HttpServlet {

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
        String klijent = request.getParameter("klijent");
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
        
        Integer PIB = Integer.valueOf(request.getParameter("PIB"));
        String nazivFirme = request.getParameter("nazivFirme");
        Integer telefon = Integer.valueOf(request.getParameter("telefon"));  
        String status = "insert";
       
        if (
            PIB == null || nazivFirme.isEmpty() || nazivFirme == null || telefon == null) {
            request.setAttribute("poruka", "Unesi sve podatke!");
            getServletContext().getRequestDispatcher("/UnosKlijenta.jsp").forward(request, response);
      
        } else  {
            Klijent kl = new Klijent();
            
            kl = kl.spremiKlijenta(PIB, nazivFirme, telefon, status);
            
            boolean ret = dbb.sacuvajKlijenta(kl);
        
        if (ret) {
            dbb.potvrdiDBTransakciju();
        } else {
            dbb.ponistiDBTransakciju();
        }
 
            request.setAttribute("poruka", "Uspesno ste se uneli novog klijenta!");
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
