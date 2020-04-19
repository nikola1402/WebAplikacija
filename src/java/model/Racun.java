package model;

import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import util.DBBroker;
import java.sql.Date;
import java.util.List;

public class Racun {

    Racun rac;
    DBBroker dbb = new DBBroker();
    StavkaRacuna sr;
    
    private Integer brRacuna;
    private Date datum;
    private String nazivFirme;
    private ArrayList<StavkaRacuna> stavke = new ArrayList();
    private String radnik;
    private Integer ukCena;

    private String status;
 
    public Racun (Integer brRacuna, Date datum, String nazivFirme, ArrayList<StavkaRacuna> stavke, String radnik, Integer ukCena, String status){
        this.brRacuna = brRacuna;
        this.datum = datum;
        this.nazivFirme = nazivFirme;
        this.stavke = stavke;
        this.radnik = radnik;
        this.status = status;
        this.ukCena = ukCena;
    }

  
    public Racun() {
    }
 
    public Integer getBrRacuna(){
        return brRacuna;
    }
    
    public void setBrRacuna(Integer brRacuna) {
        this.brRacuna = brRacuna;
    }
    
    public Date getDatum() {
        return datum;
    }
    
    public void setDatum (Date datum) {
        this.datum = datum;
    }
    
    public String getNazivFirme () {
        return nazivFirme;
    }
    
    public void setNazivFirme(String nazivFirme) {
        this.nazivFirme = nazivFirme;
    }
    
    public ArrayList<StavkaRacuna> getStavke() {
        return stavke;
    }
    
    public void setStavke(ArrayList<StavkaRacuna> stavke){
        this.stavke = stavke;
    }
    
    public String getRadnik() {
        return radnik;
    }
    
    public void setRadnik(String radnik) {
        this.radnik = radnik;
    }
    
    public Integer getUkCena() {
        return ukCena;
    }
    
    public void setUkCena(Integer ukCena) {
    this.ukCena = ukCena;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public Racun spakujRacun(Integer brRacuna, Date datum, String nazivFirme, String radnik, String status) {
        Racun r = new Racun(brRacuna, datum, nazivFirme, getStavke(), radnik, ukCena, status);
        
        r.setBrRacuna(brRacuna);
        r.setDatum(datum);
        r.setNazivFirme(nazivFirme);
        r.setStavke(getStavke());
        r.setRadnik(radnik);
        r.setStatus(status);
        return r;
    }
    

    
    public Racun obrisiRacun(Integer brRacuna){
        rac = new Racun(brRacuna, datum, nazivFirme, stavke, radnik, ukCena, status );

        rac.setBrRacuna(brRacuna);
        rac.setStavke(stavke);
        rac.setStatus("delete");
                
        return rac;
    }
    
    public Racun sacuvajRacun(Integer brRacuna, Integer rbStavke, String usluga, Integer cena, String nazivFirme, Date datum, String radnik, String status) {
     
        if (rbStavke != null) {
            stavke = ubaciUKolekciju(brRacuna, rbStavke, usluga, cena, status);
        }
   
        Racun r = new Racun(brRacuna, datum, nazivFirme, stavke, radnik, ukCena, status);
        r.setBrRacuna(brRacuna);
        r.setNazivFirme(nazivFirme);
        r.setStavke(stavke);   
        r.setDatum(datum);
        r.setRadnik(radnik);
        r.setStatus(status);

        return r;
    }
    
    public Racun izmeniRacun(Integer brRacuna, Integer rbStavke, String usluga, Integer cena, String nazivFirme, Date datum, String radnik, String status) {
             
        if (rbStavke != null) {
            stavke = ubaciUKolekciju(brRacuna, rbStavke, usluga, cena, status);
        }

        Racun r = new Racun(brRacuna, datum, nazivFirme, stavke, radnik, ukCena, status);
        status = "update";
        r.setBrRacuna(brRacuna);
        r.setNazivFirme(nazivFirme);
        r.setStavke(stavke);   
        r.setDatum(datum);
        r.setRadnik(radnik);
        r.setStatus(status);
        return r;
    }
      

    
    
    public ArrayList ubaciUKolekciju(Integer brRacuna, Integer rbStavke, String usluga, Integer cena, String status) {
                
        sr = new StavkaRacuna(brRacuna, rbStavke, usluga, cena, status);

        sr.setBrRacuna(brRacuna);
        sr.setRbStavke(rbStavke);
        sr.setUsluga(usluga);
        sr.setCena(cena);
        sr.setStatus(status);

        stavke.add(sr);
        
        return stavke;
    }


    public Racun(ResultSet rs) throws SQLException {
        this.brRacuna = rs.getInt("brrac");
        this.nazivFirme = rs.getString("nazivFirme");
        this.datum = rs.getDate("datum");
        this.radnik = rs.getString("radnik");
        this.ukCena = rs.getInt("ukupnacena");        
    }   
}