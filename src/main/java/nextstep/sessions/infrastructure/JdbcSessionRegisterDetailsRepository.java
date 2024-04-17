package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.CountOfStudent;
import nextstep.sessions.domain.Price;
import nextstep.sessions.domain.SessionRegisterDetails;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionType;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("sessionRegisterDetailsRepository")
public class JdbcSessionRegisterDetailsRepository implements SessionRegisterDetailsRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    public JdbcSessionRegisterDetailsRepository(JdbcTemplate jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public int save(SessionRegisterDetails sessionRegisterDetails) {
        // 처음 강의 세부 사항을 저장할 때 사용하는 쿼리
        String sql = "INSERT INTO session_register_details (id, max_count_of_students, session_type, price, session_status, ns_user_id) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, sessionRegisterDetails.getId(), sessionRegisterDetails.getMaxCountOfStudents(), sessionRegisterDetails.getSessionType(), sessionRegisterDetails.getPrice(), sessionRegisterDetails.getSessionStatus(), sessionRegisterDetails.getListeners().get(0).getId());
    }

    @Override
    public Optional<SessionRegisterDetails> findById(long sessionRegisterDetailsId) {
        String sql = "SELECT id, current_count_of_students, max_count_of_students, session_type, price, session_status FROM session_register_details WHERE id = ?";
        SessionRegisterDetails sessionRegisterDetails = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            int currentCountOfStudents = rs.getInt("current_count_of_students");
            int maxCountOfStudents = rs.getInt("max_count_of_students");
            String sessionType = rs.getString("session_type");
            long price = rs.getLong("price");
            String sessionStatus = rs.getString("session_status");
            List<NsUser> listeners = userRepository.findNsUsersBySessionRegisterDetailsId(id);

            return new SessionRegisterDetails(id, new CountOfStudent(currentCountOfStudents, maxCountOfStudents, SessionType.valueOf(sessionType)), new Price(price), SessionStatus.valueOf(sessionStatus), listeners);
        }, sessionRegisterDetailsId);
        return Optional.ofNullable(sessionRegisterDetails);
    }
}
