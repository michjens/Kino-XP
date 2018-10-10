package kino.xp.prototype;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class Film {
    private String url_billede;
    private String titel;
    private int pris;
    private int sal;
    private int aldersgranse;
    private String skuespiller;
    private String kategori;
    private ArrayList<Date> dates = new ArrayList<>();

    public Film(String url_billede,String titel, int pris, int sal, int aldersgranse, String skuespiller, String kategori, ArrayList<Date> dates) {
        this.url_billede = url_billede;
        this.titel = titel;
        this.pris = pris;
        this.sal = sal;
        this.aldersgranse = aldersgranse;
        this.skuespiller = skuespiller;
        this.kategori = kategori;
        this.dates = dates;
    }

    public String getUrl_billede() {
        return url_billede;
    }

    public void setUrl_billede(String url_billede) {
        this.url_billede = url_billede;
    }

    public Film() {
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

    public int getAldersgranse() {
        return aldersgranse;
    }

    public void setAldersgranse(int aldersgranse) {
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
        Connection con;
        con = dbConn.getInstance().createConnection();
        PreparedStatement stmtFilm = con.prepareStatement("DELETE FROM Film WHERE idFilm = (?)");
        stmtFilm.setString(1, inputEmail);
        stmtFilm.executeUpdate();

    }
}


