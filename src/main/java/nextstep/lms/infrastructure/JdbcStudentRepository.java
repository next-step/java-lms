package nextstep.lms.infrastructure;

import nextstep.lms.domain.RegisterType;
import nextstep.lms.domain.Student;
import nextstep.lms.domain.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
                ", register_type" +
                ") values(" +
                "?" +
                ", ?" +
                ", ?" +
                ");";

        return jdbcTemplate.update(sql
                , student.getNsUserId()
                , student.getSessionId()
                , student.getRegisterType()
        );
    }

    @Override
    public Student findByNsUserIdAndSessionId(Long nsUserId, Long sessionId) {
        String sql = "sELECt ns_user_id" +
                ", session_id" +
                ", register_type" +
                ", created_at" +
                ", updated_at " +
                "from student " +
                "where ns_user_id = ? AND session_id = ?";

        RowMapper<Student> rowMapper = ((rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2),
                toRegisterType(rs.getString(3)),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5))
        ));

        return jdbcTemplate.queryForObject(sql, rowMapper, nsUserId, sessionId);
    }

    private RegisterType toRegisterType(String registerType) {
        if (registerType == null) {
            return null;
        }
        return RegisterType.valueOf(registerType);
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

        jdbcTemplate.update(sql, student.getRegisterType(), student.getNsUserId(), student.getSessionId());
    }
}
