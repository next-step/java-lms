package nextstep.courses.infrastructure;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import nextstep.courses.domain.application.Application;
import nextstep.courses.domain.application.ApplicationRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("applicationRepository")
public class JdbcApplicationRepository implements ApplicationRepository {

  private final JdbcOperations jdbcTemplate;

  public JdbcApplicationRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<Application> findById(Long id) {
    String sql = "select id, pass"
        + ", ns_user_id, course_id"
        + ", creator_id, created_at, updated_at "
        + "from registration "
        + "where id = ?";
    RowMapper<Application> rowMapper = (rs, rowNum) -> new Application(
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
  public List<Application> findByCourseId(Long courseId) {
    String sql = "select id, pass"
        + ", ns_user_id, course_id"
        + ", creator_id, created_at, updated_at "
        + "from registration "
        + "where course_id = ?";
    RowMapper<Application> rowMapper = (rs, rowNum) -> new Application(
        rs.getLong(1),
        rs.getBoolean(2),
        rs.getLong(3),
        rs.getLong(4),
        rs.getLong(5),
        toLocalDateTime(rs.getTimestamp(6)),
        toLocalDateTime(rs.getTimestamp(7)));
    return jdbcTemplate.query(sql, rowMapper, courseId);
  }

  @Override
  public Long save(Application application) {
    if (application.getId() != null && findById(application.getId()).isPresent()) {
      String sql = "update application set pass = ?, updated_at = ? "
          + "where id = ?";
      jdbcTemplate
          .update(sql, application.isPass(), LocalDateTime.now(), application.getId());
      return application.getId();
    }

    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "insert into application (pass"
        + ", ns_user_id, session_id"
        + ", creator_id, created_at, updated_at) "
        + "values(?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
      ps.setBoolean(1, application.isPass());
      ps.setLong(2, application.getNsUserId());
      ps.setLong(3, application.getCourseId());
      ps.setLong(4, application.getCreatorId());
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
