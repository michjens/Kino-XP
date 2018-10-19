package kino.xp.prototype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SeatingController {

    @GetMapping("/seating")
    public String seating(Model model){
        model.addAttribute("test", "hey");
        return "seating";
    }

}
