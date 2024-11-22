package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.EnrollmentRepository;
import nextstep.courses.domain.session.EnrollmentStatus;
import nextstep.users.domain.NsUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private static final Logger log = LoggerFactory.getLogger(JdbcEnrollmentRepository.class);
    private final JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Set<NsUser> findEnrolledUsersBySessionId(long sessionId) {
        String sql = "SELECT us.id, us.user_id, us.password, us.name, us.email, e.enrollment_status, us.created_at, us.updated_at " +
                "FROM enrollment e JOIN ns_user us ON e.user_id = us.id WHERE session_id = ?";

        List<NsUser> enrolledUsers = jdbcTemplate.query(sql, rowMapper, sessionId);

        return new HashSet<>(enrolledUsers);
    }

    private final RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
            rs.getLong(1),
            rs.getString(2),
            rs.getString(3),
            rs.getString(4),
            rs.getString(5),
            EnrollmentStatus.valueOf(rs.getString(6)),
            rs.getTimestamp(7).toLocalDateTime(),
            rs.getTimestamp(8) != null ? rs.getTimestamp(8).toLocalDateTime() : null
    );

    @Override
    public void save(long sessionId, NsUser user) {
        log.info("[save] enrollmentStatus {}", user.getEnrollmentStatus());
        String sql = "INSERT INTO enrollment (session_id, user_id, enrollment_status) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, sessionId, user.getId(), user.getEnrollmentStatus().name());
    }

    @Override
    public void updateEnrollmentStatus(long sessionId, long userId, EnrollmentStatus status) {
        String sql = "UPDATE enrollment SET enrollment_status = ? WHERE session_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, status.name(), sessionId, userId);
    }

}