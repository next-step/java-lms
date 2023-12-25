package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (id, title, image_size, image_type, image_width, image_height, " +
                "started_at, ended_at, state, session_type, capacity, price) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                session.getId(),
                session.getTitle(),
                session.getImage().getImageSize().getSize(),
                session.getImage().getImageType().getType(),
                session.getImage().getImageSize().getWidth(),
                session.getImage().getImageSize().getHeight(),
                java.sql.Date.valueOf(session.getSessionPeriod().getStartedAt()),
                java.sql.Date.valueOf(session.getSessionPeriod().getEndedAt()),
                session.getEnrollment().getSessionState().getState(),
                session.getSessionType().toString(),
                session.getEnrollment().getStudents().getCapacity(),
                session.getPrice());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, title, image_size, image_type, image_width, image_height, " +
                "started_at, ended_at, state, session_type, capacity, price " +
                "from session where id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getInt("image_size"),
                rs.getString("image_type"),
                rs.getInt("image_width"),
                rs.getInt("image_height"),
                toLocalDate(rs.getDate("started_at")),
                toLocalDate(rs.getDate("ended_at")),
                rs.getString("state"),
                rs.getString("session_type"),
                rs.getInt("capacity"),
                rs.getInt("price"));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDate toLocalDate(Date timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDate();
    }
}
