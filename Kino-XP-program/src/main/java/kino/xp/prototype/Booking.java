package kino.xp.prototype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Booking {
    private String fornavn;
    private String efternavn;
    private long telefon;
    private String email;
    private boolean reklamer;

    static Connection con;


    public Booking(String fornavn, String efternavn, long telefon, String email, boolean reklamer) {
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.telefon = telefon;
        this.email = email;
        this.reklamer = reklamer;
    }

    public Booking() {
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public long getTelefon() {
        return telefon;
    }

    public void setTelefon(long telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isReklamer() {
        return reklamer;
    }

    public void setReklamer(boolean reklamer) {
        this.reklamer = reklamer;
    }

    public static void opretBooking(Booking booking){
        con = dbConn.getInstance().createConnection();
        try{
            PreparedStatement stmtKunde = con.prepareStatement("INSERT INTO Kunder VALUES (?,?,?,?,?)");
            stmtKunde.setString(1,booking.getFornavn());
            stmtKunde.setString(2,booking.getEfternavn());
            stmtKunde.setString(3,booking.getEmail());
            stmtKunde.setLong(4,booking.getTelefon());
            if(booking.isReklamer()) {
                stmtKunde.setInt(5, 1);
            }else {
                stmtKunde.setInt(5, 0);
            }
            stmtKunde.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
