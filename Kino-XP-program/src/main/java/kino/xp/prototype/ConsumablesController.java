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

    @GetMapping("/consumablesSales")
    public String opretSlikSalg(Model model) throws Exception {

        model.addAttribute("sale", new Consumables());

        return "consumablesSales";
    }

    @PostMapping("/consumablesSales")
    public String opretSlikSalg(@ModelAttribute String sale, String Consumables) {

        return "consumablesOverview";
    }
}
