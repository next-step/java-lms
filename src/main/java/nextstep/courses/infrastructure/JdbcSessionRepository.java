package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("SessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;
    private final CourseRepository courseRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, CourseRepository courseRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.courseRepository = courseRepository;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (course_id, name, type, status, image_name, image_path, " +
                "capacity_number, capacity_number_max, created_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getCourse().getId(), session.getName(), session.getType().toString(),
                session.getStatus().toString(), session.getImage().getName(), session.getImage().getPath(),
                session.getCapacity().getNumber(), session.getCapacity().getNumberMax(), session.getCreatedAt());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, name, type, status, image_name, image_path, " +
                "capacity_number, capacity_number_max, created_at, updated_at from session where id = ?";
        return jdbcTemplate.queryForObject(sql, getSessionRowMapper(), id);
    }

    private RowMapper<Session> getSessionRowMapper() {
        return (rs, rowNum) -> {
            Course course = courseRepository.findById(rs.getLong("course_id"));
            return new Session(
                    rs.getLong(1),
                    course,
                    new SessionImage(rs.getString(6), rs.getString(7)),
                    new SessionCapacity(rs.getInt(8), rs.getInt(9)),
                    rs.getString(3),
                    SessionType.from(rs.getString(4)),
                    SessionStatus.from(rs.getString(5)),
                    toLocalDateTime(rs.getTimestamp(10)),
                    toLocalDateTime(rs.getTimestamp(11)));
        };
    }

    @Override
    public int update(Session session) {
        String sql = "UPDATE session SET name = ?, type = ?, status = ?, image_name = ?, image_path = ?, " +
                "capacity_number = ?, capacity_number_max = ?, updated_at = ?, course_id = ? WHERE id = ?";
        return jdbcTemplate.update(sql, session.getName(), session.getType().toString(), session.getStatus().toString(),
                session.getImage().getName(), session.getImage().getPath(), session.getCapacity().getNumber(),
                session.getCapacity().getNumberMax(), session.getCreatedAt(), session.getCourse().getId(),
                session.getId());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
