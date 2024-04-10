package nextstep.courses.infrastructure;

import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (maximum_number_of_student, started_at, ended_at, status, amount) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                session.getMaximumNumberOfStudent(),
                session.getStartedAt(),
                session.getEndedAt(),
                session.getStatus(),
                session.getAmount()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, maximum_number_of_student, started_at, ended_at, status, amount from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new PaidSession(
                rs.getLong(1),
                rs.getLong(2),
                0,
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
