package kino.xp.prototype;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "forside";
        }

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


}
