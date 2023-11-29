package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.ThumbnailRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;
    private final ThumbnailRepository thumbnailRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate,
                                 ThumbnailRepository thumbnailRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.thumbnailRepository = thumbnailRepository;
    }


    @Override
    public Session findById(long sessionId) {
        String sql = "select id, course_id, title, start_at, end_at, session_status, enrollment_type, " +
                "enrollment_status, capacity, amount" +
                "created_at, " +
                "updated_at " +
                "from session " +
                "where " +
                "session_id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                thumbnailRepository.findBySessionId(rs.getLong(1)),
                toLocalDate(rs.getTimestamp(4)),
                toLocalDate(rs.getTimestamp(5)),
                rs.getString(6),
                new Enrollment(rs.getString(7),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getLong(10)),
                toLocalDateTime(rs.getTimestamp(11)),
                toLocalDateTime(rs.getTimestamp(12)));

        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }
}
