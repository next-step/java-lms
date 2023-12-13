package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.enums.SessionProcessStatus;
import nextstep.courses.domain.session.enums.SessionRecruitStatus;
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
        String sql = "insert into sessions (started_at, end_at, recruit_status, process_status, pay_type, price, capacity," +
                " course_id, image_id) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.startedAt(), session.endAt(), session.getRecruitStatus(), session.getProcessStatus(), session.payType(),
                session.price(), session.capacity(), session.courseId(), null);
    }

    public Session findById(Long id) {
        String sql = "select id, started_at, end_at, recruit_status, process_status, pay_type, price, capacity from sessions where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> {
            try {
                return new Session(
                        rs.getLong(1),
                        new Period(toLocalDate(rs.getTimestamp(2)), toLocalDate(rs.getTimestamp(3))),
                        SessionRecruitStatus.valueOf(rs.getString(4)),
                        SessionProcessStatus.valueOf(rs.getString(5)),
                        new SessionType(PayType.valueOf(rs.getString(6)),
                            rs.getLong(7),
                            rs.getInt(8)
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
