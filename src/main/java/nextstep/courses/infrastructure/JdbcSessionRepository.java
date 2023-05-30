package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Session> findByCourseId(Long courseId) {
        return null;
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, startAt, endAt, imageUrl, isFree, sessionStatus, maxStudents, creatorId, createdAt, updatedAt from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                toLocalDate(rs.getTimestamp(2)),
                toLocalDate(rs.getTimestamp(3)),
                rs.getString(4),
                rs.getBoolean(5),
                toSessionStatus(rs.getString(6)),
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

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

    private SessionStatus toSessionStatus(String string) {
        if (string == null) {
            return null;
        }
        return SessionStatus.getByName(string);
    }
}
