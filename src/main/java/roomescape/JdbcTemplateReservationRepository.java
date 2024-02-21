package roomescape;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class JdbcTemplateReservationRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateReservationRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Reservation> findAll() {
        return jdbcTemplate.query("select * from reservation", reservationRowMapper);
    }

    private final RowMapper<Reservation> reservationRowMapper = (rs, rowNum) -> {
            Reservation reservation = new Reservation(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("date"),
                    rs.getString("time")
            );
            return reservation;
    };
}
