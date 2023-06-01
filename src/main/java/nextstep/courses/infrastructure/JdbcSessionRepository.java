package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.Status;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final int INDEX_STARTED_AT = 0;
    private static final int INDEX_ENDED_AT = 1;

    @Override
    public int save(Long courseId, Session session) {
        List<LocalDateTime> terms = session.terms();

        String sql = "insert into session (course_id, is_free, status, current_students, max_students, started_at, ended_at) values(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, courseId, session.isFree(), session.statusToString(), session.currentStudents(), session.maxStudents(), terms.get(INDEX_STARTED_AT), terms.get(INDEX_ENDED_AT));
    }

    @Override
    public Session findById(int id) {
        String sql = "select id, course_id, cover_image_url, is_free, status, current_students, max_students, started_at, ended_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getBoolean(4),
                Status.toStatus(rs.getString(5)),
                rs.getInt(6),
                rs.getInt(7),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Session> findByCourseId(Long id) {
        String sql = "select id, course_id, cover_image_url, is_free, status, current_students, max_students, started_at, ended_at from session where course_id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getBoolean(4),
                Status.toStatus(rs.getString(5)),
                rs.getInt(6),
                rs.getInt(7),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)));
        return jdbcTemplate.query(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
