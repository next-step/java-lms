package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.SessionRegisterDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("sessionRegisterDetailsRepository")
public class JdbcSessionRegisterDetailsRepository implements SessionRegisterDetailsRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionRegisterDetailsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionRegisterDetails sessionRegisterDetails) {
        // 처음 강의 세부 사항을 저장할 때 사용하는 쿼리
        String sql = "INSERT INTO session_register_details (max_count_of_students, session_type, price, session_status) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, sessionRegisterDetails.getMaxCountOfStudents(), sessionRegisterDetails.getSessionType(), sessionRegisterDetails.getPrice(), sessionRegisterDetails.getSessionStatus());
    }
}
