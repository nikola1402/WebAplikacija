package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Klijent;
import model.Racun;
import util.DBBroker;

//@WebServlet(name = "PretragaKlijentaServlet", urlPatterns = {"/PretragaKlijentaServlet"})
public class PretragaKlijentaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DBBroker dbb = new DBBroker();
    List podaci = new ArrayList();

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet KlijentPretragaServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet KlijentPretragaServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
        
        
        try {     
            ArrayList<Klijent> klijenti = new ArrayList();
            klijenti = dbb.nadjiKlijente();
            
            if (klijenti != null) {
                request.setAttribute("klijenti", klijenti);
                RequestDispatcher rd = request.getRequestDispatcher("/PretragaKlijenta.jsp");
                rd.forward(request, response);
                return;
            } else {
                System.out.println("prazni klijenti");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PretragaKlijentaServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        //processRequest(request, response);
        //response.setContentType("text/html;charset=UTF-8");

         String nazivFirme = request.getParameter("nazivFirme");
         
         try {
            Klijent kl = dbb.nadjiKlijenta(nazivFirme);
            ArrayList<Racun> racuni = new ArrayList();
            racuni = dbb.nadjiRacuneKlijent(nazivFirme);
            
            Racun r = new Racun();
            for (int i=0; i<racuni.size(); i++){
                r = racuni.get(i);
            }
            
            Integer br = racuni.size();
            if (kl != null || racuni != null){
                request.setAttribute("kl", kl);
                request.setAttribute("racuni", racuni);
                request.setAttribute("r", r);
                request.setAttribute("br", br);
                RequestDispatcher rd = request.getRequestDispatcher("/IzmenaKlijenta.jsp");
                rd.forward(request, response);
                racuni.clear();          
            } else {
                request.setAttribute("poruka", "Pokusajte ponovo.");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/PretragaKlijenta.jsp");
                rd.forward(request, response);    
            }
 
         } catch (SQLException e) {
             e.printStackTrace();
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
