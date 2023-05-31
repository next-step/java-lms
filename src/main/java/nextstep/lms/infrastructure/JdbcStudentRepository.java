package nextstep.lms.infrastructure;

import nextstep.lms.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "iNSERt iNTo student(" +
                "ns_user_id" +
                ", session_id" +
                ", selected_type" +
                ", approved_type" +
                ", register_type" +
                ") values(" +
                "?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ");";

        return jdbcTemplate.update(sql
                , student.getNsUserId()
                , student.getSessionId()
                , student.getStudentSelectedType()
                , student.getStudentApprovedType()
                , student.getStudentRegisterType()
        );
    }

    @Override
    public Optional<Student> findByNsUserIdAndSessionId(Long nsUserId, Long sessionId) {
        String sql = "sELECt ns_user_id" +
                ", session_id" +
                ", selected_type" +
                ", approved_type" +
                ", register_type" +
                ", created_at" +
                ", updated_at " +
                "from student " +
                "where ns_user_id = ? AND session_id = ?";

        RowMapper<Student> rowMapper = ((rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2),
                toSelectedType(rs.getString(3)),
                toApprovedType(rs.getString(4)),
                toRegisterType(rs.getString(5)),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7))
        ));

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, nsUserId, sessionId));
    }

    private StudentSelectedType toSelectedType(String selectedType) {
        if (selectedType == null) {
            return null;
        }
        return StudentSelectedType.valueOf(selectedType);
    }

    private StudentApprovedType toApprovedType(String approvedType) {
        if (approvedType == null) {
            return null;
        }
        return StudentApprovedType.valueOf(approvedType);
    }

    private StudentRegisterType toRegisterType(String registerType) {
        if (registerType == null) {
            return null;
        }
        return StudentRegisterType.valueOf(registerType);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    @Override
    public void sessionCancel(Student student) {
        String sql = "uPDATe student SET register_type = ? WHERE ns_user_id = ? AND session_id = ?";

        jdbcTemplate.update(sql, student.getStudentRegisterType(), student.getNsUserId(), student.getSessionId());
    }

    @Override
    public void changeStudentSelectedType(Student student) {
        String sql = "uPDATe student SET selected_type = ? WHERE ns_user_id = ? AND session_id = ?";

        jdbcTemplate.update(sql, student.getStudentSelectedType(), student.getNsUserId(), student.getSessionId());
    }

    @Override
    public void changeStudentApprovedType(Student student) {
        String sql = "uPDATe student SET approved_type = ? WHERE ns_user_id = ? AND session_id = ?";

        jdbcTemplate.update(sql, student.getStudentApprovedType(), student.getNsUserId(), student.getSessionId());
    }
}
