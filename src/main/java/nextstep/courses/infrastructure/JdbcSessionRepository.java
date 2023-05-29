package nextstep.courses.infrastructure;

import nextstep.courses.domain.PaymentTypeEnum;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatusEnum;
import nextstep.courses.util.LocalDateTimeUtil;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Session session) {
        String sql = "insert into session (" +
                                    "title, " +
                                    "thumbnail_url, "  +
                                    "payment_type,"+
                                    "enrollments, " +
                                    "status," +
                                    "maximum_enrollments, " +
                                    "start_date, " +
                                    "end_date) values(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                session.getTitle(),
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
        String sql = "select id, title, enrollments, maximum_enrollments, thumbnail_url, payment_type, status, start_date, end_date " +
                "from session where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getInt(4),
                rs.getString(5),
                PaymentTypeEnum.valueOf(rs.getString(6)),
                SessionStatusEnum.valueOf(rs.getString(7)),
                LocalDateTimeUtil.toLocalDateTime(rs.getTimestamp(8)),
                LocalDateTimeUtil.toLocalDateTime(rs.getTimestamp(9)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

}
