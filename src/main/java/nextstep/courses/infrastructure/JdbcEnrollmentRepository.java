package nextstep.courses.infrastructure;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import nextstep.courses.domain.enrollment.EnrollmentStatus;
import nextstep.courses.domain.session.*;
import nextstep.courses.exception.NoSuchSessionException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.exception.NoSuchUserException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class JdbcEnrollmentRepository implements EnrollmentRepository {

    private final JdbcOperations jdbcTemplate;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate,
                                    SessionRepository sessionRepository,
                                    UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public int save(Enrollment enrollment) {
        String sql = "insert into enrollment (session_id, ns_user_id, status, created_at) values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, enrollment.getSession().getId(), enrollment.getUser().getId(),
                enrollment.getStatus().toString(), enrollment.getCreatedAt());
    }

    @Override
    public Enrollment findById(Long id) {
        String sql = "select id, session_id, ns_user_id, status, created_at, updated_at from enrollment where id = ?";
        return jdbcTemplate.queryForObject(sql, getSessionRowMapper(), id);
    }

    private RowMapper<Enrollment> getSessionRowMapper() {
        return (rs, rowNum) -> {
            Session session = Optional.ofNullable(sessionRepository.findById(rs.getLong("session_id")))
                    .orElseThrow(NoSuchSessionException::new);
            NsUser nsUser = userRepository.findById(rs.getLong("ns_user_id"))
                    .orElseThrow(NoSuchUserException::new);

            return new Enrollment(
                    rs.getLong(1),
                    session,
                    nsUser,
                    EnrollmentStatus.valueOf(rs.getString(4)),
                    toLocalDateTime(rs.getTimestamp(5)),
                    toLocalDateTime(rs.getTimestamp(6)));
        };
    }

    @Override
    public int update(Enrollment enrollment) {
        String sql = "update enrollment set status= ? where id = ?";
        return jdbcTemplate.update(sql, enrollment.getStatus().name(), enrollment.getId());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
