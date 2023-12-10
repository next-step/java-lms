package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.session.SessionUserEnrolment;
import nextstep.courses.domain.session.SessionUserEnrolmentRepository;
import nextstep.courses.type.SessionSubscriptionStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("SessionUserEnrolmentRepository")
public class JdbcSessionUserEnrolmentRepository implements SessionUserEnrolmentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionUserEnrolmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionUserEnrolment enrolment) {
        String sql = "insert into session_user_enrolment (ns_user_id, session_id, subscriptionstatus) values(?, ?, ?)";
        return jdbcTemplate.update(sql, enrolment.nsUserId(), enrolment.sessionId(), enrolment.subscriptionStatus().toString());
    }

    @Override
    public int update(SessionUserEnrolment enrolment) {
        String sql = "update session_user_enrolment set subscriptionstatus = ? where ns_user_id = ? and session_id = ?";
        return jdbcTemplate.update(sql, enrolment.subscriptionStatus().toString(), enrolment.nsUserId(), enrolment.sessionId());
    }

    @Override
    public SessionUserEnrolment findBySessionIdAndUserId(Long sessionId, Long userId) {
        String sql = "select id, ns_user_id, session_id, subscriptionstatus from session_user_enrolment where session_id = ? and ns_user_id = ?";
        RowMapper<SessionUserEnrolment> rowMapper = (rs, rowNum) -> {
            Long id = rs.getLong(1);
            Long nsUserId = rs.getLong(2);
            Long sessionId1 = rs.getLong(3);
            String subscriptionStatus = rs.getString(4);
            return new SessionUserEnrolment(id, nsUserId, sessionId1, SessionSubscriptionStatus.valueOf(subscriptionStatus));
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId, userId);
    }
}
