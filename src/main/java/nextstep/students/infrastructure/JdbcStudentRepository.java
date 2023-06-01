package nextstep.students.infrastructure;

import nextstep.students.domain.Student;
import nextstep.students.domain.StudentApprovalType;
import nextstep.students.domain.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into student (ns_user_id, session_id, approval_type, created_at) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                student.getUserId(), student.getSessionId(), student.getApprovalTypeName(), student.getCreatedAt());
    }

    @Override
    public Optional<Student> findById(Long id) {
        String sql = "select id, ns_user_id, session_id, approval_type, created_at, updated_at from student where id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                StudentApprovalType.find(rs.getString(4)),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6))

        );
        Student savedStudent = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return Optional.ofNullable(savedStudent);
    }

    @Override
    public List<Student> findAllBySessionId(Long sessionId) {
        String sql = "select id, ns_user_id, session_id, approval_type, created_at, updated_at from student where session_id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) ->
                new Student(
                        rs.getLong(1),
                        rs.getString(2),
                        rs.getLong(3),
                        StudentApprovalType.find(rs.getString(4)),
                        toLocalDateTime(rs.getTimestamp(5)),
                        toLocalDateTime(rs.getTimestamp(6))

                );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    @Override
    public void update(Student student) {
        String sql = "update student set approval_type = ?, updated_at = ? where id = ?";
        jdbcTemplate.update(sql, student.getApprovalTypeName(), student.getUpdatedAt(), student.getId());
    }

}
