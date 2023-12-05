package nextstep.courses.infrastructure;

import nextstep.courses.domain.repository.CourseSession;
import nextstep.courses.domain.repository.CourseSessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("courseSessionRepository")
public class JdbcCourseSessionRepository implements CourseSessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcCourseSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public int save(CourseSession courseSession) {
        String sql = "insert into course_session (course_id, session_id) values(? , ?)";
        return jdbcTemplate.update(sql, courseSession.courseId(), courseSession.sessionId());
    }

    @Override
    public List<Integer> findByCourseId(Long courseId) {
        String sql = "select session_id from course_session where course_id = ?";
        return jdbcTemplate.queryForList(sql, Integer.class, courseId);
    }

    @Override
    public int findBySessionId(Long sessionId) {
        String sql = "select course_id from course_session where session_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, sessionId);
    }
}
