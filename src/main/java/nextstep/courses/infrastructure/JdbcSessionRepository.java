package nextstep.courses.infrastructure;

import static nextstep.courses.infrastructure.RepositorySupporters.toLocalDateTime;

import nextstep.courses.domain.session.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionParticipant participant, SessionCondition condition, SessionTerm term, Long courseId) {
        String sql = "INSERT INTO session (course_id, type, status, maximum_student, start_at, end_at) "
                + "VALUES (?, ?, ?, ?, ? ,?)";

        return jdbcTemplate.update(sql, courseId, condition.getSessionType().name(), condition.getSessionStatus().name(),
                participant.getMaximumStudent(), term.getStartAt(), term.getEndAt());
    }

    @Override
    public Session findById(Long id) {
        String sql = "SELECT id, course_id, status, type, maximum_student, current_student, start_at, end_at, created_at, updated_at FROM Session WHERE id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                new SessionRequired(
                        new SessionParticipant(rs.getInt(5)),
                        new SessionCondition(
                                SessionStatus.valueOf(rs.getString(3)),
                                SessionType.valueOf(rs.getString(4))),
                        new SessionTerm(toLocalDateTime(rs.getTimestamp(7)), toLocalDateTime(rs.getTimestamp(8)))
                ),
                new SessionOptional(toLocalDateTime(rs.getTimestamp(9)), toLocalDateTime(rs.getTimestamp(10)))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
