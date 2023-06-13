package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUserBuilder;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository("sessionJoinRepository")
public class JdbcSessionJoinRepository implements SessionJoinRepository {
    private final JdbcOperations jdbcTemplate;
    private final SessionRepository sessionRepository;

    public JdbcSessionJoinRepository(JdbcTemplate jdbcTemplate, SessionRepository sessionRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public int save(Session session) {
        if (CollectionUtils.isEmpty(session.getSessionJoins())) {
            return 0;
        }

        String sql = "insert into session_join (session_id, user_id, session_join_status, created_at) values (?,?,?,?)";

        int savedCount = 0;
        for (SessionJoin sessionJoin : session.getSessionJoins()) {
            savedCount += jdbcTemplate.update(sql, sessionJoin.getSession().getId(), sessionJoin.getNsUser().getId(),
                    sessionJoin.getSessionJoinStatus().name(), sessionJoin.getCreatedAt());
        }

        return savedCount;
    }

    @Override
    public List<SessionJoin> findAllBySessionId(Long sessionId) {
        String sql = "select id, session_id, user_id, session_join_status, created_at, updated_at from session_join where session_id = ?";
        RowMapper<SessionJoin> rowMapper = (rs, rowNum) ->
                new SessionJoin(rs.getLong(1),
                        sessionRepository.findById(rs.getLong(2)),
                        NsUserBuilder.init().id(rs.getLong(3)).build(),
                        SessionJoinStatus.find(rs.getString(4)),
                        toLocalDateTime(rs.getTimestamp(5)),
                        toLocalDateTime(rs.getTimestamp(6))
                );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public SessionJoin findBySessionIdAndUserId(Long sessionId, Long userId) {
        String sql = "select id, session_id, user_id, session_join_status, created_at, updated_at from session_join where session_id = ? and user_id = ?";
        RowMapper<SessionJoin> rowMapper = (rs, rowNum) ->
                new SessionJoin(rs.getLong(1),
                        sessionRepository.findById(rs.getLong(2)),
                        NsUserBuilder.init().id(rs.getLong(3)).build(),
                        SessionJoinStatus.find(rs.getString(4)),
                        toLocalDateTime(rs.getTimestamp(5)),
                        toLocalDateTime(rs.getTimestamp(6))
                );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId, userId);
    }

    @Override
    public void updateSessionJoinStatus(SessionJoin sessionJoin) {
        String sql = "update session_join set session_join_status = ?, updated_at = ? where id = ?";

        jdbcTemplate.update(sql, sessionJoin.getSessionJoinStatus().name(), sessionJoin.getUpdatedAt(), sessionJoin.getId());
    }


    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
