package nextstep.courses.infrastructure.persistence.dao;

import java.util.List;
import nextstep.courses.infrastructure.persistence.entity.SessionEnrollmentEntity;
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

  public Long save(SessionEnrollmentEntity sessionEnrollmentEntity) {
    SqlParameterSource parameters = new MapSqlParameterSource()
        .addValue(SESSION_ID, sessionEnrollmentEntity.getSessionId())
        .addValue(USER_ID, sessionEnrollmentEntity.getUserId())
        .addValue(CREATED_AT, sessionEnrollmentEntity.getCreatedAt())
        .addValue(UPDATED_AT, sessionEnrollmentEntity.getUpdatedAt());

    return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
  }
}
