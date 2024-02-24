package roomescape;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("")
    public ResponseEntity<List<Reservation>> showReservationList() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @PostMapping("")
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        Reservation newReservation = reservationService.addReservation(reservation);
        return ResponseEntity.created(URI.create("/reservations/" + newReservation.id())).body(newReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservations(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
