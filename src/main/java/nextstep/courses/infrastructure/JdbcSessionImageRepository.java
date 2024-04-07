package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class JdbcSessionImageRepository implements SessionImageRepository {
  private String INSERT_SQL = "INSERT INTO session_image(width, height, extension, file_size, file_name) VALUES(?, ?, ?, ?, ?)";

  private JdbcOperations jdbcTemplate;

  public JdbcSessionImageRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public int save(SessionImage sessionImage) {
    return jdbcTemplate.update(INSERT_SQL, sessionImage.getWidth(), sessionImage.getHeight(), sessionImage.getExtension().toString(), sessionImage.getFileSize(), sessionImage.getFileName());
  }

  @Override
  public Long saveAndGetGeneratedKey(SessionImage sessionImage) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(
            connection -> createPreparedStatement(connection, sessionImage),
            keyHolder);

    return Objects.requireNonNull(keyHolder.getKey()).longValue();
  }

  private PreparedStatement createPreparedStatement(Connection connection, SessionImage sessionImage) {
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setInt(1, sessionImage.getWidth());
      preparedStatement.setInt(2, sessionImage.getHeight());
      preparedStatement.setString(3, sessionImage.getExtension().toString());
      preparedStatement.setInt(4, sessionImage.getFileSize());
      preparedStatement.setString(5, sessionImage.getFileName());
    } catch (SQLException e) {
      throw new IllegalStateException("SQL 실행 중 에러가 발생하였습니다.");
    }

    return preparedStatement;
  }

  @Override
  public SessionImage findById(Long id) {
    String sql = "SELECT * FROM session_image WHERE id = ?";
    RowMapper<SessionImage> rowMapper = (rs, rowNum) -> new SessionImage(
            rs.getLong("id"),
            rs.getInt("width"),
            rs.getInt("height"),
            rs.getString("extension"),
            rs.getInt("file_size"),
            rs.getString("file_name")
    );
    return jdbcTemplate.queryForObject(sql, rowMapper, id);
  }
}
