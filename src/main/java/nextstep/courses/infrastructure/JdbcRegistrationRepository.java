package nextstep.courses.infrastructure;

import nextstep.courses.domain.Registration;
import nextstep.courses.domain.RegistrationRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class JdbcRegistrationRepository implements RegistrationRepository {
  private JdbcOperations jdbcTemplate;

  public JdbcRegistrationRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  String sql = "INSERT INTO registration(session_id, user_id) VALUES(?, ?)";
  @Override
  public int save(Registration registration) {
    return jdbcTemplate.update(sql, registration.sessionId(), registration.userId());
  }

  @Override
  public Registration findById(Long id) {
    String sql = "SELECT * FROM registration WHERE id = ?";
    RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getLong("user_id")
    );

    return jdbcTemplate.queryForObject(sql, rowMapper, id);
  }

  @Override
  public List<Registration> findByUserId(Long userId) {
    return null;
  }

  @Override
  public List<Registration> findBySessionId(Long sessionId) {
    return null;
  }
}
