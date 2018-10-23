package kino.xp.prototype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Controller
public class LoginController {

    String error = "";

    @GetMapping("/")
    public String login(Model model) {

        model.addAttribute("bruger", new Bruger());
        model.addAttribute("error", error);

        error = "";

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Bruger bruger, HttpServletRequest request) {

        Bruger eksisterendeBruger = getBrugerFraDB(bruger.getEmail());

        if (!eksisterendeBruger.getEmail().isEmpty()) {
            if (eksisterendeBruger.getPassword().equals(bruger.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("bruger", eksisterendeBruger);
                return "redirect:/";
            }
        }
        error = "Ugyldigt login";

        return "redirect:/login";
    }

    public Bruger getBrugerFraDB(String email) {
        dbConn db = dbConn.getInstance();
        Connection con = db.createConnection();
        PreparedStatement ps = null;
        Bruger bruger = new Bruger();

        try {
            ps = con.prepareStatement("SELECT * FROM bruger WHERE Email = ?");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                try {
                    //Tjekker om email matcher bruger
                    bruger.setID(rs.getInt("idBruger"));
                    bruger.setFornavn(rs.getString("Fornavn"));
                    bruger.setEfternavn(rs.getString("Efternavn"));
                    bruger.setEmail(rs.getString("Email"));
                    bruger.setPassword(rs.getString("Kodeord"));
                    bruger.setAccess(rs.getInt("Access"));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bruger;
    }

}
