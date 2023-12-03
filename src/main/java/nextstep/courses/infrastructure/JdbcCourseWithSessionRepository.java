package nextstep.courses.infrastructure;

import nextstep.courses.domain.repository.CourseWithSessionRepository;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

public class JdbcCourseWithSessionRepository implements CourseWithSessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcCourseWithSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Long courseId, Long sessionId) {
        String sql = "insert into course_with_session (course_id, session_id) values(? , ?)";
        return jdbcTemplate.update(sql, courseId, sessionId);
    }

    @Override
    public List<Integer> findByCourseId(Long courseId) {
        String sql = "select session_id from course_with_session where course_id = ?";
        return jdbcTemplate.queryForList(sql, Integer.class, courseId);
    }

    @Override
    public int findBySessionId(Long sessionId) {
        String sql = "select course_id from course_with_session where session_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, sessionId);
    }
}
