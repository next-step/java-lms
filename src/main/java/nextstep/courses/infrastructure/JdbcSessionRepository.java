package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
        sql.append("(session_payment, session_cover_image, session_status, user_count, started_at, ended_at, created_at, updated_at) ");
        sql.append("VALUES(?,?,?,?,?,?,?,?) ");

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, session.getSessionPayment().name());
            ps.setString(2, session.getSessionCoverImage().getPath());
            ps.setString(3, session.getSessionStatus().name());
            ps.setInt(4, session.getSessionUsers().getUserCount());
            ps.setTimestamp(5, Timestamp.valueOf(session.getSessionPeriod().getFromDate()));
            ps.setTimestamp(6, Timestamp.valueOf(session.getSessionPeriod().getToDate()));
            ps.setTimestamp(7, Timestamp.valueOf(session.getCreatedAt()));
            ps.setTimestamp(8, Timestamp.valueOf(session.getUpdatedAt()));
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
                rs.getLong(1),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(7)), toLocalDateTime(rs.getTimestamp(8))),
                new SessionCoverImage(rs.getString(3)),
                SessionStatus.find(rs.getString(4)),
                SessionEnrollmentStatus.find(rs.getString(5)),
                SessionPayment.find(rs.getString(2)),
                new SessionUsers(rs.getInt(6)),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10))
        );
        return jdbcTemplate.queryForObject(sql.toString(), rowMapper, id);
    }

    @Override
    public void saveSessionUser(Session session) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO SESSION_NS_USER ");
        sql.append("(session_id, approval_status, user_id, created_at, updated_at) ");
        sql.append("values (?,?,?,?,?) ");

        for (SessionUser sessionUser : session.getSessionUsers().getSessionUsers()) {
            jdbcTemplate.update(sql.toString(), sessionUser.getSession().getId(), sessionUser.getApprovalStatus().name(), sessionUser.getNsUser().getId(), sessionUser.getCreatedAt(), sessionUser.getUpdatedAt());
        }
    }

    @Override
    public List<SessionUser> findAllBySessionId(Long sessionId) {
        JdbcUserRepository jdbcUserRepository = new JdbcUserRepository(jdbcTemplate);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM SESSION_NS_USER ");
        sql.append("WHERE session_id = ? ");

        RowMapper<SessionUser> rowMapper = (rs, rowNum) -> new SessionUser(
                rs.getLong(1),
                findById(rs.getLong(2)),
                ApprovalStatus.find(rs.getString(3)),
                jdbcUserRepository.findById(rs.getInt(4)).orElse(new NsUser()),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6))
        );

        return jdbcTemplate.query(sql.toString(), rowMapper, sessionId);
    }

    @Override
    public void updateSessionApprovalStatus(SessionUser sessionUser) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE SESSION_NS_USER ");
        sql.append("SET approval_status = ?, updated_at = ? ");
        sql.append("WHERE id = ? ");

        jdbcTemplate.update(sql.toString(), sessionUser.getApprovalStatus().name(), sessionUser.getUpdatedAt(), sessionUser.getId());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
