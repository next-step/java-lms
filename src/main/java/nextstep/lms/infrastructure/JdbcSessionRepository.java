package nextstep.lms.infrastructure;

import nextstep.lms.domain.Session;
import nextstep.lms.domain.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

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
}
