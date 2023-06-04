package nextstep.courses.infrastructure;

import nextstep.courses.domain.registration.EnrollmentOpenType;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionCostType;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionState;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static nextstep.courses.util.LocalDateTimeUtil.toLocalDateTime;

@Repository
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (course_id, title, cover, cardinal_number, session_cost_type, registration_open_type, session_state, max_user, start_date, end_date) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getCourseId(), session.getTitle(), session.getCover(), session.getCardinalNumber()
                , session.getSessionCostType().getCode(), session.getSessionEnrollment().getRegistrationOpenType().getCode()
                , session.getSessionEnrollment().getSessionState().getCode(), session.getSessionEnrollment().getMaxUser()
                , session.getSessionPeriod().getStartDate(), session.getSessionPeriod().getEndDate());
    }

    @Override
    public Optional<Session> findById(long id) {
        String sql = "select id, course_id, title, cover, cardinal_number, session_cost_type, registration_open_type, session_state, max_user, start_date, end_date from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> Session.of(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                SessionCostType.valueOf(rs.getString(6)),
                EnrollmentOpenType.valueOf(rs.getString(7)),
                SessionState.valueOf(rs.getString(8)),
                rs.getInt(9),
                toLocalDateTime(rs.getTimestamp(10)),
                toLocalDateTime(rs.getTimestamp(11)));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

}
