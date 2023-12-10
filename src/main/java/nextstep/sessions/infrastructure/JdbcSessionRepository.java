package nextstep.sessions.infrastructure;

import nextstep.common.Period;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionCharge;
import nextstep.sessions.domain.SessionImage;
import nextstep.sessions.domain.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDate;

public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (name, start_at, end_at, image_size, image_width, image_height, image_type, price, limit_count, student_count, status)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getName(), session.getDate().getStartAt(), session.getDate().getEndAt(), session.getImage().getSize(), session.getImage().getWidth(), session.getImage().getHeight(), session.getImage().getType().toString(), session.getCharge().getPrice(), session.getCharge().getLimitCount(), session.getStudentCount(), session.getStatus().toString());
    }

    @Override
    public Session findByName(String name) {
        String sql = "select id, name, start_at, end_at, image_size, image_width, image_height, image_type, price, limit_count, student_count, status from session where name = ?";
        RowMapper<Session> rowMapper = ((rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                new Period(toLocalDate(rs.getTimestamp(3)), toLocalDate(rs.getTimestamp(4))),
                new SessionImage(rs.getInt(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8)),
                new SessionCharge(rs.getInt(9) > 0 ? true : false, rs.getLong(9), rs.getInt(10)),
                rs.getInt(11),
                SessionStatus.valueOf(rs.getString(12))
        ));
        return jdbcTemplate.queryForObject(sql, rowMapper, name);
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

    @Override
    public int update(Session session) {
        String sql = "update session set student_count = ? where name = ?";
        return jdbcTemplate.update(sql, session.getStudentCount(), session.getName());
    }
}
