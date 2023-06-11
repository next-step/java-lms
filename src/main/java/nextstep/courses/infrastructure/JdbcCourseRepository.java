package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
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

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }

  @Override
  public int save(Course course) {
    if (course.getId() != null && findById(course.getId()).isPresent()) {
      String sql = "update course set title = ?, now_batch_no = ?, updated_at = ? where id = ?";
      return jdbcTemplate
          .update(sql, course.getTitle(), course.getNowBatchNo(), LocalDateTime.now(),
              course.getId());
    }

    String sql = "insert into course (title, now_batch_no, creator_id, created_at) values(?, ?, ?, ?)";
    return jdbcTemplate
        .update(sql, course.getTitle(), course.getNowBatchNo(), course.getCreatorId(),
            LocalDateTime.now());
  }
}
