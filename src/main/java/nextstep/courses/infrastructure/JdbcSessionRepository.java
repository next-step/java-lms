package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.enums.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static nextstep.courses.utils.DateUtil.toLocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final CourseRepository courseRepository;
    private final SessionCoverRepository sessionCoverRepository;
    private final RegistrationRepository registrationRepository;
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(CourseRepository courseRepository, SessionCoverRepository sessionCoverRepository, RegistrationRepository registrationRepository, JdbcOperations jdbcTemplate) {
        this.courseRepository = courseRepository;
        this.sessionCoverRepository = sessionCoverRepository;
        this.registrationRepository = registrationRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (begin_dt, end_dt, session_status, capacity, price, course_id, session_cover_id, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?, ? ,?)";
        return jdbcTemplate.update(sql, session.period().getBeginDt(), session.period().getBeginDt(), session.status(), session.capacity().capacity(),
                session.price().price(), session.course().id(), session.sessionCover().id(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select s.id, s.begin_dt, s.nd_dt, s.session_status, s.capacity, s.price, " +
                "s.course_id, s.session_cover_id, s.created_at, s.updated_at" +
                "from session s where s.id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                toLocalDateTime(rs.getTimestamp(2)),
                toLocalDateTime(rs.getTimestamp(3)),
                SessionStatus.valueOf(rs.getString(4)),
                new Capacity(rs.getInt(5)),
                new Price(rs.getInt(6)),
                courseRepository.findById(rs.getLong(7)),
                sessionCoverRepository.findById(rs.getLong(8)),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10)),
                registrationRepository.findParticipantsBySessionId(rs.getLong(1))
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
