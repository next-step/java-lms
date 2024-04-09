package nextstep.sessions.infrastructore;

import nextstep.sessions.domain.Enrollment;
import nextstep.sessions.domain.EnrollmentState;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment (session_id, ns_user_id, state, created_at) VALUES(?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, enrollment.getSessionId());
            ps.setLong(2, enrollment.getNsUserId());
            ps.setString(3, enrollment.getState().name());
            ps.setTimestamp(4, Timestamp.valueOf(enrollment.getCreatedAt()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public void saveAll(List<Enrollment> enrollments) {
        String sql = "INSERT INTO enrollment (session_id, ns_user_id, state, created_at) VALUES(?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, enrollments, enrollments.size(), (ps, enrollment) -> {
            ps.setLong(1, enrollment.getSessionId());
            ps.setLong(2, enrollment.getNsUserId());
            ps.setString(3, enrollment.getState().name());
            ps.setTimestamp(4, Timestamp.valueOf(enrollment.getCreatedAt()));
        });
    }

    @Override
    public List<Enrollment> findBySessionId(Long sessionId) {
        String sql = "SELECT id, session_id, ns_user_id, state, created_at, updated_at FROM enrollment WHERE session_id = ?";
        return jdbcTemplate.query(sql, getEnrollmentRowMapper(), sessionId);
    }

    @Override
    public List<Enrollment> findByNsUserId(Long nsUserId) {
        String sql = "SELECT id, session_id, ns_user_id, state, created_at, updated_at FROM enrollment WHERE ns_user_id = ?";
        return jdbcTemplate.query(sql, getEnrollmentRowMapper(), nsUserId);
    }

    @Override
    public List<Enrollment> findBy(Long sessionId, Long nsUserId) {
        String sql = "SELECT id, session_id, ns_user_id, state, created_at, updated_at FROM enrollment WHERE session_id = ? AND ns_user_id = ?";
        return jdbcTemplate.query(sql, getEnrollmentRowMapper(), sessionId, nsUserId);
    }

    private RowMapper<Enrollment> getEnrollmentRowMapper() {
        return (rs, rowNum) -> new Enrollment(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                EnrollmentState.valueOf(rs.getString(4)),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6))
        );
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) throws SQLException {
        if (timestamp != null) {
            return timestamp.toLocalDateTime();
        }

        return null;
    }

    @Override
    public int update(Enrollment enrollment) {
        String sql = "UPDATE enrollment SET state = ?, updated_at = ? WHERE id = ?";
        return jdbcTemplate.update(sql, ps -> {
            ps.setString(1, enrollment.getState().name());
            ps.setTimestamp(2, Timestamp.valueOf(enrollment.getUpdatedAt()));
            ps.setLong(3, enrollment.getId());
        });
    }
}
