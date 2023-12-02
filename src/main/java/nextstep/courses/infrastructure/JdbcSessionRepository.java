package nextstep.courses.infrastructure;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionDuration;
import nextstep.courses.domain.SessionEnrolment;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatusType;
import nextstep.courses.domain.SessionStudent;
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
        String sql = "INSERT INTO session (course_id, start_date, end_date, max_student_count, session_status_type, recruitment_status_type, amount, is_free) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

        return jdbcTemplate.update(
                sql,
                session.courseId(),
                session.startDate(),
                session.endDate(),
                session.totalStudentCount(),
                session.sessionStatus(),
                session.recruitmentStatus(),
                session.sessionAmount(),
                session.isFree());
    }

    @Override
    public Session findById(Long id) {
        String sql = "SELECT session.id, session.course_id, session.start_date, session.end_date, session.max_student_count, session.session_status_type, session.amount, session.is_free " +
                "FROM session WHERE session.id = ? ";

        RowMapper<Session> rowMapper = (resultSet, rowNumber) -> {
            long sessionId = resultSet.getLong(1);
            long courseId = resultSet.getLong(2);
            LocalDateTime startDate = toLocalDateTime(resultSet.getTimestamp(3));
            LocalDateTime endDate = toLocalDateTime(resultSet.getTimestamp(4));
            SessionDuration sessionDuration = new SessionDuration(startDate, endDate);
            SessionStudent sessionStudent = new SessionStudent(resultSet.getInt(5));
            SessionStatusType sessionStatusType = SessionStatusType.valueOf(resultSet.getString(6));
            Amount amount = new Amount(resultSet.getLong(7));
            boolean isFree = resultSet.getBoolean(8);

            SessionEnrolment sessionEnrolment = new SessionEnrolment(sessionStudent, sessionStatusType, amount, isFree);
            return new Session(sessionId, courseId, sessionDuration, sessionEnrolment);
        };

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int update(Session session) {
        String sql = "UPDATE session SET course_id  = ?, start_date = ?, end_date = ?, max_student_count = ?, session_status_type = ?, amount = ?, is_free = ? WHERE id = ? ";

        return jdbcTemplate.update(
                sql,
                session.courseId(),
                session.startDate(),
                session.endDate(),
                session.totalStudentCount(),
                session.sessionStatus(),
                session.sessionAmount(),
                session.isFree(),
                session.id());
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM session WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
