package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.CountOfStudent;
import nextstep.sessions.domain.Price;
import nextstep.sessions.domain.SessionRegisterDetails;
import nextstep.sessions.domain.SessionRegisterDetailsRepository;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("sessionRegisterDetailsRepository")
public class JdbcSessionRegisterDetailsRepository implements SessionRegisterDetailsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SessionRepository sessionRepository;

    public JdbcSessionRegisterDetailsRepository(JdbcTemplate jdbcTemplate, SessionRepository sessionRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public int save(SessionRegisterDetails sessionRegisterDetails) {
        String sql = "INSERT INTO session_register_details (id, session_id, current_count_of_students, max_count_of_students, session_type, price, session_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, sessionRegisterDetails.getId(), sessionRegisterDetails.getSessionId(), sessionRegisterDetails.getCurrentCountOfStudents(), sessionRegisterDetails.getMaxCountOfStudents(), sessionRegisterDetails.getSessionType(), sessionRegisterDetails.getPrice(), sessionRegisterDetails.getSessionStatus());
    }

    @Override
    public Optional<SessionRegisterDetails> findById(long sessionRegisterDetailsId) {
        String sql = "SELECT id, session_id, current_count_of_students, max_count_of_students, session_type, price, session_status FROM session_register_details WHERE id = ?";
        SessionRegisterDetails sessionRegisterDetails = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            long sessionId = rs.getLong("session_id");
            int currentCountOfStudents = rs.getInt("current_count_of_students");
            int maxCountOfStudents = rs.getInt("max_count_of_students");
            String sessionType = rs.getString("session_type");
            long price = rs.getLong("price");
            String sessionStatus = rs.getString("session_status");

            return new SessionRegisterDetails(
                    id,
                    sessionId,
                    new CountOfStudent(currentCountOfStudents, maxCountOfStudents, SessionType.valueOf(sessionType)),
                    new Price(price),
                    SessionStatus.valueOf(sessionStatus));
        }, sessionRegisterDetailsId);
        return Optional.ofNullable(sessionRegisterDetails);
    }
}
