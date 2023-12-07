package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Session session) {
        String sql = "insert into session (course_id, start_date, end_date, image_id, paid_type," +
                " max_student_number, applied_number, session_fee, session_status) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getCourseId(), session.getPeriod().getStartDate(),
                session.getPeriod().getEndDate(), session.getImage().getId(), session.getPaidType().toString(),
                session.getMaxStudentNumber(), session.getAppliedNumber(), session.getSessionFee(),
                session.getSessionStatus().toString());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, start_date, end_date, image_id, paid_type, max_student_number," +
                "applied_number, session_fee, session_status from session where id = ?";
        RowMapper<Session> rowMapper = ((rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                toLocalDateTime(rs.getTimestamp(3)),
                toLocalDateTime(rs.getTimestamp(4)),
                rs.getLong(5),
                rs.getString(6),
                rs.getInt(7),
                rs.getInt(8),
                rs.getLong(9),
                rs.getString(10)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
