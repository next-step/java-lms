package nextstep.session.infrastructure;

import nextstep.session.domain.Session;
import nextstep.session.domain.Subscriber;
import nextstep.session.domain.SubscriberRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("subscriberRepository")
public class JdbcSubscriberRepository implements SubscriberRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSubscriberRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Subscriber subscriber) {
        String sql = "insert into subscriber (session_id, ns_user_id, created_at, updated_at) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                subscriber.getSession().getId(),
                subscriber.getNsUser().getId(),
                subscriber.getDateDomain().getCreatedAt(),
                subscriber.getDateDomain().getUpdatedAt()
        );
    }

    @Override
    public Subscriber findById(Long id) {
        String sql = "select id, session_id, ns_user_id, created_at, updated_at from subscriber where id = ?";
        RowMapper<Subscriber> rowMapper = (rs, rowNum) -> new Subscriber(
                rs.getLong(1),
                findSessionById(rs.getLong(2)),
                findUserById(rs.getLong(3)),
                toLocalDateTime(rs.getTimestamp(4)),
                toLocalDateTime(rs.getTimestamp(5)));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private Session findSessionById(Long sessionId) {
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
                toLocalDateTime(rs.getTimestamp(13)),
                toLocalDateTime(rs.getTimestamp(14)));

        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
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
