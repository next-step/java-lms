package nextstep.sessions.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.*;
import nextstep.sessions.domain.data.vo.*;
import nextstep.sessions.repository.SessionRepository;
import nextstep.users.domain.NsUser;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session findSessionBySessionId(int sessionId) {
        String sql = "select paid_type, fee, capacity, state, start_date, end_date from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
            new SessionInfo(
                new EnrollmentInfo(
                    new SessionType(PaidType.valueOf(rs.getString(1)), rs.getLong(2), rs.getInt(3)),
                    SessionState.valueOf(rs.getString(4))
                ),
                new OpenInfo(
                    new Duration(toLocalDateTime(rs.getTimestamp(5)), toLocalDateTime(rs.getTimestamp(6)))
                )
            )
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    @Override
    public CoverImage findCoverImageBySessionId(int sessionId) {
        String sql = "select session_id, type, file_size, width, height from cover_image where session_id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
            ImageType.valueOf(rs.getString(2)),
            new ImageSize(rs.getInt(3), rs.getInt(4), rs.getInt(5))
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    @Override
    public List<Registration> findAllRegistrations(int sessionId) {
        String sql = "select s.paid_type, s.fee, s.capacity, s.state, s.start_date, s.end_date, u.id, u.user_id, u.password, u.name, u.email from registration r join session s on r.session_id = s.id left join ns_user u on r.user_id = u.id where r.session_id = ?";
        RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
            new Session(
                new SessionInfo(
                    new EnrollmentInfo(
                        new SessionType(PaidType.valueOf(rs.getString(1)), rs.getLong(2), rs.getInt(3)),
                        SessionState.valueOf(rs.getString(4))
                    ),
                    new OpenInfo(
                        new Duration(toLocalDateTime(rs.getTimestamp(5)), toLocalDateTime(rs.getTimestamp(6)))
                    )
                )
            ),
            new NsUser(rs.getLong(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getNString(11)),
            new Payment()
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }


    @Override
    public void saveRegistration(int sessionId, Registration registration) {
        String sql = "insert into registration (session_id, user_id, payment_id) values(?, ?, ?)";
        jdbcTemplate.update(sql,
            sessionId, registration.userPaymentInfo().user().getId(), registration.userPaymentInfo().payment().getId()
        );
    }

    @Override
    public int saveSession(Session session) {
        String sql = "insert into session (paid_type, fee, capacity, state, start_date, end_date) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            session.sessionInfo().enrollmentInfo().sessionType().payInfo().paidType().name(),
            session.sessionInfo().enrollmentInfo().sessionType().payInfo().fee(),
            session.sessionInfo().enrollmentInfo().sessionType().capacity(),
            session.sessionInfo().enrollmentInfo().sessionState().name(),
            Timestamp.valueOf(session.sessionInfo().openInfo().duration().startDate()),
            Timestamp.valueOf(session.sessionInfo().openInfo().duration().endDate())
        );
    }

    @Override
    public int saveCoverImage(int sessionId, CoverImage coverImage) {
        String sql = "insert into cover_image (session_id, type, file_size, width, height) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            sessionId,
            coverImage.imageType().name(),
            coverImage.imageSize().fileSize(),
            coverImage.imageSize().pixelSize().width(),
            coverImage.imageSize().pixelSize().height()
        );
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
