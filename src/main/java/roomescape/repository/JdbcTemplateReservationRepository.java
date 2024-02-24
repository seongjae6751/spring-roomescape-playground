package roomescape.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
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

    public Long insertAndGetGeneratedKey(Reservation reservation) {
        String sql = "insert into reservation (name, date, time) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    sql, new String[]{"id"});
            ps.setString(1, reservation.name());
            ps.setString(2, reservation.date());
            ps.setString(3, reservation.time());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public boolean delete(Long id) {
        String sql = "delete from reservation where id = ?";

        // 삭제에 성공하면 1반환 실패하면 0 반환
        int isDeleted = jdbcTemplate.update(sql, Long.valueOf(id));
        return isDeleted == 1;
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
