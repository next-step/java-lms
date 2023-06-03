package nextstep.lms.infrastructure;

import nextstep.lms.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

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
                ", session_recruiting_state" +
                ", session_paid_type" +
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
                ", ?" +
                ")";

        return jdbcTemplate.update(sql
                , session.getSessionDate().getStartDate()
                , session.getSessionDate().getEndDate()
                , session.getSessionState()
                , session.getSessionRecruitingState()
                , session.getSessionPaidType()
                , session.getStudentCapacity().getRegisteredStudent()
                , session.getStudentCapacity().getStudentCapacity()
                , session.getImageId()
        );
    }

    @Override
    public void changeImage(Session session) {
        String sql = "uPDATe session SET image_id = ? WHERE id = ?";

        jdbcTemplate.update(sql, session.getImageId(), session.getId());
    }

    @Override
    public Optional<Session> findById(Long id) {
        String sql = "sELECt id" +
                ", start_date" +
                ", end_date" +
                ", session_state" +
                ", session_recruiting_state" +
                ", session_paid_type" +
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
                toSessionRecruitingState(rs.getString(5)),
                toSessionPaidType(rs.getString(6)),
                rs.getInt(7),
                rs.getInt(8),
                rs.getLong(9));

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
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

    private SessionRecruitingState toSessionRecruitingState(String sessionRecruitingState) {
        if (sessionRecruitingState == null) {
            return null;
        }
        return SessionRecruitingState.valueOf(sessionRecruitingState);
    }

    private SessionPaidType toSessionPaidType(String sessionType) {
        if (sessionType == null) {
            return null;
        }
        return SessionPaidType.valueOf(sessionType);
    }

    @Override
    public void changeSessionState(Session session) {
        String sql = "uPDATe session SET session_state = ? WHERE id = ?";

        jdbcTemplate.update(sql, session.getSessionState(), session.getId());
    }

    @Override
    public void updateRegisteredStudent(Session session) {
        String sql = "uPDATe session SET registered_student = ? WHERE id = ?";

        jdbcTemplate.update(sql, session.getStudentCapacity().getRegisteredStudent(), session.getId());
    }

    @Override
    public void changeSessionPaidType(Session session) {
        String sql = "uPDATe session SET session_paid_type = ? WHERE id = ?";

        jdbcTemplate.update(sql, session.getSessionPaidType(), session.getId());
    }

    @Override
    public void changeSessionRecruitingState(Session session) {
        String sql = "uPDATe session SET session_recruiting_state = ? WHERE id = ?";

        jdbcTemplate.update(sql, session.getSessionRecruitingState(), session.getId());
    }
}
