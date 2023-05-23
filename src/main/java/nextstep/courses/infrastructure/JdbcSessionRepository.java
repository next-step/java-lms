package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
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
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO SESSION ");
        sql.append("(session_payment, session_cover_image, session_status, user_count, from_at, to_at, created_at) ");
        sql.append("VALUES(?,?,?,?,?,?,?) ");

        System.out.println("sql: " + sql.toString());
        return jdbcTemplate.update(sql.toString(),
                session.getSessionPayment().getStatus(),
                session.getSessionCoverImage().getPath(),
                session.getSessionStatus().getStatus(),
                session.getSessionUser().getUserCount(),
                Timestamp.valueOf(session.getSessionPeriod().getFromDate()),
                Timestamp.valueOf(session.getSessionPeriod().getToDate()),
                Timestamp.valueOf(session.getCreatedAt())
        );
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
