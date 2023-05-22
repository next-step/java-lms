package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long save(Session session) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "insert into session (bill_type, session_cover_image, session_status, session_recruit_status, max_user_count, started_at, ended_at, created_at) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, session.getSessionBillType().name());
            ps.setString(2, session.getSessionCoverImageUrl());
            ps.setString(3, session.getSessionStatus().name());
            ps.setString(4, session.getSessionRecruitStatus().name());
            ps.setInt(5, session.getMaxUserCount());
            ps.setTimestamp(6, Timestamp.valueOf(session.getSessionPeriod().getStartedAt()));
            ps.setTimestamp(7, Timestamp.valueOf(session.getSessionPeriod().getEndedAt()));
            ps.setTimestamp(8, Timestamp.valueOf(session.getCreatedAt()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, bill_type, session_cover_image, session_status, session_recruit_status, max_user_count, started_at, ended_at, " +
                "created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) ->
                new Session(rs.getLong(1),
                            SessionBillType.find(rs.getString(2)),
                            new SessionCoverImage(rs.getString(3)),
                            new SessionRegistration(SessionStatus.find(rs.getString(4)),
                                                    SessionRecruitStatus.find(rs.getString(5)),
                                                    rs.getInt(6)),
                            new SessionPeriod(toLocalDateTime(rs.getTimestamp(7)),
                                              toLocalDateTime(rs.getTimestamp(8))),
                            toLocalDateTime(rs.getTimestamp(9)),
                            toLocalDateTime(rs.getTimestamp(10))
                );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
