package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Racun;
import util.DBBroker;

public class StavkaRacuna {
    
    DBBroker dbb = new DBBroker();
    Racun r = new Racun();
    
    private Integer brRacuna;
    private Integer rbStavke;
    private String usluga;
    private Integer cena;
    private String status;
    
    public StavkaRacuna(Integer brRacuna, Integer rbStavke, String usluga, Integer cena, String status) {
        this.brRacuna = brRacuna;
        this.rbStavke = rbStavke;
        this.usluga = usluga;
        this.cena = cena;
        this.status = status;
    }

    public StavkaRacuna() {
    }

    public int getBrRacuna(){
        return brRacuna;
    }
    
    public void setBrRacuna(Integer brRacuna){
        this.brRacuna = brRacuna;
    }
    
    public int getRbStavke () {
        return rbStavke;
    }
    
    public void setRbStavke(Integer rbStavke) {
        this.rbStavke = rbStavke;
    }
    
    public String getUsluga () {
        return usluga;
    }
    
    public void setUsluga(String usluga) {
        this.usluga = usluga;
    }
    
    public Integer getCena() {
        return cena;
    }
    
    public void setCena (Integer cena) {
        this.cena = cena;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
       
    
    public ArrayList<StavkaRacuna> ubaciUListu(Integer brRacuna, Integer rbStavke, String usluga, Integer cena, String status) {
        
        ArrayList<StavkaRacuna> stavke = new ArrayList();
        StavkaRacuna s = new StavkaRacuna();
        
        s.setBrRacuna(brRacuna);
        s.setRbStavke(rbStavke);
        s.setUsluga(usluga);
        s.setCena(cena);
        s.setStatus(status);
        
        stavke.add(s);
        return stavke;
    }

    
    public StavkaRacuna(ResultSet rs) throws SQLException {
        this.brRacuna = rs.getInt("brrac");
        this.rbStavke = rs.getInt("stavkaR");
        this.usluga = rs.getString("usluga");
        this.cena = rs.getInt("cena");       
    }   
}