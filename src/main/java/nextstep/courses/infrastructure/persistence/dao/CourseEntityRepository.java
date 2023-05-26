package nextstep.courses.infrastructure.persistence.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.infrastructure.persistence.entity.CourseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository("courseEntityRepository")
public class CourseEntityRepository {

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleJdbcInsert;

  public CourseEntityRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName("course")
        .usingGeneratedKeyColumns("id");
  }

  public Long save(CourseEntity course) {
    MapSqlParameterSource parameters = new MapSqlParameterSource()
        .addValue("title", course.getTitle())
        .addValue("creator_id", course.getCreatorId())
        .addValue("generation", course.getGeneration())
        .addValue("created_at", course.getCreatedAt());

    return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
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
