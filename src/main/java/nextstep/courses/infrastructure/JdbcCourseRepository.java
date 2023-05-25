package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.sql.DataSource;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {

    private final JdbcOperations jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("COURSE")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public long save(Course course) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(course);

        return jdbcInsert.executeAndReturnKey(params).longValue();
    }

    @Override
    public Course findById(Long id) {
        String sql = "select id, title, creator_id, created_at, updated_at from course where id = ?";
        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
            rs.getLong(1),
            rs.getString(2),
            rs.getLong(3),
            toLocalDateTime(rs.getTimestamp(4)),
            toLocalDateTime(rs.getTimestamp(5)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
