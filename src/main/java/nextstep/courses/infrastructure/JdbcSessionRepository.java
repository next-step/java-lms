package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (cover_image, payment_type, state, max_capacity, start_date, end_date) "
                + "values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                session.getCoverImage(),
                session.getPaymentType(),
                session.getState(),
                session.getMaxCapacityOfStudents(),
                session.getStartDate(),
                session.getEndDate());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select cover_image, payment_type, state, max_capacity, start_date, end_date from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                new Image(rs.getString(1)),
                PaymentType.valueOf(rs.getString(2)),
                SessionState.valueOf(rs.getString(3)),
                rs.getInt(4),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(5)), toLocalDateTime(rs.getTimestamp(6))));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }


    private LocalDate toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
