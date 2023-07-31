package nextstep.courses.infrastructure;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import nextstep.courses.domain.curriculum.Curriculum;
import nextstep.courses.domain.curriculum.CurriculumRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("curriculumRepository")
public class JdbcCurriculumRepository implements CurriculumRepository {

  private final JdbcOperations jdbcTemplate;

  public JdbcCurriculumRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<Curriculum> findById(Long id) {
    String sql = "select id, batch_id, session_id"
        + ", creator_id, created_at, updated_at "
        + "from curriculum "
        + "where id = ?";

    RowMapper<Curriculum> rowMapper = (rs, rowNum) -> new Curriculum(
        rs.getLong(1),
        rs.getLong(2),
        rs.getLong(3),
        rs.getLong(4),
        toLocalDateTime(rs.getTimestamp(5)),
        toLocalDateTime(rs.getTimestamp(6)));
    return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
  }

  @Override
  public List<Curriculum> findByBatchId(Long batchId) {
    String sql = "select id, batch_id, session_id"
        + ", creator_id, created_at, updated_at "
        + "from curriculum "
        + "where batch_id = ?";

    RowMapper<Curriculum> rowMapper = (rs, rowNum) -> new Curriculum(
        rs.getLong(1),
        rs.getLong(2),
        rs.getLong(3),
        rs.getLong(4),
        toLocalDateTime(rs.getTimestamp(5)),
        toLocalDateTime(rs.getTimestamp(6)));
    return jdbcTemplate.query(sql, rowMapper, batchId);
  }

  @Override
  public Long save(Curriculum curriculum) {
    if (curriculum.getId() != null && findById(curriculum.getId()).isPresent()) {
      throw new IllegalArgumentException("커리큘럼은 변경이 불가능 합니다.");
    }

    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "insert into curriculum (batch_id, session_id"
        + ", creator_id, created_at, updated_at) "
        + "values(?, ?, ?, ?, ?)";
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
      ps.setLong(1, curriculum.getBatchId());
      ps.setLong(2, curriculum.getSessionId());
      ps.setLong(3, curriculum.getCreatorId());
      ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
      ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
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
