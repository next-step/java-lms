package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.utils.TimeUtils;
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
        RowMapper<Course> rowMapper = (rs, rowNum) -> new Course.Builder()
                .id(rs.getLong(1))
                .title(rs.getString(2))
                .creatorId(rs.getLong(3))
                .createdAt(TimeUtils.toLocalDateTime(rs.getTimestamp(4)))
                .updatedAt(TimeUtils.toLocalDateTime(rs.getTimestamp(5)))
                .build();
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
