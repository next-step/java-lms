package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseId;
import nextstep.courses.domain.CourseRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course save(Course course) {
        String sql = "insert into course (title, creator_id, created_at) values(?, ?, ?)";
        int courseId = jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), course.getCreatedAt());
        return new Course(
                new CourseId((long) courseId),
                course.getTitle(),
                course.getCreatorId(),
                null,
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }

    @Override
    public Optional<Course> findById(Long courseId) {
        String sql = "select course_id, title, creator_id, created_at, updated_at from course where course_id = ?";
        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
                new CourseId( rs.getLong(1)),
                rs.getString(2),
                rs.getLong(3),
                null,
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)));
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, courseId));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
