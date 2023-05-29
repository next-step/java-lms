package nextstep.lms.infrastructure;

import nextstep.lms.domain.Student;
import nextstep.lms.domain.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

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
}
