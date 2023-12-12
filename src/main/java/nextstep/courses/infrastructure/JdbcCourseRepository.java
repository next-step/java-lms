package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.course.session.SessionRepository;
import nextstep.courses.domain.course.session.Sessions;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private final JdbcOperations jdbcTemplate;
    private final SessionRepository sessionRepository;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Override
    public int save(Course course) {
        String sql = "insert into course " +
                "(title, ordering, creator_id, created_at) " +
                "values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, course.getTitle(), course.getOrdering(),
                course.getCreatorId(), course.getCreatedAt());
    }

    @Override
    public Course findById(Long id) {
        String sql = "select " +
                "id, title, ordering, creator_id, created_at, updated_at " +
                "from course where id = ?";
        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
                rs.getLong(1),
                rs.getString(2),
                rs.getInt(3),
                findAllByCourseId(rs.getLong(1)),
                rs.getLong(4),
                rs.getTimestamp(5).toLocalDateTime(),
                toLocalDateTime(rs.getTimestamp(6)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private Sessions findAllByCourseId(Long id) {
        return this.sessionRepository.findAllByCourseId(id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
