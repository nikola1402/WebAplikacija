package util;

import model.Klijent;
import model.Racun;
import model.StavkaRacuna;
import controller.PretragaKlijentaServlet;
import static java.lang.System.out;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBBroker {

    private static Connection conn;
    //private PreparedStatement pst;
    public List klPodaci;
    ArrayList<Racun> racuni = new ArrayList();
    ArrayList<Klijent> klijenti = new ArrayList();
    ArrayList<StavkaRacuna> stavke = new ArrayList();
    StavkaRacuna sr;

    Integer klijentID;
    String nazivFirme;
    Integer PIB;
    Integer telefon;

    public DBBroker(){        
    }

public Connection pokreniDBTransakciju() {
    
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","orcl");
    } catch (ClassNotFoundException ex) {
        System.err.println("Greska prilikom ucitavanja driver-a...->" + ex); 
    } catch (SQLException ex) {
        System.err.println("Greska prilikom otvaranja konekcije sa bazom...->" + ex);
    }
    return conn;
    }

public void potvrdiDBTransakciju() {
    try {
        conn.commit();
        conn.close();
    } catch (SQLException ex) {
        System.err.println("Greska prilikom commit operacije...->" + ex);
    }
 }
 
 public void ponistiDBTransakciju() {
    try{
        conn.rollback();
        conn.close();
    } catch (SQLException ex) {
        System.err.println("Greska prilikom rollback operacije...->" + ex);
    }
 }

public Klijent nadjiKlijenta(String nazivFirme) throws SQLException {
        
    Klijent k = null;
    conn = pokreniDBTransakciju();

    String upit = "select * from klijent k where k.nazivfirme='"+nazivFirme+"'";

    PreparedStatement ps = conn.prepareStatement(upit);
        
    ResultSet rs = ps.executeQuery(upit);
    while(rs.next()) {
        k = new Klijent(rs); 
    } 
    return k;
 }
 
public Racun nadjiRacun(Integer brRacuna) throws SQLException {
     
    Racun r = null;
    stavke = null;
    conn = pokreniDBTransakciju();

    String upit = "select * from racun r where r.brrac=?";
     
    PreparedStatement ps = conn.prepareStatement(upit);
    ps.setInt(1, brRacuna);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        r = new Racun(rs);
        stavke = nadjiStavke(brRacuna);
        
        if (stavke != null) {   
            r.setStavke(stavke);
        }
    }
    return r;
 }
 
public StavkaRacuna nadjiStavku(Integer brRacuna, Integer rbStavke) throws SQLException {
     
    StavkaRacuna sr = null;
    conn = pokreniDBTransakciju();

    String upit = "select * from stavkarac sr where sr.brrac=? and sr.stavkar=?";
     
    PreparedStatement ps = conn.prepareStatement(upit);
    ps.setInt(1, brRacuna);
    ps.setInt(2, rbStavke);
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        sr = new StavkaRacuna(rs);
    }
    return sr;
 }
 
public ArrayList<Klijent> nadjiKlijente() throws SQLException {
     
    Klijent k = null;
    conn = pokreniDBTransakciju();

    String upit = "select * from klijent";
     
    PreparedStatement ps = conn.prepareStatement(upit);
    ResultSet rs = ps.executeQuery();
    while(rs.next()) {
        k = new Klijent(rs);        
        klijenti.add(k);
    }
    return klijenti;
}
  
public ArrayList<Racun> nadjiRacune(Integer brRacuna, String nazivFirme) throws SQLException {
     
    Racun r = null;
    conn = pokreniDBTransakciju();

    String upit = "select * from racun where brrac=? and nazivfirme=?";
     
    PreparedStatement ps = conn.prepareStatement(upit);
    ps.setInt(1, brRacuna);
    ps.setString(2, nazivFirme);
    ResultSet rs = ps.executeQuery();
    while(rs.next()) {
        r = new Racun(rs);         
        racuni.add(r);
    }
    return racuni;
}
  
public ArrayList<Racun> nadjiSveRacune() throws SQLException {
     
    Racun r = null;
    conn = pokreniDBTransakciju();

    String upit = "select * from racun";
     
    PreparedStatement ps = conn.prepareStatement(upit);
    ResultSet rs = ps.executeQuery();
    while(rs.next()) {
        r = new Racun(rs);         
        racuni.add(r);
    }
    return racuni;
 }
 
public ArrayList<Racun> nadjiRacuneKlijent(String nazivFirme) throws SQLException {
     
    Racun r = null;
     
    sr = null;
    conn = pokreniDBTransakciju();

    String upit = "select * from racun r where r.nazivfirme='"+nazivFirme+"'";
     
    PreparedStatement ps = conn.prepareStatement(upit);
    ResultSet rs = ps.executeQuery(upit);
    while(rs.next()) {
        r = new Racun(rs);
         
        stavke = nadjiStavke(r.getBrRacuna());
        if (stavke != null) {
            r.setStavke(stavke);
        }
        racuni.add(r);
    }     
    return racuni;
}
 
public ArrayList<StavkaRacuna> nadjiStavke(Integer brRacuna) throws SQLException{
    StavkaRacuna sr = new StavkaRacuna();
    stavke = new ArrayList();
    conn = pokreniDBTransakciju();
    
    String upit = "select * from stavkarac where brrac=?";
    
    PreparedStatement ps = conn.prepareStatement(upit);
    ps.setInt(1, brRacuna);
    
    ResultSet rs = ps.executeQuery();
    while (rs.next()) {
        sr = new StavkaRacuna(rs);
        stavke.add(sr);
    }
    return stavke;
}

 
public boolean sacuvajKlijenta (Klijent kl) {
    try {
        conn = pokreniDBTransakciju();
        String upit = "";
        PreparedStatement pst = null;
        
            if (kl.getStatus().equals("insert")){
                upit = "insert into klijent (pib, nazivfirme, telefon) values (?, ?, ?)";
                
                pst = conn.prepareStatement(upit);
                
                pst.setInt(1, kl.getPIB());
                pst.setString(2, kl.getNazivFirme());
                pst.setInt(3, kl.getTelefon());
                
                pst.execute();

            }
            
            else if (kl.getStatus().equals("update")) {
                
                upit = "update Klijent set nazivFirme=?, telefon=?, PIB=? where kid=?";
                pst = conn.prepareStatement(upit);
                
                pst.setString(1, kl.getNazivFirme());
                pst.setInt(2, kl.getTelefon());
                pst.setInt(3, kl.getTelefon());
                pst.setInt(4, kl.getKlijentID());
                
                pst.execute();
                
            }
            
            else if (kl.getStatus().equals("delete")) {
                upit = "delete from racun where kid=?";
                pst = conn.prepareStatement(upit);
                pst.setInt(1, kl.getKlijentID());
                pst.executeQuery();
                String upit1 = "delete from klijent where kid=?";
                pst = conn.prepareStatement(upit1);
                pst.setInt(1, kl.getKlijentID());
                pst.executeQuery();
            }
            return true;
    } catch (SQLException ex) {
        System.err.println("Klijent ne moze da se zapamti u bazi...->" + ex);
    } 
    return false;
 }

public boolean sacuvajRacun(Racun rac) {
     
    try{   
        conn = pokreniDBTransakciju();
        String upit = "";
        ArrayList<StavkaRacuna> stavke = rac.getStavke();
        System.out.println("Insert RACUN1: broj: "+rac.getBrRacuna()+" nazivfirme: "+rac.getNazivFirme()+" datum: " +rac.getDatum()+" stavke: "+rac.getStavke()+ " status: "+rac.getStatus());
         PreparedStatement pst = null;

        if(rac.getStatus().equals("insert")){
            System.out.println("Insert RACUN2: broj: "+rac.getBrRacuna()+" nazivfirme: "+rac.getNazivFirme()+" datum: " +rac.getDatum()+" stavke: "+rac.getStavke());
            upit = "insert into Racun (brrac, nazivfirme, datum, radnik) values (?, ?, ?, ?)";          
             
            pst = conn.prepareStatement(upit);
             
            pst.setInt(1, rac.getBrRacuna());
            pst.setString(2, rac.getNazivFirme());
            pst.setDate(3, rac.getDatum());    
            pst.setString(4, rac.getRadnik());
            pst.executeUpdate();
            if(stavke!=null){
                for(int i=0; i<stavke.size(); i++){
                    StavkaRacuna sr = stavke.get(i);
                    //sr.setBrRacuna(rac.getBrRacuna());
                    sacuvajStavku(sr);             
                } 
            } else { System.out.println("Stavke nisu ubacene u racun!");}
            
            String upit1 = "update Racun set kid =(select kid from Klijent where nazivFirme=?) where nazivFirme=?";
            PreparedStatement ps = conn.prepareStatement(upit1);
            ps.setString(1, rac.getNazivFirme());
            ps.setString(2, rac.getNazivFirme());
            ps.execute();
        } 
        else if (rac.getStatus().equals("update")){
             
            upit = "update Racun set radnik=? where brrac=?";  
             
            pst = conn.prepareStatement(upit);
             
            pst.setString(1, rac.getRadnik());
            pst.setInt(2, rac.getBrRacuna());
            pst.executeUpdate();
            if(stavke!=null){
                for(int i=0; i<stavke.size(); i++){
                    StavkaRacuna sr = stavke.get(i);
                    sacuvajStavku(sr);             
                } 
            }
            
        } 
        else if (rac.getStatus().equals("delete")) {
            
            stavke = new ArrayList();
            try {
                stavke = nadjiStavke(rac.getBrRacuna());
                for (int i=0; i<stavke.size(); i++){
                    StavkaRacuna sr = stavke.get(i);
                    sr.setStatus("delete");
                    sacuvajStavku(sr);
                }
            } catch (SQLException e) {
                System.out.println("ne mogu da pronadjem stavku" + e);
            }
            
            
            upit = "delete from Racun where brrac=?"; 
             
            pst = conn.prepareStatement(upit);
             
            pst.setInt(1, rac.getBrRacuna());
            pst.executeUpdate();
        }
            

            System.out.println("Racun ubacen!");                  
            return true;

    }catch (SQLException ex) {
         System.out.println("Racun ne moze da se zapamti u bazi...->" + ex);
    } 
    return false;
}
 
public boolean sacuvajStavku (StavkaRacuna sr) {
 
    try {
        
        conn = pokreniDBTransakciju();
        String upit = "";
                PreparedStatement pst = null;

            if (sr.getStatus().equals("insert")){
                  
                upit = "insert into stavkarac(stavkar, brrac, usluga, cena) values (?, ?, ?, ?)";
                
                pst = conn.prepareStatement(upit);
             
                pst.setInt(1, sr.getRbStavke());
                pst.setInt(2, sr.getBrRacuna());
                pst.setString(3, sr.getUsluga());
                pst.setInt(4, sr.getCena());
            
            }
            
            else if (sr.getStatus().equals("update")) {
                upit = "update stavkarac set usluga=?, cena=? where stavkar=? and brrac=?";
                
                pst = conn.prepareStatement(upit);
                
                pst.setString(1, sr.getUsluga());
                pst.setInt(2, sr.getCena());
                pst.setInt(3, sr.getRbStavke());
                pst.setInt(4, sr.getBrRacuna());
                
            }
            else if (sr.getStatus().equals("delete")) {
                
                upit = "delete from stavkarac where stavkar=? and brrac=?";
                
                pst = conn.prepareStatement(upit);
                
                pst.setInt(1, sr.getRbStavke());
                pst.setInt(2, sr.getBrRacuna());
            }
            
            pst.executeQuery();
            System.out.println("Stavka sacuvana!");
            return true;
            
    } catch (SQLException ex) {
        System.err.println("Stavka ne moze da se zapamti u bazi...->" + ex);
    } 
    return false;
}
 
}