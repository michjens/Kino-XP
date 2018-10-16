package kino.xp.prototype;

import java.sql.*;

public class Booking {
    private String kundeFornavn;
    private String efternavn;
    private String telefon;
    private String email;
    private boolean reklamer;
    private int brugerId;


    static Connection con;

    public Booking(String kundeFornavn, String efternavn, String telefon, String email, boolean reklamer, int brugerId) {
        this.kundeFornavn = kundeFornavn;
        this.efternavn = efternavn;
        this.telefon = telefon;
        this.email = email;
        this.reklamer = reklamer;
        this.brugerId = brugerId;
    }

    public Booking() {
    }

    public Booking(int brugerId) {
        this.brugerId = brugerId;
    }

    public String getKundeFornavn() {
        return kundeFornavn;
    }

    public void setKundeFornavn(String fornavn) {
        this.kundeFornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
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

    public int getBrugerId() {
        return brugerId;
    }

    public void setBrugerId(int brugerId) {
        this.brugerId = brugerId;
    }

    public static void opretBooking(Booking booking){
        con = dbConn.getInstance().createConnection();
        try{
            PreparedStatement stmtKunde = con.prepareStatement("INSERT INTO Kunder (Fornavn, Efternavn, Email, Tlf, Reklame) VALUES (?,?,?,?,?)");
            stmtKunde.setString(1,booking.getKundeFornavn());
            stmtKunde.setString(2,booking.getEfternavn());
            stmtKunde.setString(3,booking.getEmail());
            stmtKunde.setString(4,booking.getTelefon());

            if(booking.isReklamer()) {
                stmtKunde.setInt(5, 1);
            }else {
                stmtKunde.setInt(5, 0);
            }
            stmtKunde.executeUpdate();

            PreparedStatement ps = con.prepareStatement("SELECT IdKunder" + "  FROM Kunder" + " ORDER BY IdKunder DESC LIMIT 1;");
            ResultSet rs = ps.executeQuery();

            // Tilføj Antal sæder / pris + idVisning når dette er implementeret
            PreparedStatement stmtBooking = con.prepareStatement("INSERT INTO Booking (idBruger, Seats, idKunder, idVisning) VALUES (?,?,?,?)");
            if (rs.next()) {
                System.out.println(booking.getBrugerId());
                System.out.println(rs.getInt("idKunder"));
                stmtBooking.setInt(1, booking.getBrugerId());
                // Sæt sædeantal ind på x' plads
                stmtBooking.setInt(2, 2);
                stmtBooking.setInt(3, rs.getInt("idKunder"));
                // Sæt idVisning ind på x' plads
                stmtBooking.setInt(4, 1);
                stmtBooking.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    // Lav slet booking fra oversigt funktion
    public static void sletBooking(Booking booking){

    }

    // Lav rediger booking fra oversigt funktion
    public static void redigerBooking(Booking booking){

    }
}
