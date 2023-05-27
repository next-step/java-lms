package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session (title, status, price, charge_type, start_at, end_at, max_number_of_attendees, cover_image) " +
                     "values(?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(
                sql,
                session.title(),
                session.status().toString(),
                session.price(),
                session.chargeType().toString(),
                session.startDate(),
                session.endDate(),
                session.maxNumberOfAttendees(),
                session.coverImage()
        );
    }

    @Override
    public Session findById(long id) {
        SessionInformation sessionInformation = new SessionInformation(
                findSessionCoverImageById(id),
                findSessionTitleById(id),
                findSessionPriceById(id),
                findSessionDateById(id)
        );
        SessionStatus foundSessionStatus = findSessionStatusById(id);
        SessionAttendees foundSessionAttendees = new SessionAttendees(
                findSessionMaxNumberOfAttendeesById(id)
        );

        return new Session(
                id,
                sessionInformation,
                foundSessionStatus,
                foundSessionAttendees
        );
    }

    private Set<NsUser> findSessionAttendeesBySessionId(long sessionId) {
        String sql = "select ns_user.id, ns_user.user_id, ns_user.password, ns_user.name, ns_user.email, ns_user.created_at, ns_user.updated_at " +
                       "from session_attendee inner join ns_user on session_attendee.user_id = ns_user.user_id " +
                      "where session_attendee.session_id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                localDateTimeOf(rs.getTimestamp(6)),
                localDateTimeOf(rs.getTimestamp(7))
        );

        return new HashSet<>(jdbcTemplate.query(sql, rowMapper, sessionId));
    }

    private LocalDateTime localDateTimeOf(Timestamp timestamp) {
        if(timestamp == null) {
            return null;
        }

        return timestamp.toLocalDateTime();
    }

    private int findSessionMaxNumberOfAttendeesById(long id) {
        String sql = "select max_number_of_attendees from session where id = ?";
        RowMapper<Integer> rowMapper = (rs, rowNum) -> rs.getInt(1);

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private SessionStatus findSessionStatusById(long id) {
        String sql = "select status from session where id = ?";
        RowMapper<SessionStatus> rowMapper = (rs, rowNum) -> SessionStatus.valueOf(rs.getString(1));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private SessionCoverImage findSessionCoverImageById(long id) {
        String sql = "select cover_image from session where id = ?";
        RowMapper<SessionCoverImage> rowMapper = (rs, rowNum) -> new SessionCoverImage(rs.getBytes(1));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private SessionPrice findSessionPriceById(long id) {
        String sql = "select price from session where id = ?";
        RowMapper<SessionPrice> rowMapper = (rs, rowNum) -> new SessionPrice(rs.getInt(1));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private SessionTitle findSessionTitleById(long id) {
        String sql = "select title from session where id = ?";
        RowMapper<SessionTitle> rowMapper = (rs, rowNum) -> SessionTitle.of(rs.getString(1));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private SessionDate findSessionDateById(long id) {
        String sql = "select start_at, end_at from session where id = ?";
        RowMapper<SessionDate> rowMapper = (rs, rowNum) -> new SessionDate(
                rs.getTimestamp(1).toLocalDateTime().toLocalDate(),
                rs.getTimestamp(2).toLocalDateTime().toLocalDate()
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
