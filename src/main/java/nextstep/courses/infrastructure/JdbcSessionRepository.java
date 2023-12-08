package nextstep.courses.infrastructure;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.AttendeeRepository;
import nextstep.courses.domain.session.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    private final AttendeeRepository attendeeRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, AttendeeRepository attendeeRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public Optional<EnrollmentSession> findBySessionId(Long sessionId) {
        String sql = "select id, course_id, session_status, session_type, start_at, end_at, amount, max_capacity" +
                " from session" +
                " where id = ?";
        RowMapper<EnrollmentSession> rowMapper = (rs, rowNum) -> {
            List<Attendee> attendees = attendeeRepository.findAllBySeesionId(sessionId);
            return new EnrollmentSession(
                    rs.getLong(1),
                    new SessionInformation(SessionStatus.valueOf(rs.getString(3)),
                                           SessionType.valueOf(rs.getString(4)),
                                           new Period(from(rs.getTimestamp(5)), from(rs.getTimestamp(6)))),
                    EnrollmentFactory.create(SessionType.valueOf(rs.getString(4)), attendees, rs.getLong(7), rs.getInt(8)));
        };

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, sessionId));
    }

    private LocalDate from(Timestamp timeStamp) {
        if (timeStamp == null) {
            return null;
        }
        return timeStamp.toLocalDateTime().toLocalDate();
    }
}
