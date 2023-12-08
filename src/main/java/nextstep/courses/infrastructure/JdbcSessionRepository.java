package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, status, start_date_time, end_date_time, free, max_attendance from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                SessionStatus.valueOf(rs.getString(2)),
                rs.getTimestamp(3).toLocalDateTime(),
                rs.getTimestamp(4).toLocalDateTime(),
                rs.getBoolean(5),
                rs.getInt(6));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public void save(Session session) {
        String sql = "insert into session (status, start_date_time, end_date_time, free, max_attendance) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, session.getSessionStatus().name(), session.getPeriod().getStartDateTime(),
                session.getPeriod().getEndDateTime(), session.getSessionType().isFree(), session.getSessionType().getMaxAttendance());
    }

    @Override
    public void enrollNsUser(Session session, NsUser nsUser) {

    }
}
