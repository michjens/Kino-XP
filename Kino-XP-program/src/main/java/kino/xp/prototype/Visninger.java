package kino.xp.prototype;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Visninger {
    static Connection con;


    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;
    private int sal;
    private int idFilm;

    public Visninger() {
    }

    public Visninger(LocalDate date, int sal, int idFilm) {
        this.date = date;
        this.sal = sal;
        this.idFilm = idFilm;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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



    public static void opretVisning(Visninger visninger, Film f) throws Exception {
        con = dbConn.getInstance().createConnection();
        //Statement s = null;
        try {
            //s = con.createStatement();
            PreparedStatement stmtFilm = con.prepareStatement("INSERT INTO KinoXP.Visninger(Dato, idFilm, Sal) VALUES (?,?,?)");
            LocalDateTime datetime = LocalDateTime.of(visninger.getDate(), visninger.getTime());
            java.sql.Timestamp date = java.sql.Timestamp.valueOf(datetime);
            stmtFilm.setTimestamp(1, date);
            stmtFilm.setInt(2, (f.getId()));
            stmtFilm.setInt(3, visninger.getSal());
            stmtFilm.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
