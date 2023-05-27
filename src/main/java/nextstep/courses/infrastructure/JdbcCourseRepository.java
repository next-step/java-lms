package nextstep.courses.infrastructure;

import nextstep.common.domain.Image;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseId;
import nextstep.courses.domain.CourseRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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
        String sql = "select * from course where course_id = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper(), courseId));
        } catch (EmptyResultDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> findAll() {
        return jdbcTemplate.query("SELECT * from course", rowMapper());
    }

    @Override
    public void deleteAll() {
        //jdbcTemplate.update("delete from course");
        jdbcTemplate.update("TRUNCATE table course");
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    private RowMapper<Course> rowMapper() {
        return (resultSet, rowNumber) -> {
            return new Course(
                    new CourseId(resultSet.getLong("course_id")),
                    resultSet.getString("title"),
                    resultSet.getLong("creator_id"),
                    null,
                    toLocalDateTime(resultSet.getTimestamp("created_at")),
                    toLocalDateTime(resultSet.getTimestamp("updated_at"))
            );
        };
    }
}
