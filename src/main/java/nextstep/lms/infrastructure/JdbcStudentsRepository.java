package nextstep.lms.infrastructure;

import nextstep.lms.domain.Students;
import nextstep.lms.repository.StudentsRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository("studentsRepository")
public class JdbcStudentsRepository implements StudentsRepository {
    private JdbcOperations jdbcTemplate;
    public JdbcStudentsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Long userId, Long sessionId) {
        String sql = "insert into students (user_id, session_id, created_at) values(?,?,?)";

        return jdbcTemplate.update(sql,
                userId,
                sessionId,
                LocalDateTime.now());
    }

    @Override
    public Students findBySession(Long sessionId) {
        String sql = "select user_id from students where session_id = ?";
        RowMapper<Long> rowMapper = (rs, rowNum) -> rs.getLong(1);
        List<Long> userIds = jdbcTemplate.query(sql, rowMapper, sessionId);
        return new Students(userIds);
    }
}
