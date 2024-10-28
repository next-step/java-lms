package nextstep.session.infrastructure;

import nextstep.DateDomain;
import nextstep.session.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public int save(Session session) {
        String sql = "insert into session (title, paymentType, image_id, subscribeStatus, subscribeMax, price, start_date, end_date, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                session.getTitle(),
                session.getPaymentType().name(),
                session.getImage().getId(),
                session.getSubscribeStatus().name(),
                session.getSubscribeMax(),
                session.getPrice(),
                session.getDateRange().getStartDate(),
                session.getDateRange().getEndDate(),
                session.getDateDomain().getCreatedAt(),
                session.getDateDomain().getUpdatedAt()
        );
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, title, paymentType, subscribeStatus, subscribeMax, price, start_date, end_date, created_at, updated_at from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(4),
                rs.getString(5),
                rs.getInt(6),
                rs.getInt(7),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)),
                toLocalDateTime(rs.getTimestamp(10)),
                toLocalDateTime(rs.getTimestamp(11)));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
