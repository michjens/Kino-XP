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

    @PostMapping("/createConsumables")
    public String opretSlik(@ModelAttribute Consumables consumables) {
        System.out.println(consumables.pris);

        consumables.gemGuf();
        System.out.println(consumables.pris);
        return "redirect:/";
    }
}
