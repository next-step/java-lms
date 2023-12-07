package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionEnroll;
import nextstep.courses.domain.SessionEnrollRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionEnrollRepository")
public class JdbcSessionEnrollRepository implements SessionEnrollRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionEnrollRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(SessionEnroll sessionEnroll) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into session_enroll (session_id, student_id, payment_id, created_at) values(?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, sessionEnroll.getSessionId());
            ps.setLong(2, sessionEnroll.getStudentId());
            ps.setString(3, sessionEnroll.getPaymentId());
            ps.setTimestamp(4, Timestamp.valueOf(sessionEnroll.getCreatedAt()));
            return ps;
        }, keyHolder);
        return (Long) keyHolder.getKey();
    }

    @Override
    public SessionEnroll findById(Long id) {
        String sql = "select id, session_id, student_id, payment_id, created_at from session_enroll where id = ?";
        RowMapper<SessionEnroll> rowMapper = (rs, rowNum) -> new SessionEnroll(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getString(4),
                toLocalDateTime(rs.getTimestamp(5)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
