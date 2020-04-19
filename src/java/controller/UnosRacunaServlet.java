package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Klijent;
import model.StavkaRacuna;
import model.Racun;
import util.DBBroker;

public class UnosRacunaServlet extends HttpServlet {
    DBBroker dbb = new DBBroker();
    Racun rac = new Racun();
    ArrayList<StavkaRacuna> stavke = new ArrayList();
            ArrayList lista = new ArrayList();


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
        //processRequest(request, response);
    
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
        
        
        Integer brRacuna = Integer.valueOf(request.getParameter("brRacuna"));
        String nazivFirme = request.getParameter("nazivFirme");
        String radnik = request.getParameter("radnik");
        java.sql.Date datum = new java.sql.Date(new java.util.Date().getTime());    
   
        String status = "insert";
        
        String Akcija = request.getParameter("Akcija");

            HttpSession ss = request.getSession();

            
            if (Akcija.equals("SacuvajStavku")){
                Racun r = new Racun();
                Integer rbStavke = Integer.valueOf(request.getParameter("stavkaR"));
                String usluga = request.getParameter("usluga");
                Integer cena = Integer.valueOf(request.getParameter("cena"));  
  
                rac = rac.sacuvajRacun(brRacuna, rbStavke, usluga, cena, nazivFirme, datum, radnik, status);
                
                ss.setAttribute("brRacuna", brRacuna);
                ss.setAttribute("nazivFirme", nazivFirme);
                ss.setAttribute("radnik", radnik);
                request.setAttribute("poruka", "Uspesno ste se uneli novu stavku!");

                RequestDispatcher rd = request.getRequestDispatcher("/UnosRacuna.jsp");
                rd.forward(request, response);

            } else if (Akcija.equals("SacuvajRacun")) {
                    System.out.println("sacuvajracun: " +rac.getBrRacuna()+rac.getStavke()+rac.getStatus());
                    
                    boolean ret = dbb.sacuvajRacun(rac);
        
                    if (ret) {
                        dbb.potvrdiDBTransakciju();
                    } else {
                        dbb.ponistiDBTransakciju();
                    }
                    stavke.clear();
                    request.setAttribute("poruka", "Uspesno ste se uneli novi racun!");
                    getServletContext().getRequestDispatcher("/index.html").forward(request, response);
                    return;
                    
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
