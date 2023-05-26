package nextstep.courses.infrastructure.persistence.dao;

import java.util.List;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("sessionEnrollmentEntityRepository")
public class SessionEnrollmentEntityRepository {

  private final JdbcOperations jdbcTemplate;

  public SessionEnrollmentEntityRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }


  public List<String> findUserIdsBySessionId(Long sessionId) {
    String sql = "select user_id from session_enrollment where session_id = ?";
    return jdbcTemplate.queryForList(sql, String.class, sessionId);
  }
}
