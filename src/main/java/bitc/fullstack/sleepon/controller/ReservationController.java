package bitc.fullstack.sleepon.controller;

import bitc.fullstack.sleepon.model.UserReservation;
import bitc.fullstack.sleepon.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public void createReservation(@RequestBody UserReservation reservation) {
        reservationService.saveReservation(reservation);
    }
}
