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

  private static final String SESSION_ENROLLMENT = "session_enrollment";
  private static final String ID = "id";
  private static final String SESSION_ID = "session_id";
  private static final String USER_ID = "user_id";
  private static final String CREATED_AT = "created_at";
  private static final String UPDATED_AT = "updated_at";

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleJdbcInsert;

  public SessionEnrollmentEntityRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName(SESSION_ENROLLMENT)
        .usingGeneratedKeyColumns(ID);
  }


  public List<Long> findUserKeyIdsBySessionId(Long sessionId) {
    String sql = "select user_id from session_enrollment where session_id = ?";
    return jdbcTemplate.queryForList(sql, Long.class, sessionId);
  }

  public Long save(Long sessionId, Long userId) {
    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue(SESSION_ID, sessionId)
        .addValue(USER_ID, userId)
        .addValue(CREATED_AT, LocalDateTime.now())
        .addValue(UPDATED_AT, LocalDateTime.now());

    return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
  }
}
