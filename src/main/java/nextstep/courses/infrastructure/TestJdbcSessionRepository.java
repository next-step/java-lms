package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("testSessionRepository")
public class TestJdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public TestJdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into session (bill_type, session_status, session_cover_image, max_user_count, started_at, ended_at, created_at) " +
                "values(?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, session.getSessionBillType().name());
            ps.setString(2, session.getSessionStatus().name());
            ps.setString(3, session.getSessionCoverImageUrl());
            ps.setInt(4, session.getMaxUserCount());
            ps.setTimestamp(5, Timestamp.valueOf(session.getSessionPeriod().getStartedAt()));
            ps.setTimestamp(6, Timestamp.valueOf(session.getSessionPeriod().getEndedAt()));
            ps.setTimestamp(7, Timestamp.valueOf(session.getCreatedAt()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    private static void saveSessionJoin(JdbcOperations jdbcTemplate, List<SessionJoin> sessionJoins, long sessionId) {
        for (SessionJoin sessionJoin : sessionJoins) {
            String sql = "insert into session_join (session_id, user_id) values (?,?)";
            jdbcTemplate.update(sql, sessionId, sessionJoin.getNsUser());
        }
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, bill_type, session_status, session_cover_image, max_user_count, started_at, ended_at, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) ->
                new Session(rs.getLong(1),
                            SessionBillType.find(rs.getString(2)),
                            SessionStatus.find(rs.getString(3)),
                            new SessionCoverImage(rs.getString(4)),
                            rs.getInt(5),
                            new SessionPeriod(toLocalDateTime(rs.getTimestamp(6)), toLocalDateTime(rs.getTimestamp(7))),
                            toLocalDateTime(rs.getTimestamp(8)),
                            toLocalDateTime(rs.getTimestamp(9))
                );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int saveSessionJoin(Session session) {
        return 0;
    }

    @Override
    public List<SessionJoin> findAllSessionJoinBySessionId(Long sessionId) {
        return null;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
