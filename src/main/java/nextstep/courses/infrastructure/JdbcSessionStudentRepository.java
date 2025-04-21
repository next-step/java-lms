package nextstep.courses.infrastructure;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcSessionStudentRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionStudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void register(Long sessionId, Long studentId) {
        String sql = "insert into session_student (session_id, student_id) values (?, ?)";
        jdbcTemplate.update(sql, sessionId, studentId);
    }

    public void unregister(Long sessionId, Long studentId) {
        String sql = "delete from session_student where session_id = ? and student_id = ?";
        jdbcTemplate.update(sql, sessionId, studentId);
    }

    public List<Long> findStudentIdsBySessionId(Long sessionId) {
        String sql = "select student_id from session_student where session_id = ?";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> rs.getLong("student_id"),
                sessionId);
    }

    public boolean isRegistered(Long sessionId, Long studentId) {
        String sql = "select count(*) from session_student where session_id = ? and student_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, sessionId, studentId);
        return count != null && count > 0;
    }

    public void deleteAllBySessionId(Long sessionId) {
        jdbcTemplate.update("delete from session_student where session_id = ?", sessionId);
    }

    public void deleteAllByStudentId(Long studentId) {
        jdbcTemplate.update("delete from session_student where student_id = ?", studentId);
    }
}