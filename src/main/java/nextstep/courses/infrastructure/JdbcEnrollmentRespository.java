package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.repository.EnrollmentRepository;
import nextstep.courses.record.EnrollmentRecord;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRespository implements EnrollmentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRespository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Enrollment enrollment) {
        String sql = "insert into enrollment (session_id, user_id, selection_status, enrollment_status, created_at)" +
                " values (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                enrollment.getSessionId(),
                enrollment.getUser().getId(),
                enrollment.getSelectionStatus().name(),
                enrollment.getEnrollmentStatus().name(),
                enrollment.getCreatedAt()
        );
    }

    @Override
    public List<EnrollmentRecord> findBySessionId(Long sessionId) {
        String sql = "select * from enrollment where session_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new EnrollmentRecord(
                    rs.getLong("id"),
                    rs.getLong("user_id"),
                    rs.getLong("session_id"),
                    rs.getString("selection_status"),
                    rs.getString("enrollment_status"),
                    toLocalDateTime(rs.getTimestamp("created_at")),
                    toLocalDateTime(rs.getTimestamp("updated_at"))
            );
        }, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
