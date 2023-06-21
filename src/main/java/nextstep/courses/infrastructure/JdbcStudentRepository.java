package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("studentsRepository")
public class JdbcStudentRepository implements StudentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into student (session_id, user_id, approval_status) values(?, ?, ?)";
        return jdbcTemplate.update(sql, student.getSessionId(), student.getUserId(), student.getApprovalStatus().toString());
    }

    @Override
    public List<NsUser> findAllBySessionIdAndApprovalStatus(Long id, ApprovalStatus approvalStatus) {
        String sql = "select ns_user.id, ns_user.user_id, ns_user.password, ns_user.name, ns_user.email, ns_user.created_at, ns_user.updated_at from ns_user join student on ns_user.id = student.user_id where student.session_id = ? and student.approval_status = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7))
        );
        return jdbcTemplate.query(sql, rowMapper, id, approvalStatus.toString());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
