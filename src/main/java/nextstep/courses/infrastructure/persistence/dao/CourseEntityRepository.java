package nextstep.courses.infrastructure.persistence.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.infrastructure.persistence.entity.CourseEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository("courseEntityRepository")
public class CourseEntityRepository {

  private static final String COURSE = "course";
  private static final String ID = "id";
  private static final String TITLE = "title";
  private static final String CREATOR_ID = "creator_id";
  private static final String GENERATION = "generation";
  private static final String CREATED_AT = "created_at";
  private static final String UPDATED_AT = "updated_at";

  private final JdbcTemplate jdbcTemplate;
  private final SimpleJdbcInsert simpleJdbcInsert;

  public CourseEntityRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
    this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName(COURSE)
        .usingGeneratedKeyColumns(ID);
  }

  public Long save(CourseEntity course) {
    MapSqlParameterSource parameters = new MapSqlParameterSource()
        .addValue(TITLE, course.getTitle())
        .addValue(CREATOR_ID, course.getCreatorId())
        .addValue(GENERATION, course.getGeneration())
        .addValue(CREATED_AT, course.getCreatedAt())
        .addValue(UPDATED_AT, course.getUpdatedAt());

    return simpleJdbcInsert.executeAndReturnKey(parameters).longValue();
  }

  public Optional<CourseEntity> findById(Long id) {
    String sql = "select id, title, creator_id, generation, created_at, updated_at from course where id = ?";
    try {
      return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper(), id));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }

  private RowMapper<CourseEntity> rowMapper() {
    return (rs, rowNum) -> {
      Long id = rs.getLong(ID);
      String title = rs.getString(TITLE);
      Long creatorId = rs.getLong(CREATOR_ID);
      String generation = rs.getString(GENERATION);
      LocalDateTime createdAt = rs.getTimestamp(CREATED_AT).toLocalDateTime();
      LocalDateTime updatedAt = rs.getTimestamp(UPDATED_AT).toLocalDateTime();

      return new CourseEntity(id, title, creatorId, generation, createdAt, updatedAt);
    };
  }


  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }

}
