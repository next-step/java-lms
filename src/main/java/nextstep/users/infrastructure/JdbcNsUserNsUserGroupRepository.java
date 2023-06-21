package nextstep.users.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import nextstep.users.domain.NsUserNsUserGroup;
import nextstep.users.domain.NsUserNsUserGroupRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("nsUserNsUserGroupRepository")
public class JdbcNsUserNsUserGroupRepository implements NsUserNsUserGroupRepository {

  private JdbcOperations jdbcTemplate;

  public JdbcNsUserNsUserGroupRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<NsUserNsUserGroup> findByNsUserId(Long nsUserId) {
    String sql = "select id, ns_user_id, ns_user_group_id from ns_user_ns_user_group where ns_user_id = ?";

    return jdbcTemplate.query(sql,
        (rs, rowNum) -> new NsUserNsUserGroup(rs.getLong(1), rs.getLong(2), rs.getLong(3)),
        nsUserId);
  }
}
