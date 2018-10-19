package kino.xp.prototype;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class Visninger {
    static Connection con;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;
    private int sal;
    private int idFilm;

    public Visninger(Date date, int sal, int idFilm) {
        this.date = date;
        this.sal = sal;
        this.idFilm = idFilm;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public Visninger() {
    }

    public static void opretVisning(Visninger visninger, Film f) throws Exception {
        con = dbConn.getInstance().createConnection();
        //Statement s = null;
        try {
            //s = con.createStatement();
            PreparedStatement stmtFilm = con.prepareStatement("INSERT INTO KinoXP.Visninger(Dato, idFilm, Sal) VALUES (?,?,?) WHERE idVisninger = ?");
            stmtFilm.setTimestamp(1, new java.sql.Timestamp(visninger.getDate().getTime()));
            stmtFilm.setInt(2, (f.getId()));
            stmtFilm.setInt(3, visninger.getSal());
            stmtFilm.executeUpdate();


        } catch (SQLException e) {

        }

    }
}
