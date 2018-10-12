package kino.xp.prototype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.jar.Attributes;

@Controller
public class HomeController {

    static Connection con;

  //  @GetMapping("/")
    //public String index(){
       // return "forside";
        //}

    @GetMapping("/opretFilm")
    public String opretFilm(){
        return "opretFilm";
    }

    @GetMapping("/billetStatistik")
    public String billetStatistik(){
        return "billetStatistik";
    }

    @GetMapping("/redigerFilm")
    public String redigerFilm(){
        return "redigerFilm";
    }

    @GetMapping("/")
    public String forside(Model model) throws SQLException {
        ArrayList<Film> films = new ArrayList<>();
        //ArrayList<Film> films = loadMovies();
        Film film = new Film("https://dqj2xt0g8bn02.cloudfront.net/cdn/farfuture/EN4wI_6eHY1xCgm8CI78pAYp0k8f_EoTB4dhR9wsbwY/mtime:1521713019/files/styles/220_315_scale_crop/public/terrifier-2017-clown-horror-film-movie-poster.png?itok=smrTw0Zp", "name", 100, 15, "bob", "horror", 100);
        Film film1 = new Film("https://www.henshaws.org.uk/wp-content/uploads/2017/01/film-016.jpg", "titel", 50, 20, "hej", "lol", 100);
        Film film2 = new Film("https://media.comicbook.com/2018/07/avengers-4-end-game-probably-not-real-1119406-1280x0.jpeg", "De fantastike ", 75, 12,"Brad pitt", "sjov", 100);
        films.add(film);
        films.add(film1);
        films.add(film2);

        model.addAttribute("films", films);

        return "forside";
    }

    public static ArrayList<Film> loadMovies() throws SQLException {
        ArrayList<Film> movies = new ArrayList<>();
        con = dbConn.getInstance().createConnection();
        Statement s = null;
        s = con.createStatement();
        ResultSet  rs = s.executeQuery("SELECT Titel, Skuespiller, Pris, Aldergrænse, FilmPlakat, Tid, Kategori FROM Film");
        while(rs.next()){
            movies.add(new Film(rs.getString(5), rs.getString(1), rs.getInt(3),rs.getInt(4),rs.getString(2), rs.getString(7),rs.getInt(6)));
        }
        return movies;
        //String url_billede, String titel, int pris, int aldersgranse, String skuespiller, String kategori, int tid
    }



}
