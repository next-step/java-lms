package nextstep.courses.infrastructure;

import java.util.List;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudentRepository;
import nextstep.courses.domain.session.student.SessionStudentStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import utils.LocalDateTimeUtils;

@Repository
public class JdbcSessionStudentRepository implements SessionStudentRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<SessionStudent> rowMapper = (rs, rowNum) -> new SessionStudent (
        rs.getLong("id"),
        rs.getLong("session_id"),
        rs.getLong("ns_user_id"),
        SessionStudentStatus.findByName(rs.getString("student_status")),
        rs.getBoolean("cancel_flag"),
        LocalDateTimeUtils.of(rs.getTimestamp("create_at")),
        LocalDateTimeUtils.of(rs.getTimestamp("update_at"))
    );

    @Override
    public Long takeSession(Long sessionId, Long nsUserId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO session_student(session_id, ns_user_id, student_status) VALUES (?, ?, ?)";

         jdbcTemplate.update(connection -> {
            var pstmt = connection.prepareStatement(sql, new String[]{"id"});
            pstmt.setLong(1, sessionId);
            pstmt.setLong(2, nsUserId);
            pstmt.setString(3, SessionStudentStatus.REQUEST.name());
            return pstmt;
        }, keyHolder);

         return keyHolder.getKeyAs(Long.class);
    }

    @Override
    public int cancelSession(Long studentId) {
        final String sql = "UPDATE session_student SET cancel_flag = 1 WHERE id = ?";
        return jdbcTemplate.update(sql, pstmt -> {
            pstmt.setLong(1, studentId);
        });
    }

    @Override
    public List<SessionStudent> getStudents(Long sessionId) {
        final String sql =
            "SELECT id, session_id, ns_user_id, student_status, cancel_flag, create_at, update_at"
          + " FROM session_student WHERE session_id = ? AND cancel_flag = 0";
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public int changeStudentStatus(Long id, Long teacherNsUserId, Long studentId, SessionStudentStatus studentStatus) {
        final String sql =
              " UPDATE session_student SET student_status = ?, update_teacher_id = ?, update_at = now()"
            + " WHERE id = ?";

        return jdbcTemplate.update(sql, pstmt -> {
            pstmt.setString(1, studentStatus.name());
            pstmt.setLong(2, teacherNsUserId);
            pstmt.setLong(3, studentId);
        });
    }
}
