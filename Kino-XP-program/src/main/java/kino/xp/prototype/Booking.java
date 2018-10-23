package kino.xp.prototype;

import java.sql.*;
import java.util.ArrayList;

public class Booking {
    private String kundeFornavn;
    private String efternavn;
    private String telefon;
    private String email;
    private boolean reklamer;
    private int brugerId;

    private int idBooking;
    private int seats;
    private int idKunde;
    private int idVisning;

    static Connection con;

    public Booking(int brugerId, int idBooking, int seats, int idKunde, int idVisning) {
        this.brugerId = brugerId;
        this.idBooking = idBooking;
        this.seats = seats;
        this.idKunde = idKunde;
        this.idVisning = idVisning;
    }

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

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getIdKunde() {
        return idKunde;
    }

    public void setIdKunde(int idKunde) {
        this.idKunde = idKunde;
    }

    public int getIdVisning() {
        return idVisning;
    }

    public void setIdVisning(int idVisning) {
        this.idVisning = idVisning;
    }

    /*TEST:
    * Skal kunne gemme en booking i databasen
    * */
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

    /*TEST:
    * Skal kunne hente en booking fra databasen
    * */
    public static ArrayList<Booking> loadBooking(String SQLQuery) throws SQLException {
        ArrayList<Booking> bookingArrayList = new ArrayList<>();

        con = dbConn.getInstance().createConnection();
        Statement s = null;
        s = con.createStatement();

        ResultSet rs = s.executeQuery(SQLQuery);
        while (rs.next()){
            bookingArrayList.add(new Booking(rs.getInt(2),rs.getInt(1),rs.getInt(3), rs.getInt(4), rs.getInt(5)));
        }
        return  bookingArrayList;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "brugerId=" + brugerId +
                ", idBooking=" + idBooking +
                ", seats=" + seats +
                ", idKunde=" + idKunde +
                ", idVisning=" + idVisning +
                '}';
    }

    /*TEST:
    * Skal kunne opdatere en eksisterende booking i databasen
    * */
    public static void updateBooking(Booking booking, boolean isDelete) throws Exception {
        con = dbConn.getInstance().createConnection();
        Statement s = con.createStatement();           // idBruger, Seats, idKunder, idVisning
        if(isDelete){
            PreparedStatement stmt = con.prepareStatement("DELETE FROM Booking WHERE idBooking = ?");
            stmt.setInt(1, booking.getIdBooking());
            stmt.executeUpdate();
        } else {
            PreparedStatement stmt = con.prepareStatement("UPDATE Booking SET Seats = ?, idKunder = ?, idVisning = ? WHERE idBooking = ?");
            stmt.setInt(1, booking.getSeats());
            stmt.setInt(2, booking.getIdKunde());
            stmt.setInt(3, booking.getIdVisning());
            stmt.setInt(4, booking.getIdBooking());
            stmt.executeUpdate();
        }
    }

}
