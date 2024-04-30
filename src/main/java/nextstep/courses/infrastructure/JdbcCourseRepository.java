package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Session;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Course course) {
        String sql = "INSERT INTO course (title, creator_id, created_at) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), course.getCreatedAt());
    }

    @Override
    public Course findById(Long id) {
        String sessionSql = "SELECT * FROM session WHERE course_id = ?";
        List<Session> sessions = jdbcTemplate.query(sessionSql, (rs, rowNum) ->
                new Session(
                        LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp("created_at")),
                        LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp("updated_at")),
                        null,
                        rs.getLong("fee"),
                        rs.getInt("count"),
                        rs.getString("status"),
                        LocalDateMappingUtil.toLocalDate(rs.getTimestamp("start_date")),
                        LocalDateMappingUtil.toLocalDate(rs.getTimestamp("end_date")),
                        rs.getLong("course_id"),
                        rs.getLong("id")
        ), id);

        String sql = "SELECT id, title, creator_id, created_at, updated_at FROM course WHERE id = ?";
        RowMapper<Course> rowMapper = (rs, rowNum) -> {
            Course course = new Course(
                    rs.getLong(1),
                    rs.getString(2),
                    rs.getLong(3),
                    LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp(4)),
                    LocalDateMappingUtil.toLocalDateTime(rs.getTimestamp(5))
            );
            course.getSessions().addAll(sessions);
            return course;
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
