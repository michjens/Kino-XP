package kino.xp.prototype;

import java.sql.Connection;
<<<<<<< HEAD
import java.sql.PreparedStatement;
import java.sql.SQLException;
=======
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
>>>>>>> f9f93fe56e597fc3b38e78df4889bb537d2fa82a

public class Consumables {

    double pris;
    String navn;
    int antal;

    static Connection con;


    public Consumables() {}

    public Consumables(double pris, String navn){

    }

<<<<<<< HEAD
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


=======
    public Consumables(double pris, String navn, int antal) {
        this.pris = pris;
        this.navn = navn;
        this.antal = antal;
>>>>>>> f9f93fe56e597fc3b38e78df4889bb537d2fa82a
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

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public static ArrayList<Consumables> loadConsumables(String SQLQuery) throws SQLException {
        ArrayList<Consumables> consumablesArrayList = new ArrayList<>();

        con = dbConn.getInstance().createConnection();
        Statement s = null;
        s = con.createStatement();

        ResultSet rs = s.executeQuery(SQLQuery);
        while (rs.next()){
            consumablesArrayList.add(new Consumables(rs.getDouble("pris"), rs.getString("navn"), 0));
        }
        return  consumablesArrayList;
    }

}

