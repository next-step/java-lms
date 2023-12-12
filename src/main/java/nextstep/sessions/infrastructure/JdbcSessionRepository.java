package nextstep.sessions.infrastructure;

import nextstep.common.Period;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionCharge;
import nextstep.sessions.domain.SessionImage;
import nextstep.sessions.domain.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDate;

public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Session session) {
        String sql = "insert into session (name, start_at, end_at, image_size, image_width, image_height, image_type, price, limit_count, student_count, status)" +
                " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"ID"});
            ps.setString(1, session.getName());
            ps.setDate(2, Date.valueOf(session.getDate().getStartAt()));
            ps.setDate(3, Date.valueOf(session.getDate().getEndAt()));
            ps.setInt(4, session.getImage().getSize());
            ps.setDouble(5, session.getImage().getWidth());
            ps.setDouble(6, session.getImage().getHeight());
            ps.setString(7, session.getImage().getType().toString());
            ps.setDouble(8, session.getCharge().getPrice());
            ps.setInt(9, session.getCharge().getLimitCount());
            ps.setInt(10, session.getStudentCount());
            ps.setString(11, session.getStatus().toString());
            System.out.println(ps);
            return ps;
        }, keyHolder);
        System.out.println(keyHolder);
        return (long) keyHolder.getKey();
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, name, start_at, end_at, image_size, image_width, image_height, image_type, price, limit_count, student_count, status from session where id = ?";
        RowMapper<Session> rowMapper = ((rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                new Period(toLocalDate(rs.getTimestamp(3)), toLocalDate(rs.getTimestamp(4))),
                new SessionImage(rs.getInt(5), rs.getDouble(6), rs.getDouble(7), rs.getString(8)),
                new SessionCharge(rs.getInt(9) > 0 ? true : false, rs.getLong(9), rs.getInt(10)),
                rs.getInt(11),
                SessionStatus.valueOf(rs.getString(12))
        ));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

    @Override
    public int enroll(Session session) {
        String sql = "update session set student_count = ? where name = ?";
        return jdbcTemplate.update(sql, session.getStudentCount(), session.getName());
    }
}
