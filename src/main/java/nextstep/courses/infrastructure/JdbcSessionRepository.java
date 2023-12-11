package nextstep.courses.infrastructure;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.AttendeeRepository;
import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.image.Images;
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

    private final ImageRepository imageRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate,
                                 AttendeeRepository attendeeRepository,
                                 ImageRepository imageRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.attendeeRepository = attendeeRepository;
        this.imageRepository = imageRepository;
    }

    @Override
    public Optional<EnrollmentSession> findBySessionId(Long sessionId) {
        String sql = "select id, course_id, session_status, session_type, start_at, end_at, recruitment, amount, max_capacity" +
                " from new_session" +
                " where id = ?";
        RowMapper<EnrollmentSession> rowMapper = (rs, rowNum) -> {
            List<Attendee> attendees = attendeeRepository.findAllBySessionId(sessionId);
            List<Image> images = imageRepository.findAllBySessionId(sessionId);
            return new EnrollmentSession(
                    rs.getLong(1),
                    new SessionInformation(SessionStatus.valueOf(rs.getString(3)),
                                           new Period(from(rs.getTimestamp(5)), from(rs.getTimestamp(6))),
                                           new Images(images),
                                           Recruitment.valueOf(rs.getString(7))),
                    EnrollmentFactory.create(SessionType.valueOf(rs.getString(4)), attendees, rs.getLong(8), rs.getInt(9)));
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
