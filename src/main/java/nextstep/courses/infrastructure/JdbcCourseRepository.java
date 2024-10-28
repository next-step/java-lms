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
    private JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Course course) {
        String sql = "insert into course (title, term, creator_id, created_at, updated_at) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, course.getTitle(), course.getTerm(), course.getCreatorId(), course.getDateDomain().getCreatedAt(), course.getDateDomain().getUpdatedAt());
    }

    @Override
    public Course findById(Long id) {
        String sql = "select id, title, term, creator_id, created_at, updated_at from course where id = ?";
        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                rs.getLong(4),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Course> findAll() {
        String sql = "select id, title, term, creator_id, created_at, updated_at from course";
        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getLong("term"),
                rs.getLong("creator_id"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at"))
        );
        return jdbcTemplate.query(sql, rowMapper);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
