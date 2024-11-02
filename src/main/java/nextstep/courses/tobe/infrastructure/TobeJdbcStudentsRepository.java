package nextstep.courses.tobe.infrastructure;

import nextstep.courses.tobe.domain.ApprovedStatus;
import nextstep.courses.tobe.domain.SelectedStatus;
import nextstep.courses.tobe.domain.TobeStudent;
import nextstep.courses.tobe.domain.TobeStudentsRepository;
import nextstep.courses.tobe.domain.session.TobeStudents;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class TobeJdbcStudentsRepository implements TobeStudentsRepository {
    private final JdbcTemplate jdbcTemplate;

    public TobeJdbcStudentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveAll(TobeStudents students) {
        String sql = "insert into tobe_student (session_id, ns_user_id, selected_status, approved_status, created_at) values(?, ?, ?, ?, ?) ";

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
    public TobeStudents findAllBySessionId(long sessionId) {
        String sql = "select id, session_id, ns_user_id, selected_status, approved_status, created_at, updated_at from tobe_student where session_id = ?";
        RowMapper<TobeStudent> rowMapper = (rs, rowNum) -> new TobeStudent(
                rs.getLong("id"),
                rs.getLong("session_id"),
                rs.getLong("ns_user_id"),
                SelectedStatus.valueOf(rs.getString("selected_status")),
                ApprovedStatus.valueOf(rs.getString("approved_status")),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at")));
        List<TobeStudent> students = jdbcTemplate.query(sql, rowMapper, sessionId);
        return new TobeStudents(students);
    }

    protected static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
