package nextstep.courses.infrastructure;

import java.util.List;
import nextstep.courses.domain.session.SessionStudent;
import nextstep.courses.domain.session.SessionStudentRepository;
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

    @Override
    public Long takeSession(Long sessionId, Long nsUserId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO session_student(session_id, ns_user_id) VALUES (?, ?)";

         jdbcTemplate.update(connection -> {
            var pstmt = connection.prepareStatement(sql, new String[]{"id"});
            pstmt.setLong(1, sessionId);
            pstmt.setLong(2, nsUserId);
            return pstmt;
        }, keyHolder);

         return keyHolder.getKeyAs(Long.class);
    }

    @Override
    public int cancelSession(Long sessionId, Long nsUserId) {
        final String sql = "UPDATE session_student SET cancel_flag = 0 WHERE session_id = ? AND ns_user_id = ?";
        return jdbcTemplate.update(sql, pstmt -> {
            pstmt.setLong(1, sessionId);
            pstmt.setLong(2, nsUserId);
        });
    }

    @Override
    public List<SessionStudent> getStudents(Long sessionId) {
        final String sql = "SELECT id, session_id, ns_user_id, cancel_flag, create_at, update_at FROM session_student WHERE session_id = ?";

        RowMapper<SessionStudent> rowMapper = (rs, rowNum) -> new SessionStudent (
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getLong("ns_user_id"),
            rs.getBoolean("cancel_flag"),
            LocalDateTimeUtils.of(rs.getTimestamp("create_at")),
            LocalDateTimeUtils.of(rs.getTimestamp("update_at"))
        );

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

}
