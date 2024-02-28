package roomescape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import roomescape.domain.Reservation;
import roomescape.exception.ParamException;
import roomescape.exception.ReservationException;
import roomescape.repository.JdbcTemplateReservationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final JdbcTemplateReservationRepository jdbcTemplateReservationRepository;

    public List<Reservation> getAllReservations() {
        return jdbcTemplateReservationRepository.findAllReservation();
    }
    public Reservation addReservation(Reservation reservation) {

        if (StringUtils.isEmpty(reservation.name()) ||
            StringUtils.isEmpty(reservation.date()) ||
            StringUtils.isEmpty(reservation.time())) {
            throw new ParamException(ParamException.Type.MISSING_PARAMETER);
        }
        long generatedId = jdbcTemplateReservationRepository.insertReservation(reservation);

        Reservation newReservation = new Reservation(generatedId, reservation.name(), reservation.date(), reservation.time());

        return newReservation;
    }

    public void deleteReservation(Long id) {
        if(!jdbcTemplateReservationRepository.deleteReservation(id)) {
            throw new ReservationException(ReservationException.Type.NOT_FOUND_RESERVATION);
        }
    }
}
