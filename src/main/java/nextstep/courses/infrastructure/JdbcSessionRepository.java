package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
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
        String sql = "insert into session (" +
                                    "thumbnail_url, "  +
                                    "payment_type,"+
                                    "enrollments, " +
                                    "status," +
                                    "maximum_enrollments, " +
                                    "start_date, " +
                                    "end_date) values(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                session.getThumbnailUrl(),
                session.getPaymentType(),
                session.getEnrollments(),
                session.getStatus(),
                session.getMaximumEnrollments(),
                session.getStartDate(),
                session.getEndDate());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, enrollments, maximum_enrollments, thumbnail_url, payment_type, status, start_date, end_date " +
                "from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getInt(2),
                rs.getInt(3),
                rs.getString(4),
                PaymentTypeEnum.valueOf(rs.getString(5)),
                SessionStatusEnum.valueOf(rs.getString(6)),
                toLocalDateTime(rs.getTimestamp(7)),
                toLocalDateTime(rs.getTimestamp(8)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
