package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Klijent;
import model.Racun;
import util.DBBroker;

public class NadjiServlet extends HttpServlet {
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NadjiServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NadjiServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
            String Akcija = request.getParameter("Akcija");
            
        try {     
            
            if (Akcija.equals("Pronadji klijenta")) {
                ArrayList<Klijent> klijenti = new ArrayList();
                
                klijenti = dbb.nadjiKlijente();
                if (klijenti != null) {
                    request.setAttribute("klijenti", klijenti);
                    RequestDispatcher rd = request.getRequestDispatcher("/PretragaKlijenta.jsp");
                    rd.forward(request, response);
                    klijenti.clear();
                    return;
                }
            
            } else if (Akcija.equals("Unesi racun2")) {
                ArrayList<Klijent> klijenti = new ArrayList();
                
                klijenti = dbb.nadjiKlijente();
                if (klijenti != null) {
                    request.setAttribute("klijenti", klijenti);
                    RequestDispatcher rd = request.getRequestDispatcher("/UnosRacuna2.jsp");
                    rd.forward(request, response);
                    klijenti.clear();
                    return;
                }
            }
            else if (Akcija.equals("Pronadji racun")) {
                ArrayList<Racun> racuni = new ArrayList();
                racuni = dbb.nadjiSveRacune();
                if (racuni != null) {
                    request.setAttribute("racuni", racuni);
                    RequestDispatcher rd = request.getRequestDispatcher("/PretragaRacuna.jsp");
                    rd.forward(request, response);
                    racuni.clear();
                }
                
            } else if(Akcija.equals("Racuni")){
                ArrayList<Racun> racuni = new ArrayList();
                String nazivFirme = request.getParameter("nazivFirme"); 
                racuni = dbb.nadjiRacuneKlijent(nazivFirme);
                if (racuni != null) {
                    request.setAttribute("racuni", racuni);
                    RequestDispatcher rd = request.getRequestDispatcher("/PretragaRacuna.jsp");
                    rd.forward(request, response);
                    racuni.clear();
                }
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
