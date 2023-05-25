package nextstep.courses.infrastructure.persistence.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.infrastructure.persistence.entity.CourseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("courseEntityRepository")
public class CourseEntityRepository {

  private JdbcOperations jdbcTemplate;

  public CourseEntityRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public int save(CourseEntity course) {
    String sql = "insert into course (title, creator_id, generation, created_at) values(?, ?, ?, ?)";
    return jdbcTemplate.update(sql, course.getTitle(), course.getCreatorId(), course.getGeneration(),course.getCreatedAt());
  }

  public Optional<CourseEntity> findById(Long id) {
    String sql = "select id, title, creator_id, generation, created_at, updated_at from course where id = ?";
    return jdbcTemplate.queryForObject(sql, rowMapper(), id);
  }

  private RowMapper<Optional<CourseEntity>> rowMapper() {
    return (rs, rowNum) -> Optional.of(new CourseEntity(
        rs.getLong(1),
        rs.getString(2),
        rs.getLong(3),
        rs.getString(4),
        toLocalDateTime(rs.getTimestamp(5)),
        toLocalDateTime(rs.getTimestamp(6))));
  }


  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }

}
