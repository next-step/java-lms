package nextstep.sessions.infrastructure;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(final JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(final Session session) {
        final String sql = "insert into session (max_enrollment, session_recruiting_status, session_price, started_at, ended_at, course_id) values (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getMaxEnrollment(), session.getSessionStatus(),
                session.getSessionPrice(), session.getSessionStartDate(), session.getSessionEndDate(),
                session.getCourse());
    }
}
