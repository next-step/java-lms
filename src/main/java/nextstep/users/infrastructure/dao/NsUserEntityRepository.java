package nextstep.users.infrastructure.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.users.infrastructure.entity.NsUserEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Entity를 영속화 하는 Repository (DAO 성격에 가까움)
 */
@Repository("nsUserEntityRepository")
public class NsUserEntityRepository {

  private JdbcOperations jdbcTemplate;

  public NsUserEntityRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Optional<NsUserEntity> findByUserId(String userId) {
    String sql = "select id, user_id, password, name, email, created_at, updated_at from ns_user where user_id = ?";
    return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper(), userId));
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }


  private RowMapper<NsUserEntity> rowMapper() {
    return (rs, rowNum) -> new NsUserEntity(
        rs.getLong(1),
        rs.getString(2),
        rs.getString(3),
        rs.getString(4),
        rs.getString(5),
        toLocalDateTime(rs.getTimestamp(6)),
        toLocalDateTime(rs.getTimestamp(7)));
  }


}
