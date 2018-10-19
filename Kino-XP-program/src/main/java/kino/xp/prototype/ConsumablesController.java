package kino.xp.prototype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConsumablesController {

    @GetMapping("/createConsumables")
    public String opretSlik(Model model) {

        model.addAttribute("candy", new Consumables());

        return "createConsumables";

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
}
