package nextstep.courses.infrastructure;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("courseRepository")
public class JdbcCourseRepository implements CourseRepository {

  private JdbcOperations jdbcTemplate;

  public JdbcCourseRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<Course> findById(Long id) {
    String sql = "select id, title, now_batch_no, creator_id, created_at, updated_at from course where id = ?";
    RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
        rs.getLong(1),
        rs.getString(2),
        rs.getInt(3),
        rs.getLong(4),
        toLocalDateTime(rs.getTimestamp(5)),
        toLocalDateTime(rs.getTimestamp(6)));
    return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
  }

  @Override
  public Long save(Course course) {
    if (course.getId() != null && findById(course.getId()).isPresent()) {
      String sql = "update course set title = ?, now_batch_no = ?"
          + ", updated_at = ? where id = ?";
      jdbcTemplate
          .update(sql, course.getTitle(), course.getNowBatchNo()
              , LocalDateTime.now(), course.getId());
      return course.getId();
    }

    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "insert into course (title, now_batch_no, creator_id, created_at, updated_at) "
        + "values(?, ?, ?, ?, ?)";
    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
      ps.setString(1, course.getTitle());
      ps.setInt(2, course.getNowBatchNo());
      ps.setLong(3, course.getCreatorId());
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
