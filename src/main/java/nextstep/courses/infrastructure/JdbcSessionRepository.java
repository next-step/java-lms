package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionCostType;
import nextstep.courses.domain.SessionPeriod;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.registration.SessionRecruitmentStatus;
import nextstep.courses.domain.registration.SessionRegistration;
import nextstep.courses.domain.registration.SessionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
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
        String sql = "insert into session (status, max_user_count, started_at, ended_at, cover_image, cost_type, recruitment_status)" +
                " values(?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, session.getSessionStatus().name());
            ps.setInt(2, session.getMaxUserCount());
            ps.setTimestamp(3, Timestamp.valueOf(session.startedAt()));
            ps.setTimestamp(4, Timestamp.valueOf(session.endedAt()));
            ps.setString(5, session.getSessionCoverImage());
            ps.setString(6, session.getSessionCostType().name());
            ps.setString(7, session.getRecruitmentStatus().name());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Session findById(Long sessionId) {
        String sql = "select id, status, max_user_count, started_at, ended_at, cover_image, cost_type, recruitment_status " +
                "from session " +
                "where id = ? ";

        RowMapper<Session> rowMapper = ((rs, rowNum) -> new Session(
                rs.getLong(1),
                SessionStatus.valueOf(rs.getString(2)),
                new SessionRegistration(SessionRecruitmentStatus.valueOf(rs.getString(8)),
                        rs.getInt(3)),
                new SessionPeriod(toLocalDateTime(rs.getTimestamp(4)), toLocalDateTime(rs.getTimestamp(5))),
                rs.getString(6),
                SessionCostType.valueOf(rs.getString(7))
        ));

        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    @Override
    public int updateSessionStatus(Session session) {
        String sql = "update session set status = ? where id = ? ";
        return jdbcTemplate.update(sql, session.getSessionStatus().name(), session.getId());
    }


    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
