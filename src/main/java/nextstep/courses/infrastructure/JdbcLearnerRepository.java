package nextstep.courses.infrastructure;

import nextstep.courses.infrastructure.dto.LearnerDto;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcLearnerRepository {

    private static final Integer EXIST = 1;

    private final JdbcOperations jdbcTemplate;

    public JdbcLearnerRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(LearnerDto learnerDto) {
        String sql = "insert into session_learner (session_id, user_id, is_accepted) values(?, ?, ?)";
        jdbcTemplate.update(sql, learnerDto.getSessionId(), learnerDto.getUserId(), learnerDto.isAccepted());
    }

    public boolean exists(LearnerDto learnerDto) {
        String sql = "select count(*) from session_learner where session_id = ? and user_id = ?";

        return EXIST.equals(jdbcTemplate.queryForObject(sql, Integer.class,
            learnerDto.getSessionId(), learnerDto.getUserId()
        ));
    }

    public void update(Long sessionId, Long userId, boolean isAccepted) {
        String sql = "update session_learner set is_accepted = ? where session_id = ? and user_id = ?";
        jdbcTemplate.update(sql, sessionId, userId, isAccepted);
    }
}
