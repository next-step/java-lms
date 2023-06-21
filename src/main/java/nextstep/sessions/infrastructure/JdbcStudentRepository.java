package nextstep.sessions.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.StudentRepository;
import nextstep.sessions.domain.StudentStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

  private JdbcOperations jdbcTemplate;

  public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Optional<Student> findById(Long id) {
    String sql = "select id, session_id, ns_user_id, student_status_id, created_at, updated_at from student where id = ?";

    return Optional.of(jdbcTemplate.queryForObject(sql,
        (rs, rowNum) -> new Student(rs.getLong(1), rs.getLong(2), rs.getLong(3),
            StudentStatus.from(rs.getLong(4)), toLocalDateTime(rs.getTimestamp(5)),
            toLocalDateTime(rs.getTimestamp(6))),
        id));
  }

  @Override
  public void update(Student student) {
    String sql = "update student set student_status_id = ?, updated_at = ? where id = ?";

    jdbcTemplate.update(sql, student.getStudentStatus().getOrder(), LocalDateTime.now(), student.getId());
  }

  private LocalDateTime toLocalDateTime(Timestamp timestamp) {
    if (timestamp == null) {
      return null;
    }

    return timestamp.toLocalDateTime();
  }
}
