package nextstep.courses.infrastructure;

import nextstep.courses.domain.RegisterStatus;
import nextstep.courses.domain.Registration;
import nextstep.courses.domain.RegistrationRepository;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("registrationRepository")
public class JdbcRegistrationRepository implements RegistrationRepository {
  private static final String INSERT_SQL = "INSERT INTO registration(session_id, user_id, status) VALUES(?, ?, ?)";
  private static final String COMMON_SELECT_CLAUSE = "SELECT * " +
                                                       "FROM registration r ";
  private static final String SELECT_BY_ID_SQL = COMMON_SELECT_CLAUSE + "WHERE id = ? ";
  private static final String SELECT_BY_USER_ID_SQL = COMMON_SELECT_CLAUSE + "WHERE user_id = ? ";
  private static final String SELECT_BY_SESSION_ID_SQL = COMMON_SELECT_CLAUSE + "WHERE session_id = ? ";
  private static final String SELECT_BY_USER_ID_AND_SESSION_ID = COMMON_SELECT_CLAUSE + "WHERE user_id = ? AND session_id = ? ";

  private JdbcOperations jdbcTemplate;
  private SessionRepository sessionRepository;
  private UserRepository userRepository;

  private final RowMapper<Registration> rowMapper = (rs, rowNum) -> {
    System.out.println("session_id: " + rs.getLong("session_id"));
    return new Registration(
            rs.getLong("id"),
            sessionRepository.findById(rs.getLong("session_id")),
            userRepository.findById(rs.getLong("user_id"))
                    .orElseThrow(() -> {
                      throw new IllegalStateException("존재하지 않는 사용자입니다.");
                    }),
            RegisterStatus.valueOf(rs.getString("status"))
    );
  };

  public JdbcRegistrationRepository(JdbcOperations jdbcTemplate, SessionRepository sessionRepository, UserRepository userRepository) {
    this.sessionRepository = sessionRepository;
    this.userRepository = userRepository;
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int save(Registration registration) {
    return jdbcTemplate.update(INSERT_SQL, registration.sessionId(), registration.userId(), registration.getStatus().toString());
  }

  @Override
  public Registration findById(Long id) {
    return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, rowMapper, id);
  }

  @Override
  public Registration findByUserIdAndSessionId(Long userId, Long sessionId) {
    return jdbcTemplate.queryForObject(SELECT_BY_USER_ID_AND_SESSION_ID, rowMapper, userId, sessionId);
  }

  @Override
  public List<Registration> findByUserId(Long userId) {
    return jdbcTemplate.query(SELECT_BY_USER_ID_SQL, rowMapper, userId);
  }

  @Override
  public List<Registration> findBySessionId(Long sessionId) {
    return jdbcTemplate.query(SELECT_BY_SESSION_ID_SQL, rowMapper, sessionId);
  }
}
