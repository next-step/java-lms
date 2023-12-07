package nextstep.courses.infrastructure;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.AttendeeRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcAttendeeRepository implements AttendeeRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcAttendeeRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Attendee> findById(Long attendeeId) {
        String sql = "select user_id, session_id" +
                " from enrollment" +
                " where id = ?";
        RowMapper<Attendee> rowMapper = (rs, rowNum) -> new Attendee(
                rs.getLong(1),
                rs.getLong(2)
        );
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, attendeeId));
    }

    @Override
    public void save(Attendee attendee) {
        String sql = "insert into enrollment (user_id, session_id)" +
                " values (?, ?)";
        jdbcTemplate.update(sql, attendee.getStudentId(), attendee.getSessionId());
    }
}
