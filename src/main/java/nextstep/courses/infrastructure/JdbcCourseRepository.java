package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private static final String INSERT_SQL = "insert into course (title, term, creator_id, created_at) values(?, ?, ?, ?)";
    private static final String SELECT_SQL = "select id, term, title, creator_id, created_at, updated_at from course where id = ?";

    private JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Course course) {
        return jdbcTemplate.update(INSERT_SQL, course.getTitle(), course.term(), course.getCreatorId(), course.getCreatedAt());
    }

    @Override
    public Course findById(Long id) {
        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
                rs.getLong("id"),
                rs.getString("term"),
                rs.getString("title"),
                List.of(),
                rs.getLong("creator_id"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at")));
        return jdbcTemplate.queryForObject(SELECT_SQL, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
