package nextstep.session.infrastructure;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
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
        String sql = "insert into session (max_number_of_student, progress_status, recruit_status) values(?, ?, ?)";
        return jdbcTemplate.update(sql,
                session.getMaxNumberOfStudent(),
                session.getProgressStatus().getStatus(),
                session.getRecruitmentStatus().getStatus());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, start_date, end_date, image, progress_status, max_number_of_student, is_free, recruit_status from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                toLocalDateTime(rs.getTimestamp(2)),
                toLocalDateTime(rs.getTimestamp(3)),
                rs.getString(4),
                rs.getString(5),
                rs.getLong(6),
                rs.getBoolean(7),
                rs.getString(8)
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
