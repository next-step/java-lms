package nextstep.courses.infrastructure.persistence.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.ApproveStatus;
import nextstep.courses.infrastructure.persistence.entity.SessionEnrollmentEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
  private static final String APPROVE_STATUS = "approve_status";
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


  public List<SessionEnrollmentEntity> findBySessionId(Long sessionId) {
    String sql = "select id, session_id, user_id, approve_status, created_at, updated_at from session_enrollment where session_id = ?";
    return jdbcTemplate.query(sql, rowMapper(), sessionId);
  }

  private RowMapper<SessionEnrollmentEntity> rowMapper() {
    return (rs, rowNum) -> {
      Long id = rs.getLong(ID);
      Long sessionId = rs.getLong(SESSION_ID);
      Long userId = rs.getLong(USER_ID);
      String approveStatus = rs.getString(APPROVE_STATUS);
      LocalDateTime createdAt = toLocalDateTime(rs.getTimestamp(CREATED_AT));
      LocalDateTime updatedAt = toLocalDateTime(rs.getTimestamp(UPDATED_AT));
      return new SessionEnrollmentEntity(id, sessionId, userId, approveStatus, createdAt, updatedAt);
    };
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }
}
