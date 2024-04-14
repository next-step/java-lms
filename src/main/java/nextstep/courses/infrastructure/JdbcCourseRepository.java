package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private JdbcOperations jdbcTemplate;

    private final SessionRepository sessionRepository;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate, SessionRepository sessionRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public int save(Course course) {
        String sql = "insert into course (id, title, creator_id, created_at) values(?, ?, ?, ?)";
        int result = jdbcTemplate.update(sql, course.getId(), course.getTitle(), course.getCreatorId(), course.getCreatedAt());


        course.getSessions().getSessions().forEach(session -> {
            if (session instanceof PaySession) {
                sessionRepository.savePaySession((PaySession) session, course.getId());
            }
            if (session instanceof FreeSession) {
                sessionRepository.saveFreeSession((FreeSession) session, course.getId());
            }
        });

        return result;
    }

    @Override
    public Course findById(Long id) {
        String sql = "select id, title, creator_id, created_at, updated_at from course where id = ?";
        Sessions sessions = sessionRepository.findByCourseId(id);

        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)),
                sessions);

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
