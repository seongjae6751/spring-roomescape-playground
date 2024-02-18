package roomescape;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final List<Reservation> reservations = new ArrayList<>();
    private final AtomicLong index = new AtomicLong(0);

    public List<Reservation> getAllReservations() {
        return reservations;
    }
    public Reservation addReservation(Reservation reservation) {
        Reservation newReservation = Reservation.builder()
                .id(index.incrementAndGet())
                .name(reservation.getName())
                .date(reservation.getDate())
                .time(reservation.getTime())
                .build();

        reservations.add(newReservation);

        return newReservation;
    }

    public void deleteReservation(Long id) {
        reservations.removeIf(reservation -> reservation.getId().equals(id));
    }
}
