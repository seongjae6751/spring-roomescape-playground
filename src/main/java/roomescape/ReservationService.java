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
    private final JdbcTemplateReservationRepository jdbcTemplateReservationRepository;

    public List<Reservation> getAllReservations() {
        return jdbcTemplateReservationRepository.findAll();
    }
    public Reservation addReservation(Reservation reservation) {

        if (StringUtils.isEmpty(reservation.getName()) || StringUtils.isEmpty(reservation.getDate()) || StringUtils.isEmpty(reservation.getTime())) {
            throw new ParamException(ParamException.Type.MISSING_PARAMETER);
        }

        long generatedId = jdbcTemplateReservationRepository.insertAndGetGeneratedKey(reservation);

        Reservation newReservation = Reservation.builder()
                .id(generatedId)
                .name(reservation.getName())
                .date(reservation.getDate())
                .time(reservation.getTime())
                .build();

        return newReservation;
    }

    public void deleteReservation(Long id) {
    if(!jdbcTemplateReservationRepository.delete(id)) throw new ReservationException(ReservationException.Type.NOT_FOUND_RESERVATION);
    }
}
