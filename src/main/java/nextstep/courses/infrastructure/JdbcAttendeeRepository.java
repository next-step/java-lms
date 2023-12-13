package nextstep.courses.infrastructure;

import nextstep.courses.domain.attendee.Approval;
import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.AttendeeRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcAttendeeRepository implements AttendeeRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcAttendeeRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Attendee> findByStudentIdAndSessionId(Long studentId, Long sessionId) {
        String sql = "select user_id, session_id, approval" +
                " from new_enrollment" +
                " where user_id =? and session_id = ?";
        RowMapper<Attendee> rowMapper = (rs, rowNum) -> new Attendee(
                rs.getLong(1),
                rs.getLong(2),
                Approval.valueOf(rs.getString(3))
        );
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, studentId, sessionId));
    }

    @Override
    public void save(Attendee attendee) {
        String sql = "insert into new_enrollment (user_id, session_id, approval)" +
                " values (?, ?, ?)";
        jdbcTemplate.update(sql, attendee.getStudentId(), attendee.getSessionId(), attendee.getApprovalString());
    }

    @Override
    public List<Attendee> findAllBySessionId(Long sessionId) {
        String sql = "select user_id, session_id, approval" +
                " from new_enrollment" +
                " where session_id = ?";
        RowMapper<Attendee> rowMapper = (rs, rowNum) -> new Attendee(
                rs.getLong(1),
                rs.getLong(2),
                Approval.valueOf(rs.getString(3))
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public void update(Attendee approvedAttendee) {
        String sql = "update new_enrollment" +
                " set student_id = ?, " +
                " session_id = ?, " +
                " approval = ? " +
                " where student_id = ? and session_id = ?";
        jdbcTemplate.update(sql,
                            approvedAttendee.getStudentId(),
                            approvedAttendee.getSessionId(),
                            approvedAttendee.getApprovalString(),
                            approvedAttendee.getStudentId(),
                            approvedAttendee.getSessionId());
    }
}
