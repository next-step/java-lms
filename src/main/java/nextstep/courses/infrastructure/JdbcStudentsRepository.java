package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentsRepository;
import nextstep.courses.domain.session.Students;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class JdbcStudentsRepository implements StudentsRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcStudentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveAll(Students students) {
        String sql = "insert into student (session_id, ns_user_id, created_at) values(?, ?, ?) ";

        return students.getStudents()
                .stream()
                .mapToInt(student -> jdbcTemplate.update(sql, student.toParameters()))
                .sum();
    }

    @Override
    public Students findAllBySessionId(long sessionId) {
        String sql = "select id, session_id, ns_user_id, created_at, updated_at from student where session_id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong("id"),
                rs.getLong("session_id"),
                rs.getLong("ns_user_id"),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at")));
        List<Student> students = jdbcTemplate.query(sql, rowMapper, sessionId);
        return new Students(students);
    }

    protected static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
