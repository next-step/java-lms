package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.session.Session;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;

@Repository("sessionRepository")
public class JdbcSessionRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Session session) {
        String sql = "insert into sessions (started_at, end_at, status, pay_type, price, capacity," +
                " course_id, image_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.startedAt(), session.endAt(), session.status(), session.payType(),
                session.price(), session.capacity(), session.courseId(), session.imageId());
    }

    public Session findById(Long id) {
        String sql = "select started_at, end_at, status, pay_type, price, capacity from sessions where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            try {
                return new Session(
                        new Period(toLocalDate(rs.getTimestamp(1)), toLocalDate(rs.getTimestamp(2))),
                        SessionStatus.valueOf(rs.getString(3)),
                        new SessionType(PayType.valueOf(rs.getString(4)),
                            rs.getLong(5),
                            rs.getInt(6)
                        ));
            } catch (PeriodException e) {
                throw new RuntimeException(e);
            }
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDate toLocalDate(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime().toLocalDate();
    }

}
