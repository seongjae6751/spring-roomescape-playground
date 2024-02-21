package roomescape;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import roomescape.Exception.ParamException;
import roomescape.Exception.ReservationException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final List<Reservation> reservations = new ArrayList<>();
    private final AtomicLong index = new AtomicLong(0);
    private final JdbcTemplateReservationRepository jdbcTemplateReservationRepository;

    public List<Reservation> getAllReservations() {
        return jdbcTemplateReservationRepository.findAll();
    }
    public Reservation addReservation(Reservation reservation) {

        if (StringUtils.isEmpty(reservation.getName()) || StringUtils.isEmpty(reservation.getDate()) || StringUtils.isEmpty(reservation.getTime())) {
            throw new ParamException(ParamException.Type.MISSING_PARAMETER);
        }

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
        // 삭제할 예약이 없을 때 예외처리
        if (reservations.stream().noneMatch(reservation -> reservation.getId().equals(id))) {
            throw new ReservationException(ReservationException.Type.NOT_FOUND_RESERVATION);
        }

        reservations.removeIf(reservation -> reservation.getId().equals(id));
    }
}
