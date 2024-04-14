package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (fee, count, status, course_id, start_date, end_date, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                session.getEnrollmentManager().getFee(),
                session.getEnrollmentManager().getCount(),
                session.getEnrollmentManager().getStatus(),
                session.getCourseId(),
                session.getSessionPeriod().getStartDate(),
                session.getSessionPeriod().getEndDate(),
                session.getCreatedAt(),
                session.getUpdatedAt()
                );
    }

    @Override
    public Optional<Session> findById(Long sessionId) {
        String sql = "select id, fee, count, status, course_id, start_date, end_date, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)),
                new EnrollmentManager(rs.getLong(2), rs.getInt(3), rs.getString(4)),
                new SessionPeriod(toLocalDate(rs.getTimestamp(6)), toLocalDate(rs.getTimestamp(7))),
                rs.getLong(5),
                rs.getLong(1)
                );
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, sessionId));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    private LocalDate toLocalDate(Timestamp timestamp){
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
