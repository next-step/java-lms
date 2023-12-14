package nextstep.courses.infrastructure;

import nextstep.courses.InvalidImageFormatException;
import nextstep.courses.domain.SystemTimeStamp;
import nextstep.courses.domain.session.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("SessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (title, course_id, session_type, enrollment_status" +
                ", start_date, end_date, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql
                , session.getTitle()
                , session.getCourseId()
                , session.getSessionType().name()
                , session.getSessionPlan().getEnrollmentStatus().name()
                , session.getSessionPlan().getStartDate()
                , session.getSessionPlan().getEndDate()
                , session.getSystemTimeStamp().getCreatedAt()
                , session.getSystemTimeStamp().getUpdatedAt());
    }

    @Override
    public Session findBySessionId(Long id) {
        String sql = "select id, " +
                "       title, " +
                "       course_id, " +
                "       session_type, " +
                "       enrollment_status, " +
                "       start_date, " +
                "       end_date, " +
                "       created_at, " +
                "       updated_at " +
                "       from session " +
                "       where id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            try {
                return new Session(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        SessionType.valueOf(rs.getString(4)),
                        new SessionPlan(EnrollmentStatus.valueOf(rs.getString(5)),
                                rs.getDate(6).toLocalDate(),
                                rs.getDate(7).toLocalDate()),
                        new SystemTimeStamp(toLocalDateTime(rs.getTimestamp(8)), toLocalDateTime(rs.getTimestamp(9))));
            } catch (InvalidImageFormatException e) {
                throw new RuntimeException(e);
            }
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
