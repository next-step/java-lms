package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.enums.SessionStatus;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session save(Session session) {
        String sql = "insert into session(course_id, cover_image, is_free, session_status, ns_users_id, start_date, end_date,max_user_count) values(?, ?, ?, ?, ?, ?, ?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, session.getCourseId());
            ps.setString(2, session.getCoverImage());
            ps.setBoolean(3, session.isFree());
            ps.setString(4, session.getEnrollment().getSessionStatus().name());
            ps.setLong(5, session.getEnrollment().getNsUsers().getId());
            ps.setTimestamp(6, toTimeStamp(session.getPeriod().getStartDate()));
            ps.setTimestamp(7, toTimeStamp(session.getPeriod().getEndDate()));
            ps.setInt(8, session.getEnrollment().getMaxUserCount());
            return ps;
        }, keyHolder);


        Number key = keyHolder.getKey();
        long sessionId = key != null ? key.longValue() : -1;

        return new Session.Builder()
                .with(session)
                .withId(sessionId)
                .build();
    }

    @Override
    public Session findById(Long sessionId) {
        String sql = "select id, course_id, start_date, end_date, cover_image, is_free, session_status, ns_users_id, max_user_count from session where id = ?";

        RowMapper<Session> rowMapper = sessionRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    @Override
    public long saveNsUser(Session session, NsUser nsUser) {
        String sql = "insert into ns_users(user_id, session_id) values(?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, nsUser.getId());
            ps.setLong(2, session.getId());
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        return key != null ? key.longValue() : -1;
    }

    @Override
    public List<NsUser> findAllUserBySessionId(Long sessionId) {
        String sql = "select u.id, u.user_id, u.password, u.name, u.email, u.created_at, u.updated_at from ns_users nu " +
                "inner join ns_user u on (nu.user_id = u.id) " +
                "where nu.session_id = ?";

        RowMapper<NsUser> rowMapper = userRowMapper();
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private RowMapper<Session> sessionRowMapper() {
        return (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getLong(2),
                toLocalDateTime(rs.getTimestamp(3)),
                toLocalDateTime(rs.getTimestamp(4)),
                rs.getString(5),
                rs.getBoolean(6),
                SessionStatus.valueOf(rs.getString(7)),
                rs.getLong(8),
                rs.getInt(9)
        );
    }

    private RowMapper<NsUser> userRowMapper() {
        return (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7))
        );
    }

    private Timestamp toTimeStamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Timestamp.valueOf(localDateTime);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

}
