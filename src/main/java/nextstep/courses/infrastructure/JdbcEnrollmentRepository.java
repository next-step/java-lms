package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.EnrollmentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private final JdbcOperations jdbcTemplate;
    private final UserRepository userRepository;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public Set<NsUser> findEnrolledUsersBySessionId(Long sessionId) {
        String sql = "SELECT user_id FROM enrollment WHERE session_id = ?";
        List<Long> userIds = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getLong("user_id"), sessionId);
        Set<NsUser> enrolledUsers = new HashSet<>();

        userIds.forEach(id -> {
            Optional<NsUser> byId = userRepository.findById(id);
            byId.ifPresent(enrolledUsers::add);
        });

        return enrolledUsers;
    }

    @Override
    public void save(Long sessionId, NsUser user) {
        String sql = "INSERT INTO enrollment (session_id, user_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, sessionId, user.getId());
    }
}