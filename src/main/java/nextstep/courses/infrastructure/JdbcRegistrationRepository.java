package nextstep.courses.infrastructure;

import nextstep.courses.domain.Registration;
import nextstep.courses.domain.RegistrationRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("registrationRepository")
public class JdbcRegistrationRepository implements RegistrationRepository {
  private static final String INSERT_SQL = "INSERT INTO registration(session_id, user_id) VALUES(?, ?)";
  private static final String SELECT_BY_ID_SQL = "SELECT * FROM registration WHERE id = ?";
  private static final String SELECT_BY_USER_ID_SQL = "SELECT * FROM registration WHERE user_id = ?";
  private static final String SELECT_BY_SESSION_ID_SQL = "SELECT * FROM registration WHERE session_id = ?";

  private JdbcOperations jdbcTemplate;

  public JdbcRegistrationRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int save(Registration registration) {
    return jdbcTemplate.update(INSERT_SQL, registration.sessionId(), registration.userId());
  }

  @Override
  public Registration findById(Long id) {
    RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getLong("user_id")
    );

    return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, rowMapper, id);
  }

  @Override
  public List<Registration> findByUserId(Long userId) {
    RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getLong("user_id")
    );

    return jdbcTemplate.query(SELECT_BY_USER_ID_SQL, rowMapper, userId);
  }

  @Override
  public List<Registration> findBySessionId(Long sessionId) {

    RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getLong("user_id")
    );

    return jdbcTemplate.query(SELECT_BY_SESSION_ID_SQL, rowMapper, sessionId);
  }
}
