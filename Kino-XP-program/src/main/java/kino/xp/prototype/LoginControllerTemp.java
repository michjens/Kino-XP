package kino.xp.prototype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.ArrayList;


@Controller
public class LoginControllerTemp {

    @GetMapping("/")
    public String index() {


        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam (value = "email") String email, @RequestParam(value = "password") String password, Model model)throws SQLException{
        String [] login = Utility.login(email, password);
        this.email = login[0];
        if(login[1].equals("student")){
            return("menuAdmin");
        }
        if(){
            return("menuMedarbejder");
        }
        if(login[1].equals("admin")){
            return("userList");
        }
        return"login";
    }

}
