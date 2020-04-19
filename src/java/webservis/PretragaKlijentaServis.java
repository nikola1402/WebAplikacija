package webservis;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Klijent;
import util.DBBroker;

@WebService(serviceName = "PretragaKlijentaServisService", targetNamespace="http://www.klijenti.com",portName = "PretragaKlijentaServisPort")
public class PretragaKlijentaServis {
    
    
    DBBroker dbb = new DBBroker();
    ArrayList<Klijent> klijenti = new ArrayList();
    
    
    /**
     * This is a sample web service operation
     */
    @WebMethod
    public ArrayList<Klijent> vratiKlijente() {
          
        try {
            klijenti.clear();
            klijenti = dbb.nadjiKlijente();                        
        } catch (SQLException e) {
            System.out.println("Nema klijenata" +e);
        }
    
    return klijenti;
    
    }
}    
        
    

