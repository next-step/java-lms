package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Course;
import nextstep.courses.domain.repository.CourseRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
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
        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getLong("creator_id"),
                rs.getTimestamp("created_at"),
                rs.getTimestamp("updated_at"));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

}
