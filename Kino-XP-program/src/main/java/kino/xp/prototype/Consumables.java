package kino.xp.prototype;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Consumables {

    double pris;
    String navn;


    public Consumables() {}

    public Consumables(double pris, String navn){

    }

    public void gemGuf () {

        Connection con = dbConn.getInstance().createConnection();

        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO MadOgDrikke (navn, pris) VALUES (?,?)");
            ps.setString(1, navn);
            ps.setDouble(2, pris);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

}
