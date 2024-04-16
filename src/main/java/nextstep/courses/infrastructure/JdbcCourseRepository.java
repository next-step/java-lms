package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(final JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(final Course course) {
        final String sql = "insert into course (title, creator_id, created_at) values(?, ?, ?)";

        return jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), course.getCreatedAt());
    }

    @Override
    public Optional<Course> findById(final Long id) {
        final String sql = "select id, title, creator_id, created_at, updated_at from course where id = ?";
        final RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)));

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    private LocalDateTime toLocalDateTime(final Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }

        return timestamp.toLocalDateTime();
    }
}
