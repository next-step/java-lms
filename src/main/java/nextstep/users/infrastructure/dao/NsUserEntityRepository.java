package nextstep.users.infrastructure.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import nextstep.users.infrastructure.entity.NsUserEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Entity를 영속화 하는 Repository (DAO 성격에 가까움)
 */
@Repository("nsUserEntityRepository")
public class NsUserEntityRepository {

  private static final String USER_IDS = "userIds";
  private static final String ID = "id";
  private static final String USER_ID = "user_id";
  private static final String PASSWORD = "password";
  private static final String NAME = "name";
  private static final String EMAIL = "email";
  private static final String CREATED_AT = "created_at";
  private static final String UPDATED_AT = "updated_at";


  private final JdbcOperations jdbcTemplate;
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public NsUserEntityRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
  }

  public List<NsUserEntity> findByUserKeyIds(List<Long> userIds) {
    String sql = "select id, user_id, password, name, email, created_at, updated_at from ns_user where id in (:userIds)";
    Map<String, List<Long>> paramMap = Collections.singletonMap(USER_IDS, userIds);
    return namedParameterJdbcTemplate.query(sql, paramMap, rowMapper());
  }

  public Optional<NsUserEntity> findByUserId(String userId) {
    String sql = "select id, user_id, password, name, email, created_at, updated_at from ns_user where user_id = ?";
    try {
      return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper(), userId));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }


  private RowMapper<NsUserEntity> rowMapper() {
    return (rs, rowNum) -> {
      Long id = rs.getLong(ID);
      String userId = rs.getString(USER_ID);
      String password = rs.getString(PASSWORD);
      String name = rs.getString(NAME);
      String email = rs.getString(EMAIL);
      LocalDateTime createdAt = toLocalDateTime(rs.getTimestamp(CREATED_AT));
      LocalDateTime updatedAt = toLocalDateTime(rs.getTimestamp(UPDATED_AT));
      return new NsUserEntity(id, userId, password, name, email, createdAt, updatedAt);
    };
  }


}
