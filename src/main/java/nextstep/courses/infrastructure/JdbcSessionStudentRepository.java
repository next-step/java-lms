package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.repository.SessionStudentRepository;
import nextstep.courses.domain.session.student.SelectionStatus;
import nextstep.courses.domain.session.student.SessionStudent;
import nextstep.courses.domain.session.student.SessionStudents;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("studentRepository")
public class JdbcSessionStudentRepository implements SessionStudentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(SessionStudent student) {
        String sql = "insert into student (enrolment_id, ns_user_id, selection_status) values(?, ?, ?)";
        jdbcTemplate.update(sql, student.getEnrolmentId(), student.getNsUserId(), student.getSelectionStatus().toString());
    }

    @Override
    public void update(SessionStudent student) {
        String sql = "update student set selection_status = ? where id = ?";
        jdbcTemplate.update(sql, student.getSelectionStatus().toString(), student.getId());
    }

    @Override
    public SessionStudents findAllBySession(Long sessionId) {
        String sql = "select * from student where enrolment_id = ?";

        RowMapper<SessionStudent> rowMapper = (rs, rowNum) -> new SessionStudent(
            rs.getLong(1),
            rs.getLong(2),
            rs.getLong(3),
            SelectionStatus.valueOf(rs.getString(4))
        );

        List<SessionStudent> students = jdbcTemplate.query(sql, rowMapper, sessionId);
        return new SessionStudents(students);
    }

    @Override
    public Optional<SessionStudent> findById(Long studentId) {
        String sql = "select * from student where id = ?";

        RowMapper<SessionStudent> rowMapper = (rs, rowNum) -> new SessionStudent(
            rs.getLong(1),
            rs.getLong(2),
            rs.getLong(3),
            SelectionStatus.valueOf(rs.getString(4))
        );

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, studentId));
    }
}
