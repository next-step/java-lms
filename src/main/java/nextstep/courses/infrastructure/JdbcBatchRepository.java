package nextstep.courses.infrastructure;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import nextstep.courses.domain.batch.Batch;
import nextstep.courses.domain.batch.BatchRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("batchRepository")
public class JdbcBatchRepository implements BatchRepository {

  private JdbcOperations jdbcTemplate;

  public JdbcBatchRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<Batch> findById(Long id) {
    String sql = "select id, batch_no, course_id, creator_id, created_at, updated_at "
        + "from batch "
        + "where id = ?";
    RowMapper<Batch> rowMapper = (rs, rowNum) -> new Batch(
        rs.getLong(1),
        rs.getInt(2),
        rs.getLong(3),
        rs.getLong(4),
        toLocalDateTime(rs.getTimestamp(5)),
        toLocalDateTime(rs.getTimestamp(6)));
    return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
  }

  @Override
  public List<Batch> findByCourseId(Long courseId) {
    String sql = "select id, batch_no, course_id, creator_id, created_at, updated_at "
        + "from batch "
        + "where course_id = ?";
    RowMapper<Batch> rowMapper = (rs, rowNum) -> new Batch(
        rs.getLong(1),
        rs.getInt(2),
        rs.getLong(3),
        rs.getLong(4),
        toLocalDateTime(rs.getTimestamp(5)),
        toLocalDateTime(rs.getTimestamp(6)));

    return jdbcTemplate.query(sql, rowMapper, courseId);
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }

  @Override
  public Long save(Batch batch) {
    if (batch.getId() != null && findById(batch.getId()).isPresent()) {
      throw new IllegalArgumentException("기수는 변경이 불가능 합니다.");
    }

    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "insert into batch (batch_no, course_id, creator_id, created_at, updated_at) "
        + "values(?, ?, ?, ?, ?)";
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
      ps.setInt(1, batch.getBatchNo());
      ps.setLong(2, batch.getCourseId());
      ps.setLong(3, batch.getCreatorId());
      ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
      ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
      return ps;
    }, keyHolder);

    return keyHolder.getKey().longValue();
  }
}
