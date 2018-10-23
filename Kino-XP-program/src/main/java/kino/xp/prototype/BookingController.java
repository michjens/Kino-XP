package kino.xp.prototype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class BookingController {


    /*TEST:
     * Skal vise opretBooking.html
     * Skal forhindre visning af html hvis bruger ikke er logget ind
     * */
    @GetMapping("/opretBooking")
    public String opretBooking(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Bruger bruger = (Bruger) session.getAttribute("bruger");
        model.addAttribute("bruger", bruger);
        model.addAttribute("booking", new Booking());
        return "opretBooking";
    }

    /*TEST:
     * Skal kunne tage imod et Booking objekt, tilknytte bruger id til det og oprette det i databasen
     * */
    @PostMapping("/opretBooking")
    public String opretBooking(@ModelAttribute Booking booking, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Bruger bruger = (Bruger) session.getAttribute("bruger");
        booking.setBrugerId(bruger.getID());
        Booking.opretBooking(booking);
        return "redirect:/";
    }

    /*TEST:
     * Skal kunne vise alle bookinger på bookingOversigt.html
     * */
    @GetMapping("/bookingOversigt")
    public String bookingOversigt(Model model) throws SQLException {
        model.addAttribute("booking", Booking.loadBooking("SELECT idBooking, idBruger, Seats, idKunder, idVisning FROM Booking"));
        return "bookingOversigt";
    }

    /*TEST:
     * Skal kunne hente en enkel booking ud fra url
     * */
    @GetMapping("/redigerBooking")
    public String redigerBooking(@RequestParam(value = "id") int id, Model model) throws Exception {
        model.addAttribute("booking", Booking.loadBooking("SELECT idBooking, idBruger, Seats, idKunder, idVisning FROM Booking WHERE idBooking = " + id).get(0));
        return "redigerBooking";
    }

    /*TEST:
     * Skal tage imod en Booking fra POST
     * Skal redigere en booking
     * Skal redirecte tilbage til bookingOversigt
     * */
    @PostMapping("/redigerBooking")
    public String redigerBooking(@ModelAttribute Booking booking) throws Exception {
        Booking.updateBooking(booking, false);
        return "redirect:/bookingOversigt";
    }

    /*TEST:
     * Skal kunne slette en booking ud fra ID
     * */
    @GetMapping("/sletBooking")
    public String sletBooking(@RequestParam(value = "id") int id, Model model) throws Exception {
        model.addAttribute("booking", Booking.loadBooking("SELECT idBooking, idBruger, Seats, idKunder, idVisning FROM Booking WHERE idBooking = " + id).get(0));
        return "sletBooking";
    }

    /*Skal kunne slette en booking after en POST*/
    @PostMapping("/sletBooking")
    public String sletBooking(@ModelAttribute Booking booking) throws Exception {
        Booking.updateBooking(booking, true);
        return "redirect:/bookingOversigt";
    }


}
