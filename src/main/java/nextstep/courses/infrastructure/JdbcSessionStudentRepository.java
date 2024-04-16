package nextstep.courses.infrastructure;

import nextstep.courses.domain.student.SessionStudent;
import nextstep.courses.infrastructure.engine.SessionStudentRepository;
import nextstep.courses.infrastructure.util.LocalDateTimeConverter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.time.LocalTime.now;

@Repository("sessionStudentRepository")
public class JdbcSessionStudentRepository implements SessionStudentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionStudent student) {
        String sql = "insert into session_student (session_id, ns_user_id, enrollment_status, created_at) " +
                "values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, student.getSessionId(), student.getNsUserId(), student.getEnrollmentStatus().get(), now());
    }

    @Override
    public List<SessionStudent> findAllBySessionId(Long sessionId) {
        String sql = "select id, session_id, ns_user_id, enrollment_status, created_at, updated_at " +
                "from session_student " +
                "where session_id = ?";

        return jdbcTemplate.query(sql, studentEntityMapper(), sessionId);
    }

    private RowMapper<SessionStudent> studentEntityMapper() {
        return (rs, rowNum) -> new SessionStudent(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getString(4),
                LocalDateTimeConverter.convert(rs.getTimestamp(5)),
                LocalDateTimeConverter.convert(rs.getTimestamp(6))
        );
    }

}
