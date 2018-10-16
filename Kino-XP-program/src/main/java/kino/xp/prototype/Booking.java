package kino.xp.prototype;

public class Booking {
    private String fornavn;
    private String efternavn;
    private long telefon;
    private String email;
    private boolean reklamer;

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
}
