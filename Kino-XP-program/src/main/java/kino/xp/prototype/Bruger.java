package kino.xp.prototype;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Bruger {
    private String fornavn;
    private String efternavn;
    private int ID;
    private String password;
    private String email;
    private int access;

    public Bruger(){
        this.email = "";
    }

    public Bruger(String fornavn, String efternavn, int ID, String password, String email, int access) {
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.ID = ID;
        this.password = password;
        this.email = email;
        this.access = access;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }
    public static String[] login(String username, String password) throws SQLException {
        Connection con;
        String[] login = new String[2];
        con = dbConn.getInstance().createConnection();
        Statement s = null;
        s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT email, password, Bruger.access FROM ap.Bruger");
        while (rs.next()) {
            if (username.toLowerCase().equals(rs.getString("email").toLowerCase()) && password.toLowerCase().equals(rs.getString("password").toLowerCase())) {
                login[0] = rs.getString("email").toLowerCase();
                login[1] = rs.getString("access").toLowerCase();
                return login;
            }
        }
        return login;
    }
}
