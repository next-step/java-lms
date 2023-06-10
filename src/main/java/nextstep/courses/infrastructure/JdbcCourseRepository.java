package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
  public int save(Course course) {
    String sql = "insert into course (title, now_batch_no, creator_id, created_at) values(?, ?, ?, ?)";
    return jdbcTemplate
        .update(sql, course.getTitle(), course.getNowBatchNo(), course.getCreatorId(),
            course.getCreatedAt());
  }

  @Override
  public Course findById(Long id) {
    String sql = "select id, now_batch_no, title, creator_id, created_at, updated_at from course where id = ?";
    RowMapper<Course> rowMapper = (rs, rowNum) -> new Course(
        rs.getLong(1),
        rs.getInt(2),
        rs.getString(3),
        rs.getLong(4),
        toLocalDateTime(rs.getTimestamp(5)),
        toLocalDateTime(rs.getTimestamp(6)));
    return jdbcTemplate.queryForObject(sql, rowMapper, id);
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }
    return timestamp.toLocalDateTime();
  }
}
