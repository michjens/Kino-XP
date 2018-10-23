package kino.xp.prototype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class ConsumablesController {

    @GetMapping("/slikValg")
    public String slikValg(){
        return "slikValg";
    }

    @GetMapping("/createConsumables")
    public String opretSlik(Model model) {

        model.addAttribute("candy", new Consumables());

        return "createConsumables";

    }

    @PostMapping("/createConsumables")
    public String opretSlik(@ModelAttribute Consumables consumables) {

        consumables.gemGuf();
        return "redirect:/";
    }
    @GetMapping("/slik")
    public String slik(Model model) throws Exception {

        model.addAttribute("consumables", Consumables.loadConsumables("SELECT navn, pris FROM MadOgDrikke"));

        return "slik";
    }

    @PostMapping("/slik")
    public String slik(@ModelAttribute String sale, String Consumables) {


        return "consumablesOverview";
    }

    @GetMapping("/slikSalg")
    public String slikSalg(Model model){
        model.addAttribute("slik", altSilk());
        return "slikSalg";
    }

    @PostMapping("/slikSalg")
    public String slikSalg(){

        return "redirect:/";
    }

    public static ArrayList<Consumables> altSilk(){
        ArrayList<Consumables> altSilk = new ArrayList<>();

        Connection conn = dbConn.getInstance().createConnection();

        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM MadOgDrikke");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Consumables silk = new Consumables();

                silk.setId(rs.getInt("id"));
                silk.setNavn(rs.getString("navn"));
                silk.setPris(rs.getDouble("pris"));

                altSilk.add(silk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return altSilk;
    }
}
