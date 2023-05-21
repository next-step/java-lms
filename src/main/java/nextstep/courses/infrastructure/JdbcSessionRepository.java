package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUserBuilder;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Session session) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "insert into session (bill_type, session_status, session_cover_image, max_user_count, started_at, ended_at, created_at) " +
                "values(?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, session.getSessionBillType().name());
            ps.setString(2, session.getSessionStatus().name());
            ps.setString(3, session.getSessionCoverImageUrl());
            ps.setInt(4, session.getMaxUserCount());
            ps.setTimestamp(5, Timestamp.valueOf(session.getSessionPeriod().getStartedAt()));
            ps.setTimestamp(6, Timestamp.valueOf(session.getSessionPeriod().getEndedAt()));
            ps.setTimestamp(7, Timestamp.valueOf(session.getCreatedAt()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, bill_type, session_status, session_cover_image, max_user_count, started_at, ended_at, " +
                "created_at, updated_at from session where id = ?";
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
        if (CollectionUtils.isEmpty(session.getSessionJoins())) {
            return 0;
        }

        String sql = "insert into session_join (session_id, user_id, created_at) values (?,?,?)";

        int savedCount = 0;
        for (SessionJoin sessionJoin : session.getSessionJoins()) {
            savedCount += jdbcTemplate.update(sql, sessionJoin.getSession().getId(), sessionJoin.getNsUser().getId(), sessionJoin.getCreatedAt());
        }

        return savedCount;
    }

    @Override
    public List<SessionJoin> findAllSessionJoinBySessionId(Long sessionId) {
        //TODO: 추후에 proxy 객체 활용 일단은 일반 객체 직접 조회로
        String sql = "select id, session_id, user_id, created_at, updated_at from session_join where session_id = ?";
        RowMapper<SessionJoin> rowMapper = (rs, rowNum) ->
                new SessionJoin(rs.getLong(1),
                                findById(rs.getLong(2)),
                                NsUserBuilder.aNsUser().withId(rs.getLong(3)).build(),
                                toLocalDateTime(rs.getTimestamp(4)),
                                toLocalDateTime(rs.getTimestamp(5))
                );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
