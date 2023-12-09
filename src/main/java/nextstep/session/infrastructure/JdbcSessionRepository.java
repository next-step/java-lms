package nextstep.session.infrastructure;

import nextstep.session.domain.FreeSession;
import nextstep.session.domain.PaidSession;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionImageRepository;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionStatus;
import nextstep.session.domain.SessionType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

import static nextstep.common.domain.utils.JdbcConvertUtils.toDate;
import static nextstep.common.domain.utils.JdbcConvertUtils.toLocalDate;
import static nextstep.common.domain.utils.JdbcConvertUtils.toLocalDateTime;
import static nextstep.common.domain.utils.JdbcConvertUtils.toTimestamp;

@Repository
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;
    private final SessionImageRepository sessionImageRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, SessionImageRepository sessionImageRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.sessionImageRepository = sessionImageRepository;
    }

    @Override
    public Long save(Session session) {
        long sessionId = saveSession(session);
        if (session instanceof FreeSession) {
            String freeSessionSQL = "insert into free_session (session_id) values(?)";
            jdbcTemplate.update(freeSessionSQL, sessionId);
        }
        if (session instanceof PaidSession) {
            String paidSessionSQL = "insert into paid_session (session_id, capacity, price) values(?, ? , ?)";
            jdbcTemplate.update(paidSessionSQL, sessionId, ((PaidSession) session).getCapacity(), ((PaidSession) session).getPrice());
        }
        sessionImageRepository.save(session);
        return sessionId;
    }

    private long saveSession(Session session) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sessionSQL = "insert into session (created_at, updated_at, creator_id, start_date, end_date, session_status, session_type) values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            sessionSQL,
                            Statement.RETURN_GENERATED_KEYS
                    );
                    ps.setTimestamp(1, toTimestamp(session.getCreatedAt()));
                    ps.setTimestamp(2, toTimestamp(session.getUpdatedAt()));
                    ps.setLong(3, session.getCreatorId());
                    ps.setDate(4, toDate(session.getSessionDate().getStartDate()));
                    ps.setDate(5, toDate(session.getSessionDate().getEndDate()));
                    ps.setString(6, session.getSessionStatus().toString());
                    ps.setString(7, session.getSessionType().toString());
                    return ps;
                },
                keyHolder
        );
        long sessionId = keyHolder.getKey().longValue();
        return sessionId;
    }

    @Override
    public Session findById(Long id) {
        if (getSessionType(id) == SessionType.FREE) {
            String sql = "select s.id, s.created_at, s.updated_at, s.creator_id, s.start_date, s.end_date, s.session_status, s.session_type " +
                    "from session s " +
                    "join free_session fs " +
                    "on s.id = fs.session_id " +
                    "where s.id = ?";
            RowMapper<FreeSession> rowMapper = (rs, rowNum) -> new FreeSession(
                    rs.getLong(1),
                    toLocalDateTime(rs.getTimestamp(2)),
                    toLocalDateTime(rs.getTimestamp(3)),
                    rs.getLong(4),
                    toLocalDate(rs.getDate(5)),
                    toLocalDate(rs.getDate(6)),
                    sessionImageRepository.findById(rs.getLong(1)),
                    SessionStatus.valueOf(rs.getString(7)),
                    SessionType.valueOf(rs.getString(8))
            );
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        }

        if (getSessionType(id) == SessionType.PAID) {
            String sql = "select s.id, s.created_at, s.updated_at, s.creator_id, s.start_date, s.end_date, s.session_status, s.session_type, ps.capacity, ps.price " +
                    "from session s " +
                    "join paid_session ps " +
                    "on s.id = ps.session_id " +
                    "where s.id = ?";
            RowMapper<PaidSession> rowMapper = (rs, rowNum) -> new PaidSession(
                    rs.getLong(1),
                    toLocalDateTime(rs.getTimestamp(2)),
                    toLocalDateTime(rs.getTimestamp(3)),
                    rs.getLong(4),
                    toLocalDate(rs.getDate(5)),
                    toLocalDate(rs.getDate(6)),
                    sessionImageRepository.findById(rs.getLong(1)),
                    SessionStatus.valueOf(rs.getString(7)),
                    SessionType.valueOf(rs.getString(8)),
                    rs.getInt(9),
                    rs.getLong(10)
            );
            return jdbcTemplate.queryForObject(sql, rowMapper, id);
        }
        return null;
    }

    private SessionType getSessionType(Long id) {
        String checkSessionTypeSQL = "select session_type from session where id = ?";
        RowMapper<SessionType> rowMapper = (rs, rowNum) -> SessionType.valueOf(
                rs.getString(1)
        );
        return jdbcTemplate.queryForObject(checkSessionTypeSQL, rowMapper, id);
    }
}
