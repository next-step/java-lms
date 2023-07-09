package nextstep.courses.infrastructure;
import nextstep.courses.domain.*;
import nextstep.courses.domain.enums.ApprovalState;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Enrollment enrollment) {
        String sql = "insert into Enrollment (session_id, user_id, enroll_date, created_at) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, enrollment.getSessionId(), enrollment.getStudent().getUserId(),
                enrollment.getEnrollDate(), enrollment.getCreatedAt());
    }

    @Override
    public Enrollment findById(Long sessionId, String userId) {
        UserRepository userRepository = new JdbcUserRepository(jdbcTemplate);
        NsUser nsUser = userRepository.findByUserId(userId).orElseThrow(NotFoundException::new);

        String sql = "select enroll_date, created_at, updated_at from Enrollment " +
                "where session_id = ? and user_id = ?";
        RowMapper<Enrollment> rowMapper = (rs, rowNum) -> new Enrollment(
                sessionId,
                nsUser,
                rs.getString(1),
                ApprovalState.PENDING,
                toLocalDateTime(rs.getTimestamp(2)),
                toLocalDateTime(rs.getTimestamp(3)));
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId, userId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}