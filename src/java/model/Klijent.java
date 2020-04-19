package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import util.DBBroker;

@XmlRootElement(name="Klijent")
public class Klijent {
    
    Klijent kl;
    DBBroker dbb = new DBBroker();
    
    private Integer klijentID;
    private Integer PIB;
    private String nazivFirme;
    private Integer telefon;
    private String status;
    
    public Klijent (Integer klijentID, Integer PIB, String nazivFirme, Integer telefon, String status){
        this.klijentID = klijentID;
        this.PIB = PIB;
        this.nazivFirme = nazivFirme;
        this.telefon = telefon;
        this.status = status;
    }

    public Klijent() {
    }
    
    public Integer getKlijentID() {
        return klijentID;
    }
    
    public void setKlijentID(Integer klijentID){
        this.klijentID = klijentID;
    }
    
    public Integer getPIB(){
        return PIB;
    }
    
    public void setPIB(Integer PIB) {
        this.PIB = PIB;
    }
    
    public String getNazivFirme () {
        return nazivFirme;
    }
    
    public void setNazivFirme(String nazivFirme) {
        this.nazivFirme = nazivFirme;
    }
    
    public Integer getTelefon() {
        return telefon;
    }
    
    public void setTelefon(Integer telefon) {
        this.telefon = telefon;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
        
    public Klijent spremiKlijenta(Integer PIB, String nazivFirme, Integer telefon, String status) {
        kl = new Klijent(klijentID, PIB, nazivFirme, telefon, status);

        kl.setPIB(PIB);
        kl.setNazivFirme(nazivFirme);
        kl.setTelefon(telefon);
        kl.setStatus(status);
        
        return kl;
    }
    
    public Klijent izmeniKlijenta(Integer klijentID, Integer PIB, String nazivFirme, Integer telefon, String status) {
        kl = new Klijent(klijentID, PIB, nazivFirme, telefon, status);
        kl.setKlijentID(klijentID);
        kl.setPIB(PIB);
        kl.setNazivFirme(nazivFirme);
        kl.setTelefon(telefon);
        kl.setStatus(status);
        
        return kl;
    }


    
    public Klijent obrisiKlijenta(Integer klijentID){
        kl = new Klijent(klijentID, PIB, nazivFirme, telefon, status);
        kl.setKlijentID(klijentID);
        kl.setStatus("delete");
                
        return kl;
    }

    public Klijent(ResultSet rs) throws SQLException {
        this.klijentID = rs.getInt("kid");
        this.nazivFirme = rs.getString("nazivFirme");
        this.PIB = rs.getInt("PIB");
        this.telefon = rs.getInt("telefon");

    } 
}