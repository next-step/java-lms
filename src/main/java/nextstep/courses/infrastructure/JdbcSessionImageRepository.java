package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionImageRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Objects;

@Repository("sessionImageRepository")
public class JdbcSessionImageRepository implements SessionImageRepository {
  private static String INSERT_SQL = "INSERT INTO session_image(width, height, extension, file_size, file_name) VALUES(?, ?, ?, ?, ?)";
  private static String SELECT_SQL = "SELECT * FROM session_image WHERE id = ?";

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleJdbcInsert;

  public JdbcSessionImageRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("session_image")
            .usingGeneratedKeyColumns("id");
  }

  @Override
  public int save(SessionImage sessionImage) {
    return jdbcTemplate.update(INSERT_SQL, sessionImage.getWidth(), sessionImage.getHeight(), sessionImage.getExtension().toString(), sessionImage.getFileSize(), sessionImage.getFileName());
  }

  @Override
  public Long saveAndGetGeneratedKey(SessionImage sessionImage) {
    return this.simpleJdbcInsert.executeAndReturnKey(
            Map.of(
                    "width", sessionImage.getWidth(),
                    "height", sessionImage.getHeight(),
                    "extension", sessionImage.getExtension(),
                    "file_size", sessionImage.getFileSize(),
                    "file_name", sessionImage.getFileName()
            )
    ).longValue();
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
    RowMapper<SessionImage> rowMapper = (rs, rowNum) -> new SessionImage(
            rs.getLong("id"),
            rs.getInt("width"),
            rs.getInt("height"),
            rs.getString("extension"),
            rs.getInt("file_size"),
            rs.getString("file_name")
    );
    return jdbcTemplate.queryForObject(SELECT_SQL, rowMapper, id);
  }
}
