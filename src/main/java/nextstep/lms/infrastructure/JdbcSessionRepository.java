package nextstep.lms.infrastructure;

import nextstep.lms.domain.Session;
import nextstep.lms.domain.SessionRepository;
import nextstep.lms.domain.SessionState;
import nextstep.lms.domain.SessionType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "iNSERt iNTo session(" +
                "start_date" +
                ", end_date" +
                ", session_state" +
                ", session_type" +
                ", registered_student" +
                ", student_capacity" +
                ", image_id" +
                ") values(" +
                "?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ", ?" +
                ")";

        return jdbcTemplate.update(sql
                , session.getStartDate()
                , session.getEndDate()
                , session.getSessionState()
                , session.getSessionType()
                , session.getRegisteredStudent()
                , session.getStudentCapacity()
                , session.getImageCover()
        );
    }

    @Override
    public void changeImage(Session session) {
        String sql = "uPDATe session SET image_id = ? WHERE id = ?";

        jdbcTemplate.update(sql, session.getImageCover(), session.getId());
    }

    @Override
    public Session findById(Long id) {
        String sql = "sELECt id" +
                ", start_date" +
                ", end_date" +
                ", session_state" +
                ", session_type" +
                ", registered_student" +
                ", student_capacity" +
                ", image_id " +
                "from session " +
                "where id = ?";

        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                toLocalDateTime(rs.getDate(2)),
                toLocalDateTime(rs.getDate(3)),
                toSessionState(rs.getString(4)),
                toSessionType(rs.getString(5)),
                rs.getInt(6),
                rs.getInt(7),
                rs.getLong(8));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDate toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toLocalDate();
    }

    private SessionState toSessionState(String sessionState) {
        if (sessionState == null) {
            return null;
        }
        return SessionState.valueOf(sessionState);
    }

    private SessionType toSessionType(String sessionType) {
        if (sessionType == null) {
            return null;
        }
        return SessionType.valueOf(sessionType);
    }

    @Override
    public void changeSessionState(Session session) {
        String sql = "uPDATe session SET session_state = ? WHERE id = ?";

        jdbcTemplate.update(sql, session.getSessionState(), session.getId());
    }

    @Override
    public void updateRegisteredStudent(Session session) {
        String sql = "uPDATe session SET registered_student = ? WHERE id = ?";

        jdbcTemplate.update(sql, session.getRegisteredStudent(), session.getId());
    }

    @Override
    public void changeSessionType(Session session) {
        String sql = "uPDATe session SET session_type = ? WHERE id = ?";

        jdbcTemplate.update(sql, session.getSessionType(), session.getId());
    }
}
