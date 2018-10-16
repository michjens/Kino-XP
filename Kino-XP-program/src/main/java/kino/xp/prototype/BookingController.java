package kino.xp.prototype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class BookingController {

    @GetMapping("/opretBooking")
    public String opretBooking(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Bruger bruger = (Bruger) session.getAttribute("bruger");
        model.addAttribute("bruger", bruger);
        model.addAttribute("booking", new Booking());
        return "opretBooking";
    }

    @PostMapping("/opretBooking")
    public String opretBooking(@ModelAttribute Booking booking) {
        Booking.opretBooking(booking);
        return "redirect:/forside";
    }


}
