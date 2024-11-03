package nextstep.courses.infrastructure;

import nextstep.courses.domain.ApprovedStatus;
import nextstep.courses.domain.SelectedStatus;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentsRepository;
import nextstep.courses.domain.session.Students;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static nextstep.courses.infrastructure.util.LocalDateTimeFormatter.toLocalDateTime;

public class JdbcStudentsRepository implements StudentsRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcStudentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveAll(Students students) {
        String sql = "insert into student (session_id, ns_user_id, selected_status, approved_status, created_at) values(?, ?, ?, ?, ?) ";

        return students.getStudents()
                .stream()
                .mapToInt(student -> jdbcTemplate.update(sql,
                        student.getSessionId(),
                        student.getNsUserId(),
                        student.getSelectedStatus().name(),
                        student.getApprovedStatus().name(),
                        student.getCreatedAt()))
                .sum();
    }

    @Override
    public Students findAllBySessionId(long sessionId) {
        String sql = "select id, session_id, ns_user_id, selected_status, approved_status, created_at, updated_at from student where session_id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong("id"),
                rs.getLong("session_id"),
                rs.getLong("ns_user_id"),
                SelectedStatus.valueOf(rs.getString("selected_status")),
                ApprovedStatus.valueOf(rs.getString("approved_status")),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at")));
        List<Student> students = jdbcTemplate.query(sql, rowMapper, sessionId);
        return new Students(students);
    }

}
