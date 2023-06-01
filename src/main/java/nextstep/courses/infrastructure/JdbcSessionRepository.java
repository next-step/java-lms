package nextstep.courses.infrastructure;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionDate;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.enums.SessionState;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (start_date, end_date, cover_image_path, is_free, state, " +
                "max_capacity, course_id, created_at)" +
                " values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getStartDate(), session.getEndDate(), session.getCoverImagePath(),
                session.isFree(), session.getState(), session.getMaxCapacity(), session.getCourseId(), session.getCreatedAt());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, start_date, end_date, cover_image_path, is_free, state, max_capacity, course_id, " +
                "created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                new SessionDate(rs.getString(2), rs.getString(3)),
                rs.getString(4),
                rs.getBoolean(5),
                SessionState.of(rs.getInt(6)),
                rs.getInt(7),
                rs.getLong(8),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
