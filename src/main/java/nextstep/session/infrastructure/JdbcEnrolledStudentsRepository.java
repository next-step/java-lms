package nextstep.session.infrastructure;

import nextstep.session.domain.EnrolledStudents;
import nextstep.session.domain.EnrolledStudentsRepository;
import nextstep.session.domain.Session;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("enrolledStudentsRepository")
public class JdbcEnrolledStudentsRepository implements EnrolledStudentsRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcEnrolledStudentsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(EnrolledStudents enrolledStudents) {
        String sql = "insert into enrolled_students (session_id, student_id) values(?, ?)";
        int count = 0;
        for(Long studentId : enrolledStudents.getStudents()) {
            jdbcTemplate.update(sql, enrolledStudents.getSessionId(), studentId);
            count++;
        }

        return count;
    }

    @Override
    public EnrolledStudents findById(Long id) {
        String sql = "select student_id " +
                "from enrolled_students " +
                "where session_id = ?";
        RowMapper<Long> rowMapper = (rs, rowNum) -> rs.getLong(1);
        List<Long> students = jdbcTemplate.query(sql, rowMapper, id);

        return new EnrolledStudents(id, students);
    }
}
