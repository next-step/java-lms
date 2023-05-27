package nextstep.courses.infrastructure;

import nextstep.courses.code.SessionStatus;
import nextstep.courses.code.SessionType;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
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
        String sql = "insert into session(course_id, generation, cover_image, `type`, status, head_count, start_at, end_at) values(?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, session.getCourse().getId(), session.getGeneration(), session.getCoverImage(), session.getType().name(), session.getStatus().name(), session.getHeadCount(), session.getStartAt(), session.getEndAt());
    }

    public Session findById(Long id) {
        String sql = "select * from session where id =?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong("id"),
                new Course(rs.getLong("course_id")).getId(),
                rs.getInt("generation"),
                rs.getString("cover_image"),
                SessionType.valueOf(rs.getString("type")),
                SessionStatus.valueOf(rs.getString("status")),
                rs.getInt("head_count"),
                toLocalDateTime(rs.getTimestamp("start_at")),
                toLocalDateTime(rs.getTimestamp("end_at"))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

}
