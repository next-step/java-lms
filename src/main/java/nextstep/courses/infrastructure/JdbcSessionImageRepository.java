package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionImageRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sessionImageRepository")
public class JdbcSessionImageRepository implements SessionImageRepository {
  private static String INSERT_SQL = "INSERT INTO session_image(width, height, extension, file_size, file_name, session_id) VALUES(?, ?, ?, ?, ?, ?)";
  private static String SELECT_SQL = "SELECT * FROM session_image WHERE id = ?";
  private static String SELECT_BY_SESSION_ID_SQL = "SELECT * FROM session_image WHERE session_id = ?";

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleJdbcInsert;
  private final RowMapper<SessionImage> sessionImageRowMapper = (rs, rowNum) -> new SessionImage(
          rs.getLong("id"),
          rs.getInt("width"),
          rs.getInt("height"),
          rs.getString("extension"),
          rs.getInt("file_size"),
          rs.getString("file_name"),
          rs.getLong("session_id")
  );

  public JdbcSessionImageRepository(JdbcTemplate jdbcTemplate, SimpleJdbcInsert simpleJdbcInsert) {
    this.jdbcTemplate = jdbcTemplate;
    this.simpleJdbcInsert = simpleJdbcInsert
            .withTableName("session_image")
            .usingGeneratedKeyColumns("id");
  }

  @Override
  public int save(SessionImage sessionImage) {
    return jdbcTemplate.update(INSERT_SQL, sessionImage.getWidth(), sessionImage.getHeight(), sessionImage.getExtension().toString(), sessionImage.getFileSize(), sessionImage.getFileName(), sessionImage.getSessionId());
  }

  @Override
  public Long saveAndGetGeneratedKey(SessionImage sessionImage) {
    BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(sessionImage);
    return this.simpleJdbcInsert.executeAndReturnKey(params).longValue();
  }

  @Override
  public SessionImage findById(Long id) {
    return jdbcTemplate.queryForObject(SELECT_SQL, sessionImageRowMapper, id);
  }

  @Override
  public List<SessionImage> findBySessionId(Long sessionId) {
    return jdbcTemplate.query(SELECT_BY_SESSION_ID_SQL, sessionImageRowMapper, sessionId);
  }
}
