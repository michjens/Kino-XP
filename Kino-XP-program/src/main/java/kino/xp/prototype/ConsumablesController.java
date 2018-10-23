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

    /*TEST:
    * Skal kunne vise slikValg.html
    * */
    @GetMapping("/slikValg")
    public String slikValg(){
        return "slikValg";
    }

    /*TEST:
    * Skal kunne vise createConsumables.html
    * */
    @GetMapping("/createConsumables")
    public String opretSlik(Model model) {

        model.addAttribute("candy", new Consumables());

        return "createConsumables";

    }

    /*TEST:
    * Skal kunne oprette en consumables
    * */
    @PostMapping("/createConsumables")
    public String opretSlik(@ModelAttribute Consumables consumables) {

        consumables.gemGuf();
        return "redirect:/";
    }

    /*TEST:
    * Skal kunne vise slik.html og vise en liste over alt slik
    * */
    @GetMapping("/slik")
    public String slik(Model model) throws Exception {

        model.addAttribute("consumables", Consumables.loadConsumables("SELECT navn, pris FROM MadOgDrikke"));

        return "slik";
    }

    /*TEST:
    * Skal kunne vise consumablesOverview.html
    * */
    @PostMapping("/slik")
    public String slik(@ModelAttribute String sale, String Consumables) {


        return "consumablesOverview";
    }

    /*TEST:
    * Skal kunne vise slikSalg.html og overføre alle rækker med slik til model
    * */
    @GetMapping("/slikSalg")
    public String slikSalg(Model model){
        model.addAttribute("slik", altSilk());
        return "slikSalg";
    }

    /*TEST:
    * Skal kunne oprette et salg af slik i databasen
    * */
    @PostMapping("/slikSalg")
    public String slikSalg(){

        return "redirect:/";
    }

    /*TEST:
    * Skal kunne hente alle rækker med slik fra databasen
    * */
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
