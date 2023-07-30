package nextstep.courses.infrastructure;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.RegistrationRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class JdbcRegistrationRepository implements RegistrationRepository {

  private final JdbcOperations jdbcTemplate;

  public JdbcRegistrationRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<Registration> findById(Long id) {
    String sql = "select id, canceled"
        + ", ns_user_id, session_id"
        + ", creator_id, created_at, updated_at "
        + "from registration "
        + "where id = ?";
    RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
        rs.getLong(1),
        rs.getBoolean(2),
        rs.getLong(3),
        rs.getLong(4),
        rs.getLong(5),
        toLocalDateTime(rs.getTimestamp(6)),
        toLocalDateTime(rs.getTimestamp(7)));
    return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
  }

  @Override
  public List<Registration> findBySessionId(Long sessionId) {
    String sql = "select id, canceled"
        + ", ns_user_id, session_id"
        + ", creator_id, created_at, updated_at "
        + "from registration "
        + "where session_id = ?";
    RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
        rs.getLong(1),
        rs.getBoolean(2),
        rs.getLong(3),
        rs.getLong(4),
        rs.getLong(5),
        toLocalDateTime(rs.getTimestamp(6)),
        toLocalDateTime(rs.getTimestamp(7)));
    return jdbcTemplate.query(sql, rowMapper, sessionId);
  }

  @Override
  public Long save(Registration registration) {
    if (registration.getId() != null && findById(registration.getId()).isPresent()) {
      String sql = "update registration set canceled = ?, updated_at = ? "
          + "where id = ?";
      jdbcTemplate
          .update(sql, registration.isCanceled(), LocalDateTime.now(), registration.getId());
      return registration.getId();
    }

    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "insert into registration (canceled"
        + ", ns_user_id, session_id"
        + ", creator_id, created_at, updated_at) "
        + "values(?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
      ps.setBoolean(1, registration.isCanceled());
      ps.setLong(2, registration.getNsUserId());
      ps.setLong(3, registration.getSessionId());
      ps.setLong(4, registration.getCreatorId());
      ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
      ps.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
      return ps;
    }, keyHolder);

    return getId(keyHolder);
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }

  private Long getId(KeyHolder keyHolder) {
    return keyHolder.getKey().longValue();
  }
}
