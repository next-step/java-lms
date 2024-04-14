package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionFactory;
import nextstep.courses.domain.enrollment.engine.SessionEnrollment;
import nextstep.courses.infrastructure.engine.SessionRepository;
import nextstep.courses.infrastructure.util.LocalDateTimeConverter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import static java.time.LocalTime.now;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (course_id, type, status, capacity, fee, created_at) " +
                     "values (?, ?, ?, ?, ?, ?)";

        SessionEnrollment enrollment = session.getEnrollment();
        return jdbcTemplate.update(sql, session.getCourseId(), session.getType().get(),
                enrollment.getStatus().get(), enrollment.getCapacity().get(), enrollment.getFee().get(),
                now());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, type, status, capacity, fee, created_at, updated_at " +
                     "from session " +
                     "where id = ?";
        return jdbcTemplate.queryForObject(sql, sessionEntityRowMapper(), id);
    }

    private RowMapper<Session> sessionEntityRowMapper() {
        return (rs, rowNum) -> SessionFactory.get(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getLong(6),
                LocalDateTimeConverter.convert(rs.getTimestamp(7)),
                LocalDateTimeConverter.convert(rs.getTimestamp(8)));
    }

}
