package nextstep.courses.infrastructure.jdbc;

import java.sql.Timestamp;
import java.util.List;
import nextstep.courses.infrastructure.entity.RegistrationEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class RegistrationJdbcDao {
    private final JdbcOperations jdbcTemplate;

    public RegistrationJdbcDao(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(RegistrationEntity entity) {
        String sql = "insert into registration (session_id, student_id, enrolled_at) values(?, ?, ?)";

        return jdbcTemplate.update(sql,
            entity.getSessionId(),
            entity.getStudentId(),
            Timestamp.valueOf(entity.getEnrolledAt())
        );
    }

    public RegistrationEntity findById(Long id) {
        String sql = "select id, session_id, student_id, enrolled_at from registration where id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper(), id);
    }

    public List<RegistrationEntity> findBySessionId(Long sessionId) {
        String sql = "select id, session_id, student_id, enrolled_at from registration where session_id = ?";
        return jdbcTemplate.query(sql, rowMapper(), sessionId);
    }

    public int countBySessionId(Long sessionId) {
        String sql = "select count(*) from registration where session_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, sessionId);
    }

    private RowMapper<RegistrationEntity> rowMapper() {
        return (rs, rowNum) -> new RegistrationEntity(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getLong("student_id"),
            rs.getTimestamp("enrolled_at").toLocalDateTime()
        );
    }

  public List<RegistrationEntity> findByApprovedBySessionId(Long id) {
    String sql = "select id, session_id, student_id, enrolled_at from registration where id = ? and approved = true";
    return jdbcTemplate.query(sql, rowMapper(), id, true);
  }

  public RegistrationEntity findBySessionIdAndUserId(Long sessionId, Long nsUserId) {
    String sql = "select id, session_id, student_id, enrolled_at from registration where session_id = ? and student_id = ?";
    return jdbcTemplate.queryForObject(sql, rowMapper(), sessionId, nsUserId);
  }

  public void deleteBySessionIdAndUserId(Long sessionId, Long studentId) {
    String sql = "delete from registration where session_id = ? and student_id = ?";
    jdbcTemplate.update(sql, sessionId, studentId);
  }
}