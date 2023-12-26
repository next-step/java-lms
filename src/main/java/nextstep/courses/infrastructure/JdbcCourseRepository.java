package nextstep.courses.infrastructure;

import static nextstep.courses.domain.CourseBuilder.aCourse;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Course course) {
        String sql = "insert into course (title, creator_id, created_at) values(?, ?, ?)";
        return jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), course.getCreatedAt());
    }

    @Override
    public Course findById(Long id) {
        String sql = "select id, title, creator_id, created_at, updated_at from course where id = ?";
        RowMapper<Course> rowMapper = (rs, rowNum) ->
                aCourse()
                        .withId(rs.getLong("id"))
                        .withTitle(rs.getString("title"))
                        .withCreatorId(rs.getLong("creator_id"))
                        .withCreatedAt(toLocalDateTime(rs.getTimestamp("created_at")))
                        .withUpdatedAt(toLocalDateTime(rs.getTimestamp("updated_at")))
                        .build();
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
