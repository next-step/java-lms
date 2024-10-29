package nextstep.session.infrastructure;

import nextstep.session.domain.Session;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SubscribeStatus;
import nextstep.session.domain.Subscriber;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
    public int save(Session session) {
        String sql = "insert into session (title, paymentType, subscribeStatus, subscribeMax, price, start_date, end_date, image_name, image_width, image_height, image_capacity, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                session.getTitle(),
                session.getPaymentType().name(),
                session.getSubscribeStatus().name(),
                session.getSubscribeMax(),
                session.getPrice(),
                session.getDateRange().getStartDate(),
                session.getDateRange().getEndDate(),
                session.getImage().getName(),
                session.getImage().getSize().getWidth().getWidth(),
                session.getImage().getSize().getHeight().getHeight(),
                session.getImage().getCapacity().getCapacity(),
                session.getDateDomain().getCreatedAt(),
                session.getDateDomain().getUpdatedAt()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, title, paymentType, subscribeStatus, subscribeMax, price, start_date, end_date, image_name, image_width, image_height, image_capacity, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getInt(6),
                toLocalDateTime(rs.getTimestamp(7)),
                toLocalDateTime(rs.getTimestamp(8)),
                rs.getString(9),
                rs.getInt(10),
                rs.getInt(11),
                rs.getInt(12),
                findSubscriberBySessionId(rs.getLong(1)),
                toLocalDateTime(rs.getTimestamp(13)),
                toLocalDateTime(rs.getTimestamp(14)));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int updateSubscribeStatus(Long sessionId, SubscribeStatus subscribeStatus) {
        String sql = "update  session set subscribeStatus = ?, updated_at = ? where id = ?";
        return jdbcTemplate.update(sql,
                subscribeStatus.name(),
                LocalDateTime.now(),
                sessionId
        );
    }

    private List<Subscriber> findSubscriberBySessionId(Long sessionId) {
        String sql = "select id, session_id, ns_user_id, created_at, updated_at from subscriber where session_id = ?";
        RowMapper<Subscriber> rowMapper = (rs, rowNum) -> new Subscriber(
                rs.getLong(1),
                findUserById(rs.getLong(3)),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)));

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private NsUser findUserById(Long userId) {
        String sql = "select id, user_id, password, name, email, created_at, updated_at from ns_user where id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));
        return jdbcTemplate.queryForObject(sql, rowMapper, userId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
