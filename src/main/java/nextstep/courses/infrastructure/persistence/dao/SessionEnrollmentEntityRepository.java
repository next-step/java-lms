package nextstep.courses.infrastructure.persistence.dao;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository("sessionEnrollmentEntityRepository")
public class SessionEnrollmentEntityRepository {

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleJdbcInsert;

  public SessionEnrollmentEntityRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName("session_enrollment")
        .usingGeneratedKeyColumns("id");
  }


  public List<Long> findUserKeyIdsBySessionId(Long sessionId) {
    String sql = "select user_id from session_enrollment where session_id = ?";
    return jdbcTemplate.queryForList(sql, Long.class, sessionId);
  }

  public Long save(Long sessionId, Long userId) {
    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("session_id", sessionId)
        .addValue("user_id", userId)
        .addValue("created_at", LocalDateTime.now());

    return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
  }
}
