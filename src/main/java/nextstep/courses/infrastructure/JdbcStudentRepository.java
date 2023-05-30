package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.student.Student;
import nextstep.courses.domain.student.StudentRepository;
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
    private final SessionRepository sessionRepository;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate, SessionRepository sessionRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into student (ns_user_id, session_id, created_at) values (?, ?, ?)";
        return jdbcTemplate.update(sql, student.getUserId(), student.getSessionId(), student.getCreatedAt());
    }

    @Override
    public Optional<Student> findById(Long id) {
        String sql = "select id, ns_user_id, session_id, created_at, updated_at from student where id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getString(2),
                sessionRepository.findById(rs.getLong(3)).orElseThrow(IllegalStateException::new),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5))

        );
        Student savedStudent = jdbcTemplate.queryForObject(sql, rowMapper, id);
        return Optional.ofNullable(savedStudent);
    }

    @Override
    public List<Student> findAllBySessionId(Long sessionId) {
        String sql = "select id, ns_user_id, session_id, created_at, updated_at from student where session_id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) ->
                new Student(
                        rs.getLong(1),
                        rs.getString(2),
                        sessionRepository.findById(rs.getLong(3)).orElseThrow(IllegalStateException::new),
                        toLocalDateTime(rs.getTimestamp(4)),
                        toLocalDateTime(rs.getTimestamp(5))

                );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

}
