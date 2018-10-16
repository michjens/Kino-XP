package kino.xp.prototype;


import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Film {
    private String url_billede;
    private String titel;
    private int pris;
    private int sal;
    private String aldersgranse;
    private String skuespiller;
    private String kategori;
    private int tid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private ArrayList<Date> dates = new ArrayList<>();
    static Connection con;

    public Film(String url_billede, String titel, int pris, String aldersgranse, String skuespiller, String kategori, ArrayList<Date> dates) {
        this.url_billede = url_billede;
        this.titel = titel;
        this.pris = pris;
        this.aldersgranse = aldersgranse;
        this.skuespiller = skuespiller;
        this.kategori = kategori;
        this.dates = dates;
    }

    public Film(String url_billede, String titel, int pris, String aldersgranse, String skuespiller, String kategori, int tid) {
        this.url_billede = url_billede;
        this.titel = titel;
        this.pris = pris;
        this.aldersgranse = aldersgranse;
        this.skuespiller = skuespiller;
        this.kategori = kategori;
        this.tid = tid;
    }

    public Film(String titel, int pris, String aldersgranse, String skuespiller) {
        this.titel = titel;
        this.pris = pris;
        this.aldersgranse = aldersgranse;
        this.skuespiller = skuespiller;
    }

    public String getUrl_billede() {
        return url_billede;
    }

    public void setUrl_billede(String url_billede) {
        this.url_billede = url_billede;
    }

    public Film() {
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public int getSal() {
        return sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public String getAldersgranse() {
        return aldersgranse;
    }

    public void setAldersgranse(String aldersgranse) {
        this.aldersgranse = aldersgranse;
    }

    public String getSkuespiller() {
        return skuespiller;
    }

    public void setSkuespiller(String skuespiller) {
        this.skuespiller = skuespiller;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public ArrayList<Date> getDates() {
        return dates;
    }

    public void setDates(ArrayList<Date> dates) {
        this.dates = dates;
    }

    public static void deleteFilm(String inputEmail) throws SQLException {

        con = dbConn.getInstance().createConnection();
        PreparedStatement stmtFilm = con.prepareStatement("DELETE FROM Film WHERE idFilm = (?)");
        stmtFilm.setString(1, inputEmail);
        stmtFilm.executeUpdate();

    }

    public static void createFilm(Film film) {
        con = dbConn.getInstance().createConnection();
        //Statement s = null;
        try {
            //s = con.createStatement();
            PreparedStatement stmtFilm = con.prepareStatement("INSERT INTO KinoXP.Film (titel, Skuespiller, Pris, Aldersgrænse, Tid, FilmPlakat, Kategori) VALUES (?,?,?,?,?,?,?)");
            stmtFilm.setString(1, film.getTitel());
            stmtFilm.setString(2, film.getSkuespiller());
            stmtFilm.setInt(3, film.getPris());
            stmtFilm.setString(4, film.getAldersgranse());
            stmtFilm.setInt(5, film.getTid());
            stmtFilm.setString(6, film.getUrl_billede());
            stmtFilm.setString(7, film.getKategori());
            stmtFilm.executeUpdate();


        } catch (SQLException e) {

        }

    }

    public static Film loadEditFilm(String titel){
        con = dbConn.getInstance().createConnection();
        ResultSet rs;
        String selectSQL = "SELECT * FROM KinoXP.Film WHERE Titel = ?";
        Film f = new Film();
        try {
            PreparedStatement stmt = con.prepareStatement(selectSQL);
            stmt.setString(1, titel);
            rs = stmt.executeQuery();
            while(rs.next()){
                f.setId(rs.getInt("idFilm"));
                System.out.println(f.getId());
                f.setTitel(titel);
                f.setSkuespiller(rs.getString("Skuespiller"));
                f.setPris(rs.getInt("Pris"));
                f.setAldersgranse(rs.getString("Aldersgrænse"));
                f.setTid(rs.getInt("Tid"));
                f.setUrl_billede(rs.getString("FilmPlakat"));
                f.setKategori(rs.getString("Kategori"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;
    }
    public static void editFilm(Film film) throws SQLException {
        con = dbConn.getInstance().createConnection();
        Statement s = con.createStatement();
        System.out.println(film.getId());
        //s.executeQuery("SET SQL_SAFE_UPDATES=0");
        PreparedStatement stmt = con.prepareStatement("UPDATE KinoXP.Film SET titel = ?, Skuespiller = ?, Pris = ?, Aldersgrænse = ?, Tid = ?, FilmPlakat = ?, Kategori = ? WHERE idFilm = ?");
        //s.executeQuery("SET SQL_SAFE_UPDATES=1");
        //PreparedStatement stmt = con.prepareStatement("INSERT INTO KinoXP.Film (titel, Skuespiller, Pris, Aldersgrænse, Tid, FilmPlakat, Kategori) VALUES (?,?,?,?,?,?,?)");
        stmt.setString(1, film.getTitel());
        stmt.setString(2, film.getSkuespiller());
        stmt.setInt(3, film.getPris());
        stmt.setString(4, film.getAldersgranse());
        stmt.setInt(5, film.getTid());
        stmt.setString(6, film.getUrl_billede());
        stmt.setString(7, film.getKategori());
        stmt.setInt(8, film.getId());
        stmt.executeUpdate();
    }
}


