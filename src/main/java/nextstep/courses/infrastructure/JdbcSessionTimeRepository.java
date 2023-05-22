package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionTime;
import nextstep.courses.domain.repository.SessionTimeRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;

@Repository("sessionTimeRepository")
public class JdbcSessionTimeRepository implements SessionTimeRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcSessionTimeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(SessionTime sessionTime) {
        String query = "INSERT INTO session_time (opening_date_time, closing_date_time) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setTimestamp(1, Timestamp.valueOf(sessionTime.getOpeningDateTime()));
            ps.setTimestamp(2, Timestamp.valueOf(sessionTime.getClosingDateTime()));
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public SessionTime findById(Long id) {
        String query = "SELECT * FROM session_time WHERE id = ?";
        return jdbcTemplate.queryForObject(query, sessionTimeRowMapper(), id);
    }

    private RowMapper<SessionTime> sessionTimeRowMapper() {
        return (rs, rowNum) -> new SessionTime(
                rs.getLong("id"),
                rs.getTimestamp("opening_date_time").toLocalDateTime(),
                rs.getTimestamp("closing_date_time").toLocalDateTime()
        );
    }

    @Override
    public int update(SessionTime sessionTime) {
        String query = "UPDATE session_time SET opening_date_time = ?, closing_date_time = ? WHERE id = ?";
        return jdbcTemplate.update(query,
                Timestamp.valueOf(sessionTime.getOpeningDateTime()),
                Timestamp.valueOf(sessionTime.getClosingDateTime()),
                sessionTime.getId());
    }

    @Override
    public int delete(Long id) {
        String query = "DELETE FROM session_time WHERE id = ?";
        return jdbcTemplate.update(query, id);
    }
}
