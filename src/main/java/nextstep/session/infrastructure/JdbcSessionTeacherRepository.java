package nextstep.session.infrastructure;

import nextstep.session.domain.teacher.SessionTeacher;
import nextstep.session.domain.teacher.SessionTeacherRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("teacherRepository")
public class JdbcSessionTeacherRepository implements SessionTeacherRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionTeacherRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long saveTeacher(Long sessionId, Long nsUserId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO session_teacher (session_id, ns_user_id) VALUES (?, ?)";

        jdbcTemplate.update(connection -> {
            PreparedStatement pstmt = connection.prepareStatement(sql, new String[]{"id"});
            pstmt.setLong(1, sessionId);
            pstmt.setLong(2, nsUserId);
            return pstmt;
        }, keyHolder);
        return keyHolder.getKeyAs(Long.class);
    }

    @Override
    public List<SessionTeacher> getTeachers(Long sessionId) {
        String sql = "SELECT id, session_id, ns_user_id FROM session_teacher WHERE session_id = ?";

        RowMapper<SessionTeacher> rowMapper = (rs, rowNum) -> new SessionTeacher(
                rs.getLong("id"),
                rs.getLong("session_id"),
                rs.getLong("ns_user_id")
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
