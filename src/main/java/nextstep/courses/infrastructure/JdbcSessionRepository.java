package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.repository.SessionRepository;
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
        String sql = "insert into session (start_at, end_at, session_type, session_status, maximum_enrollment_count, price) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.start_at(), session.getEndAt(), session.getSessionType(), session.getSessionStatus(), session.getMaximumEnrollmentCount(), session.getPrice());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, start_at, end_at, session_type, session_open_status, session_progress_status, maximum_enrollment_count, price from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                toLocalDateTime(rs.getTimestamp(2)),
                toLocalDateTime(rs.getTimestamp(3)),
                SessionType.valueOf(rs.getString(4)),
                SessionOpenStatus.valueOf(rs.getString(5)),
                SessionProgressStatus.valueOf(rs.getString(6)),
                rs.getInt(7),
                rs.getInt(8),
                null);
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
