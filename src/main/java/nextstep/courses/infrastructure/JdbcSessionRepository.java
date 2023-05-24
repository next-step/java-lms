package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Session session) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO SESSION ");
        sql.append("(session_payment, session_cover_image, session_status, user_count, started_at, ended_at, created_at) ");
        sql.append("VALUES(?,?,?,?,?,?,?) ");

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, session.getSessionPayment().getStatus());
            ps.setString(2, session.getSessionCoverImage().getPath());
            ps.setString(3, session.getSessionStatus().getStatus());
            ps.setInt(4, session.getSessionUser().getUserCount());
            ps.setTimestamp(5, Timestamp.valueOf(session.getSessionPeriod().getFromDate()));
            ps.setTimestamp(6, Timestamp.valueOf(session.getSessionPeriod().getToDate()));
            ps.setTimestamp(7, Timestamp.valueOf(session.getCreatedAt()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Session findById(Long id) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM SESSION ");
        sql.append("WHERE id = ? ");
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(6)), toLocalDateTime(rs.getTimestamp(7))),
                new SessionCoverImage(rs.getString(3)),
                SessionStatus.find(rs.getString(4)),
                SessionPayment.find(rs.getString(2)),
                new SessionUser(rs.getInt(5)));
        return jdbcTemplate.queryForObject(sql.toString(), rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
